package BotAI;

import java.util.Random;

import org.dreambot.api.methods.combat.Combat;
import org.dreambot.api.methods.combat.CombatStyle;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.methods.skills.Skills;
import org.dreambot.api.methods.tabs.Tab;
import org.dreambot.api.methods.tabs.Tabs;
import org.dreambot.api.script.listener.ChatListener;
import org.dreambot.api.wrappers.widgets.message.Message;


public class LevelManager {
	private int levelAttack = Skills.getRealLevel(Skill.ATTACK);
	private int levelStrength = Skills.getRealLevel(Skill.STRENGTH);
	private int levelDefence = Skills.getRealLevel(Skill.DEFENCE);
	private int levelWoodCutting = Skills.getRealLevel(Skill.WOODCUTTING);
	private int levelAttackTo = 0;
	private int levelStrengthTo = 0;
	private int levelDefenceTo = 0;
	private int levelWoodCuttingTo = 0;
	private int numberOfActionsToObtain = 0;
	private int numberOfActions = 0;
	private Random dom = new Random();

	
	public LevelManager() {}
	
	public LevelManager(int numberOfActionsToObtain) {
		this.numberOfActionsToObtain = numberOfActionsToObtain;
	}
	
	
	public boolean continueLevelingCombat() {
		getLevels();
		if(levelAttackTo
				+levelStrengthTo
				+levelDefenceTo == 0) {
			return true;
		}
		if(levelStrength < levelStrengthTo) {
			if(Combat.getCombatStyle() != CombatStyle.STRENGTH)
				Tabs.open(Tab.COMBAT);
				Combat.setCombatStyle(CombatStyle.STRENGTH);
			return true;
		}
		else if(levelAttack < levelAttackTo) {
			if(Combat.getCombatStyle() != CombatStyle.ATTACK)
				Tabs.open(Tab.COMBAT);
				Combat.setCombatStyle(CombatStyle.ATTACK);
			return true;
		}
		else if (levelDefence < levelDefenceTo) {
			if(Combat.getCombatStyle() != CombatStyle.DEFENCE)
				Tabs.open(Tab.COMBAT);
				Combat.setCombatStyle(CombatStyle.DEFENCE);
			return true;
		}
		
		return false;
	}
	
	public boolean continueSlaying() {
		continueLevelingCombat();
		return (getNumberOfActions() < getNumberOfActionsToObtain());		
	}
	
	public boolean continueLevelingWoodcutting() {
		return (getNumberOfActions() < getNumberOfActionsToObtain());
	}

	public boolean continueLevelingCooking(String[] cookingitems){
		if(Bank.getBankHistoryCache() != null){
			if(Bank.contains(cookingitems) || Inventory.contains(cookingitems)
					&& getNumberOfActions() < getNumberOfActionsToObtain());
			return true;
		}
		return false;
	}

	public boolean continueLevelingGeneral(){
		return (getNumberOfActions() < getNumberOfActionsToObtain());
	}
	public void increaseActionCount() {
		numberOfActions += 1;
	}
	
	private int generateActionCount(int offset) {
		double d = dom.nextDouble() * 60 + offset;
		return (int) d;
	}
	
	public void resetActionCount(int offset) {
		setNumberOfActionsToObtain(generateActionCount(offset));
		setNumberOfActions(0);
	}

	private void getLevels() {
		this.levelAttack = Skills.getRealLevel(Skill.ATTACK);
		this.levelStrength = Skills.getRealLevel(Skill.STRENGTH);
		this.levelDefence = Skills.getRealLevel(Skill.DEFENCE);
		this.levelWoodCutting = Skills.getRealLevel(Skill.WOODCUTTING);
	}


	public void setLevelAttackTo(int levelAttackTo) {
		this.levelAttackTo = levelAttackTo;
	}

	public void setLevelStrengthTo(int levelStrengthTo) {
		this.levelStrengthTo = levelStrengthTo;
	}

	public void setLevelDefenceTo(int levelDefenceTo) {
		this.levelDefenceTo = levelDefenceTo;
	}

	public int getLevelAttack() {
		return levelAttack;
	}

	public void setLevelAttack(int levelAttack) {
		this.levelAttack = levelAttack;
	}

	public int getLevelStrength() {
		return levelStrength;
	}

	public void setLevelStrength(int levelStrength) {
		this.levelStrength = levelStrength;
	}

	public int getLevelDefence() {
		return levelDefence;
	}

	public void setLevelDefence(int levelDefence) {
		this.levelDefence = levelDefence;
	}

	public int getLevelAttackTo() {
		return levelAttackTo;
	}

	public int getLevelStrengthTo() {
		return levelStrengthTo;
	}

	public int getLevelDefenceTo() {
		return levelDefenceTo;
	}

	public int getLevelWoodCut() {
		return levelWoodCutting;
	}

	public void setLevelWoodCut(int levelWoodCut) {
		this.levelWoodCutting = levelWoodCut;
	}

	public int getLevelWoodCutting() {
		return levelWoodCutting;
	}


	public void setLevelWoodCutting(int levelWoodCutting) {
		this.levelWoodCutting = levelWoodCutting;
	}


	public int getLevelWoodCuttingTo() {
		return levelWoodCuttingTo;
	}


	public void setLevelWoodCuttingTo(int levelWoodCuttingTo) {
		this.levelWoodCuttingTo = levelWoodCuttingTo;
	}


	public int getNumberOfActionsToObtain() {
		return numberOfActionsToObtain;
	}



	public int getNumberOfActions() {
		return numberOfActions;
	}

	public void setNumberOfActions(int numberOfActions) {
		this.numberOfActions = numberOfActions;
	}

	public void setNumberOfActionsToObtain(int numberOfActionsToObtain) {
		this.numberOfActionsToObtain = numberOfActionsToObtain;
	}

	public void getWoodCuttingAxe(){
		//getLevelWoodCut()
	}

	@Override
	public String toString() {
		return "[CurrentAttack =" + levelAttack + ", levelAttackTo=" + levelAttackTo 
				+ "\nCurrent Strength =" + levelStrength + ", levelStrengthTo =" + levelStrengthTo
				+ "\nCurrent Defence =" + levelDefence + ", levelDefenceTo =" + levelDefenceTo 
				+ "\nCurrent Actions = " + numberOfActions + " : To Obtain = " + numberOfActionsToObtain +"]";
	}
	

	
}


