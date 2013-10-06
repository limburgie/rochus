package be.rochus.domain;

import be.rochus.domain.type.Ploeg;

public class Resultaat {

	private Long id;
	private Schutter schutter;
	private Ploeg ploeg;
	private int plaats;

	private int raak30mm;
	private boolean raak16mm;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Schutter getSchutter() {
		return schutter;
	}

	public void setSchutter(Schutter schutter) {
		this.schutter = schutter;
	}

	public Ploeg getPloeg() {
		return ploeg;
	}

	public void setPloeg(Ploeg ploeg) {
		this.ploeg = ploeg;
	}

	public int getPlaats() {
		return plaats;
	}

	public void setPlaats(int plaats) {
		this.plaats = plaats;
	}

	public int getRaak30mm() {
		return raak30mm;
	}

	public void setRaak30mm(int raak30mm) {
		this.raak30mm = raak30mm;
	}

	public boolean isRaak16mm() {
		return raak16mm;
	}

	public void setRaak16mm(boolean raak16mm) {
		this.raak16mm = raak16mm;
	}

}
