package telefon;

public class Kontakt extends Stavka {
	private Broj broj;
	private String ime;
	public Kontakt(String ime, Broj broj) {
		super(ime, broj.toString());
		this.broj = broj;
		this.ime = ime;
	}
	public Broj getBroj() {
		return broj;
	}
	public String getIme() {
		return ime;
	}
}
