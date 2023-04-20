package telefon;

import greske.GNePostoji;

public class Imenik extends ListaStavki {
	@Override
	public void dodaj(Stavka stavka) {
		if (!(stavka instanceof Kontakt)) {
			return;
		}
		super.dodaj(stavka);
	}
	public String dohvatiIme(Broj broj) throws GNePostoji {
		for (Stavka s : stavke) {
			Kontakt tren = (Kontakt) s;
			if (tren.getBroj().equals(broj)) {
				return tren.getIme();
			}
		}
		throw new GNePostoji();
	}
	public Broj dohvatiBroj(String ime) throws GNePostoji {
		for (Stavka s : stavke) {
			Kontakt tren = (Kontakt) s;
			if (tren.getIme().equals(ime)) {
				return tren.getBroj();
			}
		}
		throw new GNePostoji();
	}
}
