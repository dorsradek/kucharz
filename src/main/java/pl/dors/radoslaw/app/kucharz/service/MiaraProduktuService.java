package pl.dors.radoslaw.app.kucharz.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.dors.radoslaw.app.kucharz.model.MiaraProduktu;
import pl.dors.radoslaw.app.kucharz.repository.MiaraProduktuRepository;
import pl.dors.radoslaw.app.kucharz.repository.PrzepisRepository;

@SuppressWarnings("serial")
@Service
public class MiaraProduktuService implements Serializable {

	@Autowired
	private MiaraProduktuRepository miaraProduktuRepository;

	@Autowired
	private PrzepisRepository przepisRepository;

	@Transactional(readOnly = true)
	public List<MiaraProduktu> findAll() {
		return miaraProduktuRepository.findAll(sortByNazwa());
	}

	@Transactional
	public MiaraProduktu save(MiaraProduktu miaraProduktu) {
		return miaraProduktuRepository.save(miaraProduktu);
	}

	@Transactional
	public boolean delete(MiaraProduktu miaraProduktu) {
		Long countedMiaraProduktu = przepisRepository.countWithMiaraProduktu(miaraProduktu.getMipId());
		if (countedMiaraProduktu == 0) {
			miaraProduktuRepository.delete(miaraProduktu);
			return true;
		} else {
			return false;
		}
	}

	private Sort sortByNazwa() {
		return new Sort(Sort.Direction.ASC, "mipNazwa");
	}

}
