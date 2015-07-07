package pl.dors.radoslaw.app.kucharz.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * The persistent class for the przepis_produkt database table.
 * 
 */
@Entity
@Table(name = "przepis_produkt")
@NamedQuery(name = "PrzepisProdukt.findAll", query = "SELECT p FROM PrzepisProdukt p")
public class PrzepisProdukt implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ppr_id")
	private Long pprId;

	@Column(name = "ppr_ilosc")
	private double pprIlosc;

	// bi-directional many-to-one association to Produkt
	@ManyToOne
	@JoinColumn(name = "ppr_produkt_fk")
	@JsonBackReference("produkt")
	private Produkt produkt;

	// bi-directional many-to-one association to Przepis
	@ManyToOne
	@JoinColumn(name = "ppr_przepis_fk")
	@JsonBackReference("przepis")
	private Przepis przepis;

	public PrzepisProdukt() {
	}

	public Long getPprId() {
		return this.pprId;
	}

	public void setPprId(Long pprId) {
		this.pprId = pprId;
	}

	public double getPprIlosc() {
		return this.pprIlosc;
	}

	public void setPprIlosc(double pprIlosc) {
		this.pprIlosc = pprIlosc;
	}

	public Produkt getProdukt() {
		return this.produkt;
	}

	public void setProdukt(Produkt produkt) {
		this.produkt = produkt;
	}

	public Przepis getPrzepis() {
		return this.przepis;
	}

	public void setPrzepis(Przepis przepis) {
		this.przepis = przepis;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(pprIlosc);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((produkt == null) ? 0 : produkt.hashCode());
		result = prime * result + ((przepis == null) ? 0 : przepis.hashCode());
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
		PrzepisProdukt other = (PrzepisProdukt) obj;
		if (Double.doubleToLongBits(pprIlosc) != Double.doubleToLongBits(other.pprIlosc))
			return false;
		if (produkt == null) {
			if (other.produkt != null)
				return false;
		} else if (!produkt.equals(other.produkt))
			return false;
		if (przepis == null) {
			if (other.przepis != null)
				return false;
		} else if (!przepis.equals(other.przepis))
			return false;
		return true;
	}

}