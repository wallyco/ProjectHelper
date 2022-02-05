package BotLocations;

import org.dreambot.api.methods.map.Area;

import java.util.HashMap;

public enum Cooking implements Skilling { //food to cook , deposit items
    VARROCK_JULIETHOUSE(Varrock.JULIETSHOUSE_RANGE.getArea(), new String[]{"Raw shrimps"}, new String[]{null}, "Range", "Cook");
    private final HashMap<Integer, String> COOKING_LEVELS = new HashMap<>();

    Cooking(Area area, String[] foodToCook, String[] depositItems, String name, String interact){
        this.botArea = area;
        this.equipment = foodToCook;
        this.depositItems = depositItems;
        this.npcName = name;
        this.interact = interact;
    }

    private Area botArea;
    private String[] equipment;
    private String[] depositItems;
    private String npcName;
    private String interact;


    @Override
    public Area getArea() {
        return botArea;
    }

    @Override
    public String[] getEquipment() {
        return equipment;
    }

    @Override
    public String[] getDepositItems() {
        return depositItems;
    }

    @Override
    public String getInteract() {
        return interact;
    }

    @Override
    public String getName() {
        return npcName;
    }

}
