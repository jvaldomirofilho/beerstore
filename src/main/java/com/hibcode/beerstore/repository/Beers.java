package com.hibcode.beerstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hibcode.beerstore.model.Beer;

public interface Beers extends JpaRepository<Beer, Long>{

}
