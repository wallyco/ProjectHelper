package BotTask.Skilling;

import BotLocations.Skilling;
import BotMain.Main;
import BotTask.UTIL.Walk;
import Task.Task;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.widget.Widget;
import org.dreambot.api.methods.widget.Widgets;
import org.dreambot.api.methods.widget.helpers.ItemProcessing;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.pushingpixels.substance.internal.utils.WidgetUtilities;

import java.util.ArrayList;

public class RangeMaster implements Task {
    private GameObject cookingSpot;
    private String spotName;
    private ArrayList<String> depositItems = new ArrayList<>();
    private String[] equipment;
    private Skilling botLocation;
    private Area area;
    private String cookingInteract;
    private boolean firstrun = true;

    public RangeMaster(){}

    public RangeMaster(Skilling info){
        this.botLocation = info;
        this.area = info.getArea();
        this.spotName = info.getName();
        this.cookingInteract = info.getInteract();
        this.equipment = info.getEquipment();
        for(String s: info.getDepositItems()){
            depositItems.add(s);
        }
    }

    @Override
    public boolean execute() {
        if(firstrun) {
            levelManager.resetActionCount(200);
            equipmentManager.add(botLocation.getEquipment());
            firstrun = false;
        }else {
            cook();
        }

        return levelManager.continueLevelingCooking(botLocation.getDepositItems());
    }

    private boolean cook(){
        if(shouldCook()){
            if(setRange(cookingSpot)) {
                cookingSpot.interact(cookingInteract);
                MethodProvider.sleepUntil(() -> ItemProcessing.isOpen(), 2000);
                if(ItemProcessing.isOpen()){
                    //TODO Abstraction possibly
                    Widgets.getWidget(270).getChild(14).getChild(38).interact();
                }
                MethodProvider.sleepWhile(() -> !player.isAnimating(), 1200);
            }

            if(ItemProcessing.isOpen()){
                Widgets.getWidget(270).getChild(14).getChild(38).interact();
                MethodProvider.sleepWhile(() -> !player.isAnimating(), 1200);
            }
        }
        return true;
    }

    private boolean setRange(GameObject range){
        range = null;
        range = GameObjects.closest(r -> r != null && r.getName().equals(spotName) && r.canReach() && r.distance() < 10);
        if(range == null) {
            Main.ai.getTaskManager().insertAtHeadCopy(new Walk(area));
            return false;
        }
        cookingSpot = range;
        return true;
    }

    private boolean shouldCook(){
        if(Dialogues.inDialogue()){
            Dialogues.continueDialogue();
        }

        if(player.isAnimating()){
            MethodProvider.sleep(100);
            if(player.isAnimating()){
                return false;
            }
        }

        if(!Inventory.contains(equipment)){
            return false;
        }

        return true;

    }

    @Override
    public double fatigueRate() {
        return 0;
    }
}
