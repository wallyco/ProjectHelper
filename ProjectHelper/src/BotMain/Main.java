package BotMain;

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
	public static TaskManager<Task> manager = new TaskManager<>();
	int i = 6969;
	
	@Override
	public void onStart() {
		MethodProvider.log("Loading gui");
		gui.main();
	}

	@Override
	public int onLoop() {
		UI.textLog.setText(manager.toString());
		FatigueManager.getInstance().checkEnergy();
		UI.textLog.setText(UI.textLog.getText() + "\n" + FatigueManager.getInstance().getEnergy());
		UI.textLog.setText(UI.textLog.getText() + "\n" + FatigueManager.getInstance().getFatigueState().getFatigueState());
		manager.getNext();
		this.i = FatigueManager.getInstance().nextInt();
		return this.i;
	}
	

	@Override
	public void onExit() {
		manager.clear();
		gui.kill();
	}
	

	
}
