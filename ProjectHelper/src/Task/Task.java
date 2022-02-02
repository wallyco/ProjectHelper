package Task;

import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.wrappers.interactive.Player;

import BotAI.FatigueManager;
import BotAI.LevelManager;
import BotMain.Main;

public interface Task {
	FatigueManager fatigueManager = FatigueManager.getInstance();
	LevelManager levelManager = Main.ai.getLevelManager();
	Player player = Players.localPlayer();
	
	boolean execute();
		
	double fatigueRate();
	
}
