package com.hibcode.beerstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hibcode.beerstore.model.Beer;
import com.hibcode.beerstore.repository.Beers;
import com.hibcode.beerstore.service.exception.BeerAlreadyExistException;

@Service
public class BeerService {

	private Beers beers;
	
	public BeerService(@Autowired final Beers beers) {
		this.beers = beers;
	}
	
	public Beer save(final Beer beer) {
		verifyIfBeerExists(beer);
		return beers.save(beer);
	}

	private void verifyIfBeerExists(final Beer beer) {
		Optional<Beer> beerNamedAndType = beers.findByNameAndType(beer.getName(), beer.getType());
		
		if (beerNamedAndType.isPresent() 
						&& (beer.isNew() 
								|| isUpdatingToADifferentBeer(beer, beerNamedAndType))) {
			throw new BeerAlreadyExistException();
		}
	}

	private boolean isUpdatingToADifferentBeer(final Beer beer, Optional<Beer> beerNamedAndType) {
		return beer.alreadyExist() && !beerNamedAndType.get().equals(beer);
	}
	
	public List<Beer> findAll(){
		return beers.findAll();
	}

}
