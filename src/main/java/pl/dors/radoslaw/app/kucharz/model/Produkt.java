package pl.dors.radoslaw.app.kucharz.model;

import java.io.Serializable;
import java.util.Set;

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
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * The persistent class for the produkt database table.
 * 
 */
@Entity
@NamedQuery(name = "Produkt.findAll", query = "SELECT p FROM Produkt p")
public class Produkt implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "prd_id")
	private Long prdId;

	@Column(name = "prd_nazwa")
	private String prdNazwa;

	// bi-directional many-to-one association to MiaraProduktu
	@ManyToOne
	@JoinColumn(name = "prd_miara_fk")
	@JsonBackReference("miara")
	private MiaraProduktu miaraProduktu;

	// bi-directional many-to-one association to RodzajProduktu
	@ManyToOne
	@JoinColumn(name = "prd_rodzaj_fk")
	@JsonBackReference("rodzaj")
	private RodzajProduktu rodzajProduktu;

	// bi-directional many-to-one association to PrzepisProdukt
	@OneToMany(mappedBy = "produkt")
	@JsonManagedReference("produkt")
	private Set<PrzepisProdukt> przepisProdukty;

	@Transient
	private double ilosc;

	public Produkt() {
	}

	public Long getPrdId() {
		return this.prdId;
	}

	public void setPrdId(Long prdId) {
		this.prdId = prdId;
	}

	public String getPrdNazwa() {
		return this.prdNazwa;
	}

	public void setPrdNazwa(String prdNazwa) {
		this.prdNazwa = prdNazwa;
	}

	public MiaraProduktu getMiaraProduktu() {
		return this.miaraProduktu;
	}

	public void setMiaraProduktu(MiaraProduktu miaraProduktu) {
		this.miaraProduktu = miaraProduktu;
	}

	public RodzajProduktu getRodzajProduktu() {
		return this.rodzajProduktu;
	}

	public void setRodzajProduktu(RodzajProduktu rodzajProduktu) {
		this.rodzajProduktu = rodzajProduktu;
	}

	public Set<PrzepisProdukt> getPrzepisProdukty() {
		return this.przepisProdukty;
	}

	public void setPrzepisProdukty(Set<PrzepisProdukt> przepisProdukty) {
		this.przepisProdukty = przepisProdukty;
	}

	public PrzepisProdukt addPrzepisProdukty(PrzepisProdukt przepisProdukty) {
		getPrzepisProdukty().add(przepisProdukty);
		przepisProdukty.setProdukt(this);

		return przepisProdukty;
	}

	public PrzepisProdukt removePrzepisProdukty(PrzepisProdukt przepisProdukty) {
		getPrzepisProdukty().remove(przepisProdukty);
		przepisProdukty.setProdukt(null);

		return przepisProdukty;
	}

	public double getIlosc() {
		return ilosc;
	}

	public void setIlosc(double ilosc) {
		this.ilosc = ilosc;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((miaraProduktu == null) ? 0 : miaraProduktu.hashCode());
		result = prime * result + ((prdNazwa == null) ? 0 : prdNazwa.hashCode());
		result = prime * result + ((rodzajProduktu == null) ? 0 : rodzajProduktu.hashCode());
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
		Produkt other = (Produkt) obj;
		if (miaraProduktu == null) {
			if (other.miaraProduktu != null)
				return false;
		} else if (!miaraProduktu.equals(other.miaraProduktu))
			return false;
		if (prdNazwa == null) {
			if (other.prdNazwa != null)
				return false;
		} else if (!prdNazwa.equals(other.prdNazwa))
			return false;
		if (rodzajProduktu == null) {
			if (other.rodzajProduktu != null)
				return false;
		} else if (!rodzajProduktu.equals(other.rodzajProduktu))
			return false;
		return true;
	}

}