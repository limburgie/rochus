package be.rochus.service.impl;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import be.rochus.domain.VlasOption;
import be.rochus.domain.VlasQuestion;
import be.rochus.domain.VlasResult;
import be.rochus.domain.type.VlasExam;
import be.rochus.repository.VlasOptionRepository;
import be.rochus.repository.VlasQuestionRepository;
import be.rochus.repository.VlasResultRepository;
import be.rochus.service.VlasService;

@Named @Transactional(readOnly=true)
public class VlasServiceImpl implements VlasService {

	@Inject private VlasResultRepository resultRepo;
	@Inject private VlasQuestionRepository questionRepo;
	@Inject private VlasOptionRepository optionRepo;
	
	public VlasExam generateExam(int nbQuestions) {
		List<VlasQuestion> questions = questionRepo.findAll();
		Collections.shuffle(questions);
		List<VlasQuestion> selectedQuestions = questions.subList(0, nbQuestions);
		
		VlasExam result = new VlasExam();
		for(VlasQuestion question: selectedQuestions) {
			result.addQuestion(question);
		}
		return result;
	}
	
	public VlasOption getOption(long id) {
		return optionRepo.findOne(id);
	}

	public int getNbQuestions() {
		return (int) questionRepo.count();
	}
	
	@Transactional
	public void saveResult(VlasResult result) {
		resultRepo.save(result);
	}

	public List<VlasResult> getResults() {
		return resultRepo.getResults();
	}

}
