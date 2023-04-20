package lab2v1;

import greske.GVreme;

public abstract class Sadrzaj implements Comparable<Sadrzaj> {
	
	private String naziv;
	private Vreme vremeTrajanja;
	private Vreme vremePocetka;
	
	{
		vremePocetka = new Vreme();
	}
	
	public Sadrzaj(String naziv, Vreme vremeTrajanja) {
		this.naziv = naziv;
		this.vremeTrajanja = vremeTrajanja;
	}
	
	public Vreme preklapaSe(Sadrzaj s) {
		boolean s1[] = vratiSatnicu();
		boolean s2[] = s.vratiSatnicu();
		for (int i = 0; i < 96; i++) {
			if (s1[i] == true && s2[i] == true) {
				int sati = i / 4;
				int minuti = (i % 4) * 15;
				try {
					Vreme v = new Vreme(sati, minuti);
					return v;
				} catch (GVreme e) {
					return null;
				}
			}
		}
		return null;
	}
	
	public String dohvNaziv() {
		return naziv;
	}
	
	public Vreme dohvTrajanje() {
		return vremeTrajanja;
	}
	
	public Vreme dohvPocetak() {
		return vremePocetka;
	}
	
	public void postaviVremePocetka(Vreme v) {
		vremePocetka = v;
	}
	
	public void pomeri(Vreme v) {
		vremePocetka = vremePocetka.saberi(v);
	}
	
	public abstract char dohvatiVrstu();
	
	@Override
	public String toString() {
		return dohvatiVrstu() + ", " + naziv + " | " 
				+ vremePocetka + " - " 
				+ vremePocetka.saberi(vremeTrajanja);
	}
	
	@Override
	public int compareTo(Sadrzaj o) {
		return this.vremePocetka.compareTo(o.vremePocetka);
	}
	
	public boolean[] vratiSatnicu() {
		boolean niz[] = new boolean[96];
		int j = vremePocetka.saberi(vremeTrajanja).vremeUMin() / 15;
		for (int i = vremePocetka.vremeUMin() / 15; i < j; i += 1) {
			niz[i] = true;
		}
		return niz;
	}
}