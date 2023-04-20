package lab2v1;

import greske.*;

public class Vreme implements Cloneable, Comparable<Vreme> {
	
	private int sati;
	private int minuti;
	
	public Vreme() {
		sati = 0;
		minuti = 0;
	}
	
	public Vreme(int sati, int minuti) throws GVreme {
		if (sati < 0 || sati > 23 
				|| minuti < 0 || minuti > 59 
				|| (minuti % 15 != 0)) {
			throw new GVreme();
		}
		this.sati = sati;
		this.minuti = minuti;
	}
	
	public boolean jednako(Vreme v) {
		return sati == v.sati && minuti == v.minuti;
	}

	public Vreme saberi(Vreme v) {
		Vreme tmp;
		try {
			int sati = (vremeUMin() + v.vremeUMin()) / 60;
			int minuti = (vremeUMin() + v.vremeUMin()) % 60;
			tmp = new Vreme(sati, minuti);
		} catch (GVreme e) {
			return null;
		}
		return tmp;
	}
	
	public int vremeUMin() {
		return 60 * sati + minuti;
	}
	
	@Override
	public String toString() {
		return "(" + ((Integer) sati).toString()
				+ ":" + ((Integer) minuti).toString() + ")";
	}
	
	@Override
	protected Vreme clone() {
		try {
			return new Vreme(sati, minuti);
		} catch (GVreme e) {
			return null;
		}
	}

	@Override
	public int compareTo(Vreme v) {
		if (vremeUMin() < v.vremeUMin()) {
			return -1;
		}
		if (this.jednako(v)) {
			return 0;
		}
		return 1;
	}

}