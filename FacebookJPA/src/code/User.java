package code;

import java.io.Serializable;
import javax.persistence.*;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import java.util.List;
import code.Prijateljstvo;

/**
 * The persistent class for the uporabnik database table.
 * 
 */
@Entity
@Table(name="uporabnik")
@NamedQueries(
		@NamedQuery(name = "getUserkByupIme", query = "select u from User u Where u.upIme= :upIme"))
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String email;

	private String geslo;

	private String ime;

	private int letnik;

	@Column(name="letnik_diplomiranja")
	private int letnikDiplomiranja;

	private String naslov;

	private String priimek;

	private String smer;

	private String sol;

	private String spol;

	private String stopnja;

	private String studij;

	@Column(name="tip_uporabnika")
	private int tipUporabnika;

	@Column(name="up_ime")
	private String upIme;

	private String vrsta;



	@OneToMany(mappedBy="idUporabnikaPobudnik")
	private List<Prijateljstvo> prijateljstvoPobudnikList;



	@OneToMany(mappedBy="idUporabnikaPrejemnik")
	private List<Prijateljstvo> prijateljstvoPrejemnikList;
	
	
    public User() {
    	super();
    	this.email = "";
    	this.geslo = "";
    	this.ime = "";
    	this.letnik = -1;
    	this.letnikDiplomiranja = -1;
    	this.naslov = "";
    	this.priimek = "";
    	this.smer = "";
    	this.sol = "";
    	this.spol = "";
    	this.stopnja = "";
    	this.studij = "";
    	this.tipUporabnika = 1;
    	this.upIme = "";
    	this.vrsta = "";
    }
    public User(String ime, String priimek, String geslo, int letnik,
    		int letnikDiplomiranja, String naslov, String email, String smer, String spol, String stopnja, String studij, String upIme, String vrsta) {
    	super();
    	this.email = email;
    	this.geslo = geslo;
    	this.ime = ime;
    	this.letnik = letnik;
    	this.letnikDiplomiranja = letnikDiplomiranja;
    	this.naslov = naslov;
    	this.priimek = priimek;
    	this.smer = smer;
    	this.sol = "";
    	this.spol = spol;
    	this.stopnja = stopnja;
    	this.studij = studij;
    	this.tipUporabnika = 1;
    	this.upIme = upIme;
    	this.vrsta = vrsta;
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGeslo() {
		return this.geslo;
	}

	public void setGeslo(String geslo) {
		this.geslo = geslo;
	}

	public String getIme() {
		return this.ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public int getLetnik() {
		return this.letnik;
	}

	public void setLetnik(int letnik) {
		this.letnik = letnik;
	}

	public int getLetnikDiplomiranja() {
		return this.letnikDiplomiranja;
	}

	public void setLetnikDiplomiranja(int letnikDiplomiranja) {
		this.letnikDiplomiranja = letnikDiplomiranja;
	}

	public String getNaslov() {
		return this.naslov;
	}

	public void setNaslov(String naslov) {
		this.naslov = naslov;
	}

	public String getPriimek() {
		return this.priimek;
	}

	public void setPriimek(String priimek) {
		this.priimek = priimek;
	}

	public String getSmer() {
		return this.smer;
	}

	public void setSmer(String smer) {
		this.smer = smer;
	}

	public String getSol() {
		return this.sol;
	}

	public void setSol(String sol) {
		this.sol = sol;
	}

	public String getSpol() {
		return this.spol;
	}

	public void setSpol(String spol) {
		this.spol = spol;
	}

	public String getStopnja() {
		return this.stopnja;
	}

	public void setStopnja(String stopnja) {
		this.stopnja = stopnja;
	}

	public String getStudij() {
		return this.studij;
	}

	public void setStudij(String studij) {
		this.studij = studij;
	}

	public int getTipUporabnika() {
		return this.tipUporabnika;
	}

	public void setTipUporabnika(int tipUporabnika) {
		this.tipUporabnika = tipUporabnika;
	}

	public String getUpIme() {
		return this.upIme;
	}

	public void setUpIme(String upIme) {
		this.upIme = upIme;
	}

	public String getVrsta() {
		return this.vrsta;
	}

	public void setVrsta(String vrsta) {
		this.vrsta = vrsta;
	}
	public List<Prijateljstvo> getPrijateljstvoPobudnikList() {
		return this.prijateljstvoPobudnikList;
	}
	public void setPrijateljstvoPobudnikList(List<Prijateljstvo> prijateljstvoListPobudnikList) {
		this.prijateljstvoPobudnikList = prijateljstvoListPobudnikList;
	}
	public List<Prijateljstvo> getPrijateljstvoPrejemnikList() {
		return this.prijateljstvoPrejemnikList;
	}
	public void setPrijateljstvoPrejemnikList(List<Prijateljstvo> prijateljstvoPrejemnikList) {
		this.prijateljstvoPrejemnikList = prijateljstvoPrejemnikList;
	}

}