package be.rochus.domain.type;

import be.rochus.domain.VlasOption;
import be.rochus.domain.VlasQuestion;

public class VlasAnswer {

	private VlasQuestion question;
	private VlasOption selectedOption;

	public boolean isCorrect() {
		return question.getCorrectAnswer().equals(selectedOption);
	}
	
	public VlasQuestion getQuestion() {
		return question;
	}

	public void setQuestion(VlasQuestion question) {
		this.question = question;
	}

	public VlasOption getSelectedOption() {
		return selectedOption;
	}

	public void setSelectedOption(VlasOption selectedOption) {
		this.selectedOption = selectedOption;
	}
	
}
