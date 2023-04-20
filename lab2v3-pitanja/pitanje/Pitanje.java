package pitanje;

public class Pitanje implements Cloneable {
	private static int br = 1;

	private int id = br++;
	private String tekst;
	private int poeni;
	private double tezina;
	
	public Pitanje(String tekst, int poeni, double tezina) {
		this.tekst = tekst;
		this.poeni = poeni;
		this.tezina = tezina;
		if (tezina > 10) {
			this.tezina = 10;
		}
		if (tezina < 1) {
			this.tezina = 1;
		}
	}

	public String getTekst() {
		return tekst;
	}

	public int getPoeni() {
		return poeni;
	}

	public double getTezina() {
		return tezina;
	}
	
	@Override
	public Pitanje clone() {
		Pitanje pitanje = new Pitanje(this.tekst, this.poeni, this.tezina);
		pitanje.id = this.id;
		return pitanje;
	}
	
	@Override
	public String toString() {
		return "Pitanje " + id + ": " + tekst;
	}
}
