package katring;

import java.util.ArrayList;
import java.util.Iterator;

import greske.GNeodgovarajuciObjekat;

public class Krivina extends Specificnost implements Cloneable {
	
	static class Elem {
		public Vozilo vzl;
		public double brz;
		
		public Elem(Vozilo voz, double brzn) {
			vzl = voz;
			brz = brzn;
		}
	}
	
	private double maksBrz;
	private ArrayList<Elem> vozila = new ArrayList<Elem>();
	
	public Krivina(double mBr) {
		maksBrz = mBr;
	}
	
	public double getMaksBrz() {
		return maksBrz;
	}

	@Override
	public void ispoljiEfekat(Object o) throws GNeodgovarajuciObjekat {
		if (!(o instanceof Vozilo)) {
			throw new GNeodgovarajuciObjekat();
		}
		Vozilo voz = (Vozilo) o;
		vozila.add(new Elem(voz, voz.getMaksBrz()));
		double novaBrz = maksBrz * voz.getUpravljivost();
		if (novaBrz < voz.getMaksBrz()) {
			voz.setMaksBrz(novaBrz);
		}
	}

	@Override
	public void ponistiEfekat(Object o) throws GNeodgovarajuciObjekat {
		if (!(o instanceof Vozilo)) {
			throw new GNeodgovarajuciObjekat();
		}
		Vozilo voz = (Vozilo) o;
		Iterator<Elem> iter = vozila.iterator();
		boolean imaObj = false;
		while(iter.hasNext()) {
			Elem tren = iter.next();
			if (tren.vzl == voz) {
				voz.setMaksBrz(tren.brz);
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
	public Krivina clone() {
		return new Krivina(this.maksBrz);
	}
	
	@Override
	public String toString() {
		return "K" + maksBrz;
	}

}