package be.rochus.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class VlasQuestion {

	@Id @GeneratedValue
	private Long id;

	private String label;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "question")
	private List<VlasOption> options;

	@OneToOne
	private VlasOption correctAnswer;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public List<VlasOption> getOptions() {
		return options;
	}

	public void setOptions(List<VlasOption> options) {
		this.options = options;
	}

	public VlasOption getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(VlasOption correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VlasQuestion other = (VlasQuestion) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
