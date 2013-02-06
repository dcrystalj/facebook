package code;

import java.util.Date;

public class Novice extends Entiteta {
	int id;
	String naziv;
	int id_avtorja;
	String vsebina;
	Date datum_objave;

	
	public Novice() {
		super();
	}
	//brez ID --> autoincrement
	public Novice(String naziv, int id_avtorja, String vsebina, Date datum_objave) {
		this.naziv = naziv;
		this.id_avtorja = id_avtorja;
		this.vsebina = vsebina;
		this.datum_objave = datum_objave;
	}
	public Novice(int id, String naziv, int id_avtorja, String vsebina, Date datum_objave) {
		this.id = id;
		this.naziv = naziv;
		this.id_avtorja = id_avtorja;
		this.vsebina = vsebina;
		this.datum_objave = datum_objave;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public int getId_avtorja() {
		return id_avtorja;
	}
	public void setId_avtorja(int id_avtorja) {
		this.id_avtorja = id_avtorja;
	}
	public String getVsebina() {
		return vsebina;
	}
	public void setVsebina(String vsebina) {
		this.vsebina = vsebina;
	}
	public Date getDatum_objave() {
		return datum_objave;
	}
	public void setDatum_objave(Date datum_objave) {
		this.datum_objave = datum_objave;
	}
	@Override
	public String toString() {
		return "Novice [naziv=" + naziv + ", id_avtorja=" + id_avtorja
				+ ", vsebina=" + vsebina + ", datum_objave=" + datum_objave
				+ "]";
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
