package pl.dors.radoslaw.app.kucharz.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.dors.radoslaw.app.kucharz.model.PracochlonnoscPrzepisu;
import pl.dors.radoslaw.app.kucharz.repository.PracochlonnoscPrzepisuRepository;

@SuppressWarnings("serial")
@Service
public class PracochlonnoscPrzepisuService implements Serializable {

	@Autowired
	private PracochlonnoscPrzepisuRepository pracochlonnoscPrzepisuRepository;

	@Transactional(readOnly = true)
	public List<PracochlonnoscPrzepisu> findAll() {
		return pracochlonnoscPrzepisuRepository.findAll();
	}
}
