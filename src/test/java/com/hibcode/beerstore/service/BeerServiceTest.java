package com.hibcode.beerstore.service;

import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.hibcode.beerstore.model.Beer;
import com.hibcode.beerstore.model.BeerType;
import com.hibcode.beerstore.repository.Beers;
import com.hibcode.beerstore.service.exception.BeerAlreadyExistException;

public class BeerServiceTest {
	
	@Mock
	private Beers beersMocked;
	private BeerService beerService;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		beerService = new BeerService(beersMocked);
	}
	
	@Test(expected = BeerAlreadyExistException.class)
	public void should_deny_creation_of_beer_that_exists() {
		Beer beerInDatabase = new Beer();
		beerInDatabase.setId(10L);
		beerInDatabase.setName("Skol");
		beerInDatabase.setType(BeerType.LAGER);
		beerInDatabase.setVolume(new BigDecimal("300"));
		
		when(beersMocked.findByNameAndType("Skol", BeerType.LAGER)).thenReturn(Optional.of(beerInDatabase));
		
		Beer newBeer = new Beer();
		newBeer.setName("Skol");
		newBeer.setType(BeerType.LAGER);
		newBeer.setVolume(new BigDecimal("300"));
		
		beerService.save(newBeer);
		
	}
	
	
	@Test
	public void should_create_new_beer() {
		Beer newBeer = new Beer();
		newBeer.setName("Skol");
		newBeer.setType(BeerType.LAGER);
		newBeer.setVolume(new BigDecimal("300"));
		
		Beer newBeerInDatabase = new Beer();
		newBeerInDatabase.setId(10L);
		newBeerInDatabase.setName("Skol");
		newBeerInDatabase.setType(BeerType.LAGER);
		newBeerInDatabase.setVolume(new BigDecimal("300"));
		
		when(beersMocked.save(newBeer)).thenReturn(newBeerInDatabase);
		
		Beer beerSave = beerService.save(newBeer);
		
		assertThat(beerSave.getId(), equalTo(10L));
		assertThat(beerSave.getName(), equalTo("Skol"));
		assertThat(beerSave.getType(), equalTo(BeerType.LAGER));
	}
	
	@Test
	public void should_update_beer() {
		Beer beerInDatabase = new Beer();
		beerInDatabase.setId(10L);
		beerInDatabase.setName("Skol");
		beerInDatabase.setType(BeerType.LAGER);
		beerInDatabase.setVolume(new BigDecimal("300"));
		
		Beer updateBeer = new Beer();
		updateBeer.setId(10L);
		updateBeer.setName("Itaipava");
		updateBeer.setType(BeerType.PILSEN);
		updateBeer.setVolume(new BigDecimal("250"));
		
		when(beersMocked.save(beerInDatabase)).thenReturn(updateBeer);
		
		Beer beerUpdate = beerService.save(beerInDatabase);
		
		assertThat(beerUpdate.getId(), equalTo(updateBeer.getId()));
		assertThat(beerUpdate.getName(), equalTo(updateBeer.getName()));
		assertThat(beerUpdate.getType(), equalTo(updateBeer.getType()));
		
	}
	

}
