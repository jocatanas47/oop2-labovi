package sladoled;

import java.util.ArrayList;
import java.util.List;

public class Sladoled {
	public static class Elem {
		public Ukus ukus;
		public int kolicina;
		public Elem(Ukus ukus, int kolicina) {
			this.ukus = ukus;
			this.kolicina = kolicina;
		}
	}
	private int velicina;
	private List<Elem> ukusi = new ArrayList<>();
	public Sladoled(int velicina) {
		if (velicina <= 0) {
			velicina = 200;
		}
		this.velicina = velicina;
	}
	
	public int getVelicina() {
		return velicina;
	}
	public int ukKolicina() {
		int ukKolicina = 0;
		for (Elem e : ukusi) {
			ukKolicina += e.kolicina;
		}
		return ukKolicina;
	}
	public void dodaj(int kolicina, Ukus ukus) {
		if (kolicina <= 0) {
			return;
		}
		if (kolicina + ukKolicina() > velicina) {
			kolicina = velicina - ukKolicina();
		}
		if (kolicina == 0) {
			return;
		}
		for (Elem e : ukusi) {
			if (e.ukus.equals(ukus)) {
				e.kolicina += kolicina;
				return;
			}
		}
		ukusi.add(new Elem(ukus, kolicina));
	}
	@Override
	public String toString() {
		String str = new String();
		for (Elem e : ukusi) {
			str += e.kolicina + "ml" + e.ukus + " ";
		}
		return str;
	}
}
