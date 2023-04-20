package sah;

public class Skakac extends Figura {

	public Skakac(Polje polje) {
		super(polje);
	}

	@Override
	public boolean mogucPomeraj(Polje p) {
		return this.polje.rastojanje(p) == 3;
	}

	@Override
	public String dohvatiOznaku() {
		return "S";
	}

}
