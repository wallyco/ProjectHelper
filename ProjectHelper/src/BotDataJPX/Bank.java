package BotDataJPX;
import java.util.Objects;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Tile;

import BotMain.Main;
import Task.Task;

public class Bank implements Task {
	private String itemName ="undefined";
	private Tile returnTile = Players.localPlayer().getTile();
	private Tile bankTile = org.dreambot.api.methods.container.impl.bank.Bank.getClosestBankLocation().getCenter();
	private boolean firstrun = true; //TODO TEMP FIX SYNCHRONISE THE QUEUE
	
	public Bank(String name) {
		itemName = name;
	}
	
	@Override
	public boolean execute() {
		if(firstrun) {
			Main.ai.getTaskManager().insertAtHeadCopy(new Walk(bankTile.getArea(5)));
			firstrun = false;
			return true;
		}else if(Inventory.contains(itemName)) {
			if(!org.dreambot.api.methods.container.impl.bank.Bank.isOpen()) {
				org.dreambot.api.methods.container.impl.bank.Bank.open();
			}else if(org.dreambot.api.methods.container.impl.bank.Bank.isOpen()) {
				org.dreambot.api.methods.container.impl.bank.Bank.depositAll(itemName);
				return true;
			}
		}
		
		if(!Inventory.contains(itemName)) {
			Main.ai.getTaskManager().insertBehindHead(new Walk(returnTile.getArea(6)));
			return false;
		}
		
		return true;		
	}

	@Override
	public double fatigueRate() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public String toString() {
		return "Bank [itemName=" + itemName + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(itemName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bank other = (Bank) obj;
		return Objects.equals(itemName, other.itemName);
	}
	
			

}
