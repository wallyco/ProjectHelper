package BotLocations;

import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;

public enum Combat implements Locations{ // AREA, EQUIPMENT, LOOT, NAME, Interact
	LUMBRIDGE_GOBLIN_BARN(Lumbridge.GOBLIN_BARN.getArea(), new String[] {null}, new String[] {null}, "Goblin", "Attack"),
	LUMBRIDGE_COW_SMALLFIELD(Lumbridge.COWS_SMALLFIELD.getArea(), new String[] {null}, new String[] {null}, "Cow", "Attack"),
	LUMBRIDGE_CHICKEN_LARGEFIELD(Lumbridge.CHICKEN_LARGEFIELD.getArea(), new String[] {null}, new String[] {null}, "Chicken", "Attack"),
	LUMBRIDGE_COW_LARGEFIELD(Lumbridge.COWS_LARGEFIELD.getArea(), new String[] {null}, new String[] {null}, "Cow", "Attack"),
	BARBARIANVILLAGE_BARBARIAN(new Tile(3084, 3432,0).getArea(5), new String[]{null}, new String[]{null}, "Barbarian", "Attack"),
	MONASTERY_MONK(new Tile(3052, 3492, 0).getArea(5), new String[]{null}, new String[]{null}, "Monk", "Attack"),
	DWARFMINE_DWARF(new Tile(3015, 3450, 0).getArea(5), new String[]{null}, new String[]{null},"Dwarf", "Attack");

	Combat(Area area, String[] equipment, String[] loot, String npcName, String interact){
		this.area = area;
		this.equipment = equipment;
		this.loot = loot;
		this.npcName = npcName;
		this.interact = interact;
	}
	
	private Area area;
	private String[] equipment;
	private String[] loot;
	private String npcName;
	private String interact;
	
	public Area getArea() {
		return area;
	}
	public void setArea(Area area) {
		this.area = area;
	}
	public String[] getEquipment() {
		return equipment;
	}
	public void setEquipment(String[] equipment) {
		this.equipment = equipment;
	}
	public String[] getLoot() {
		return loot;
	}
	public void setLoot(String[] loot) {
		this.loot = loot;
	}
	public String getNpcName() {
		return npcName;
	}
	public void setNpcName(String npcName) {
		this.npcName = npcName;
	}
	public String getInteract() {
		return interact;
	}
	public void setInteract(String interact) {
		this.interact = interact;
	}
	
}
