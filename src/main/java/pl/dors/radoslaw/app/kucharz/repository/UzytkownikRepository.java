package pl.dors.radoslaw.app.kucharz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.dors.radoslaw.app.kucharz.model.Uzytkownik;

@Repository
public interface UzytkownikRepository extends JpaRepository<Uzytkownik, Long> {

	public Uzytkownik findFirstByUztLogin(String login);
}