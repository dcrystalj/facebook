import code.queue.*;

public class Main {
	
	static int stSporocil=10;
	
	/* (non-Java-doc)
	 * @see java.lang.Object#Object()
	 */
	public Main() {
		super();
	}
	public static void main(String[] args) {
		//Vsebuje metodi za posiljanje sporocila
		VrstaPosiljatelj vp = new VrstaPosiljatelj();
		//Posljemo doloceno stevilo sporocil v vrsto (queue)
		vp.vstaviSporocila();
		System.out.println("vstavljeno");
	}



}