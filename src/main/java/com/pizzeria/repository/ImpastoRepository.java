package com.pizzeria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pizzeria.model.Impasto;

public interface ImpastoRepository extends JpaRepository<Impasto, Integer> {

}
