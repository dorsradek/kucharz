package pl.dors.radoslaw.app.kucharz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pl.dors.radoslaw.app.kucharz.model.MiaraProduktu;

@Repository
public interface MiaraProduktuRepository extends JpaRepository<MiaraProduktu, Long> {

	@Query("from MiaraProduktu m where m.mipNazwa = :nazwa")
	MiaraProduktu findByNazwa(@Param("nazwa") String nazwa);
}