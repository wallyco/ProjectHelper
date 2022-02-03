package BotLocations;

import org.dreambot.api.methods.map.Area;

public enum Fishing implements Skilling, Locations {
	LUMBRIDGE_SWAMPS_NET(Lumbridge.SWAMPS_SKILLING.getArea(), new String[] {"Small fishing net"}, new String[] {"Raw shrimps","Raw anchovies"}, "Net", "Fishing spot"),
	LUMBRIDGE_SWAMPS_BAIT(Lumbridge.SWAMPS_SKILLING.getArea(), new String[] {"Fishing rod", "Fishing bait"}, new String[] {"Raw herring","Raw sardines"}, "Bait", "Fishing spot"),
	DRAYNOR_VILLAGE_SMALLNET(Lumbridge.DRAYNOR_VILLAGE.getArea(), new String[] {"Small fishing net"}, new String[] {"Raw shrimps","Raw anchovies"}, "Small Net", "Fishing spot"),
	DRAYNOR_VILLAGE_BAIT(Lumbridge.DRAYNOR_VILLAGE.getArea(), new String[] {"Fishing rod", "Fishing bait"}, new String[] {"Raw herring","Raw sardines"}, "Bait", "Fishing spot");


	Fishing(Area area, String[] equipment, String[] deposit, String npcInteraction, String fishingSpot){
		this.area = area;
		this.equipment = equipment;
		this.depositItems = deposit;
		this.npcInteraction = npcInteraction;
		this.fishingSpotName = fishingSpot;
	}
	
	private Area area;
	private String[] depositItems;
	private String[] equipment;
	private String npcInteraction;
	private String fishingSpotName;
	
	@Override
	public Area getArea() {
		return this.area;
	}
	@Override
	public String[] getEquipment() {
		return this.equipment;
	}
	@Override
	public String[] getDepositItems() {
		return this.depositItems;
	}
	@Override
	public String getInteract() {
		return this.npcInteraction;
	}
	@Override
	public String getName() {
		return this.fishingSpotName;
	}
}
