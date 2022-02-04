package BotTask.JPXBank;

import BotMain.Main;
import Task.Task;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.container.impl.bank.Bank;


import java.util.ArrayList;

public class SkillingDeposit implements Task {
    private ArrayList<String> listOfItemsToDeposit = new ArrayList<>();

    private Tile bankTile = Bank.getClosestBankLocation().getCenter();
    private Tile returnTile = Players.localPlayer().getTile();
    private int bankRadius = 8;
    private int returnRadius = 5;

    public SkillingDeposit() {}

    public SkillingDeposit(ArrayList<String> list) {
        for(String s : list) {
            this.listOfItemsToDeposit.add(s);
        }
    }

    public SkillingDeposit(String[] name) {
        for(String e: name) {
            MethodProvider.log("adding" + e + "SkillingDeposit");
            this.listOfItemsToDeposit.add(e);
        }
    }

    @Override
    public boolean execute() {
        if(!Inventory.containsAll(listOfItemsToDeposit) &&
            !Inventory.contains(listOfItemsToDeposit.get(0))) {
            Main.ai.getTaskManager().insertBehindHead(new BotTask.UTIL.Walk(returnTile.getArea(returnRadius)));
            return false;
        }

        if(bankTile.getArea(15).contains(player)){
            MethodProvider.sleepUntil(() -> Bank.open(), 500);
        }else{
            Main.ai.getTaskManager().insertAtHeadCopy(new BotTask.UTIL.Walk(bankTile.getArea(bankRadius)));
            return true;
        }

        if(Bank.isOpen()){
            listOfItemsToDeposit.forEach(e -> {
                Bank.depositAll(e);
            });
        }
        return true;
    }

    @Override
    public double fatigueRate() {
        return 1.0;
    }
}
