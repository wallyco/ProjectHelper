package BotTask;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Stream;

import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.item.GroundItems;
import org.dreambot.api.methods.map.Tile;

import BotMain.Main;
import Task.Task;

public class Bank implements Task {
	private ArrayList<String> listOfItemsToWithdraw = new ArrayList<>();
	private ArrayList<String> listOfItemsToDeposit = new ArrayList<>();
	private boolean bankingMethod = true; //true == deposit
	private Tile returnTile = Players.localPlayer().getTile();
	private Tile bankTile = org.dreambot.api.methods.container.impl.bank.Bank.getClosestBankLocation().getCenter();
	private boolean firstrun = true; //TODO TEMP FIX SYNCHRONIZE THE QUEUE
	private int amount = 0;
	
	public Bank() {}
	
	
	public Bank(String name) {
		listOfItemsToDeposit.add(name);
	}
	
	public Bank(ArrayList<String> list) {
		for(String s : list) {
			this.listOfItemsToDeposit.add(s);
		}
	}
	
	public Bank(Object[] name) {
		for(Object e: name) {
			MethodProvider.log("adding");
			this.listOfItemsToDeposit.add((String) e);
		}
	}
	public Bank(boolean method, String itemName) {
		this.bankingMethod = method;
		this.listOfItemsToDeposit.add(itemName);
	}
	
	public Bank(boolean method, String itemName, int amount) {
		this.bankingMethod = method;
		this.listOfItemsToDeposit.add(itemName);
		this.amount = amount;
	}
	
	public Bank(boolean method, String[] itemsToManipulate) {
		this.bankingMethod = method;
		if(method) {
			for(String s : itemsToManipulate) {
				this.listOfItemsToDeposit.add(s);
			}
		}else {
			for(String s : listOfItemsToWithdraw) {
				this.listOfItemsToWithdraw.add(s);
			}
		}
	}
	
	@Override
	public boolean execute() {
		if(Dialogues.canContinue())
			Dialogues.continueDialogue();
		
		if(firstrun) {
			Main.ai.getTaskManager().insertAtHeadCopy(new Walk(bankTile.getArea(8)));
			firstrun = false;
			return true;
		}else if(bankingMethod) {
			if(depositAll()) {
				Main.ai.getTaskManager().insertBehindHead(new BotTask.Walk(returnTile.getArea(5)));
				return false;
			}
		}else {
			if(withdrawAll()) {;
				Main.ai.getTaskManager().insertBehindHead(new BotTask.Walk(returnTile.getArea(5)));
				return false;
			}
		}

		return true;		
	}
	
	public boolean depositAll() {
		if(Inventory.contains(getListOfItemsToDeposit().get(0).toString())) {
			if(bankTile.getArea(10).contains(player) && !org.dreambot.api.methods.container.impl.bank.Bank.isOpen()) {
				org.dreambot.api.methods.container.impl.bank.Bank.open();
			}else if(org.dreambot.api.methods.container.impl.bank.Bank.isOpen()) {
				this.listOfItemsToDeposit.stream().forEach(e -> {
					org.dreambot.api.methods.container.impl.bank.Bank.depositAll(e);
				});
				return true;
			}
		}
		return false;
	}
	
	public boolean withdrawAll() {
		if(!Inventory.contains(getListOfItemsToDeposit().get(0).toString())) {
			if(bankTile.getArea(10).contains(player) && !org.dreambot.api.methods.container.impl.bank.Bank.isOpen()) {
				org.dreambot.api.methods.container.impl.bank.Bank.open();
			}else if(org.dreambot.api.methods.container.impl.bank.Bank.isOpen()) {
				this.listOfItemsToWithdraw.stream().forEach(e -> {
					org.dreambot.api.methods.container.impl.bank.Bank.withdrawAll(e);
				});
				return true;
			}
		}
		return false;
	}
	
//	public boolean withdraw(int amount) {
//		if(bankTile.getArea(10).contains(player) && !org.dreambot.api.methods.container.impl.bank.Bank.isOpen()) {
//			org.dreambot.api.methods.container.impl.bank.Bank.open();
//		}else if(org.dreambot.api.methods.container.impl.bank.Bank.isOpen()) {
//			if(org.dreambot.api.methods.container.impl.bank.Bank.withdraw(listOfItemsToWithdraw.toString(), amount));
//				return true;
//		}
//		return false;
//	}


	public void addToListOfItemsToWithdraw(String[] arr) {
		for(String s: arr) {
			listOfItemsToWithdraw.add(s);
		}
	}
	
	public void addToListOfItemsToDeposit(String[] arr) {
		for(String s: arr) {
			listOfItemsToDeposit.add(s);
		}
	}

	public void setListOfItemsToWithdraw(ArrayList<String> listOfItemsToWithdraw) {
		this.listOfItemsToWithdraw = listOfItemsToWithdraw;
	}

	public ArrayList<String> getListOfItemsToDeposit() {
		return listOfItemsToDeposit;
	}

	public void setLsitOfItemsToDeposit(ArrayList<String> listOfItemsToDeposit) {
		this.listOfItemsToDeposit = listOfItemsToDeposit;
	}

	@Override
	public double fatigueRate() {
		// TODO Auto-generated method stub
		return 0;
	}
	

	
			

}
