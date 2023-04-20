package sah;

public class Top extends Figura {

	public Top(Polje polje) {
		super(polje);
	}

	@Override
	public boolean mogucPomeraj(Polje p) {
		return this.polje.istiRed(p) || this.polje.istaKolona(p);
	}

	@Override
	public String dohvatiOznaku() {
		return "T";
	}

}
