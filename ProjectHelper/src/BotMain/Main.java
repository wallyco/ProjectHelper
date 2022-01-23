package BotMain;

import java.awt.EventQueue;

import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;

import BotAI.FatigueManager;
import MenUI.UI;
import Task.Task;
import Task.TaskManager;


@ScriptManifest(author = "JPX", category = Category.UTILITY, name = "ProjectHelper", version = 0.1)
public class Main extends AbstractScript{
	private UI gui = new UI();
	public static TaskManager<Task> taskManager = new TaskManager<>();
	int i = 6969;
	
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
		FatigueManager.getInstance().checkEnergy(); //TODO FIND new location for this
		taskManager.getNext();
		gui.updateLog();
		this.i = FatigueManager.getInstance().nextInt();
		return this.i;
	}
	

	@Override
	public void onExit() {
		taskManager.clear();
		gui.kill();
	}
	

	
}
