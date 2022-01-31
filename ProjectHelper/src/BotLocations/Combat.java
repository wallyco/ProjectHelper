package BotLocations;

import org.dreambot.api.methods.map.Area;

public enum Combat implements Locations{ // AREA, EQUIPMENT, LOOT, NAME, Interact
	LUMBRIDGE_GOBLIN_BARN(Lumbridge.GOBLIN_BARN.getArea(), new String[] {null}, new String[] {null}, "Goblin", "Attack"),
	LUMBRIDGE_COW_SMALL_FIELD(Lumbridge.COWS_SMALLFIELD.getArea(), new String[] {null}, new String[] {null}, "Cow", "Attack");
	
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
