package BotLocations;

import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;

//AREA Large Chicken pit 3178, 3293
//AREA Cows next to large chicken 3199, 3289
//AREA TEAM CAPE EDMOND TRADE, Team-18 cape 3081, 3810, 0 LARGE AREA
//AREA Draynor Willow cutting / Small Net , Bait Fishing 3084, 3240,0 small area level ~15

//TODO Add a method to test whether the level requirement is to high


public enum Lumbridge implements Locations{//npc name, interactions1, string for interact two or bank item,
	
	SWAMPS_SKILLING(3241, 3168, 0, 0),
	GOBLIN_BARN(3146, 3303, 0, 10),
	COWS_SMALLFIELD(3199, 3289, 0, 15),
	CHICKEN_LARGEFIELD(3178, 3293, 0, 0),
	DRAYNOR_VILLAGE(3084, 3240,0, 15),
	COWS_LARGEFIELD(3177, 3325, 0, 10);
	
	
	Lumbridge(int tile1, int tile2, int zaxis, int combatLevel) {
		this.botArea = new Tile(tile1, tile2, zaxis).getArea(5);
		this.requiredCombatLevel = combatLevel;
	}
	
	private Area botArea;
	private int requiredCombatLevel;

	public Area getArea() {
		return botArea;
	}

	public int getRequiredCombatLevel() {
		return requiredCombatLevel;
	}




}
