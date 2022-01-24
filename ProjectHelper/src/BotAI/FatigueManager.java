package BotAI;

import java.util.Random;

public class FatigueManager { 
	
	private static FatigueManager fm = null;
	
	FatigueStates fatigueState;
	private static final int  ENERGY_LEVEL_RELAXED = 70;
	private static final int  ENERGY_LEVEL_TIRED = 40;
	private double energy = 100.00;
	private double ResetBreakDouble = 10000000;
	//SAVE 1000000 is 10 min
	private Random dom = new Random();
	
	 
	private FatigueManager(FatigueStates fatigue) {
		this.fatigueState = fatigue;
	}
	
	public static FatigueManager getInstance() {
		if(fm == null) {
			fm = new FatigueManager(FatigueStates.DEFAULT);
		}
		return fm;
	}
	
	public int nextInt() {
		return generateRandomInt(fatigueState.getFatigueValue());
	}
	
	public void consumeEnergy(double consume) {
		setEnergy(getEnergy() - generateRandomDouble(consume));
	}
	
	public void rechargeEnergy(double add) {
		setEnergy(getEnergy() + add);
	}
	
	public void checkEnergyLevel() {
		if(getEnergy() <= ENERGY_LEVEL_RELAXED && !fatigueState.isFatigueState(FatigueStates.RELAXED)) {
			setFatigueState(FatigueStates.RELAXED);
		}
		if(getEnergy() <= ENERGY_LEVEL_TIRED && !fatigueState.isFatigueState(FatigueStates.TIRED)) {
			setFatigueState(FatigueStates.TIRED);
		}
		//TODO BUG TEST THIS
		if(getEnergy() < 0) {
			BotMain.Main.ai.getTaskManager().clearAddSave(new BotDataJPX.Break(((long) generateResetBreakDouble()), 100));
			setEnergy(.5);
		}
	}
	
	private int generateRandomInt(int offset) {
		return (int) (Math.random() * offset);
	}
	
	public double generateResetBreakDouble() {
		double d = dom.nextDouble() * this.ResetBreakDouble;
		if(d > 3000000)
			return d;
		else {
			return d + 3000000;
		}
	}

	public double generateRandomDouble(double consumeEnergy) {
		double temp = dom.nextDouble(); 
		if(temp - consumeEnergy > 0)
			return temp - consumeEnergy;
		return 0.01;
	}
	public void setFatigueState(FatigueStates fatigue) {
		this.fatigueState = fatigue;
	}
	
	public FatigueStates getFatigueState() {
		return fatigueState;
	}

	public void setEnergy(double energy) {
		this.energy = energy;
	}
	
	public double getEnergy() {
		return this.energy;
	}
	
	

}
