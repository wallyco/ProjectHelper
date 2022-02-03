import BotAI.FatigueManager;
import BotAI.EquipmentManager;

public class Test {
	public static void main(String[] args) {
		EquipmentManager equipmentManager = new EquipmentManager();

		equipmentManager.add("hello");

		System.out.println(equipmentManager);

//		for(int i = 0; i < 10; i++) {
//			System.out.println(i);
//			FatigueManager.getInstance().generateBreakDouble();
//		}
	}
}
