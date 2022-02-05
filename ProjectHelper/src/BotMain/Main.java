package BotMain;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.util.concurrent.TimeUnit;

import BotLocations.Cooking;
import BotTask.Skilling.Fish;
import BotTask.Skilling.RangeMaster;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.randoms.RandomEvent;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManager;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.api.script.listener.ChatListener;

import BotAI.AI;
import BotAI.FatigueManager;
import BotTask.Skilling.Deforester;
import MenUI.UI;

@ScriptManifest(author = "JPX", category = Category.UTILITY, name = "ProjectHelper", version = 0.1)
public class Main extends AbstractScript
implements ChatListener {
	private UI gui = new UI();
	public static AI ai = new AI();
	private boolean firstrun = true;
	private int i = 6969;
	
	@Override
	public void onStart() {
		//TODO MOVE THEM TO LEVELMANAGER
		ScriptManager.getScriptManager().addListener(new Deforester());
		ScriptManager.getScriptManager().addListener(new Fish());
		ScriptManager.getScriptManager().getCurrentScript().getRandomManager().disableSolver(RandomEvent.DISMISS);
		timeBegan = System.currentTimeMillis();
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
		//PAUSE HERE AND ADD A STATIC VALUE TO ENERGYLEVEL UI
		if(firstrun) {
			ScriptManager.getScriptManager().pause();
			firstrun = false;
		}else {
			ai.act();
		}
		gui.updateLog();
		this.i = FatigueManager.getInstance().nextInt();
		return this.i;
	}
	

	@Override
	public void onExit() {
		ai.getTaskManager().clear();
		gui.kill();
		ScriptManager.getScriptManager().stop();
	}
	
	//TODO MAKE YOUR OWN
	private long timeBegan;
	private long timeRan;
	
	
	public void onPaint(Graphics g) {
	    timeRan = System.currentTimeMillis() - this.timeBegan;
	    g.drawString(ft(timeRan), 1,256);
    }
	private String ft(long duration) {

		String res = "";
	
		long days = TimeUnit.MILLISECONDS.toDays(duration);
	
		long hours = TimeUnit.MILLISECONDS.toHours(duration) - TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(duration));
	
		long minutes = TimeUnit.MILLISECONDS.toMinutes(duration) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(duration));
	
		long seconds = TimeUnit.MILLISECONDS.toSeconds(duration)- TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration));
	
		if (days == 0) {
			res = ("Total run time: " + hours + ":" + minutes + ":" + seconds);
		} else {
			res = ("Total run time: " + days + ":" + hours + ":" + minutes + ":" + seconds);
		}
		
	return res;
	}
	

	
	
}
