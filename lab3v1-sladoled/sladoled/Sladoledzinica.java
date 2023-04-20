package sladoled;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import greske.GUkusPostoji;

public class Sladoledzinica extends Frame {
	private Aparat aparat;
	private Panel panelZaDodavanje;
	private TextField ime;
	private TextField boja;
	public Sladoledzinica() {
		super("Sladoledzinica");
		aparat = new Aparat();
		add(aparat, BorderLayout.NORTH);
		ime = new TextField();
		ime.setColumns(4);
		boja = new TextField();
		boja.setColumns(4);
		panelZaDodavanje = new Panel();
		Label imeLabela = new Label("Naziv: ");
		imeLabela.setFont(new Font("Default", Font.BOLD, 12));
		panelZaDodavanje.add(imeLabela);
		panelZaDodavanje.add(ime);
		Label color = new Label("Boja: ");
		color.setFont(new Font("Default", Font.BOLD, 12));
		panelZaDodavanje.add(color);
		panelZaDodavanje.add(boja);
		Button dodaj = new Button("Dodaj ukus");
		dodaj.addActionListener((ae) -> {
			String naziv = ime.getText();
			String novaBoja = boja.getText();
			if (novaBoja.length() != 6) {
				return;
			}
			int kod = 0;
			try {
				kod = (int) Long.parseLong(novaBoja, 16);
			} catch (Exception e) {
				return;
			}
			Color najnovijaBoja = new Color(kod);
			try {
				aparat.dodajUkus(new Ukus(naziv, najnovijaBoja));
			} catch (GUkusPostoji e) {}
			revalidate();
			pack();
		});
		panelZaDodavanje.add(dodaj);
		panelZaDodavanje.setBackground(Color.CYAN);
		add(panelZaDodavanje, BorderLayout.SOUTH);
		setResizable(false);
		pack();
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				aparat.getMesto().zaustavi();
				dispose();
			}
		});
	}
	public static void main(String[] args) {
		new Sladoledzinica();
	}
}
