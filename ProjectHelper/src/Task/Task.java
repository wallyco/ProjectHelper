package Task;

import BotAI.EquipmentManager;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.wrappers.interactive.Player;

import BotAI.FatigueManager;
import BotAI.LevelManager;
import BotMain.Main;

public interface Task {
	FatigueManager fatigueManager = FatigueManager.getInstance();
	LevelManager levelManager = Main.ai.getLevelManager();
	Player player = Players.localPlayer();
	EquipmentManager equipmentManager = Main.ai.getEquipmentManager();

	boolean execute();
		
	double fatigueRate();
	
}
