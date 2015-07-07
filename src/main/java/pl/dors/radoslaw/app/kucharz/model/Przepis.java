package pl.dors.radoslaw.app.kucharz.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * The persistent class for the przepis database table.
 * 
 */
@Entity
@NamedQuery(name = "Przepis.findAll", query = "SELECT p FROM Przepis p")
@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class, property = "@UUID", scope = Przepis.class)
public class Przepis implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "prp_id")
	private Long prpId;

	@Column(name = "prp_czas")
	private String prpCzas;

	@Column(name = "prp_data_dodania")
	private Timestamp prpDataDodania;

	@Column(name = "prp_data_ostatniej_modyfikacji")
	private Timestamp prpDataOstatniejModyfikacji;

	@Column(name = "prp_nazwa")
	private String prpNazwa;

	@Column(name = "prp_wykonanie_przepisu")
	private String prpWykonaniePrzepisu;

	// bi-directional many-to-one association to KategoriaPrzepisu
	@ManyToOne
	@JoinColumn(name = "prp_kategoria_fk")
	@JsonBackReference("kategoria")
	private KategoriaPrzepisu kategoriaPrzepisu;

	// bi-directional many-to-one association to PracochlonnoscPrzepisu
	@ManyToOne
	@JoinColumn(name = "prp_pracochlonnosc_fk")
	@JsonBackReference("pracochlonnosc")
	private PracochlonnoscPrzepisu pracochlonnoscPrzepisu;

	// bi-directional many-to-one association to PrzepisProdukt
	@OneToMany(mappedBy = "przepis")
	@JsonManagedReference("przepis")
	private List<PrzepisProdukt> przepisProdukty;

	@Transient
	List<Produkt> produkty;

	public Przepis() {
	}

	public Long getPrpId() {
		return this.prpId;
	}

	public void setPrpId(Long prpId) {
		this.prpId = prpId;
	}

	public String getPrpCzas() {
		return this.prpCzas;
	}

	public void setPrpCzas(String prpCzas) {
		this.prpCzas = prpCzas;
	}

	public Timestamp getPrpDataDodania() {
		return this.prpDataDodania;
	}

	public void setPrpDataDodania(Timestamp prpDataDodania) {
		this.prpDataDodania = prpDataDodania;
	}

	public Timestamp getPrpDataOstatniejModyfikacji() {
		return this.prpDataOstatniejModyfikacji;
	}

	public void setPrpDataOstatniejModyfikacji(Timestamp prpDataOstatniejModyfikacji) {
		this.prpDataOstatniejModyfikacji = prpDataOstatniejModyfikacji;
	}

	public String getPrpNazwa() {
		return this.prpNazwa;
	}

	public void setPrpNazwa(String prpNazwa) {
		this.prpNazwa = prpNazwa;
	}

	public String getPrpWykonaniePrzepisu() {
		return this.prpWykonaniePrzepisu;
	}

	public void setPrpWykonaniePrzepisu(String prpWykonaniePrzepisu) {
		this.prpWykonaniePrzepisu = prpWykonaniePrzepisu;
	}

	public KategoriaPrzepisu getKategoriaPrzepisu() {
		return this.kategoriaPrzepisu;
	}

	public void setKategoriaPrzepisu(KategoriaPrzepisu kategoriaPrzepisu) {
		this.kategoriaPrzepisu = kategoriaPrzepisu;
	}

	public PracochlonnoscPrzepisu getPracochlonnoscPrzepisu() {
		return this.pracochlonnoscPrzepisu;
	}

	public void setPracochlonnoscPrzepisu(PracochlonnoscPrzepisu pracochlonnoscPrzepisu) {
		this.pracochlonnoscPrzepisu = pracochlonnoscPrzepisu;
	}

	public List<PrzepisProdukt> getPrzepisProdukty() {
		if (this.przepisProdukty == null) {
			this.przepisProdukty = new ArrayList<PrzepisProdukt>();
		}
		return this.przepisProdukty;
	}

	public void setPrzepisProdukty(List<PrzepisProdukt> przepisProdukty) {
		this.przepisProdukty = przepisProdukty;
	}

	public PrzepisProdukt addPrzepisProdukty(PrzepisProdukt przepisProdukty) {
		getPrzepisProdukty().add(przepisProdukty);
		przepisProdukty.setPrzepis(this);

		return przepisProdukty;
	}

	public PrzepisProdukt removePrzepisProdukty(PrzepisProdukt przepisProdukty) {
		getPrzepisProdukty().remove(przepisProdukty);
		przepisProdukty.setPrzepis(null);

		return przepisProdukty;
	}

	public List<Produkt> getProdukty() {
		if (this.produkty == null) {
			this.produkty = new ArrayList<Produkt>();
		}
		return this.produkty;
	}

	public void setProdukty(List<Produkt> produkty) {
		this.produkty = produkty;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((kategoriaPrzepisu == null) ? 0 : kategoriaPrzepisu.hashCode());
		result = prime * result + ((pracochlonnoscPrzepisu == null) ? 0 : pracochlonnoscPrzepisu.hashCode());
		result = prime * result + ((prpCzas == null) ? 0 : prpCzas.hashCode());
		result = prime * result + ((prpDataDodania == null) ? 0 : prpDataDodania.hashCode());
		result = prime * result + ((prpDataOstatniejModyfikacji == null) ? 0 : prpDataOstatniejModyfikacji.hashCode());
		result = prime * result + ((prpNazwa == null) ? 0 : prpNazwa.hashCode());
		result = prime * result + ((prpWykonaniePrzepisu == null) ? 0 : prpWykonaniePrzepisu.hashCode());
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
		Przepis other = (Przepis) obj;
		if (kategoriaPrzepisu == null) {
			if (other.kategoriaPrzepisu != null)
				return false;
		} else if (!kategoriaPrzepisu.equals(other.kategoriaPrzepisu))
			return false;
		if (pracochlonnoscPrzepisu == null) {
			if (other.pracochlonnoscPrzepisu != null)
				return false;
		} else if (!pracochlonnoscPrzepisu.equals(other.pracochlonnoscPrzepisu))
			return false;
		if (prpCzas == null) {
			if (other.prpCzas != null)
				return false;
		} else if (!prpCzas.equals(other.prpCzas))
			return false;
		if (prpDataDodania == null) {
			if (other.prpDataDodania != null)
				return false;
		} else if (!prpDataDodania.equals(other.prpDataDodania))
			return false;
		if (prpDataOstatniejModyfikacji == null) {
			if (other.prpDataOstatniejModyfikacji != null)
				return false;
		} else if (!prpDataOstatniejModyfikacji.equals(other.prpDataOstatniejModyfikacji))
			return false;
		if (prpNazwa == null) {
			if (other.prpNazwa != null)
				return false;
		} else if (!prpNazwa.equals(other.prpNazwa))
			return false;
		if (prpWykonaniePrzepisu == null) {
			if (other.prpWykonaniePrzepisu != null)
				return false;
		} else if (!prpWykonaniePrzepisu.equals(other.prpWykonaniePrzepisu))
			return false;
		return true;
	}

}