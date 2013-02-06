
public class Main {
	public static void main(String[] args) {
		
		TemaSprejemnik1 vs1 = new TemaSprejemnik1();
		vs1.prejemajSporocila();
		
		TemaSprejemnik2 vs2  = new TemaSprejemnik2();
		vs2.prejemajSporocila();
	   
		try {
			Thread.sleep(10000000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		       System.out.print(".");
		
		System.out.println("prejeto");
	}

	public Main() {
		super();
	}

}