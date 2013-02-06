public class Main {
	
	static int stSporocil=10;

	public Main() {
		super();
	}
	public static void main(String[] args) {
		//Vsebuje metodi za posiljanje sporocila
		TemaPosiljatelj vp = new TemaPosiljatelj();
		//Posljemo doloceno stevilo sporocil v temo
		for (int i = 0; i < stSporocil; i++) {
			System.out.println("posiljam sporocilo: "+i);
			Sporocilo msg = new Sporocilo(i,"Sporocilo stevilka "+i);
			vp.vstaviSporocilo(msg);
		}
	}
}