package BotTask.UTIL;

import BotMain.Main;
import Task.Task;
import org.dreambot.api.methods.container.impl.bank.BankLocation;
import org.dreambot.api.script.ScriptManager;

public class Exit implements Task {
    private boolean firstrun = true;
    @Override
    public boolean execute() {
        if(firstrun){
            Main.ai.getTaskManager().insertAtHeadCopy(new Walk(BankLocation.GRAND_EXCHANGE.getArea(5)));
            firstrun = false;
            return true;
        }else {
            ScriptManager.getScriptManager().stop();
            return false;
        }
    }

    @Override
    public double fatigueRate() {
        return 0;
    }
}
