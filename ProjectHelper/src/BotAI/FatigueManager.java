package BotAI;

import java.util.Random;

public class FatigueManager { 
	
	private static FatigueManager fm = null;
	
	FatigueStates fatigueState;
	private static int  ENERGY_LEVEL_RELAXED = 70;
	private static int  ENERGY_LEVEL_TIRED = 40;
	private double energy = 100.00;
	private double resetBreakDouble = 10000000;
	private double shortBreakDouble = 1000000;
	private int shortBreakRecharge = 0;
	public boolean acceptBreak = true;
	//SAVE 1000000 is ~10-30 mins
	//SAVE 10000000 is ~1-3 hours
	private Random dom = new Random();
	
	 
	private FatigueManager(FatigueStates fatigue) {
		this.fatigueState = fatigue;
		generateEnergyLevelStateThreshholds();
	}
	
	 //DEBUG
	public static int getENERGY_LEVEL_RELAXED() {
		return ENERGY_LEVEL_RELAXED;
	}

	public static void setENERGY_LEVEL_RELAXED(int eNERGY_LEVEL_RELAXED) {
		ENERGY_LEVEL_RELAXED = eNERGY_LEVEL_RELAXED;
	}

	public static int getENERGY_LEVEL_TIRED() {
		return ENERGY_LEVEL_TIRED;
	}

	public static void setENERGY_LEVEL_TIRED(int eNERGY_LEVEL_TIRED) {
		ENERGY_LEVEL_TIRED = eNERGY_LEVEL_TIRED;
	}
	/////////////////////////////////////////

	public static FatigueManager getInstance() {
		if(fm == null) {
			fm = new FatigueManager(FatigueStates.DEFAULT);
			
		}
		return fm;
	}
	
	public int nextInt() {
		return generateRandomInputInt(fatigueState.getFatigueValue());
	}
	
	public void consumeEnergy(double consume) {
		setEnergy(getEnergy() - generateRandomConsumeDouble(consume));
	}
	
	public void rechargeEnergy(double add) {
		setEnergy(getEnergy() + add);
	}
	
	public void checkEnergyLevel() {
		if(getEnergy() <= ENERGY_LEVEL_RELAXED && !fatigueState.isFatigueState(FatigueStates.RELAXED)) {
			setFatigueState(FatigueStates.RELAXED);
		}
		else if(getEnergy() <= ENERGY_LEVEL_TIRED && !fatigueState.isFatigueState(FatigueStates.TIRED)) {
			setFatigueState(FatigueStates.TIRED);
		}
		else {
			if(getEnergy() > ENERGY_LEVEL_RELAXED && !fatigueState.isFatigueState(FatigueStates.ENGAGED)) {
				setFatigueState(FatigueStates.ENGAGED);
			}
		}
		if(getEnergy() < 0) {
			BotMain.Main.ai.getTaskManager().insertAtHead(new BotDataJPX.Break(((long) generateResetBreakDouble()), 100));
			setEnergy(.5);
			generateEnergyLevelStateThreshholds();
		}
	}
	
	private int generateRandomInputInt(int offset) {
		return (int) (Math.random() * offset);
	}
	
	private double generateResetBreakDouble() {
		acceptBreak = true; //here to be called after RESET
		double d = dom.nextDouble() * this.resetBreakDouble;
		if(d > 3000000)
			return d;
		else {
			return d + 3000000;
		}
	}
	
	public double generateBreakDouble() {
		double d = dom.nextDouble() * this.shortBreakDouble;
		double divided = (int) d / 10000;
		if(divided / 2 - 20 > 0)
			divided = divided / 2 - 20;
		else
			divided /= 2;
		this.shortBreakRecharge = (int) divided;
		return d;
	}

	private double generateRandomConsumeDouble(double consumeEnergy) {
		double temp = dom.nextDouble(); 
		if(temp - consumeEnergy > 0)
			return temp - consumeEnergy;
		return 0.01;
	}
	
	private void generateEnergyLevelStateThreshholds() {
		ENERGY_LEVEL_RELAXED = generateRandomInputInt(80) + 20;
		ENERGY_LEVEL_TIRED = generateRandomInputInt(40) + 10;
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
