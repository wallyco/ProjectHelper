package BotAI;

import java.util.Random;

import org.dreambot.api.methods.MethodProvider;

import BotMain.Main;

//TODO MAJOR BUG -- If a break gets reset mid run acceptBreak stays false
public class FatigueManager { 
	
	private static FatigueManager fm = null;
	
	FatigueStates fatigueState;
	private static int  ENERGY_LEVEL_RELAXED = 70;
	private static int  ENERGY_LEVEL_TIRED = 40;
	private double energy = 100.00;
	private double resetBreakDouble = 10000000;   //10000000
	private double shortBreakDouble = 1000000; //1000000
	private int generateResetBreakInt = 3000000;
	public int shortBreakRecharge = 0;
	public boolean acceptBreak = true;
	private int loopCounter = 0;
	//SAVE 1000000 is ~10-30 mins
	//SAVE 10000000 is ~1-3 hours
	private Random dom = new Random();
	
	 
	private FatigueManager(FatigueStates fatigue) {
		this.fatigueState = fatigue;
		generateEnergyLevelStateThresholds();
	}
	
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
		//TODO Rough fix for the reset bug
		if(Main.ai.getTaskManager().isEmpty()) {
			this.acceptBreak = true;
		}
		
		if(shouldSwitchInputTiming()  &&
				getEnergy() < 50) {
			setFatigueState(FatigueStates.TIRED);
			generateBreakDouble();
			setEnergy(getEnergy() + shortBreakRecharge);
		}else if(!fatigueState.isFatigueState(FatigueStates.ENGAGED)){
			setFatigueState(FatigueStates.ENGAGED);

		}
		
	}
		//TODO Maybe to easy to detect
//		if(getEnergy() <= ENERGY_LEVEL_RELAXED 
//				&& getEnergy() > ENERGY_LEVEL_TIRED 
//				&&!fatigueState.isFatigueState(FatigueStates.RELAXED)) {
//			setFatigueState(FatigueStates.RELAXED);
//		}
//		else if(getEnergy() <= ENERGY_LEVEL_TIRED && !fatigueState.isFatigueState(FatigueStates.TIRED)) {
//			setFatigueState(FatigueStates.TIRED);
//		}
//		else {
//			if(getEnergy() > ENERGY_LEVEL_RELAXED && !fatigueState.isFatigueState(FatigueStates.ENGAGED)) {
//				setFatigueState(FatigueStates.ENGAGED);
//			}
//		}
//		
//		if(getEnergy() <= 0 && loopCounter > 2 && isAcceptBreak()) {
//			BotMain.Main.ai.getTaskManager().insertAtHeadCopy(new BotDataJPX.Break(((long) generateResetBreakDouble()), 100));//CHANGE TO 100
//			setEnergy(.5);
//			generateEnergyLevelStateThresholds();
//			loopCounter = 0;
//		}else if(getEnergy() <= 0 && isAcceptBreak()) {
//			BotMain.Main.ai.getTaskManager().insertAtHeadCopy(new BotDataJPX.Break(((long) generateBreakDouble()), shortBreakRecharge)); //CHANGE TO 100
//			generateEnergyLevelStateThresholds();
//			loopCounter++;
//		}
//	}
	
	public boolean shouldSwitchInputTiming() {
		double d = dom.nextDouble();
		if(d > .93) {
			return true;
		}
		return false;
	}
	
	private int generateRandomInputInt(int offset) {
		return (int) (Math.random() * offset);
	}
	
	private double generateResetBreakDouble() {
		double d = dom.nextDouble() * this.resetBreakDouble;
		if(d > generateResetBreakInt)
			return d;
		else {
			return d + generateResetBreakInt;
		}
	}
	
	public double generateBreakDouble() {
		double d = dom.nextDouble() * this.shortBreakDouble;
		int divided = generateRandomInputInt(100);
		this.shortBreakRecharge = (int) divided;
		return d;
	}

	public double generateRandomConsumeDouble(double consumeEnergy) {
		double temp = dom.nextDouble(); 
//		if(temp - consumeEnergy > 0)
//			return temp - consumeEnergy;
//		return 0.01;
		return temp * consumeEnergy;
	}
	
	private void generateEnergyLevelStateThresholds() {
		ENERGY_LEVEL_RELAXED = generateRandomInputInt(80) + 20;
		ENERGY_LEVEL_TIRED = generateRandomInputInt(55) + 20;
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
	
	 public boolean isAcceptBreak() {
		return acceptBreak;
	}

	public void setAcceptBreak(boolean acceptBreak) {
		this.acceptBreak = acceptBreak;
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
	
	

}
