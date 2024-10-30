package com.rbc.holiday.country.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rbc.holiday.country.HolidayAccessBean;
import com.rbc.holiday.country.services.HolidayService;
import com.rbc.holiday.exception.BadRequestException;
import com.rbc.holiday.exception.DataNotFoundException;

@RestController
@RequestMapping("/holiday")
public class HolidayController {
	
	@Autowired
	private HolidayService holidayService;
	
	@GetMapping("/all")
	public ResponseEntity<List<HolidayAccessBean>> getAll() throws BadRequestException{
		
		List<HolidayAccessBean> holidayAccessBeansList = holidayService.findAll();
		if(holidayAccessBeansList.isEmpty())
			throw new DataNotFoundException("No Holiday Found in record");
		
		return new ResponseEntity<>(holidayAccessBeansList,HttpStatus.OK);
	}
	
	//Holiday List By Country Id
	@GetMapping("/{countryId}")
	public ResponseEntity<List<HolidayAccessBean>> getAllHolidaysByCountryIdAndActive(@PathVariable Long countryId) throws BadRequestException{
		if(countryId == null) 
			throw new BadRequestException("CountryId Request is empty");
		
		List<HolidayAccessBean> holidayAccessBeansList = holidayService.findAllHolidayByCountryId(countryId);
		if(holidayAccessBeansList.isEmpty())
			throw new DataNotFoundException("No Holiday Found in record with Id: "+countryId);
		
		return new ResponseEntity<>(holidayAccessBeansList,HttpStatus.OK);
	}
	
	//Add holidays for country
	@PostMapping("/saveHoliday")
	public ResponseEntity<HolidayAccessBean> createHoliday(@RequestBody(required = true) HolidayAccessBean holidayAccessBean){
		if(holidayAccessBean == null)
			throw new BadRequestException("Holiday Request data is empty");
		
		HolidayAccessBean newHoliday = holidayService.createHoliday(holidayAccessBean);
		
		return new ResponseEntity<>(newHoliday, HttpStatus.CREATED);
	}
	
	//Update holiday
    @PutMapping("/{holidayId}")
    public ResponseEntity<HolidayAccessBean> updateHoliday(
            @PathVariable Long holidayId, 
            @RequestBody HolidayAccessBean holidayAccessBean) {
        
        HolidayAccessBean updatedHoliday = holidayService.updateHoliday(holidayId, holidayAccessBean);
        return new ResponseEntity<>(updatedHoliday, HttpStatus.OK);
    }
	

}
