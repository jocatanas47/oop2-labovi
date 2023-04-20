package telefon;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Tastatura extends Panel implements Runnable {
	public enum Rezim {BROJEVI, SLOVA};
	private static final String[] brojevi = {"1", "2", "3", "4", "5", "6", 
			"7", "8", "9", "*", "0", "+"};
	private static final String[] slova = {"", "ABC", "DEF", "GHI", "JLK",
			"MNO", "PQRS", "TUV", "WXYZ", "", "_", ""};
	private List<Button> dugmad = new ArrayList<Button>();
	private Rezim rezim;
	private Label natpis;
	private Label sadrzaj;
	private Thread nit = new Thread();
	private String trStr;
	private int trDuz;
	private boolean novoSlovo;
	private boolean prekinuta;
	public Tastatura() {
		super(new BorderLayout());
		natpis = new Label();
		sadrzaj = new Label();
		Panel gornjiPanel = new Panel(new GridLayout(2, 1));
		gornjiPanel.add(natpis);
		gornjiPanel.add(sadrzaj);
		add(gornjiPanel, BorderLayout.NORTH);
		rezim = Rezim.BROJEVI;
		Panel dugmadPanel = new Panel(new GridLayout(4, 3));
		for (int i = 0; i < 12; i++) {
			Button novo = new Button(brojevi[i]);
			novo.setFont(new Font("Default", Font.BOLD, 12));
			dugmadPanel.add(novo);
			dugmad.add(novo);
			novo.addActionListener((ae) -> {
				switch (rezim) {
				case BROJEVI:
					natpis.setText(natpis.getText() + ((Button) ae.getSource()).getLabel());
					break;
				case SLOVA:
					String tekstDugmeta = ((Button) ae.getSource()).getLabel();
					if (tekstDugmeta.length() == 0) {
						break;
					}
					if (!nit.isAlive()) {
						trStr = tekstDugmeta;
						trDuz = tekstDugmeta.length();
						novoSlovo = false;
						prekinuta = true;
						nit = new Thread(this);
						nit.start();
					} else {
						if (tekstDugmeta == trStr) {
							nit.interrupt();
						} else {
							novoSlovo = true;
							nit.interrupt();
							try {
								nit.join();
							} catch (InterruptedException e) {}
							novoSlovo = false;
							prekinuta = true;
							trStr = tekstDugmeta;
							trDuz = tekstDugmeta.length();
							nit = new Thread(this);
							nit.start();
						}
					}
					break;
				}
			});
		}
		add(dugmadPanel);
	}
	public Rezim getRezim() {
		return rezim;
	}
	public Label getNatpis() {
		return natpis;
	}
	public Label getSadrzaj() {
		return sadrzaj;
	}
	public void promeniRezim() {
		int i = 0;
		switch (rezim) {
		case BROJEVI:
			rezim = Rezim.SLOVA;
			Iterator<Button> iter = dugmad.iterator();
			while (iter.hasNext()) {
				iter.next().setLabel(slova[i]);
				i++;
			}
			break;
		case SLOVA:
			rezim = Rezim.BROJEVI;
			iter = dugmad.iterator();
			while (iter.hasNext()) {
				iter.next().setLabel(brojevi[i]);
				i++;
			}
		}
	}
	@Override
	public void run() {
		int i = -1;
		while (prekinuta && !novoSlovo) {
			i++;
			i %= trDuz;
			prekinuta = false;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				prekinuta = true;
			}
		}
		sadrzaj.setText(sadrzaj.getText() + trStr.toCharArray()[i]);
	}
}
