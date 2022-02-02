package BotAI;

import BotMain.Main;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.equipment.Equipment;

import java.util.HashMap;

public class EquipmentManager {
    //TRUE == EQUIPPED FALSE == CALL TASK TO EQUIP
    private HashMap<String, Boolean> equipmentSet = new HashMap<>();
    private String[] equipmentSetList;

    public EquipmentManager(){ }

    public EquipmentManager(String[] obj){
        for(String s : obj){
            MethodProvider.log(s);
            equipmentSet.put(s, false);
        }

        equipmentSetList = (String[]) equipmentSet.keySet().toArray();
    }

    public boolean manageEquipment(){
        checkEquipment();
        if(equipmentSet.values().contains(false)) {
            Main.ai.getTaskManager().insertAtHeadCopy
                    (new BotTask.Bank(false, equipmentSetList));
            return false;
        }
        return true;
    }

    private void checkEquipment(){
        equipmentSet.forEach((k,v) -> {
            if(Inventory.contains(k)
            || Equipment.contains(k)){
                equipmentSet.replace(k,true);
            }
        });
    }

    public void add(String[] obj){
        for(String s : obj){
            equipmentSet.put(s, false);
        }
    }
}
