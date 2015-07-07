package pl.dors.radoslaw.app.kucharz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pl.dors.radoslaw.app.kucharz.model.Przepis;

@Repository
public interface PrzepisRepository extends JpaRepository<Przepis, Long> {

	@Query("select count(p) from Produkt p where p.miaraProduktu.mipId = :miaraProduktuId")
	Long countWithMiaraProduktu(@Param("miaraProduktuId") Long miaraProduktuId);

}
