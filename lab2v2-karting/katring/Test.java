package katring;

import greske.GNeodgovarajuciObjekat;
import greske.GPrvaDeonica;

public class Test {

	public static void main(String[] args) {
		Vozilo v1 = new Vozilo(30.0, 3.0, 0.75, "Crash Bandicoot");
		Vozilo v2 = new Vozilo(40.0, 4.0, 0.5, "Coco Bandicoot");
		v2.setMaksBrz(40.0);
		v2.setUbrzanje(4.0);
		v2.setUpravljivost(0.5);
		v2.setTrenBrz(0.0);
		System.out.println(v1.toString());
		System.out.println(v2.getImeTakmicara() + " [" +
		v2.getMaksBrz() + ", " +
		v2.getUbrzanje() + ", " +
		v2.getUpravljivost() + "]");
		System.out.println("Crash - trenutna brzina: " + v1.getTrenBrz() + "m/s");
		System.out.println("Coco - vreme za 200m: " + v2.potrebnoVreme(200) + "m");
		System.out.println("Coco - trenutna brzina: " + v2.getTrenBrz() + "m/s");
		Vozilo v3 = new Vozilo(45, 2.5, 0.25, "Tiny Tiger");
		Specificnost s1 = new Krivina(40.0);
		try {
		s1.ispoljiEfekat(v3);
		System.out.println("Tiny - maksimalna brzina: " + v3.getMaksBrz() + "m/s");
		s1.ponistiEfekat(v3);
		System.out.println("Tiny - maksimalna brzina: " + v3.getMaksBrz() + "m/s");
		} catch (GNeodgovarajuciObjekat e) {
		System.err.println(e);
		}
		Deonica d = new Deonica(100.0);
		d.dodaj(s1);
		d.dodaj(((Krivina)s1).clone());
		d.dodaj(((Krivina)s1).clone());
		d.dodaj(new Prepreka(0.4));
		d.ukloni(s1.dohvatiID());
		System.out.println("Specificnost na prvoj poziciji: " + d.dohvati(0));
		System.out.println(d);
		Deonica d2 = new Deonica(50);
		Staza stz = new Staza("Rainbow road");
		try {
			stz.dodaj(d2);
			stz.dodaj(d);
		} catch (GPrvaDeonica e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Trka trka = new Trka(stz);
		trka.dodaj(v1);
		trka.dodaj(v2);
		trka.dodaj(v3);
		trka.simuliraj(2);
		System.out.println(trka);
		trka.simuliraj(2);
		System.out.println(trka);

		trka.simuliraj(2);
		System.out.println(trka);
		trka.simuliraj(2);
		System.out.println(trka);

		trka.simuliraj(2);
		System.out.println(trka);
		trka.simuliraj(2);
		System.out.println(trka);

		trka.simuliraj(2);
		System.out.println(trka);
		trka.simuliraj(2);
		System.out.println(trka);

		trka.simuliraj(2);
		System.out.println(trka);
		trka.simuliraj(2);
		System.out.println(trka);
	}
}
