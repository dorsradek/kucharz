package pl.dors.radoslaw.app.kucharz.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * The persistent class for the uzytkownik database table.
 * 
 */
@Entity
@NamedQuery(name = "Uzytkownik.findAll", query = "SELECT u FROM Uzytkownik u")
@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class, property = "@UUID", scope = Uzytkownik.class)
public class Uzytkownik implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "uzt_id")
	private Long uztId;

	@Column(name = "uzt_login")
	private String uztLogin;

	@Column(name = "uzt_password")
	private String uztPassword;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinTable(name = "uzytkownik_rola", joinColumns = { @JoinColumn(name = "uro_uzytkownik_id") }, inverseJoinColumns = {
			@JoinColumn(name = "uro_rola_id") })
	@JsonBackReference("rola")
	private Rola rola;

	public Uzytkownik() {
	}

	public Long getUztId() {
		return this.uztId;
	}

	public void setUztId(Long uztId) {
		this.uztId = uztId;
	}

	public String getUztLogin() {
		return this.uztLogin;
	}

	public void setUztLogin(String uztLogin) {
		this.uztLogin = uztLogin;
	}

	public String getUztPassword() {
		return this.uztPassword;
	}

	public void setUztPassword(String uztPassword) {
		this.uztPassword = uztPassword;
	}

	public Rola getRola() {
		return rola;
	}

	public void setRola(Rola rola) {
		this.rola = rola;
	}

}