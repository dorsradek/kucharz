package pl.dors.radoslaw.app.kucharz.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * The persistent class for the rola database table.
 * 
 */
@Entity
@NamedQuery(name = "Rola.findAll", query = "SELECT r FROM Rola r")
@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class, property = "@UUID", scope = Rola.class)
public class Rola implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "rol_id")
	private Long rolId;

	@Column(name = "rol_rola")
	private String rolRola;

	// bi-directional many-to-many association to Uzytkownik
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "uzytkownik_rola", joinColumns = { @JoinColumn(name = "uro_rola_id") }, inverseJoinColumns = {
			@JoinColumn(name = "uro_uzytkownik_id") })
	@JsonManagedReference("rola")
	private List<Uzytkownik> uzytkowniks;

	public Rola() {
	}

	public Long getRolId() {
		return this.rolId;
	}

	public void setRolId(Long rolId) {
		this.rolId = rolId;
	}

	public String getRolRola() {
		return this.rolRola;
	}

	public void setRolRola(String rolRola) {
		this.rolRola = rolRola;
	}

	public List<Uzytkownik> getUzytkowniks() {
		return this.uzytkowniks;
	}

	public void setUzytkowniks(List<Uzytkownik> uzytkowniks) {
		this.uzytkowniks = uzytkowniks;
	}

}