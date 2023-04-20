package sah;

public class Test {
	
	public static void main(String[] args) {
		
		Polje p1 = new Polje ('A', 1);
		Polje p2 = new Polje ('A', 4);
		Polje p3 = new Polje ('C', 4);
		Polje p4 = new Polje ('G', 1);
		Polje p5 = new Polje ('F', 3);
		
		Skakac skakac = new Skakac(p4);
		Top top = new Top(p1);
		
		if (top.mogucPomeraj(p2)) {
			System.out.println("auga");
		}
		if (top.mogucPomeraj(p5)) {
			System.out.println("kawabunga");
		}
		if (skakac.mogucPomeraj(p5)) {
			System.out.println("smh");
		}
		if (skakac.mogucPomeraj(p1)) {
			System.out.println("rofl");
		}
		System.out.println(skakac);
		System.out.println(top);
	}


}
