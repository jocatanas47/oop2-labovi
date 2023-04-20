package igra;

import java.util.Random;

public class Generator {
	int getBroj(int a, int b) {
		return a + new Random().nextInt(b - a + 1);
	}
}
