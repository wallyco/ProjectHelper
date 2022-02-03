package BotTask;

import java.util.Objects;

import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.wrappers.interactive.NPC;

import Task.Task;

//TODO ADJUST random ints generated for monster kills 0 is possible

public class Slayer implements Task{
	private NPC npc;
	private String npcName;
	private int numberOfKills = 0;
	private int numberOfKillsToObtain;
	private Area botArea;
	private String interact = "Attack";
	private boolean firstrun = true;
	
	public Slayer() {
	}
	
	public Slayer(String npcName, Area area) {
		this.npcName = npcName;
		this.botArea = area;
	}
	
	public Slayer(BotLocations.Combat info) {
		this.npcName = info.getNpcName();
		this.botArea = info.getArea();
		this.interact = info.getInteract();
	}
	
	@Override
	public double fatigueRate() {
		// TODO Auto-generated method stub
		return 1.5;
	}
	
	@Override
	public boolean execute() {
		if(firstrun) {
			levelManager.resetActionCount(25);
			firstrun = false;
		}
		if(!levelManager.continueSlaying()) {
			return false;
		}
		
		if(shouldAttack()
	            && attack()) {
			fatigueManager.consumeEnergy(fatigueRate());
	    }
		
		return true;
	}
	
    protected boolean shouldAttack() {
        
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
	
	
	protected boolean attack() {
        setNPC(npc);
        MethodProvider.sleep(50);

        if(getNpc() != null
                && !getNpc().isInCombat()
                && getNpc().canReach())
        {
            MethodProvider.sleepUntil(() -> getNpc().interact(interact), 500);	
            levelManager.increaseActionCount();
            return true;
        }
        return false;
    }
	

	
	
    public void setNPC(NPC npc) {
    	if(player.isInteractedWith()) {
        	npc = NPCs.closest(e -> e.isInteracting(player));
        	this.npc = npc;
    	}else {
	        npc = NPCs.closest(n ->
	            n.getName().equals(this.npcName) 
	            && n != null 
	            && !n.isInCombat()
				&& n.canReach());

	        MethodProvider.sleep(50);
	        this.npc = npc;
	        
	        if(this.npc == null) {
	        	BotMain.Main.ai.getTaskManager().insertAtHeadCopy(new Walk(this.botArea));
	        }
    	}
    }
	
		
	public NPC getNpc() {
		return npc;
	}

	public String getNpcName() {
		return npcName;
	}
	public void setNpcName(String npcName) {
		this.npcName = npcName;
	}
	public int getNumberOfKills() {
		return numberOfKills;
	}
	public void setNumberOfKills(int numberOfKills) {
		this.numberOfKills = numberOfKills;
	}
	public int getNumberOfKillsToObtain() {
		return numberOfKillsToObtain;
	}
	public void setNumberOfKillsToObtain(int numberOfKillsToObtain) {
		this.numberOfKillsToObtain = numberOfKillsToObtain;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(npcName, numberOfKills, numberOfKillsToObtain);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Slayer other = (Slayer) obj;
		return Objects.equals(npcName, other.npcName) && numberOfKills == other.numberOfKills
				&& numberOfKillsToObtain == other.numberOfKillsToObtain;
	}

	@Override
	public String toString() {
		return "Slayer [npcName=" + npcName + "]";
	}


	
	

}
