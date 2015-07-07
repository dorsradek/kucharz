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

import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * The persistent class for the rodzaj_produktu database table.
 * 
 */
@Entity
@Table(name = "rodzaj_produktu")
@NamedQuery(name = "RodzajProduktu.findAll", query = "SELECT r FROM RodzajProduktu r")
public class RodzajProduktu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "rdp_id")
	private Long rdpId;

	@Column(name = "rdp_nazwa")
	private String rdpNazwa;

	// bi-directional many-to-one association to Produkt
	@OneToMany(mappedBy = "rodzajProduktu")
	@JsonManagedReference("rodzaj")
	private List<Produkt> produkty;

	public RodzajProduktu() {
	}

	public Long getRdpId() {
		return this.rdpId;
	}

	public void setRdpId(Long rdpId) {
		this.rdpId = rdpId;
	}

	public String getRdpNazwa() {
		return this.rdpNazwa;
	}

	public void setRdpNazwa(String rdpNazwa) {
		this.rdpNazwa = rdpNazwa;
	}

	public List<Produkt> getProdukty() {
		return this.produkty;
	}

	public void setProdukty(List<Produkt> produkty) {
		this.produkty = produkty;
	}

	public Produkt addProdukty(Produkt produkty) {
		getProdukty().add(produkty);
		produkty.setRodzajProduktu(this);

		return produkty;
	}

	public Produkt removeProdukty(Produkt produkty) {
		getProdukty().remove(produkty);
		produkty.setRodzajProduktu(null);

		return produkty;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((rdpNazwa == null) ? 0 : rdpNazwa.hashCode());
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
		RodzajProduktu other = (RodzajProduktu) obj;
		if (rdpNazwa == null) {
			if (other.rdpNazwa != null)
				return false;
		} else if (!rdpNazwa.equals(other.rdpNazwa))
			return false;
		return true;
	}

}