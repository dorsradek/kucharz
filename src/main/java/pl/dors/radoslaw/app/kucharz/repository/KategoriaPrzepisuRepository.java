package pl.dors.radoslaw.app.kucharz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pl.dors.radoslaw.app.kucharz.model.KategoriaPrzepisu;

@Repository
public interface KategoriaPrzepisuRepository extends JpaRepository<KategoriaPrzepisu, Long> {

	@Query("from KategoriaPrzepisu k where k.kprNazwa = :nazwa")
	KategoriaPrzepisu findByNazwa(@Param("nazwa") String nazwa);
}