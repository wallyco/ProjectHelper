package Task;

import BotAI.FatigueManager;

public interface Task {
	FatigueManager fm = FatigueManager.getInstance();
	
	boolean execute();
		
	double fatigueRate();
	
}
