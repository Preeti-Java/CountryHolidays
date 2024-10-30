package com.rbc.holiday.country.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rbc.holiday.country.CountryAccessBean;
import com.rbc.holiday.country.services.CountryService;
import com.rbc.holiday.exception.BadRequestException;


@ExtendWith(SpringExtension.class)
class CountryControllerTest {
	
	@InjectMocks
    private CountryController countryController;

    @Mock
    private CountryService countryService;

    private ObjectMapper objectMapper = new ObjectMapper();
    
   
	@Test
    public void testCreateCountry_Success() throws IOException, BadRequestException {
        // Read data from JSON file -> You can change country
        CountryAccessBean requestBean = objectMapper.readValue(new File("src/test/java/request_country.json"), CountryAccessBean.class);

        // return the same country with an ID
        CountryAccessBean responseBean = new CountryAccessBean();
        responseBean.setCountryId(1L);
        responseBean.setCountryName(requestBean.getCountryName());
        responseBean.setStatus(true);

        when(countryService.createCountry(any(CountryAccessBean.class))).thenReturn(responseBean);

        // Call the controller method
        ResponseEntity<CountryAccessBean> response = countryController.createCountry(requestBean);

        // Assertions
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(responseBean.getCountryId(), response.getBody().getCountryId());
        assertEquals(responseBean.getCountryName(), response.getBody().getCountryName());
    }

	@Test
	public void testCreateCountry_BadRequest() {
	    try {
	        countryController.createCountry(null);
	    } catch (BadRequestException ex) {
	        assertEquals("Country Request Data is empty", ex.getMessage());
	        return; // Ensure the test does not fail if exception is correctly caught
	    }
	    fail("Expected BadRequestException to be thrown");
	}

	

}
