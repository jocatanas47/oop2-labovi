package katring;

import java.util.ArrayList;
import java.util.Iterator;

import greske.GDeonicaStaze;
import greske.GGraniceStaze;
import greske.GPrvaDeonica;

public class Staza {

	private String ime;
	private ArrayList<Deonica> deonice = new ArrayList<Deonica>();
	
	public Staza(String ime) {
		this.ime = ime;
	}

	public String getIme() {
		return ime;
	}
	
	public void dodaj(Deonica d) throws GPrvaDeonica {
		if (deonice.size() == 0 && d.brojSpecificnosti() != 0) {
			throw new GPrvaDeonica();
		}
		deonice.add(d);
	}
	
	public double duzina() {
		double duzina = 0;
		Iterator<Deonica> iter = deonice.iterator();
		while (iter.hasNext()) {
			Deonica tren = iter.next();
			duzina += tren.getDuzina();
		}
		return duzina;
	}
	
	public Deonica dohvatiNaUdaljenosti(double m) throws GGraniceStaze {
		if (m < 0 || m > duzina()) {
			throw new GGraniceStaze();
		}
		double trDuz = 0;
		Iterator<Deonica> iter = deonice.iterator();
		while (iter.hasNext()) {
			Deonica tren = iter.next();
			if (trDuz <= m && (trDuz + tren.getDuzina()) > m) {
				return tren;
			}
			trDuz += tren.getDuzina();
		}
		return null;
	}
	
	public double udaljenost(Deonica d) throws GDeonicaStaze {
		double trDuz = 0;
		Iterator<Deonica> iter = deonice.iterator();
		while (iter.hasNext()) {
			Deonica tren = iter.next();
			if (tren == d) {
				return trDuz;
			}
			trDuz += tren.getDuzina();
		}
		throw new GDeonicaStaze();
	}
	
	@Override
	public String toString() {
		String str = new String();
		str = ime + "(" + duzina() + "m)";
		Iterator<Deonica> iter = deonice.iterator();
		while (iter.hasNext()) {
			Deonica tren = iter.next();
			str += "\n";
			str += tren;
		}
		return str;
	}
}
