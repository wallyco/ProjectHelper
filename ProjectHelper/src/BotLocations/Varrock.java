package BotLocations;

import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;

public enum Varrock implements Locations{
	WOODCUT_EAST_TREE("Tree", "Chop down", "Logs", 3278, 3446, 0, 0, "Woodcuts the trees just outside the gates of Varrock's east side");
	
	Varrock(String npcName, String npcInteractOne, String bankItem, int tile1, int tile2, int zaxis, int combatLevel, String description) {
		this.npcName = npcName;
		this.npcInteractOne = npcInteractOne;
		this.npcInteractTwoOrBankItem = bankItem;
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
	public void setInteractTwoOrBankItem(String bankItem) {
		this.npcInteractTwoOrBankItem = bankItem;
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
