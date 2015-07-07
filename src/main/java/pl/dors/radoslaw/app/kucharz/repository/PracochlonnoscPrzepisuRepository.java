package pl.dors.radoslaw.app.kucharz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pl.dors.radoslaw.app.kucharz.model.PracochlonnoscPrzepisu;

@Repository
public interface PracochlonnoscPrzepisuRepository extends JpaRepository<PracochlonnoscPrzepisu, Long> {

	@Query("from PracochlonnoscPrzepisu p where p.pppNazwa = :nazwa")
	PracochlonnoscPrzepisu findByNazwa(@Param("nazwa") String nazwa);
}