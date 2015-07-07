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
 * The persistent class for the kategoria_przepisu database table.
 * 
 */
@Entity
@Table(name = "kategoria_przepisu")
@NamedQuery(name = "KategoriaPrzepisu.findAll", query = "SELECT k FROM KategoriaPrzepisu k")
@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class, property = "@UUID", scope = KategoriaPrzepisu.class)
public class KategoriaPrzepisu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "kpr_id")
	private Long kprId;

	@Column(name = "kpr_nazwa")
	private String kprNazwa;

	// bi-directional many-to-one association to Przepis
	@OneToMany(mappedBy = "kategoriaPrzepisu")
	@JsonManagedReference("kategoria")
	private List<Przepis> przepisy;

	public KategoriaPrzepisu() {
	}

	public Long getKprId() {
		return this.kprId;
	}

	public void setKprId(Long kprId) {
		this.kprId = kprId;
	}

	public String getKprNazwa() {
		return this.kprNazwa;
	}

	public void setKprNazwa(String kprNazwa) {
		this.kprNazwa = kprNazwa;
	}

	public List<Przepis> getPrzepisy() {
		return this.przepisy;
	}

	public void setPrzepisy(List<Przepis> przepisy) {
		this.przepisy = przepisy;
	}

	public Przepis addPrzepisy(Przepis przepisy) {
		getPrzepisy().add(przepisy);
		przepisy.setKategoriaPrzepisu(this);

		return przepisy;
	}

	public Przepis removePrzepisy(Przepis przepisy) {
		getPrzepisy().remove(przepisy);
		przepisy.setKategoriaPrzepisu(null);

		return przepisy;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((kprNazwa == null) ? 0 : kprNazwa.hashCode());
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
		KategoriaPrzepisu other = (KategoriaPrzepisu) obj;
		if (kprNazwa == null) {
			if (other.kprNazwa != null)
				return false;
		} else if (!kprNazwa.equals(other.kprNazwa))
			return false;
		return true;
	}

}