package BotDataJPX;

import org.dreambot.api.utilities.Timer;
import org.dreambot.api.randoms.RandomEvent;
import org.dreambot.api.script.ScriptManager;

import Task.Task;

public class Break implements Task {
	Timer stopwatch = new Timer();
	double addedEnergy = 0;
	
	public Break(long runtime) {
		startTimer(runtime);
		fatigueManager.setAcceptBreak(false);
	}
	
	public Break(long runtime, int add) {
		startTimer(runtime);
		this.addedEnergy = add;
		ScriptManager.getScriptManager().getCurrentScript().getRandomManager().disableSolver(RandomEvent.LOGIN);
		fatigueManager.setAcceptBreak(false);
	}

	@Override
	public boolean execute() {
		if(!stopwatch.finished()) {
			//if(Players.localPlayer())
			return true;
		}
		fatigueManager.rechargeEnergy(addedEnergy);
		fatigueManager.setAcceptBreak(true);
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
