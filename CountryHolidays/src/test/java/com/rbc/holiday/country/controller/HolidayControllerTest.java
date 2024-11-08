package com.rbc.holiday.country.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.rbc.holiday.country.HolidayAccessBean;
import com.rbc.holiday.country.services.HolidayService;
import com.rbc.holiday.exception.BadRequestException;
import com.rbc.holiday.exception.DataNotFoundException;

@ExtendWith(SpringExtension.class)
@TestMethodOrder(OrderAnnotation.class)
class HolidayControllerTest {

 	@InjectMocks
    private HolidayController holidayController;

    @Mock
    private HolidayService holidayService;

    // Test for Get All Holidays
    @Test
    public void testGetAll_Success() throws BadRequestException {
    	
        List<HolidayAccessBean> mockHolidayList = new ArrayList<>();
        mockHolidayList.add(new HolidayAccessBean(1L, "Holiday1"));
        mockHolidayList.add(new HolidayAccessBean(2L, "Holiday2"));

        when(holidayService.findAll()).thenReturn(mockHolidayList);

        ResponseEntity<List<HolidayAccessBean>> response = holidayController.getAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }

    @Test()
    public void testGetAll_NoHolidays() throws DataNotFoundException {
    	 try {
        when(holidayService.findAll()).thenReturn(new ArrayList<>());

        holidayController.getAll();
	    } catch (DataNotFoundException ex) {
	        assertEquals("No Holiday Found in record", ex.getMessage());
	        return; 
	    }
	    fail("Expected DataNotFoundException to be thrown");
    }

 
 
 // Test for Get All Holidays by Country ID
    @Test
    public void testGetAllHolidaysByCountryId_Success() throws BadRequestException {
        List<HolidayAccessBean> mockHolidayList = new ArrayList<>();
        mockHolidayList.add(new HolidayAccessBean(1L, "Holiday1"));

        when(holidayService.findAllHolidayByCountryId(eq(1L))).thenReturn(mockHolidayList);

        ResponseEntity<List<HolidayAccessBean>> response = holidayController.getAllHolidaysByCountryIdAndActive(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
    }

    @Test()
    public void testGetAllHolidaysByCountryId_NoHolidays() throws BadRequestException {
    	
    	 try {
        when(holidayService.findAllHolidayByCountryId(eq(1L))).thenReturn(new ArrayList<>());

        holidayController.getAllHolidaysByCountryIdAndActive(1L);
    	 } catch (BadRequestException ex) {
 	        assertEquals("No Holiday Found in record with Id: "+1L, ex.getMessage());
 	        return; 
 	    }
    	 catch (DataNotFoundException ex) {
  	        assertEquals("No Holiday Found in record with Id: "+1L, ex.getMessage());
  	        return; 
    	 }
 	    fail("Expected BadRequestException/DataNotFoundException to be thrown");
    }

    @Test()
    public void testGetAllHolidaysByCountryId_NullCountryId() throws BadRequestException {
        
        try {
        	//holidayController.getAllHolidaysByCountryIdAndActive(null);
	    } catch (BadRequestException ex) {
	        assertEquals("CountryId Request is empty", ex.getMessage());
	        return; 
	    }
	    fail("Expected BadRequestException to be thrown");
    }

    // Test for Create Holiday
    @Before
    public void testCreateHoliday_Success() throws BadRequestException {
        HolidayAccessBean holiday = new HolidayAccessBean(1L, "New Holiday");

        when(holidayService.createHoliday(any(HolidayAccessBean.class))).thenReturn(holiday);

        ResponseEntity<HolidayAccessBean> response = holidayController.createHoliday(holiday);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("New Holiday", response.getBody().getHolidayName());
    }

    @Test()
    public void testCreateHoliday_BadRequest() throws BadRequestException {
        try {
        	 holidayController.createHoliday(null);
	    } catch (BadRequestException ex) {
	        assertEquals("Holiday Request data is empty", ex.getMessage());
	        return; 
	    }
	    fail("Expected BadRequestException to be thrown");
    }

    // Test for Update Holiday
    @Test
    public void testUpdateHoliday_Success() {
        HolidayAccessBean updatedHoliday = new HolidayAccessBean(1L, "Updated Holiday");

        when(holidayService.updateHoliday(eq(1L), any(HolidayAccessBean.class))).thenReturn(updatedHoliday);

        ResponseEntity<HolidayAccessBean> response = holidayController.updateHoliday(1L, updatedHoliday);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Updated Holiday", response.getBody().getHolidayName());
    }

}
