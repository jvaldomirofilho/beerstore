package com.hibcode.beerstore.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hibcode.beerstore.model.Beer;
import com.hibcode.beerstore.model.BeerType;

public interface Beers extends JpaRepository<Beer, Long>{
	
	Optional<Beer> findByNameAndType(String name, BeerType type);

}
