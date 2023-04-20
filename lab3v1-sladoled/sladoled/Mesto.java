package sladoled;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Mesto extends Canvas implements Runnable {
	public static class Pravougaonik {
		public Color boja;
		public int x;
		public int y;
		public int width;
		public int height;
		public Pravougaonik(Color boja, int x, int y, int width, int height) {
			this.boja = boja;
			this.x = x;
			this.y = y;
			this.width = width;
			this.height = height;
		}
		public void crtaj(Graphics g) {
			g.setColor(boja);
			g.fillRect(x, y, width, height);
		}
	}
	private Aparat vlasnik;
	private Sladoled sladoled;
	private Ukus ukus;
	private Thread nit;
	private boolean pauzirano;
	private List<Pravougaonik> pravougaonici = new ArrayList<>();
	public Mesto(Aparat aparat) {
		vlasnik = aparat;
		setBounds(0, 0, 140, 140);
		setVisible(true);
	}
	public synchronized void setUkus(Ukus ukus) {
		this.ukus = ukus;
	}
	public synchronized Sladoled getSladoled() {
		return sladoled;
	}
	public void pokreni() {
		if (nit != null) {
			nit.interrupt();
		}
		pravougaonici = new ArrayList<>();
		pauzirano = false;
		sladoled = new Sladoled(200);
		nit = new Thread(this);
		nit.start();
	}
	public synchronized boolean uToku() {
		if (nit == null) {
			return false;
		}
		return nit.isAlive() && !nit.isInterrupted();
	}
	public void zaustavi() {
		if (nit != null) {
			if (!nit.isInterrupted()) {
				nit.interrupt();
			}
		}
	}
	public synchronized void pauziraj() {
		pauzirano = true;
	}
	public synchronized void otpauziraj() {
		pauzirano = false;
		notifyAll();
	}
	@Override
	public void paint(Graphics g) {
		Color staraBoja = g.getColor();
		for (Pravougaonik p : pravougaonici) {
			p.crtaj(g);
		}
		g.setColor(staraBoja);
	}
	@Override
	public void run() {
		int visina = (int) (getHeight() * (20.0 / sladoled.getVelicina()));
		int i = 0;
		try {
			while (sladoled.ukKolicina() < 200 && !nit.isInterrupted()) {
				synchronized (this) {
					while (ukus == null || pauzirano) {
						wait();
					}
				}
				synchronized (this) {
					sladoled.dodaj(20, ukus);
					pravougaonici.add(new Pravougaonik(ukus.getBoja(),
							0, getHeight() - visina * (i + 1),
							getWidth(), visina));
					i++;
				}
				vlasnik.postaviTekst(sladoled.toString());
				repaint();
				Thread.sleep(500);
			}
		} catch (InterruptedException e) {}
		if (!nit.isInterrupted()) {
			vlasnik.omoguciProdaju();
		}
	}
}
