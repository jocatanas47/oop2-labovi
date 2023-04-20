package telefon;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;

public class Stavka extends Panel {
	private Label naslov;
	private Label tekst;
	public Stavka(String naslov, String tekst) {
		super(new GridLayout(2, 1));
		this.naslov = new Label(naslov);
		this.naslov.setFont(new Font("Default", Font.BOLD, 12));
		this.tekst = new Label(tekst);
		add(this.naslov);
		add(this.tekst);
	}
	public void postaviNaslov(String naslov) {
		this.naslov.setText(naslov);
		revalidate();
	}
}
