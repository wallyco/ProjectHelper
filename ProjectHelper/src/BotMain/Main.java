package BotMain;

import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;

import MenUI.UI;
import Task.Task;
import Task.TaskManager;


@ScriptManifest(author = "JPX", category = Category.UTILITY, name = "ProjectHelper", version = 0.1)
public class Main extends AbstractScript{
	private static UI gui = new UI();
	public static TaskManager<Task> manager = new TaskManager<>();
	
	@Override
	public void onStart() {
		MethodProvider.log("Loading gui");
		gui.main(null);
	}

	@Override
	public int onLoop() {
		UI.textLog.setText(manager.toString());
		manager.getNext();
		return generateRandomInt(Integer.parseInt(gui.textFatigueOffset.getText()));
	}
	

	@Override
	public void onExit() {
		manager.clear();
		gui.kill();
	}
	
	//TODO separate this to it's own class
	private int generateRandomInt(int offset) {
		return (int) (Math.random() * offset);
	}
	
}
