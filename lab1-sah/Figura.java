package sah;

public abstract class Figura {

	public Figura(Polje polje) {
		this.polje = polje;
	}
	
	public abstract boolean mogucPomeraj(Polje p);
	
	public abstract String dohvatiOznaku();
	
	@Override
	public String toString() {
		return this.dohvatiOznaku() + polje.toString();
	}
	
	protected Polje polje;
}
