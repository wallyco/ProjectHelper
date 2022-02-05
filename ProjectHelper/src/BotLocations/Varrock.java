package BotLocations;

import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;

public enum Varrock implements Locations {
        JULIETSHOUSE_RANGE(3160, 3429, 0, 0),
        VARROCK_NORTH_TREE(3260, 3510, 0, 0),
        VARROCK_EAST_TREE(3276, 3475, 0, 0);

    Varrock(int tile1, int tile2, int zaxis, int combatLevel) {
        this.botArea = new Tile(tile1, tile2, zaxis).getArea(4);
        this.requiredCombatLevel = combatLevel;
    }

    private Area botArea;
    private int requiredCombatLevel;

    public Area getArea(){
        return botArea;
    }

    public int getRequiredCombatLevel(){
        return requiredCombatLevel;
    }


}
