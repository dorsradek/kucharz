package pl.dors.radoslaw.app.kucharz.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * The persistent class for the pracochlonnosc_przepisu database table.
 * 
 */
@Entity
@Table(name = "pracochlonnosc_przepisu")
@NamedQuery(name = "PracochlonnoscPrzepisu.findAll", query = "SELECT p FROM PracochlonnoscPrzepisu p")
@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class, property = "@UUID", scope = PracochlonnoscPrzepisu.class)
public class PracochlonnoscPrzepisu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ppp_id")
	private Long pppId;

	@Column(name = "ppp_nazwa")
	private String pppNazwa;

	// bi-directional many-to-one association to Przepis
	@OneToMany(mappedBy = "pracochlonnoscPrzepisu")
	@JsonManagedReference("pracochlonnosc")
	private List<Przepis> przepisy;

	public PracochlonnoscPrzepisu() {
	}

	public Long getPppId() {
		return this.pppId;
	}

	public void setPppId(Long pppId) {
		this.pppId = pppId;
	}

	public String getPppNazwa() {
		return this.pppNazwa;
	}

	public void setPppNazwa(String pppNazwa) {
		this.pppNazwa = pppNazwa;
	}

	public List<Przepis> getPrzepisy() {
		return this.przepisy;
	}

	public void setPrzepisy(List<Przepis> przepisy) {
		this.przepisy = przepisy;
	}

	public Przepis addPrzepisy(Przepis przepisy) {
		getPrzepisy().add(przepisy);
		przepisy.setPracochlonnoscPrzepisu(this);

		return przepisy;
	}

	public Przepis removePrzepisy(Przepis przepisy) {
		getPrzepisy().remove(przepisy);
		przepisy.setPracochlonnoscPrzepisu(null);

		return przepisy;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pppNazwa == null) ? 0 : pppNazwa.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PracochlonnoscPrzepisu other = (PracochlonnoscPrzepisu) obj;
		if (pppNazwa == null) {
			if (other.pppNazwa != null)
				return false;
		} else if (!pppNazwa.equals(other.pppNazwa))
			return false;
		return true;
	}

}