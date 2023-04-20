package telefon;

import java.awt.GridLayout;
import java.awt.Panel;
import java.util.ArrayList;
import java.util.List;

public class ListaStavki extends Panel {
	protected List<Stavka> stavke = new ArrayList<>();
	public ListaStavki() {
		super(new GridLayout(5, 1));
	}
	public void dodaj(Stavka stavka) {
		if (stavke.size() >= 5) {
			setLayout(new GridLayout(0, 1));
		}
		stavke.add(stavka);
		add(stavka);
		repaint();
		revalidate();
	}
}
