package pitanje;

import java.util.ArrayList;

import greske.GNemaPitanja;

public class ZbirkaPitanja {

	public class IteratorPitanja {
		private int i = 0;
		
		public boolean postoji() {
			return i < pitanja.size();
		}
		
		public Pitanje dohvati() throws GNemaPitanja {
			if (!postoji()) {
				throw new GNemaPitanja();
			}
			return pitanja.get(i);
		}
		
		public void sledece() {
			i++;
		}
	}
	
	private ArrayList<Pitanje> pitanja = new ArrayList<Pitanje>();
	
	public void dodaj(Pitanje p) {
		pitanja.add(p);
	}
	
	public Pitanje dohvati(int i) throws GNemaPitanja {
		if (i >= pitanja.size() || i < 0) {
			throw new GNemaPitanja();
		}
		return pitanja.get(i);
	}
	
	public int brojPitanja() {
		return pitanja.size();
	}
	
	public IteratorPitanja iterator() {
		return new IteratorPitanja();
	}
	
	@Override
	public String toString() {
		String str = new String();
		IteratorPitanja iter = iterator();
		while(iter.postoji()) {
			try {
				str += (iter.dohvati() + "\n");
			} catch (GNemaPitanja e) {}
			iter.sledece();
		}
		return str;
	}
}
