package BotLocations;

import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;

public enum WoodCutting implements Skilling, Locations{
	VARROCK_EAST_TREE(new Tile(3278, 3446, 0).getArea(5), new String[] {"Axe"}, new String[] {"Logs"}, "Tree" , "Chop down");
	
	WoodCutting(Area area, String[] equipment, String[] deposit, String objectName, String interact){
		this.area = area;
		this.equipment = equipment;
		this.depositItems = deposit;
		this.npcInteraction = interact;
		this.name = objectName;
	}
	
	private Area area;
	private String[] depositItems;
	private String[] equipment;
	private String npcInteraction;
	private String name;

	@Override
	public Area getArea() {
		// TODO Auto-generated method stub
		return this.area;
	}

	@Override
	public String[] getEquipment() {
		// TODO Auto-generated method stub
		return this.equipment;
	}

	@Override
	public String[] getDepositItems() {
		// TODO Auto-generated method stub
		return this.depositItems;
	}

	@Override
	public String getInteract() {
		// TODO Auto-generated method stub
		return this.npcInteraction;
	}

	@Override
	public String getName() {
		return this.name;
	}
	
	

}
