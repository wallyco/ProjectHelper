package BotAI;

import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.bank.BankLocation;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.tabs.Tab;
import org.dreambot.api.methods.tabs.Tabs;

public class HealthManager {
	private boolean shouldRun = Inventory.isEmpty() || Inventory.contains(item -> item != null && !item.hasAction("Eat"));
	@SuppressWarnings("unused")
	private int hitpoints = 0;
	private int threshHoldToRun = 40;
	private int threshHoldToEat = 35;//TODO DEBUGGIN
	private boolean foodIsRequired;
	
	public HealthManager() {}
	
	public HealthManager(boolean foodReq) {
		this.foodIsRequired = foodReq;
	}
	
	public boolean isHealthOk() {
		checkShouldRun();
		if(shouldRun && getHitpoints() <= threshHoldToRun && foodIsRequired && Players.localPlayer().isInCombat()) {
			MethodProvider.log("Calling clearAddSave");
			BotMain.Main.ai.getTaskManager().clearAddSave(new BotDataJPX.Walk(BankLocation.GRAND_EXCHANGE.getArea(5)));
			return false;
		}else if(getHitpoints() <= threshHoldToEat) {
			MethodProvider.sleepUntil(() -> Tabs.open(Tab.INVENTORY), 1000);
			if(Inventory.interact(item -> item != null && item.hasAction("Eat"), "Eat")) {
				return true;
			}
		}
		return true;
	}
	
	private void checkShouldRun() {
		shouldRun = Inventory.isEmpty() || Inventory.contains(item -> item != null && !item.hasAction("Eat"));
	}
	
	public int getHitpoints() {
		return Players.localPlayer().getHealthPercent();
	}

	public void setHitpoints(int hitpoints) {
		this.hitpoints = hitpoints;
	}
}
