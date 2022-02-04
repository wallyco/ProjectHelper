package BotAI;

import BotMain.Main;
import BotTask.JPXBank.Bank;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.equipment.Equipment;

import java.util.ArrayList;
import java.util.HashMap;

public class EquipmentManager {

    //TRUE == EQUIPPED FALSE == CALL TASK TO EQUIP
    private HashMap<String, Boolean> equipmentSet = new HashMap<>();
    private HashMap<String, Integer> equipmentLevel = new HashMap<>();
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
                    (new BotTask.JPXBank.EquipmentBanking(equipmentSetList, false));
            return true;
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
        equipmentSet.clear();
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
            MethodProvider.log(getEquipmentSet().toString() + " equipmentmanager add");
            MethodProvider.log(getEquipmentSet().values() + " equipmentmanager add");
        }
    }

    public void add(String s){
        equipmentSet.put(s, false);
        equipmentSetList.add(s);
    }

    public void clear(){
        equipmentSetList.clear();
        equipmentSet.clear();
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

    public HashMap<String, Integer> getEquipmentLevel() {
        return equipmentLevel;
    }

    public void setEquipmentLevel(HashMap<String, Integer> equipmentLevel) {
        this.equipmentLevel = equipmentLevel;
    }
    @Override
    public String toString() {
        return "EquipmentManager{" +
                "equipmentSet=" + equipmentSet +
                ", equipmentSetList=" + equipmentSetList +
                '}';
    }


}
