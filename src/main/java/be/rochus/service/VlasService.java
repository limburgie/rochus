package be.rochus.service;

import java.util.List;

import be.rochus.domain.VlasOption;
import be.rochus.domain.VlasResult;
import be.rochus.domain.type.VlasExam;

public interface VlasService {

	VlasExam generateExam(int nbQuestions);
	
	VlasOption getOption(long id);
	
	int getNbQuestions();
	
	void saveResult(VlasResult result);
	
	List<VlasResult> getResults();
	
}
