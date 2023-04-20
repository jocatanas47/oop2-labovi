package telefon;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Window;

import greske.GLosaNiska;

public class Telefon extends Panel {
	private Broj broj;
	public Tastatura tastatura;
	private Imenik imenik;
	private boolean ukljucen = true;
	private String trIme;
	private String trBroj;
	public Telefon(Broj broj, Color boja) {
		super(new BorderLayout());
		this.broj = broj;
		setBackground(boja);
		
		Panel srednjiPanel = new Panel(new GridLayout(2, 1));
		tastatura = new Tastatura();
		srednjiPanel.add(tastatura);
		Panel srednjiDonjiPanel = new Panel(new BorderLayout());
		imenik = new Imenik();
		srednjiDonjiPanel.add(imenik);
		
		Panel dugmePanel = new Panel(new GridLayout(1, 2));
		Button dodaj = new Button("Dodaj Kontakt");
		dodaj.addActionListener((ae) -> {
			Tastatura.Rezim rezim = tastatura.getRezim();
			switch (rezim) {
			case BROJEVI:
				trBroj = tastatura.getNatpis().getText();
				tastatura.promeniRezim();
				break;
			case SLOVA:
				trIme = tastatura.getSadrzaj().getText();
				try {
					imenik.dodaj(new Kontakt(trIme, new Broj(trBroj)));
				} catch (GLosaNiska e) {}
				tastatura.getNatpis().setText("");
				tastatura.getSadrzaj().setText("");
				tastatura.promeniRezim();
				break;
			}
		});
		dugmePanel.add(dodaj);
		Button iskljuci = new Button("Iskljuci telefon");
		iskljuci.addActionListener((ae) -> {
			if (ukljucen) {
				ukljucen = false;
				((Button) ae.getSource()).setBackground(Color.RED);
			} else {
				ukljucen = true;
				((Button) ae.getSource()).setBackground(Color.GRAY);
			}
		});
		dugmePanel.add(iskljuci);
		
		srednjiDonjiPanel.add(dugmePanel, BorderLayout.SOUTH);
		srednjiPanel.add(srednjiDonjiPanel);
		add(srednjiPanel);
		
		Panel donjiPanel = new Panel();
		Label brojLabela = new Label(broj.toString(), Label.CENTER);
		brojLabela.setFont(new Font("Default", Font.BOLD, 12));
		donjiPanel.add(brojLabela);
		add(donjiPanel, BorderLayout.SOUTH);
	}
	public static void main(String[] args) {
		Frame w = new Frame();
		try {
			w.add(new Telefon(new Broj("+381621389544"), Color.blue));
		} catch (GLosaNiska e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		w.setBounds(400, 400, 400, 400);
		w.setVisible(true);
	}
}
