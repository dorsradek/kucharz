package pl.dors.radoslaw.app.kucharz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pl.dors.radoslaw.app.kucharz.model.PrzepisProdukt;

@Repository
public interface PrzepisProduktRepository extends JpaRepository<PrzepisProdukt, Long> {

	@Query("from PrzepisProdukt p where p.produkt.prdId = :produktId")
	List<PrzepisProdukt> findAllByProdukt(@Param("produktId") Long produktId);

	@Query("select count(p) from PrzepisProdukt p where p.produkt.prdId = :produktId")
	Long countWithProdukt(@Param("produktId") Long produktId);
}
