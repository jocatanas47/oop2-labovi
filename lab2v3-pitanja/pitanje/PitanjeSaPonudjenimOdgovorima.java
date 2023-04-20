package pitanje;

public class PitanjeSaPonudjenimOdgovorima extends Pitanje {
	
	String[] odgovori;

	public PitanjeSaPonudjenimOdgovorima(String tekst, int poeni, double tezina, String[] odgovori) {
		super(tekst, poeni, tezina);
		this.odgovori = odgovori;
	}

	@Override
	public String toString() {
		String str = super.toString();
		int i = 1;
		for (String string : odgovori) {
			str += "\n    " + i + ". " + string;
			i++;
		}
		return str;
	}
}
