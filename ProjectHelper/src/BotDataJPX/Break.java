package BotDataJPX;

import org.dreambot.api.utilities.Timer;

import BotAI.FatigueManager;
import Task.Task;

public class Break implements Task {
	
	Timer stopwatch = new Timer();
	double addedEnergy = 0;
	
	public Break(long runtime) {
		startTimer(runtime);
	}
	
	public Break(long runtime, double add) {
		startTimer(runtime);
		this.addedEnergy = add;
	}

	@Override
	public boolean execute() {
		if(!stopwatch.finished())
			return true;
		FatigueManager.getInstance().rechargeEnergy(addedEnergy);
		return false;
	}

	@Override
	public double fatigueRate() {
		return 0;
	}
	
	private void startTimer(long runtime) {
		stopwatch.setRunTime(runtime);
	}

}
