package telefon;

import greske.GLosaNiska;

public class Broj {
	private String kod;
	private String pozBr;
	private String brPret;
	public Broj(String kod, String pozBr, String brPret) {
		this.kod = kod;
		this.pozBr = pozBr;
		this.brPret = brPret;
	}
	public Broj(String str) throws GLosaNiska {
		char[] niska = str.toCharArray();
		if (niska.length < 7) {
			throw new GLosaNiska();
		}
		if (niska[0] != '+') {
			throw new GLosaNiska();
		}
		kod = new String("");
		for (int i = 1; i < 4; i++) {
			char c = niska[i];
			int b = Integer.parseInt(c + "");
			if (b < 0 || b > 9) {
				throw new GLosaNiska();
			}
			kod += c;
		}
		pozBr = new String("");
		for (int i = 4; i < 6; i++) {
			char c = niska[i];
			int b = Integer.parseInt(c + "");
			if (b < 0 || b > 9) {
				throw new GLosaNiska();
			}
			pozBr += c;
		}
		brPret = new String("");
		for (int i = 6; i < niska.length; i++) {
			char c = niska[i];
			int b = Integer.parseInt(c + "");
			if (b < 0 || b > 9) {
				throw new GLosaNiska();
			}
			brPret += c;
		}
	}
	public boolean istaDrz(Broj b) {
		return kod.equals(b.kod);
	}
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Broj)) {
			return false;
		}
		Broj b = (Broj) obj;
		return kod.equals(b.kod) && pozBr.equals(b.pozBr) && brPret.equals(b.brPret);
	}
	@Override
	public String toString() {
		return "+" + kod + " " + pozBr + " " + brPret;
	}
	public static void main(String[] args) {
		try {
			System.out.println(new Broj("+381621389544"));
		} catch (GLosaNiska e) {
			// TODO Auto-generated catch block
			System.out.println("haha");
		}
		Broj a = null;
		try {
			a = new Broj("+38162003456");
		} catch (GLosaNiska e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Broj b = null;
		try {
			b = new Broj("+38162003456");
		} catch (GLosaNiska e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(a);
		System.out.println(b);
		System.out.println(a.equals(b));
	}
}
