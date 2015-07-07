package pl.dors.radoslaw.app.kucharz.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.dors.radoslaw.app.kucharz.model.RodzajProduktu;
import pl.dors.radoslaw.app.kucharz.repository.RodzajProduktuRepository;

@SuppressWarnings("serial")
@Service
public class RodzajProduktuService implements Serializable {

	@Autowired
	private RodzajProduktuRepository rodzajProduktuRepository;

	@Transactional(readOnly = true)
	public List<RodzajProduktu> findAll() {
		return rodzajProduktuRepository.findAll();
	}

}
