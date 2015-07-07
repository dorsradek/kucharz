package pl.dors.radoslaw.app.kucharz.model;

import java.io.Serializable;
import java.util.Set;

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
 * The persistent class for the miara_produktu database table.
 * 
 */
@Entity
@Table(name = "miara_produktu")
@NamedQuery(name = "MiaraProduktu.findAll", query = "SELECT m FROM MiaraProduktu m")
public class MiaraProduktu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "mip_id")
	private Long mipId;

	@Column(name = "mip_nazwa")
	private String mipNazwa;

	@Column(name = "mip_skrot")
	private String mipSkrot;

	// bi-directional many-to-one association to Produkt
	@OneToMany(mappedBy = "miaraProduktu")
	@JsonManagedReference("miara")
	private Set<Produkt> produkty;

	public MiaraProduktu() {
	}

	public Long getMipId() {
		return this.mipId;
	}

	public void setMipId(Long mipId) {
		this.mipId = mipId;
	}

	public String getMipNazwa() {
		return this.mipNazwa;
	}

	public void setMipNazwa(String mipNazwa) {
		this.mipNazwa = mipNazwa;
	}

	public String getMipSkrot() {
		return this.mipSkrot;
	}

	public void setMipSkrot(String mipSkrot) {
		this.mipSkrot = mipSkrot;
	}

	public Set<Produkt> getProdukty() {
		return this.produkty;
	}

	public void setProdukty(Set<Produkt> produkty) {
		this.produkty = produkty;
	}

	public Produkt addProdukty(Produkt produkty) {
		getProdukty().add(produkty);
		produkty.setMiaraProduktu(this);

		return produkty;
	}

	public Produkt removeProdukty(Produkt produkty) {
		getProdukty().remove(produkty);
		produkty.setMiaraProduktu(null);

		return produkty;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mipNazwa == null) ? 0 : mipNazwa.hashCode());
		result = prime * result + ((mipSkrot == null) ? 0 : mipSkrot.hashCode());
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
		MiaraProduktu other = (MiaraProduktu) obj;
		if (mipNazwa == null) {
			if (other.mipNazwa != null)
				return false;
		} else if (!mipNazwa.equals(other.mipNazwa))
			return false;
		if (mipSkrot == null) {
			if (other.mipSkrot != null)
				return false;
		} else if (!mipSkrot.equals(other.mipSkrot))
			return false;
		return true;
	}

}