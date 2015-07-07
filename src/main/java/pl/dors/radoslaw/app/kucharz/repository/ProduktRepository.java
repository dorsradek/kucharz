package pl.dors.radoslaw.app.kucharz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.dors.radoslaw.app.kucharz.model.Produkt;

@Repository
public interface ProduktRepository extends JpaRepository<Produkt, Long> {

}