package lab2v1;

public class Ponavljajuci extends Sadrzaj {

	public Vreme perioda;
	
	public Ponavljajuci(String naziv, Vreme vremeTrajanja, Vreme perioda) {
		super(naziv, vremeTrajanja);
		this.perioda = perioda;
	}
	
	@Override
	public char dohvatiVrstu() {
		return 'P';
	}
	
	@Override
	public String toString() {
		return super.toString() + " T" + perioda;
	}

	@Override
	public boolean[] vratiSatnicu() {
		boolean niz[] = new boolean[96];
		Vreme v = dohvPocetak().clone();
		while (v.saberi(dohvTrajanje()).vremeUMin() < 1440) {
			int j = v.saberi(dohvTrajanje()).vremeUMin() / 15;
			for (int i = v.vremeUMin() / 15; i < j; i++) {
				niz[i] = true;
			}
			v = v.saberi(perioda);
			if (v == null) {
				break;
			}
		}
		return niz;
	}
}