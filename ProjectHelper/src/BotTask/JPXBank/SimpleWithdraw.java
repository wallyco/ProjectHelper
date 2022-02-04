package BotTask.JPXBank;

import BotMain.Main;
import Task.Task;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.methods.map.Tile;

import java.util.ArrayList;
import java.util.Objects;

public class SimpleWithdraw implements Task {
    private ArrayList<String> listOfItemsToWithdraw = new ArrayList<>();
    private Tile bankTile;
    private int bankRadius = 8;
    private int amount = 1;

    public SimpleWithdraw() {
    }
    public SimpleWithdraw(ArrayList<String> list, int amount) {
        for(String s : list) {
            this.listOfItemsToWithdraw.add(s);
        }
        this.amount = amount;
    }

    public SimpleWithdraw(String name, int amount){
        listOfItemsToWithdraw.add(name);
        this.amount = amount;
    }

    public SimpleWithdraw(String[] name) {
        for(String e: name) {
            MethodProvider.log("adding" + e + "SkillingDeposit");
            this.listOfItemsToWithdraw.add(e);
        }
    }
    @Override
    public boolean execute() {
        if (Inventory.containsAll(listOfItemsToWithdraw)) {
            return false;
        }

        if (getBankTile().getArea(15).contains(player)) {
            MethodProvider.sleepUntil(() -> Bank.open(), 500);
        } else {
            Main.ai.getTaskManager().insertAtHeadCopy(new BotTask.UTIL.Walk(getBankTile().getArea(bankRadius)));
            return true;
        }

        if (Bank.isOpen()) {
            listOfItemsToWithdraw.forEach(e -> {
                Bank.withdraw(e, this.amount);
            });
        }

        return true;
    }

    @Override
    public double fatigueRate() {
        return 0;
    }

    private Tile getBankTile(){
        return bankTile = Bank.getClosestBankLocation().getCenter();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleWithdraw that = (SimpleWithdraw) o;
        return amount == that.amount && Objects.equals(listOfItemsToWithdraw, that.listOfItemsToWithdraw) && bankTile.equals(that.bankTile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(listOfItemsToWithdraw, bankTile, amount);
    }
}
