package sah;

public class Polje {

	public Polje(char kolona, int red) {
		this.kolona = kolona;
		if (red > 0 && red < 9) {
			this.red = red;
		} else {
			this.red = 1;
		}
	}
	
	public boolean istiRed(Polje p) {
		return red == p.red;
	}
	
	public boolean istaKolona(Polje p) {
		return kolona == p.kolona;
	}
	
	public int rastojanje(Polje p) {
		return Math.abs(red - p.red) + Math.abs(((int)kolona) - ((int)p.kolona));
	}
	
	@Override
	public String toString() {
		return kolona + ((Integer)red).toString();
	}
	
	private char kolona;
	private int red;
}
