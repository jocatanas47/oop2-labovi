package katring;

import greske.GNeodgovarajuciObjekat;

public abstract class Specificnost {

	private static int br = 0;
	private int id = br++;
	
	public int dohvatiID() {
		return id;
	}
	
	public abstract void ispoljiEfekat(Object o) throws GNeodgovarajuciObjekat;
	public abstract void ponistiEfekat(Object o) throws GNeodgovarajuciObjekat;
	
	@Override
	protected Specificnost clone() {
		return null;
	}
}
