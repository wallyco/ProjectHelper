package BotLocations;

import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.methods.skills.Skills;
import org.dreambot.api.wrappers.items.Item;

import java.util.HashMap;
import java.util.Map;

public enum WoodCutting implements Skilling, Locations{
	VARROCK_EAST_TREE(Varrock.VARROCK_EAST_TREE.getArea(), new String[] {"Axe"}, new String[] {"Logs"}, "Tree" , "Chop down"),
	VARROCK_NORTH_TREE(new Tile(3260, 3510, 0).getArea(5), new String[]{"Axe"}, new String[]{"Logs"}, "Tree", "Chop down");

	private final HashMap<Integer, String> AXE_LEVELS = new HashMap<>();

	WoodCutting(Area area, String[] equipment, String[] deposit, String objectName, String interact){
		this.area = area;
		this.equipment = equipment;
		this.depositItems = deposit;
		this.npcInteraction = interact;
		this.name = objectName;
		AXE_LEVELS.put(1, "Bronze axe");
		AXE_LEVELS.put(6, "Steel axe");
		AXE_LEVELS.put(21, "Mithril axe");
		AXE_LEVELS.put(31, "Adamant axe");
		AXE_LEVELS.put(41, "Rune axe");
		//AXE_LEVELS.put(61, "Dragon axe");

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
		equipment[0] = setAxe();
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

	public void setEquipment(String[] equipment) {
		this.equipment = equipment;
	}

	public String setAxe(){
		int wclvl = Skills.getRealLevel(Skill.WOODCUTTING);
		String axeToReturn = "Bronze axe";
		int savedKey = 0;
		for (Map.Entry<Integer, String> entry : AXE_LEVELS.entrySet()) {
			Integer k = entry.getKey();
			String v = entry.getValue();
			if (wclvl >= k && k > savedKey) {
				savedKey = k;
				axeToReturn = v;
			}
		}
		return axeToReturn;
	}

	@Override
	public String getName() {
		return this.name;
	}
	
	

}
