package BotDataJPX;

import org.dreambot.api.utilities.Timer;
import org.dreambot.api.randoms.RandomEvent;
import org.dreambot.api.script.ScriptManager;

import BotAI.FatigueManager;
import Task.Task;

public class Break implements Task {
	Timer stopwatch = new Timer();
	double addedEnergy = 0;
	
	public Break(long runtime) {
		startTimer(runtime);
	}
	
	public Break(long runtime, int add) {
		startTimer(runtime);
		this.addedEnergy = add;
		ScriptManager.getScriptManager().getCurrentScript().getRandomManager().disableSolver(RandomEvent.LOGIN);
	}

	@Override
	public boolean execute() {
		if(!stopwatch.finished())
			return true;
		FatigueManager.getInstance().rechargeEnergy(addedEnergy);
		ScriptManager.getScriptManager().getCurrentScript().getRandomManager().enableSolver(RandomEvent.LOGIN);
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
