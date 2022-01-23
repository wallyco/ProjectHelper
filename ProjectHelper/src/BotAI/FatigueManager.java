package BotAI;

import java.util.Random;

public class FatigueManager { 
	
	private static FatigueManager fm = null;
	
	FatigueStates fatigueState;
	private static final int  ENERGY_LEVEL_ONE = 70;
	private static final int  ENERGY_LEVEL_TWO = 40;
	private double energy = 100.00;
	Random dom = new Random();
	
	 
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
	
	public void checkEnergy() {
		if(getEnergy() <= ENERGY_LEVEL_ONE && !fatigueState.isFatigueState(FatigueStates.RELAXED)) {
			setFatigueState(FatigueStates.RELAXED);
		}
		if(getEnergy() <= ENERGY_LEVEL_TWO && !fatigueState.isFatigueState(FatigueStates.TIRED)) {
			setFatigueState(FatigueStates.TIRED);
		}
		
	}
	
	
	private int generateRandomInt(int offset) {
		return (int) (Math.random() * offset);
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
