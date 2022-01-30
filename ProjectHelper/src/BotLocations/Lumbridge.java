package BotLocations;

import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;

//AREA Large Chicken pit 3178, 3293
//AREA Cows next to large chicken 3199, 3289
//AREA TEAM CAPE EDMOND TRADE, Team-18 cape 3081, 3810, 0 LARGE AREA
//AREA Draynor Willow cutting / Small Net , Bait Fishing 3084, 3240,0 small area level ~15

//TODO SPLIT UP THE LOCATION ENUM


public enum Lumbridge implements Locations{//npc name, interactions1, string for interact two or bank item, 
	FISHING_SWAMPS("Fishing spot", "Bait", "Raw shrimp",  3241, 3168, 0, 0, "Net fishes at Lumbridge swamp"),
	COMBAT_GOBLIN_FIELDS("Goblin", "Attack", null, 3146, 3303, 0, 10, "Kills goblins on the boarder of Lumbridge and Draynor"),
	COMBAT_COWS_SMALLFIELD("Cow", "Attack", null, 3199, 3289, 0, 15, "Kills cows next to the large chicken coop");
	
	
	Lumbridge(String npcName, String npcInteractOne, String npcInteractTwo, int tile1, int tile2, int zaxis, int combatLevel, String description) {
		this.npcName = npcName;
		this.npcInteractOne = npcInteractOne;
		this.npcInteractTwoOrBankItem = npcInteractTwo;
		this.setBotArea(new Tile(tile1, tile2, zaxis).getArea(5));
		this.RequiredCombatLevel = combatLevel;
		this.desc = description;

	}
	
	private String npcName;
	private String npcInteractOne;
	private String npcInteractTwoOrBankItem;
	private Area botArea;
	private int RequiredCombatLevel;
	private String desc;


	
	public String getNpcName() {
		return npcName;
	}
	public void setNpcName(String npcName) {
		this.npcName = npcName;
	}
	public String getNpcInteractOne() {
		return npcInteractOne;
	}
	public void setNpcInteractOne(String npcInteractOne) {
		this.npcInteractOne = npcInteractOne;
	}
	public String getNpcInteractTwoOrBankItem() {
		return npcInteractTwoOrBankItem;
	}
	public void setNpcInteractTwoOrBankItem(String npcInteractTwo) {
		this.npcInteractTwoOrBankItem = npcInteractTwo;
	}
	public Area getBotArea() {
		return botArea;
	}
	public void setBotArea(Area botArea) {
		this.botArea = botArea;
	}
	public int getRequiredCombatLevel() {
		return RequiredCombatLevel;
	}
	public void setRequiredCombatLevel(int requiredCombatLevel) {
		RequiredCombatLevel = requiredCombatLevel;
	}
	
	@Override
	public String description() {
		// TODO Auto-generated method stub
		return desc;
	}
	
	@Override
	public Area area() {
		// TODO Auto-generated method stub
		return getBotArea();
	}
	@Override
	public String npcName() {
		// TODO Auto-generated method stub
		return getNpcName();
	}
	@Override
	public String depostItem() {
		// TODO Auto-generated method stub
		return getNpcInteractTwoOrBankItem();
	}
	@Override
	public String interaction() {
		// TODO Auto-generated method stub
		return getNpcInteractOne();
	}

	
	
}
