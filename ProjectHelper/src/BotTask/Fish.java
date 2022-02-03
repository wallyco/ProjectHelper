package BotTask;

import java.util.ArrayList;

import BotAI.EquipmentManager;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.script.listener.ChatListener;
import org.dreambot.api.wrappers.interactive.NPC;

import BotAI.FatigueManager;
import BotMain.Main;
import Task.Task;
import org.dreambot.api.wrappers.widgets.message.Message;
//TODO Reconfigure getFishingSpot to no throw error

public class Fish implements Task, ChatListener {
	private NPC fishingSpot;
	private String fishinSpotName;
	private String fishingMethod;
	private ArrayList<String> depositItems = new ArrayList<>();

	private ArrayList<String> fishingEquipment = new ArrayList<>();
	private Area botArea;
	private boolean firstrun = true;

	public Fish() { }

	public Fish(BotLocations.Fishing info) {
		this.fishingMethod = info.getInteract();


		for(String s : info.getDepositItems()) {
			this.depositItems.add(s);
		}
		
		for(String s: info.getEquipment()) {
			this.fishingEquipment.add(s);
			equipmentManager.add(s);
		}

		this.botArea = info.getArea();
		this.fishinSpotName = info.getName();
	}
	
	
	@Override
	public boolean execute() {
		if(firstrun) {
			levelManager.resetActionCount(116);
			firstrun = false;
		}
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

		if(!equipmentManager.manageEquipment()){
			return false;
		};
		
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

	@Override
	public void onMessage(Message message) {
		if (message.getMessage().contains("You catch some")) {
			levelManager.increaseActionCount();
		}
	}

	public ArrayList<String> getFishingEquipment() {
		return fishingEquipment;
	}

	public void setFishingEquipment(ArrayList<String> fishingEquipment) {
		this.fishingEquipment = fishingEquipment;
	}
	

}
