package BotAI;

public enum FatigueStates {
	ENGAGED("Engaged", 1000),
	RELAXED("Relaxed", 6500),
	TIRED("Tired", 180000),
	DEFAULT("DEFAULT", 5000);
			
	FatigueStates(String state, int level) {
		fatigueState = state;
		fatigueValue = level;
	}
	
	private String fatigueState;
	private int fatigueValue;
	
	
	public String getFatigueState() {
		return fatigueState;
	}
	public void setFatigueState(String fatigue) {
		this.fatigueState = fatigue;
	}
	public int getFatigueValue() {
		return fatigueValue;
	}
	public void setFatigueValue(int fatigueLevel) {
		this.fatigueValue = fatigueLevel;
	}
	public boolean isFatigueState(FatigueStates fs) {
		if(this.fatigueState == fs.getFatigueState()) {
			return true;
		}
		return false;
	}
}
