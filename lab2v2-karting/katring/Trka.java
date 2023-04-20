package katring;

import java.util.ArrayList;
import java.util.Iterator;

import greske.GDeonicaStaze;
import greske.GGraniceStaze;
import greske.GNeodgovarajuciObjekat;
import greske.GNepostojeceVozilo;

public class Trka {

	static class Elem {
		public Vozilo voz;
		public double put;
		public int plasman;
		public boolean zavrsio = false;
		public double vremeZavrsetka = 0;
		
		public Elem(Vozilo voz, int pls) {
			this.voz = voz;
			put = 0.0;
			plasman = pls;
		}
	}
	
	private ArrayList<Elem> vozaci = new ArrayList<Elem>();
	private int brVozaca;
	private Staza staza;
	private double vremeSim;
	
	public Trka(Staza staza) {
		this.staza = staza;
		brVozaca = 0;
		vremeSim = 0;
	}
	
	public void dodaj(Vozilo v) {
		if (brVozaca < 8) {
			brVozaca++;
			vozaci.add(new Elem(v, brVozaca));
		}
	}
	
	public int getBrVozaca() {
		return brVozaca;
	}
	
	public void simuliraj(double t) {
		Iterator<Elem> iter = vozaci.iterator();
		while (iter.hasNext()) {
			Elem tren = iter.next();
			if (!tren.zavrsio) {
				double vreme = t;
				boolean prviPut = true;
				while (vreme > 0) {
					double trVreme = 0;
					Deonica trDeo = null;
					try {
						trDeo = staza.dohvatiNaUdaljenosti(tren.put);
					} catch (GGraniceStaze e) {
						e.printStackTrace();
					}
					if (!prviPut) {
						Iterator<Specificnost> iter2 = trDeo.specificnosti.iterator();
						while (iter2.hasNext()) {
							Specificnost tren2 = iter2.next();
							try {
								tren2.ispoljiEfekat(tren.voz);
							} catch (GNeodgovarajuciObjekat e) {
								e.printStackTrace();
							}
						}
					}
					try {
						trVreme = tren.voz.potrebnoVreme(staza.udaljenost(trDeo) + trDeo.getDuzina() - tren.put);
					} catch (GDeonicaStaze e) {
						e.printStackTrace();
					}
					if (trVreme > vreme) {
						tren.put += tren.voz.pomeri(vreme);
						vreme = 0;
					} else {
						tren.put += tren.voz.pomeri(trVreme);
						vreme -= trVreme;
						if (tren.put >= staza.duzina()) {
							tren.put = staza.duzina();
							tren.vremeZavrsetka = vremeSim + t - vreme;
							tren.zavrsio = true;
							break;
						}
						Iterator<Specificnost> iter2 = trDeo.specificnosti.iterator();
						while (iter2.hasNext()) {
							Specificnost tren2 = iter2.next();
							try {
								tren2.ponistiEfekat(tren.voz);
							} catch (GNeodgovarajuciObjekat e) {
								e.printStackTrace();
							}
						}
					}
					prviPut = false;
				}
			}
		}
		Iterator<Elem> iterI = vozaci.iterator();
		while (iterI.hasNext()) {
			Elem trenI = iterI.next();
			Iterator<Elem> iterJ = vozaci.iterator();
			while (iterJ.hasNext()) {
				Elem trenJ = iterJ.next();
				if (trenI.put > trenJ.put && trenI.plasman > trenJ.plasman) {
					int tmp = trenI.plasman;
					trenI.plasman = trenJ.plasman;
					trenJ.plasman = tmp;
				}
				if (trenI.zavrsio && trenJ.zavrsio && trenI.vremeZavrsetka < trenJ.vremeZavrsetka && trenI.plasman > trenJ.plasman) {
					int tmp = trenI.plasman;
					trenI.plasman = trenJ.plasman;
					trenJ.plasman = tmp;
				}
			}
		}
		vremeSim += t;
	}
	
	public int getPlasman(Vozilo voz) throws GNepostojeceVozilo {
		Iterator<Elem> iter = vozaci.iterator();
		while (iter.hasNext()) {
			Elem tren = iter.next();
			if (tren.voz == voz) {
				return tren.plasman;
			}
		}
		throw new GNepostojeceVozilo();
	}
	
	public double getPut(Vozilo voz) throws GNepostojeceVozilo {
		Iterator<Elem> iter = vozaci.iterator();
		while (iter.hasNext()) {
			Elem tren = iter.next();
			if (tren.voz == voz) {
				return tren.put;
			}
		}
		throw new GNepostojeceVozilo();
	}
	
	@Override
	public String toString() {
		String str = new String();
		str = staza.getIme() + "[" + vremeSim + "s]";
		int i = 1;
		while (i <= brVozaca) {
			Iterator<Elem> iter = vozaci.iterator();
			while (iter.hasNext()) {
				Elem tren = iter.next();
				if (tren.plasman == i) {
					if (tren.zavrsio) {
						str += "\n";
						str += i + ". " + tren.voz + " [" + tren.vremeZavrsetka + "s]";
					}
				}
			}
			i++;
		}
		return str;
	}
}