package BotTask;

import java.util.ArrayList;
import java.util.Objects;

import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.script.listener.ChatListener;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.interactive.Player;
import org.dreambot.api.wrappers.widgets.message.Message;

import BotMain.Main;
import Task.Task;

public class Deforester implements Task, ChatListener{
//TODO Add consume
	private GameObject tree;
	private String treeName;
	private Player player = Players.localPlayer();
	private ArrayList<String> depositItems = new ArrayList<>();
	private Area area;
	public Deforester() {}
	private boolean firstrun = true;
	
	public Deforester(String treeName, String depositItem) {
		this.treeName = treeName;
		depositItems.add(depositItem);
	}
	
	public Deforester(BotLocations.WoodCutting info) {
		this.treeName = info.getName();
		for(String s: info.getDepositItems()) {
			depositItems.add(s);
		}
		
		this.area = info.getArea();
		
	}
	@Override
	public boolean execute() {
		if(firstrun) {
			levelManager.resetActionCount(136);
			firstrun = false;
		}
		if(accept()) {
			woodcut();
		}
		return levelManager.continueLevelingWoodcutting();
	}

	@Override
	public double fatigueRate() {
		return 1.0;
	}
	
	private boolean accept() {
		if(Dialogues.canContinue())
			Dialogues.continueDialogue();
		
		
		if(tree == null) {
			return true;
		}
		
		if(player.isAnimating() 
				|| player.isMoving()) {
			MethodProvider.sleep(100);
			if(player.isAnimating()
					|| player.isMoving())
				return false;
		}
		
		if(Inventory.isFull()) {
			Main.ai.getTaskManager().insertAtHeadCopy(new Bank(depositItems));
			return false;
		}
		
		return true;
	}
	
	private boolean woodcut() {
		setTree(tree);
		if(tree != null) {
			MethodProvider.sleepUntil(() -> tree.interact("Chop down"), 2500);

			MethodProvider.sleepUntil(() -> tree.getSurroundingArea(2).contains(player), 500);
			MethodProvider.sleep(50);
			fatigueManager.consumeEnergy(fatigueRate());
			if(player.isStandingStill()) {
				Main.ai.getTaskManager().insertAtHeadCopy(new Walk(area));
			}
			return true;
		}
		return false;
	}

	public GameObject getTree() {
		return tree;
	}

	public void setTree(GameObject tree) {
		tree = GameObjects.closest(t -> t != null 
				&& t.getName().equals(treeName) 
				&& player.canReach());
		if(tree != null) {
			this.tree = tree;
		}else {
			Main.ai.getTaskManager().insertAtHeadCopy(new Walk(area));
		}
	}

	@Override
	public String toString() {
		return "Deforester [treeName=" + treeName + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(treeName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Deforester other = (Deforester) obj;
		return Objects.equals(treeName, other.treeName);
	}
	
	@Override
    public void onMessage(Message message) {
        if (message.getMessage().contains("You get some logs")) {
        	levelManager.increaseActionCount();
        }
    }
	
}
