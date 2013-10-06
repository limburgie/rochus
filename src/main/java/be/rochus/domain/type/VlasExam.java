package be.rochus.domain.type;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import be.rochus.domain.VlasQuestion;

public class VlasExam extends AbstractVlasResult {

	private List<VlasAnswer> answers = new ArrayList<VlasAnswer>();
	private int nbQuestions;

	public int getScore() {
		int result = 0;
		for(VlasAnswer answer: answers) {
			if(answer.isCorrect()) {
				result++;
			}
		}
		return result;
	}
	
	public List<VlasAnswer> getAnswers() {
		return answers;
	}
	
	public int getNbQuestions() {
		return nbQuestions;
	}
	
	public void addQuestion(VlasQuestion question) {
		VlasAnswer answer = new VlasAnswer();
		answer.setQuestion(question);
		Collections.shuffle(question.getOptions());
		answers.add(answer);
		nbQuestions++;
	}

}
