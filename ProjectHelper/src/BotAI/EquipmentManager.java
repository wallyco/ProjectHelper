package BotAI;

import BotMain.Main;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.equipment.Equipment;
import org.dreambot.api.methods.interactive.Players;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class EquipmentManager {

    //TRUE == EQUIPPED FALSE == CALL TASK TO EQUIP
    private HashMap<String, Boolean> equipmentSet = new HashMap<>();

    private ArrayList<String> equipmentSetList = new ArrayList<>();

    public EquipmentManager(){ }

    public EquipmentManager(String[] obj){
        for(String s : obj){
            MethodProvider.log(s);
            equipmentSet.put(s, false);
            equipmentSetList.add(s);
        }
    }

    public boolean manageEquipment(){
        checkEquipment();
        if(getEquipmentSet().containsValue(false)) {
            Main.ai.getTaskManager().insertAtHeadCopy
                    (new BotTask.Bank(false, getEquipmentSetList(), true));
            return false;
        }
        return true;
    }

    private void checkEquipment(){
        equipmentSet.forEach((k,v) -> {
            if(Inventory.contains(k)
            || Equipment.contains(k)){
                equipmentSet.replace(k, true);
                equipmentSetList.remove(k);
            }
        });
    }

    public void add(ArrayList<String> obj){
        for(String s : obj){
            equipmentSet.put(s, false);
            equipmentSetList.add(s);
            MethodProvider.log(s);
        }
    }

    public void add(String[] obj){
        for(String s: obj){
            equipmentSet.put(s, false);
            equipmentSetList.add(s);
            MethodProvider.log(getEquipmentSet().toString());
            MethodProvider.log(getEquipmentSet().values());
        }
    }

    public void add(String s){
        equipmentSet.put(s, false);
        equipmentSetList.add(s);
    }

    public boolean resetInventory() {
        checkEquipment();
        if(Inventory.contains(item -> !item.getName().equals(equipmentSetList.get(0)))){
            return true;
        }
        return false;
    }

    public ArrayList<String> getEquipmentSetList() {
        return equipmentSetList;
    }

    public void setEquipmentSetList(ArrayList<String> equipmentSetList) {
        this.equipmentSetList = equipmentSetList;
    }

    public HashMap<String, Boolean> getEquipmentSet() {
        return equipmentSet;
    }

    public void setEquipmentSet(HashMap<String, Boolean> equipmentSet) {
        this.equipmentSet = equipmentSet;
    }

    @Override
    public String toString() {
        return "EquipmentManager{" +
                "equipmentSet=" + equipmentSet +
                ", equipmentSetList=" + equipmentSetList +
                '}';
    }
}
