package pl.dors.radoslaw.app.kucharz.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.dors.radoslaw.app.kucharz.model.KategoriaPrzepisu;
import pl.dors.radoslaw.app.kucharz.repository.KategoriaPrzepisuRepository;

@SuppressWarnings("serial")
@Service
public class KategoriaPrzepisuService implements Serializable {

	@Autowired
	private KategoriaPrzepisuRepository kategoriaPrzepisuRepository;

	@Transactional(readOnly = true)
	public List<KategoriaPrzepisu> findAll() {
		return kategoriaPrzepisuRepository.findAll();
	}

}
