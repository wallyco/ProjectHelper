package BotDataJPX;

import java.util.Objects;

import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.wrappers.interactive.NPC;
import Task.Task;

public class Attack implements Task {

	private NPC npc;
    private String npcName;
    
    public Attack() { }
    
    public Attack(String name){
        npcName = name;
    }
    
	@Override
	public double fatigueRate() {
		return .08;
	}
    
    @Override
    public boolean execute() {
        if(shouldAttack()) {
            attack();
        }
        return true;
    }
    
    private Boolean shouldAttack() {
        if(Dialogues.canContinue()) {
            Dialogues.continueDialogue();
        }
        MethodProvider.sleep(50);
        
        if(Players.localPlayer().isAnimating() || Players.localPlayer().isInCombat()) {
        	MethodProvider.sleep(50);
        	if(Players.localPlayer().isAnimating() || Players.localPlayer().isInCombat())
        		return false;
        }      
        return true;
    }
    
    private boolean attack() {
        setNPC(npc);
        MethodProvider.sleep(50);

        if(getNPC() != null
                && !getNPC().isInCombat()
                && getNPC().canReach())
        {
            MethodProvider.sleepUntil(() -> getNPC().interact("Attack"), 1000);
            MethodProvider.sleepWhile(() -> Players.localPlayer().isAnimating(), 3000);
            fm.consumeEnergy(fatigueRate());
            return true;
        }
        return false;
    }

	public String getName() {
        return npcName;
    }
    
    public void setName(String s) {
        npcName = s;
    }

    public NPC getNPC() {
        return npc;
    }

    public void setNPC(NPC npc) {
        npc = NPCs.closest(n ->
            n.getName().equals(this.npcName) 
            && n != null 
            && !n.isInCombat());
        MethodProvider.sleep(50);
        this.npc = npc;
    }

	@Override
	public String toString() {
		return "Attack [npcName=" + npcName + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(npcName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Attack other = (Attack) obj;
		return Objects.equals(npcName, other.npcName);
	}

}