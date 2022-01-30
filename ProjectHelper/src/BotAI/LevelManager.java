package BotAI;

import org.dreambot.api.methods.combat.Combat;
import org.dreambot.api.methods.combat.CombatStyle;
import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.methods.skills.Skills;
import org.dreambot.api.methods.tabs.Tab;
import org.dreambot.api.methods.tabs.Tabs;


public class LevelManager {
	private int levelAttack = Skills.getRealLevel(Skill.ATTACK);
	private int levelStrength = Skills.getRealLevel(Skill.STRENGTH);
	private int levelDefence = Skills.getRealLevel(Skill.DEFENCE);
	private int levelWoodCutting = Skills.getRealLevel(Skill.WOODCUTTING);
	private int levelAttackTo = 0;
	private int levelStrengthTo = 0;
	private int levelDefenceTo = 0;
	private int levelWoodCuttingTo = 0;
	private int numberOfKillsToObtain = 0;
	private int numberOfKills = 0;
	
	public LevelManager() {}
	
	public LevelManager(int numberOfKillsToObtain) {
		this.numberOfKillsToObtain = numberOfKillsToObtain;
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
		if(continueLevelingCombat() ||
			getNumberOfKills() < getNumberOfKillsToObtain()) {
			return true;
		}
		return false;
	}
	
	public boolean continueLevelingWoodcutting() {
		getLevels();
		if(levelWoodCutting < levelWoodCuttingTo
				&& levelWoodCutting != 0) {
				return true;
		}
		
		return false;
	}
	
	//Break this apart into separate methods
	
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


	public int getNumberOfKillsToObtain() {
		return numberOfKillsToObtain;
	}


	public void setNumberOfKillsToObtain(int numberOfKillsToObtain) {
		this.numberOfKillsToObtain = numberOfKillsToObtain;
	}


	public int getNumberOfKills() {
		return numberOfKills;
	}


	public void setNumberOfKills(int numberOfKills) {
		this.numberOfKills = numberOfKills;
	}


	@Override
	public String toString() {
		return "[CurrentAttack =" + levelAttack + ", levelAttackTo=" + levelAttackTo 
				+ "\nCurrent Strength =" + levelStrength + ", levelStrengthTo =" + levelStrengthTo
				+ "\nCurrent Defence =" + levelDefence + ", levelDefenceTo =" + levelDefenceTo + "]";
	}
	
}


