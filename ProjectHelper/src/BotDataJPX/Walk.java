package BotDataJPX;

import java.util.Objects;

import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.walking.impl.Walking;

import BotAI.FatigueStates;
import Task.Task;

public class Walk implements Task {
	private int error = 0;
	
	private Area area = null;
	
	private String NAME = "Walk";

	public Walk() {
		fm.setFatigueState(FatigueStates.ENGAGED);

	}
	
	public Walk(Area area) {
		this.area = area;
	}
	

	@Override
	public boolean execute() {
		if(area != null
				&& !area.contains(Players.localPlayer())) {
			Walking.walk(area);
			return true;
//			}else {
//				if(Walking.walkOnScreen(Players.localPlayer().getTile().getArea(5).getRandomTile()) 
//						&& error == 0)
//					error++;
//					return true;
		}else
			return false;
		
	}

	@Override
	public double fatigueRate() {
		return 0;
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
