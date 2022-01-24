package BotMain;

import java.awt.EventQueue;

import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManager;
import org.dreambot.api.script.ScriptManifest;

import BotAI.AI;
import BotAI.FatigueManager;
import MenUI.UI;

@ScriptManifest(author = "JPX", category = Category.UTILITY, name = "ProjectHelper", version = 0.1)
public class Main extends AbstractScript{
	private UI gui = new UI();
	public static AI ai = new AI();

	private boolean firstrun = true;
	private int i = 6969;
	
	@Override
	public void onStart() {
		MethodProvider.log("Loading gui");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gui = new UI(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public int onLoop() {
		if(firstrun) {
			ScriptManager.getScriptManager().pause();
			firstrun = false;
			FatigueManager.getInstance().setEnergy(1);
		}

		ai.act();
		
		gui.updateLog();
		this.i = FatigueManager.getInstance().nextInt();
		return this.i;
	}
	

	@Override
	public void onExit() {
		ai.getTaskManager().clear();
		gui.kill();
	}

	public AI getAi() {
		return ai;
	}
	
}
