package com.hibcode.beerstore.service.exception;

import org.springframework.http.HttpStatus;

public class BeerAlreadyExistException extends BusinessException{
	public BeerAlreadyExistException() {
		super("beers-5", HttpStatus.BAD_REQUEST);
	}

	private static final long serialVersionUID = 1L;
	
}
