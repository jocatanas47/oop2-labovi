package sladoled;

import java.awt.Color;

public class Ukus {
	private String naziv;
	private Color boja;
	public Ukus(String naziv, Color boja) {
		this.naziv = naziv;
		this.boja = boja;
	}
	public String getNaziv() {
		return naziv;
	}
	public Color getBoja() {
		return boja;
	}
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Ukus)) {
			return false;
		}
		Ukus ukus = (Ukus) obj;
		return this.naziv.equals(ukus.naziv);
	}
	@Override
	public String toString() {
		return "[" + naziv + "]";
	}
}
