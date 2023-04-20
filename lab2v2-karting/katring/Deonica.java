package katring;

import java.util.ArrayList;
import java.util.Iterator;

public class Deonica implements Cloneable {

	private double duzina;
	public ArrayList<Specificnost> specificnosti = new ArrayList<Specificnost>();
	
	public Deonica(double dzn) {
		duzina = dzn;
	}
	
	public double getDuzina() {
		return duzina;
	}

	public void dodaj(Specificnost s) {
		specificnosti.add(s);
	}
	
	public void ukloni(int id) {
		Iterator<Specificnost> iter = specificnosti.iterator();
		while(iter.hasNext()) {
			Specificnost tren = iter.next();
			if (tren.dohvatiID() == id) {
				specificnosti.remove(tren);
				break;
			}
		}
	}
	
	public Specificnost dohvati(int i) {
		Iterator<Specificnost> iter = specificnosti.iterator();
		int j = 0;
		while(iter.hasNext()) {
			Specificnost tren = iter.next();
			if (i == j) {
				return tren;
			}
			j++;
		}
		return null;
	}
	
	public int dohvatiBrojSpecificnosti() {
		return specificnosti.size();
	}
	
	public int brojSpecificnosti() {
		return specificnosti.size();
	}
	
	@Override
	public Deonica clone() {
		Deonica deo = new Deonica(duzina);
		Iterator<Specificnost> iter = specificnosti.iterator();
		while(iter.hasNext()) {
			Specificnost tren = iter.next();
			deo.dodaj(tren.clone());
		}
		return deo;
	}
	
	@Override
	public String toString() {
		String string = new String();
		string = "deonica(" + duzina + "m)";
		Iterator<Specificnost> iter = specificnosti.iterator();
		while(iter.hasNext()) {
			Specificnost tren = iter.next();
			string += tren;
		}
		return string;
	}
}
