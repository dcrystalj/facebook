package code;

import java.io.Serializable;
import javax.persistence.*;
import code.User;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


/**
 * The persistent class for the prijateljstvo database table.
 * 
 */
@Entity
@Table(name="prijateljstvo")
public class Prijateljstvo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private int status;

	@ManyToOne
	@JoinColumn(name="id_uporabnika_pobudnik")
	private User idUporabnikaPobudnik;

	@ManyToOne
	@JoinColumn(name="id_uporabnika_prejemnik")
	private User idUporabnikaPrejemnik;

    public Prijateljstvo() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public User getIdUporabnikaPobudnik() {
		return this.idUporabnikaPobudnik;
	}

	public void setIdUporabnikaPobudnik(User idUporabnikaPobudnik) {
		this.idUporabnikaPobudnik = idUporabnikaPobudnik;
	}

	public User getIdUporabnikaPrejemnik() {
		return this.idUporabnikaPrejemnik;
	}

	public void setIdUporabnikaPrejemnik(User idUporabnikaPrejemnik) {
		this.idUporabnikaPrejemnik = idUporabnikaPrejemnik;
	}

}