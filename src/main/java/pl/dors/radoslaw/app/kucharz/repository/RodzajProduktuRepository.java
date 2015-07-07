package pl.dors.radoslaw.app.kucharz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pl.dors.radoslaw.app.kucharz.model.RodzajProduktu;

@Repository
public interface RodzajProduktuRepository extends JpaRepository<RodzajProduktu, Long> {

	@Query("from RodzajProduktu r where r.rdpNazwa = :nazwa")
	RodzajProduktu findByNazwa(@Param("nazwa") String nazwa);
}