package BotAI;


import Task.Task;
import Task.TaskManager;

public class AI {
	private TaskManager<Task> taskManager = new TaskManager<>();
	private HealthManager healthManager = new HealthManager(true);
	private LevelManager levelManager = new LevelManager();
	
	//Handle values in constructors and overload AI constructor
	public AI() {}
	
	public void act() {
		healthManager.isHealthOk();
		FatigueManager.getInstance().checkEnergyLevel();
		getTaskManager().perform();
	}

	public TaskManager<Task> getTaskManager() {
		return taskManager;
	}

	public void setTaskManager(TaskManager<Task> taskManager) {
		this.taskManager = taskManager;
	}

	public HealthManager getHealthManager() {
		return healthManager;
	}

	public void setHealthManager(HealthManager healthManager) {
		this.healthManager = healthManager;
	}

	public LevelManager getLevelManager() {
		return levelManager;
	}

	public void setLevelManager(LevelManager levelManager) {
		this.levelManager = levelManager;
	}
	
	
	
	
	

}
