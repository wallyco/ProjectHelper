package BotDataJPX;

import java.util.Objects;

import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.walking.impl.Walking;

import BotAI.FatigueManager;
import BotAI.FatigueStates;
import Task.Task;

public class Walk implements Task {
	private int error = 0;
	
	private Area area = null;
	
	private String NAME = "Walk";

	public Walk() {
		fatigueManager.setFatigueState(FatigueStates.ENGAGED);
	}
	
	public Walk(Area area) {
		this();
		this.area = area;
	}
	
	//TODO Prone to errors	
	@Override
	public boolean execute() {
		
		if(area != null && !area.contains(Players.localPlayer())){
			Walking.walk(area);
			FatigueManager.getInstance().consumeEnergy(fatigueRate());
			return true;
		}

		return false;
	}

	@Override
	public double fatigueRate() {
		return .01;
	}

	@Override
	public String toString() {
		return "Walk [area=" + area + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(NAME, error);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Walk other = (Walk) obj;
		return Objects.equals(NAME, other.NAME) && error == other.error;
	}
	

}
