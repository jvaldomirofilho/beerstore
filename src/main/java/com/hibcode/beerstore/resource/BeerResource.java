package com.hibcode.beerstore.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hibcode.beerstore.model.Beer;
import com.hibcode.beerstore.service.BeerService;

@RestController
@RequestMapping("/beers")
public class BeerResource {
	
	@Autowired
	private BeerService beerService;
	
	@GetMapping
	public List<Beer> all(){
		return beerService.findAll();
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Beer create(@Valid @RequestBody Beer beer) {
		return beerService.save(beer);
	}
	
	@PutMapping("/{id}")
	public Beer update(@PathVariable Long id, @Valid @RequestBody Beer beer) {
		beer.setId(id);
		beerService.save(beer);
		return beer;
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		beerService.delete(id);
	}

}
