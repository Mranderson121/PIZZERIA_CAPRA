package com.pizzeria.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.pizzeria.model.Utente;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Integer> {

	@Query("SELECT u FROM Utente u LEFT JOIN FETCH u.pizze WHERE u.username = :username AND u.password = :password")
	Utente findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

	@Query("SELECT u FROM Utente u JOIN FETCH u.pizze WHERE u.id = :idUtente")
	Optional<Utente> findByIdWithPizze(@Param("idUtente") int idUtente);

}