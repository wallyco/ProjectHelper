package BotLocations;



import org.dreambot.api.methods.map.Area;

public interface Locations {
	
	String description();
	Area area();
	String npcName();
	String depostItem();
	String interaction();
}
