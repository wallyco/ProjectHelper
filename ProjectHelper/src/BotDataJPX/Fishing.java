package BotDataJPX;

import java.util.ArrayList;

import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.wrappers.interactive.NPC;

import BotAI.FatigueManager;
import BotMain.Main;
import Task.Task;

public class Fishing implements Task{
	private NPC fishingSpot;
	private String fishinSpotName;
	private String fishingMethod;
	private ArrayList<String> depositItems = new ArrayList<>();
	private Area botArea;
	public Fishing() { }
	
	public Fishing(String fishinSpotName, String fishingMethod, String[] depositItems, Area area) {
		this.fishinSpotName = fishinSpotName;
		this.fishingMethod = fishingMethod;
		this.botArea = area;
		for(String s : depositItems) {
			this.depositItems.add(s);
		}
	}
	
	@Override
	public boolean execute() {
		if(shouldFish()) {
			fish();
		}
		return true;
	}

	@Override
	public double fatigueRate() {
		// TODO Auto-generated method stub
		return 2.0;
	}
	
	private boolean fish() {
		getFishingSpot();
		if(fishingSpot.interact(fishingMethod))
			FatigueManager.getInstance().consumeEnergy(fatigueRate());
		return true;
	}
	
	
	private boolean shouldFish() {
		if(Dialogues.canContinue())
			Dialogues.continueDialogue();
		
		if(Inventory.isFull()) {
			Main.ai.getTaskManager().insertAtHeadCopy(new Bank(depositItems));
			return false;
		}
		
		if(fishingSpot == null) {
			return true;
		}
		
		if(player.isAnimating() 
				|| player.isMoving()) {
			MethodProvider.sleep(100);
			if(player.isAnimating()
					|| player.isMoving())
				return false;
		}

		return true;
		
	}
	
	private void getFishingSpot() {
		fishingSpot = NPCs.closest(fishinSpotName);
		if(fishingSpot == null) {
			Main.ai.getTaskManager().insertAtHeadCopy(new Walk(botArea));
		}
	}
	

}
