package BotTask.JPXBank;

import BotMain.Main;
import Task.Task;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.wrappers.cache.nodes.ItemNode;
import org.dreambot.api.wrappers.items.Item;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.stream.Stream;

public class EquipmentBanking implements Task {
    private ArrayList<String> listOfItemsToWithdraw = new ArrayList<>();
    private Tile bankTile = Bank.getClosestBankLocation().getCenter();
    private int bankRadius = 8;
    private boolean equipItems = false;

    public EquipmentBanking() {}

    public EquipmentBanking(boolean equipItems) {
        this.equipItems = equipItems;
    }

    public EquipmentBanking(HashSet<String> list, boolean equipItems) {
        this(equipItems);
        for(String s : list) {
            this.listOfItemsToWithdraw.add(s);
        }
    }

    public EquipmentBanking(String[] name, boolean equipItems) {
        this(equipItems);
        for(String e: name) {
            MethodProvider.log("adding" + e + "EquipmentBanking");
            this.listOfItemsToWithdraw.add(e);
        }
    }

    @Override
    public boolean execute() {
        if(Inventory.containsAll(listOfItemsToWithdraw)){
            if(equipItems){
                Inventory.all().forEach(i -> {
                    if(i != null && i.hasAction("Wield")){
                        i.interact("Wield");
                        MethodProvider.sleep(100);
                    }
                });
            }
            return false;
        }

        for (String s : listOfItemsToWithdraw) {
            if(Bank.contains(s)){
                MethodProvider.log("true");
            }else {
                return false;
            }

        }
        withdrawAll();
        return true;
    }

    @Override
    public double fatigueRate() {
        return 0;
    }

    private boolean withdrawAll() {
        if (bankTile.getArea(10).contains(player)) {
            MethodProvider.sleepUntil(() -> Bank.open(), 500);
        } else {
            Main.ai.getTaskManager().insertAtHeadCopy(new BotTask.UTIL.Walk(bankTile.getArea(bankRadius)));
            return true;
        }
        if (Bank.isOpen()) {
            MethodProvider.sleepUntil(() -> Bank.depositAllItems(), 500);

            listOfItemsToWithdraw.forEach(e -> {
                if(Bank.contains(e)) {
                    MethodProvider.sleepUntil(() -> Bank.withdrawAll(e), 250);
                    MethodProvider.sleepUntil(()-> Inventory.contains(e), 500);
                }else {
                    //TODO IF BANK DOES NOT CONTAIN ITEM
                    MethodProvider.log("equipment banking: Bank does not contain item: " + e);
                }
            });
        }
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EquipmentBanking that = (EquipmentBanking) o;
        return equipItems == that.equipItems && Objects.equals(listOfItemsToWithdraw, that.listOfItemsToWithdraw) && bankTile.equals(that.bankTile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(listOfItemsToWithdraw, bankTile, equipItems);
    }
}
