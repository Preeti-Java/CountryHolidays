package com.rbc.holiday.country.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rbc.holiday.country.CountryAccessBean;
import com.rbc.holiday.country.services.CountryService;
import com.rbc.holiday.exception.BadRequestException;

@RestController
@RequestMapping("/country")
public class CountryController {
	
	@Autowired
	private CountryService countryService;
	
	//Create country -
	@PostMapping("/saveCountry")
	public ResponseEntity<CountryAccessBean> createCountry(@RequestBody(required = true) CountryAccessBean countryAccessBean) throws BadRequestException{
		if(countryAccessBean == null)
			throw new BadRequestException("Country Request Data is empty");
		
		CountryAccessBean newCountry = countryService.createCountry(countryAccessBean);
		
		return new ResponseEntity<>(newCountry, HttpStatus.CREATED);
	}
	

}
