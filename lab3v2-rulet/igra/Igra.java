package igra;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;

public class Igra extends Frame {
	private Mreza mreza = new Mreza(this);
	private Panel panelZaUpravljanje = new Panel(new GridLayout(0, 1));
	private Panel statusniPanel = new Panel();
	private Label statusnaTraka = new Label();
	private Label balansLabel = new Label();
	private Label kvotaLabel = new Label();
	private Label dobitakLabel = new Label();
	private TextField ulog = new TextField("100", 4);
	private Button igraj = new Button("Igraj");
	private double balans = 0;
	private double dobitak = 0;
	private double kvota = 0;
	public Igra() {
		add(mreza, BorderLayout.CENTER);
		
		panelZaUpravljanje.setBackground(Color.LIGHT_GRAY);
		panelZaUpravljanje.setBounds(0, 0, 200, 500);
		Panel panel1 = new Panel();
		panel1.add(new Label("Balans: "));
		balansLabel.setText(balans + "");
		balansLabel.setPreferredSize(new Dimension(50, 20));
		panel1.add(balansLabel);
		panelZaUpravljanje.add(panel1);
		
		Panel panel2 = new Panel();
		panel2.add(new Label("Ulog: "));
		ulog.addTextListener((te) -> {
			if (ulog.getText().length() == 0) {
				dobitak = 0;
			} else {
				try {
					dobitak = Integer.parseInt(ulog.getText()) * kvota;
				} catch (Exception e) {
					return;
				}
			}
			dobitakLabel.setText(String.format("%1.2f", dobitak));
			revalidate();
		});
		ulog.setPreferredSize(new Dimension(50, 20));
		panel2.add(ulog);
		panelZaUpravljanje.add(panel2);
		
		Panel panel3 = new Panel();
		panel3.add(new Label("Kvota: "));
		panel3.add(kvotaLabel);
		kvotaLabel.setPreferredSize(new Dimension(50, 20));
		panelZaUpravljanje.add(panel3);
		
		Panel panel4 = new Panel();
		panel4.add(new Label("Dobitak: "));
		panel4.add(dobitakLabel);
		dobitakLabel.setPreferredSize(new Dimension(50, 20));
		panelZaUpravljanje.add(panel4);
		
		igraj.addActionListener((ae) -> {
			Generator generator = new Generator();
			Integer broj = generator.getBroj(0, 19);
			statusnaTraka.setText(broj + "");
			int noviUlog;
			if (ulog.getText().length() == 0) {
				noviUlog = 0;
			} else {
				try {
					noviUlog = Integer.parseInt(ulog.getText());
				} catch (Exception e) {
					return;
				}
			}
			if (mreza.dohvatiSkup().contains(broj)) {
				balans += dobitak - noviUlog;
				statusniPanel.setBackground(Color.GREEN);
				statusnaTraka.setBackground(Color.GREEN);
				
			} else {
				balans -= noviUlog;
				statusniPanel.setBackground(Color.RED);
				statusnaTraka.setBackground(Color.RED);
			}
			balansLabel.setText(String.format("%1.2f", balans));
			repaint();
			revalidate();
		});
		igraj.setEnabled(false);
		Panel panelIgraj = new Panel(new FlowLayout(FlowLayout.RIGHT));
		panelIgraj.add(igraj);
		panelZaUpravljanje.add(panelIgraj);
		add(panelZaUpravljanje, BorderLayout.EAST);
		
		statusniPanel.add(statusnaTraka, BorderLayout.CENTER);
		statusniPanel.setBackground(Color.GRAY);
		add(statusniPanel, BorderLayout.SOUTH);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		pack();
		setVisible(true);
	}
	public void promenjenStatus() {
		if (mreza.getIzabrana().size() != 0) {
			kvota = mreza.getVelicina() * 1.0 / mreza.getIzabrana().size();
			igraj.setEnabled(true);
		} else {
			kvota = 0;
			igraj.setEnabled(false);
		}
		kvotaLabel.setText(String.format("%1.2f", kvota));
		if (ulog.getText().length() == 0) {
			dobitak = 0;
		} else {
			try {
				dobitak = Integer.parseInt(ulog.getText()) * kvota;
			} catch (Exception e) {
				return;
			}
		}
		dobitakLabel.setText(String.format("%1.2f", dobitak));
		revalidate();
	}
	public static void main(String[] args) {
		new Igra();
	}
}
