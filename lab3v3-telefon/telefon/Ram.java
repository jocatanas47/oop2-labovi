package telefon;

import java.awt.Color;
import java.awt.Frame;

import greske.GLosaNiska;

public class Ram extends Frame {

	public Ram() {
		Broj broj = null;
		try {
			broj = new Broj("+381621389544");
		} catch (GLosaNiska e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Telefon telefon = new Telefon(broj, Color.CYAN);
		add(telefon);
		setVisible(true);
		pack();
	}
	public static void main(String[] args) {
		new Ram();
	}
}
