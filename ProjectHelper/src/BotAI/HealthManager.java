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
	private int thresholdToRun = 50;
	private int thresholdToEat = 35;
	private boolean foodIsRequired;
	private int loopCounter = 0;
	private boolean isInsertCalled = false;
	
	public HealthManager() {}
	
	public HealthManager(boolean foodReq) {
		this.foodIsRequired = foodReq;
	}
	
	public boolean isHealthOk() {
		generateIntToEat();
		checkShouldRun();
		if(shouldRun && getHitpoints() <= thresholdToRun && foodIsRequired && !isInsertCalled && Players.localPlayer().isInCombat()) {
			BotMain.Main.ai.getTaskManager().insertAtHeadDelete(new BotDataJPX.Walk(BankLocation.GRAND_EXCHANGE.getArea(5)));
			isInsertCalled = true;
			return false;
		}else if(getHitpoints() <= thresholdToEat) {
			MethodProvider.sleepUntil(() -> Tabs.open(Tab.INVENTORY), 1000);
			if(Inventory.interact(item -> item != null && item.hasAction("Eat"), "Eat")) {
				return false;
			}
		}
		isInsertCalled = false;
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
	
	private void generateIntToEat() {
		loopCounter++;
		if(loopCounter == 10) {
			this.thresholdToEat = (int) (Math.random() * 30 + 45);
			loopCounter = 0;
		}
	}
}
