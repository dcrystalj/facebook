package code;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the obvestilo database table.
 * 
 */
@Entity
@Table(name="obvestilo")
public class Obvestilo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

    @Temporal( TemporalType.DATE)
	private Date datum;

	private String naziv;

	private String vsebina;
	
    @OneToOne
    @JoinColumn(name="id_avtorja")
    private User idAvtorja;
	
    public Obvestilo() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDatum() {
		return this.datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getVsebina() {
		return this.vsebina;
	}

	public void setVsebina(String vsebina) {
		this.vsebina = vsebina;
	}
	
    public User getIdAvtorja() {
       return this.idAvtorja;
    }

    public void setIdAvtorja(User idAvtorja) {
        this.idAvtorja = idAvtorja;
    }
}