package be.rochus.domain.type;

public abstract class AbstractVlasResult {

	private static final int PASS_PERCENTAGE = 60;
	
	public abstract int getScore();
	
	public abstract int getNbQuestions();
	
	public boolean isPassed() {
		return getPercentage() >= PASS_PERCENTAGE;
	}

	public int getPercentage() {
		float perc = 100f*getScore()/getNbQuestions();
		return Math.round(perc);
	}
	
}
