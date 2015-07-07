package pl.dors.radoslaw.app.kucharz.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.dors.radoslaw.app.kucharz.model.Produkt;
import pl.dors.radoslaw.app.kucharz.model.Przepis;
import pl.dors.radoslaw.app.kucharz.model.PrzepisProdukt;
import pl.dors.radoslaw.app.kucharz.repository.PrzepisProduktRepository;
import pl.dors.radoslaw.app.kucharz.repository.PrzepisRepository;

@SuppressWarnings("serial")
@Service
public class PrzepisService implements Serializable {

	@Autowired
	private PrzepisRepository przepisRepository;

	@Autowired
	private PrzepisProduktRepository przepisProduktRepository;

	@Transactional
	public void save(Przepis przepis) {

		przepis = przepisRepository.save(przepis);

		PrzepisProdukt przepisProdukt = null;
		for (Produkt produkt : przepis.getProdukty()) {
			przepisProdukt = new PrzepisProdukt();
			przepisProdukt.setPprIlosc(produkt.getIlosc());
			przepisProdukt.setProdukt(produkt);
			przepisProdukt.setPrzepis(przepis);
			przepisProdukt = przepisProduktRepository.save(przepisProdukt);
			przepis.addPrzepisProdukty(przepisProdukt);
		}
	}

	@Transactional(readOnly = true)
	public List<Przepis> findAll() {
		return przepisRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Przepis findOne(Long id) {
		return przepisRepository.findOne(id);
	}

	@Transactional(readOnly = true)
	public Przepis findOneAndReferences(Long id) {
		Przepis przepis = przepisRepository.findOne(id);

		for (PrzepisProdukt przepisProdukt : przepis.getPrzepisProdukty()) {
			Produkt produkt = przepisProdukt.getProdukt();
			produkt.setIlosc(przepisProdukt.getPprIlosc());
			przepis.getProdukty().add(produkt);
		}

		return przepis;
	}
}
