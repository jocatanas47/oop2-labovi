package igra;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Panel;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import igra.Polje.Status;

public class Mreza extends Panel {
	private Polje[][] matrica;
	private List<Polje> izabrana = new ArrayList<>();
	private Igra igra;
	private int velicina;
	public Mreza(int r, int c, Igra igra) {
		if (r <= 0) {
			r = 4;
		}
		if (c <= 0) {
			c = 5;
		}
		matrica = new Polje[r][c];
		setLayout(new GridLayout(r, c, 3, 3));
		setBackground(Color.BLACK);
		int i = 0;
		for (int j = 0; j < r; j++) {
			for (int k = 0; k < c; k++) {
				matrica[j][k] = new Polje(this, i);
				add(matrica[j][k]);
				i++;
			}
		}
		this.igra = igra;
		velicina = r * c;
	}
	public Mreza(Igra igra) {
		this(4, 5, igra);
	}
	public int getVelicina() {
		return velicina;
	}
	public List<Polje> getIzabrana() {
		return izabrana;
	}
	public Set<Integer> dohvatiSkup() {
		Set<Integer> skup = new HashSet<>();
		for (Polje p : izabrana) {
			skup.add(p.getNatpis());
		}
		return skup;
	}
	public void promeniStatus(Polje p) {
		if (p.getStatus() == Status.IZABRANO) {
			izabrana.add(p);
		} else {
			izabrana.remove(p);
		}
		igra.promenjenStatus();
	}
}
