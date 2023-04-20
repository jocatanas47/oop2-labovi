package katring;

public class Vozilo {
	private double maksBrz;
	private double trenBrz;
	private double ubrzanje;
	private double upravljivost;
	private String imeTakmicara;
	
	public Vozilo(double maksBrz, double ubrzanje, 
			double upravljivost, String imeTakmicara)
	{
		this.maksBrz = maksBrz;
		this.ubrzanje = ubrzanje;
		this.imeTakmicara = imeTakmicara;
		if (upravljivost >= 0 && upravljivost <= 1) {
			this.upravljivost = upravljivost;
		}
		else if (upravljivost < 0) {
			this.upravljivost = 0;
		}
		else {
			this.upravljivost = 1;
		}
		trenBrz = 0;
	}
	
	public double getMaksBrz() {
		return maksBrz;
	}
	public void setMaksBrz(double maksBrz) {
		this.maksBrz = maksBrz;
		if (trenBrz > maksBrz) {
			trenBrz = maksBrz;
		}
	}
	public double getTrenBrz() {
		return trenBrz;
	}
	public void setTrenBrz(double trenBrz) {
		this.trenBrz = trenBrz;
	}
	public double getUbrzanje() {
		return ubrzanje;
	}
	public void setUbrzanje(double ubrzanje) {
		this.ubrzanje = ubrzanje;
	}
	public double getUpravljivost() {
		return upravljivost;
	}
	public void setUpravljivost(double upravljivost) {
		this.upravljivost = upravljivost;
	}
	public String getImeTakmicara() {
		return imeTakmicara;
	}
	public void setImeTakmicara(String imeTakmicara) {
		this.imeTakmicara = imeTakmicara;
	}
	
	public double pomeri(double t) {
		double brz = trenBrz + ubrzanje * t;
		if (brz <= maksBrz) {
			double put = trenBrz * t + ubrzanje * t * t / 2.0;
			trenBrz = brz;
			return put;
		} else {
			double vrm = (maksBrz - trenBrz) / ubrzanje;
			double put = trenBrz * vrm + ubrzanje * vrm * vrm / 2.0 
					+ (t - vrm) * maksBrz;
			trenBrz = maksBrz;
			return put;
		}
	}
	
	public double potrebnoVreme(double s) {
		double brz = Math.sqrt(trenBrz * trenBrz + 2 * ubrzanje * s);
		if (brz <= maksBrz) {
			return (brz - trenBrz) / ubrzanje;
		} else {
			double vrm = (maksBrz - trenBrz) / ubrzanje;
			return (s - trenBrz * vrm - ubrzanje * vrm * vrm / 2.0) / maksBrz + vrm;
		}
	}
	
	public String toString() {
		return imeTakmicara + " [" + maksBrz + ", " + ubrzanje + ", " + upravljivost + "]";
	};
}
