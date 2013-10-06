package be.rochus.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import be.rochus.domain.type.AbstractVlasResult;

@Entity
public class VlasResult extends AbstractVlasResult {

	@Id @GeneratedValue
	private Long id;

	private Date date;
	private String name;
	private int nbQuestions;
	private int score;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNbQuestions() {
		return nbQuestions;
	}

	public void setNbQuestions(int nbQuestions) {
		this.nbQuestions = nbQuestions;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

}
