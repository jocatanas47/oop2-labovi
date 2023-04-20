package katring;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import greske.GNeodgovarajuciObjekat;

public class Prepreka extends Specificnost implements Cloneable {
	
	private double tezina;
	private ArrayList<Vozilo> vozila = new ArrayList<Vozilo>();
	
	public Prepreka(double tezina) {
		this.tezina = tezina;
		if (tezina > 1) {
			this.tezina = 1;
		}
		if (tezina < 0) {
			this.tezina = 0;
		}
	}

	public double getTezina() {
		return tezina;
	}

	@Override
	public void ispoljiEfekat(Object o) throws GNeodgovarajuciObjekat {
		if (!(o instanceof Vozilo)) {
			throw new GNeodgovarajuciObjekat();
		}
		Vozilo voz = (Vozilo) o;
		vozila.add(voz);
		if (voz.getUpravljivost() < tezina) {
			double rand = new Random().nextDouble();
			double d = tezina - voz.getUpravljivost();
			if (d > rand) {
				voz.setTrenBrz(0.0);
			}
		}
	}

	@Override
	public void ponistiEfekat(Object o) throws GNeodgovarajuciObjekat {
		if (!(o instanceof Vozilo)) {
			throw new GNeodgovarajuciObjekat();
		}
		Vozilo voz = (Vozilo) o;
		Iterator<Vozilo> iter = vozila.iterator();
		boolean imaObj = false;
		while(iter.hasNext()) {
			Vozilo tren = iter.next();
			if (tren == voz) {
				vozila.remove(tren);
				imaObj = true;
				break;
			}
		}
		if (!imaObj) {
			throw new GNeodgovarajuciObjekat();
		}
	}
	
	@Override
	public Prepreka clone() {
		return new Prepreka(tezina);
	}
	
	@Override
	public String toString() {
		return "P" + tezina;
	}

}
