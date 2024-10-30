package com.rbc.holiday.country.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbc.holiday.country.CountryAccessBean;
import com.rbc.holiday.country.repository.CountryRepository;
import com.rbc.holiday.exception.ResourceAlreadyExistsException;

@Service("countryService")
public class CountryServiceImpl implements CountryService{
	
	@Autowired
	private CountryRepository countryRepository;

	@Override
	public CountryAccessBean createCountry(CountryAccessBean countryAccessBean) {
		 // Check if the country already exists based on its name
        if (countryRepository.existsByCountryName(countryAccessBean.getCountryName())) {
            throw new ResourceAlreadyExistsException("Country already exists with name: " + countryAccessBean.getCountryName());
        }
        // Save if not duplicate
        return countryRepository.save(countryAccessBean);
	}

}
