package BotAI;

import BotLocations.Skilling;
import BotMain.Main;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.methods.container.impl.equipment.Equipment;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class EquipmentManager {

    //TRUE == EQUIPPED FALSE == CALL TASK TO EQUIP
    private HashMap<String, Boolean> equipmentSet = new HashMap<>();
    private HashMap<String, Integer> equipmentLevel = new HashMap<>();
    private HashSet<String> equipmentSetList = new HashSet<>();
    private int loopcount = 98;
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
        for(String s: equipmentSetList){
            if(loopcount > 10 && !Bank.contains(s)){
                MethodProvider.log("loop reset and bank doesn't contain");
                if (!Main.ai.getTaskManager().isEmpty()) {
                    Main.ai.getTaskManager().getNext();
                    clear();
                }
                loopcount = 0;
                return true;
            }
        }
        if(getEquipmentSet().containsValue(false)) {
            Main.ai.getTaskManager().insertAtHeadCopy
                    (new BotTask.JPXBank.EquipmentBanking(equipmentSetList, true));
        }
        loopcount++;
        return true;
    }

    private void checkEquipment(){
        equipmentSet.forEach((k,v) -> {
            if(Inventory.contains(k)
            || Equipment.contains(k)){
                equipmentSet.replace(k, true);
                equipmentSetList.remove(k);
            }else if(!equipmentSetList.contains(k)){
                equipmentSet.replace(k, false);
                equipmentSetList.add(k);
            }
        });
    }

    public void add(ArrayList<String> obj){
        clear();
        for(String s : obj){
            equipmentSet.put(s, false);
            equipmentSetList.add(s);
        }
    }

    public void add(String[] obj){
        clear:
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
    public void getWoodcuttingAxe(Skilling skillenum){
        clear();
        add(skillenum.getEquipment());
    }


    public HashSet<String> getEquipmentSetList() {
        return equipmentSetList;
    }

    public void setEquipmentSetList(HashSet<String> equipmentSetList) {
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
