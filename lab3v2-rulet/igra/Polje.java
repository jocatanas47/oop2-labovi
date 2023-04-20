package igra;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Polje extends Canvas {
	public enum Status {SLOBODNO, IZABRANO};
	private Mreza vlasnik;
	private int natpis;
	private Status status;
	public Polje(Mreza vlasnik, int natpis) {
		this.vlasnik = vlasnik;
		this.natpis = natpis;
		status = Status.SLOBODNO;
		setBounds(0, 0, 75, 75);
		setBackground(Color.ORANGE);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (status == Status.SLOBODNO) {
					status = Status.IZABRANO;
				} else {
					status = Status.SLOBODNO;
				}
				repaint();
				vlasnik.promeniStatus(Polje.this);
			}
		});
	}
	public int getNatpis() {
		return natpis;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	@Override
	public void paint(Graphics g) {
		g.setColor(Color.BLACK);
		if (status == Status.IZABRANO) {
			g.setColor(Color.BLUE);
			g.fillOval(0, 0, getWidth(), getHeight());
			g.setColor(Color.WHITE);
		}
		int velicina = Math.min(getWidth(), getHeight()) / 3;
		String str = ((Integer) natpis).toString();
		double s = str.length();
		s = s / 2;
		g.setFont(new Font("Default", Font.PLAIN, velicina));
		g.drawString(str, (int) (getWidth() / 2 - s * velicina / 2), (int) (getHeight() / 2 + velicina / 2));
	}
}
