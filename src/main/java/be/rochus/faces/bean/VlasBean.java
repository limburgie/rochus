package be.rochus.faces.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import be.rochus.domain.VlasResult;
import be.rochus.domain.type.VlasAnswer;
import be.rochus.domain.type.VlasExam;
import be.rochus.faces.FacesUtil;
import be.rochus.service.VlasService;

@Named
@Scope("session")
public class VlasBean implements Serializable {

	@Inject private VlasService vlasService;
	@Inject private FacesUtil facesUtil;

	private String name;
	private boolean started;
	private int currentIndex;
	private VlasExam exam;
	private int nbQuestions = 20;
	private Integer maxNbQuestions;
	
	private List<VlasResult> results;

	public void reset() {
		retrieveResults();
		exam = null;
		currentIndex = 0;
		started = false;
	}
	
	public void save() {
		VlasResult result = new VlasResult();
		result.setDate(new Date());
		result.setName(name);
		result.setNbQuestions(nbQuestions);
		result.setScore(exam.getScore());
		vlasService.saveResult(result);
		facesUtil.info("Resultaat was opgeslagen.");
	}
	
	public void start() {
		exam = vlasService.generateExam(nbQuestions);
		started = true;
	}
	
	public void next() {
		currentIndex++;
	}
	
	public int getQuestionNumber() {
		return currentIndex + 1;
	}
	
	public boolean isLastQuestion() {
		return getQuestionNumber() == exam.getAnswers().size();
	}

	public VlasExam getExam() {
		return exam;
	}

	public void setExam(VlasExam exam) {
		this.exam = exam;
	}

	public VlasAnswer getCurrent() {
		return exam.getAnswers().get(currentIndex);
	}

	public int getNbQuestions() {
		return nbQuestions;
	}

	public void setNbQuestions(int nbQuestions) {
		this.nbQuestions = nbQuestions;
	}
	
	public boolean isStarted() {
		return started;
	}
	
	public int getMaxNbQuestions() {
		if(maxNbQuestions == null) {
			maxNbQuestions = vlasService.getNbQuestions();
		}
		return maxNbQuestions;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<VlasResult> getResults() {
		if(results == null) {
			retrieveResults();
		}
		return results;
	}

	private void retrieveResults() {
		results = vlasService.getResults();
	}

}
