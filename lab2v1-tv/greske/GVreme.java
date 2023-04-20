package greske;

public class GVreme extends Exception {

	public GVreme() {
		super("Komponente izvan opsega ili minuti nisu deljivi sa 15");
	}
}
