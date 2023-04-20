package lab2v1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import greske.GDodaj;
import greske.GIndeks;
import greske.GVreme;

public class Sema {
	
	public List<Sadrzaj> sadrzaji;
	private String naziv;
	private Vreme vremePocetka;
	private Vreme vremeKraja;
	
	public Sema(String naziv) throws GVreme {
		this(naziv, new Vreme(8, 0), new Vreme(22, 0));
	}
	
	public Sema(String naziv, Vreme poc, Vreme kraj) {
		this.naziv = naziv;
		vremePocetka = poc;
		vremeKraja = kraj;
		sadrzaji = new ArrayList<Sadrzaj>();
	}
	
	public void dodaj(Sadrzaj s) throws GDodaj {
		Vreme pomeraj = null;
		try {
			pomeraj = new Vreme(0, 15);
		} catch (GVreme e) {}
		if (s.dohvPocetak().compareTo(vremePocetka) == -1) {
			s.postaviVremePocetka(vremePocetka);
		}
		Iterator<Sadrzaj> iter = sadrzaji.iterator();
		while (iter.hasNext()) {
			Sadrzaj tren = iter.next();
			while (s.preklapaSe(tren) != null) {
				s.pomeri(pomeraj);
				if (s.dohvPocetak().saberi(s.dohvTrajanje()).jednako(vremeKraja)) {
					throw new GDodaj();
				}
			}
		}
		sadrzaji.add(s);
		Collections.sort(sadrzaji);
	}
	
	public int dohvatiBrojSadrzaja() {
		return sadrzaji.size();
	}
	
	public Sadrzaj dohvatiSadrzaj(int i) throws GIndeks {
		Iterator<Sadrzaj> iter = sadrzaji.iterator();
		if (i < 0 || i >= dohvatiBrojSadrzaja()) {
			throw new GIndeks();
		}
		Sadrzaj tren = null;
		for (int j = 0; j <= i; j++) {
			tren = iter.next();
		}
		return tren;
	}
	
	public double zauzetost() {
		boolean satnica[] = new boolean[96];
		Iterator<Sadrzaj> iter = sadrzaji.iterator();
		while(iter.hasNext()) {
			Sadrzaj tren = (Sadrzaj) iter.next();
			boolean niz[] = tren.vratiSatnicu();
			int i = vremeKraja.vremeUMin() / 15;
			if (niz[i] == true || niz[i - 1] == true ) {
				while (niz[i] == true) {
					niz[i] = false;
					i--;
				}
			}
			for (int j = vremePocetka.vremeUMin() / 15; j < vremeKraja.vremeUMin() / 15; j++) {
				satnica[j] = satnica[j] || niz[j];
			}
		}
		double br = 0;
		for (int i = 0; i < 96; i++) {
			if (satnica[i]) {
				br++;
			}
		}
		return (double) (br / ((vremeKraja.vremeUMin() - vremePocetka.vremeUMin()) / 15)) * 100;
	}
	
	@Override
	public String toString() {
		String string = new String();
		string = naziv + " : " + vremePocetka + " - " + vremeKraja;
		Iterator<Sadrzaj> iter = sadrzaji.iterator();
		while (iter.hasNext()) {
			Sadrzaj tren = iter.next();
			string += "\n";
			string += tren;
		}
		string += "\n";
		return string;
	}
}