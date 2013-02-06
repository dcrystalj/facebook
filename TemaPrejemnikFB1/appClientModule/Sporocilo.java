
import java.io.Serializable;

public class Sporocilo implements Serializable{
	private static final long serialVersionUID = -3631253706670396572L;
	
	int id;
	String vsebina;
	
	public Sporocilo(int id, String vsebina) {
		super();
		this.id = id;
		this.vsebina = vsebina;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getVsebina() {
		return vsebina;
	}
	public void setVsebina(String vsebina) {
		this.vsebina = vsebina;
	}
	
}
