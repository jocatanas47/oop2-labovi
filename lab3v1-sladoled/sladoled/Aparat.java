package sladoled;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.border.Border;

import greske.GUkusPostoji;

public class Aparat extends Panel {
	private Mesto mesto;
	private Panel panelUkusi;
	private Button prodaj;
	private Label donjaLabela;
	private Panel gornjiPanel;
	private List<Ukus> ukusi = new ArrayList<>();
	public Aparat() {
		super(new BorderLayout());
		dodajElemente();
	}
	public Mesto getMesto() {
		return mesto;
	}
	public void postaviTekst(String str) {
		donjaLabela.setText(str);
		revalidate();
	}
	public void dodajUkus(Ukus ukus) throws GUkusPostoji {
		if (ukusi.contains(ukus)) {
			throw new GUkusPostoji();
		}
		Button novoDugme = new Button(ukus.getNaziv());
		novoDugme.setBackground(ukus.getBoja());
		novoDugme.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mesto.setUkus(ukus);
				if (!mesto.uToku()) {
					mesto.pokreni();
					prodaj.setEnabled(false);
				} else {
					mesto.otpauziraj();
				}
				revalidate();
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				mesto.pauziraj();
			}
		});
		int brojKomponenti = ukusi.size();
		if (brojKomponenti == 0) {
			panelUkusi.setLayout(new GridLayout(1, 1));
		}
		if (brojKomponenti == 1) {
			panelUkusi.setLayout(new GridLayout(2, 1));
		}
		if (brojKomponenti > 1) {
			panelUkusi.setLayout(new GridLayout(0, 2));
		}
		ukusi.add(ukus);
		panelUkusi.add(novoDugme);
		repaint();
		revalidate();
	}
	public void omoguciProdaju() {
		prodaj.setEnabled(true);
	}
	private void dodajElemente() {
		Label sladoledLabela = new Label("Sladoled: ");
		sladoledLabela.setFont(new Font("Default", Font.BOLD, 12));
		donjaLabela = new Label();
		Panel labelaPanel = new Panel();
		labelaPanel.setBackground(Color.GRAY);
		labelaPanel.add(sladoledLabela);
		labelaPanel.add(donjaLabela);
		
		gornjiPanel = new Panel(new BorderLayout());
		panelUkusi = new Panel(new GridLayout(1, 1));
		panelUkusi.setBackground(Color.LIGHT_GRAY);
		gornjiPanel.add(panelUkusi, BorderLayout.CENTER);
		
		Panel desniPanel = new Panel(new GridLayout(2, 1));
		prodaj = new Button("Prodaj");
		prodaj.setEnabled(false);
		prodaj.addActionListener((ae) -> {
			Sladoled sladoled = mesto.getSladoled();
			mesto.zaustavi();
			desniPanel.remove(mesto);
			mesto = new Mesto(this);
			desniPanel.add(mesto);
			System.out.println(sladoled);
			prodaj.setEnabled(false);
		});
		desniPanel.add(prodaj);
		mesto = new Mesto(this);
		desniPanel.add(mesto);
		
		gornjiPanel.add(desniPanel, BorderLayout.EAST);
		add(gornjiPanel, BorderLayout.NORTH);
		add(labelaPanel, BorderLayout.SOUTH);
	}
}
