package br.com.eduardo.bds02.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.eduardo.bds02.entities.City;

public interface CityRepository extends JpaRepository<City, Long>{

}
