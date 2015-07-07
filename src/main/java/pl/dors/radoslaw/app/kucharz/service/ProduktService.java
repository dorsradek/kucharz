package pl.dors.radoslaw.app.kucharz.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.dors.radoslaw.app.kucharz.model.Produkt;
import pl.dors.radoslaw.app.kucharz.repository.ProduktRepository;
import pl.dors.radoslaw.app.kucharz.repository.PrzepisProduktRepository;

@SuppressWarnings("serial")
@Service
public class ProduktService implements Serializable {

	@Autowired
	private ProduktRepository produktRepository;

	@Autowired
	private PrzepisProduktRepository przepisProduktRepository;

	@Transactional(readOnly = true)
	public List<Produkt> findAll() {
		return produktRepository.findAll(sortByNazwa());
	}

	@Transactional(readOnly = true)
	public Produkt findById(long id) {
		return produktRepository.findOne(id);
	}

	@Transactional
	public Produkt save(Produkt produkt) {
		return produktRepository.save(produkt);
	}

	@Transactional
	public boolean delete(Produkt produkt) {
		Long countedProduktPrzepis = przepisProduktRepository.countWithProdukt(produkt.getPrdId());
		if (countedProduktPrzepis == 0) {
			produktRepository.delete(produkt);
			return true;
		} else {
			return false;
		}
	}

	private Sort sortByNazwa() {
		return new Sort(Sort.Direction.ASC, "prdNazwa");
	}

}
