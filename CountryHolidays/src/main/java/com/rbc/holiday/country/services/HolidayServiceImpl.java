package com.rbc.holiday.country.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbc.holiday.country.CountryAccessBean;
import com.rbc.holiday.country.HolidayAccessBean;
import com.rbc.holiday.country.repository.CountryRepository;
import com.rbc.holiday.country.repository.HolidayRepository;
import com.rbc.holiday.exception.DataNotFoundException;
import com.rbc.holiday.exception.ResourceAlreadyExistsException;

@Service("holidayService")
public class HolidayServiceImpl implements HolidayService{
	
	@Autowired
	private HolidayRepository holidayRepository;
	
	@Autowired
	private CountryRepository countryRepository;

	@Override
	public HolidayAccessBean createHoliday(HolidayAccessBean holidayAccessBean) {
		Long countryId = holidayAccessBean.getCountryAccessBean().getCountryId();
		// Fetch the Country from the database
		Optional<CountryAccessBean> country = countryRepository.findById(countryId);
		if(country.isEmpty()) {
	       throw new DataNotFoundException("Country Not Exist with Country Id:"+countryId);
		}
		
		 // Check if the holiday already exists based on its name
        if (holidayRepository.existsByHolidayName(holidayAccessBean.getHolidayName())) {
            throw new ResourceAlreadyExistsException("Holiday Name already exists with name: " + holidayAccessBean.getHolidayName());
        }
        
		 holidayAccessBean.setCountryAccessBean(country.get());  
		 return holidayRepository.save(holidayAccessBean);
	}

	@Override
	public List<HolidayAccessBean> findAllHolidayByCountryId(Long countryId) {
		List<HolidayAccessBean> holidayList = holidayRepository.findAllByCountryId(countryId);
		 // Check if the holiday name is empty or not
		if(holidayList.isEmpty())
			throw new DataNotFoundException("Holiday Not Exist with Country Id:"+countryId);
		
		return holidayList;
	}

	@Override
	public HolidayAccessBean updateHoliday(Long holidayId, HolidayAccessBean updateHolidayAccessBean) {
		
		 Optional<HolidayAccessBean> existHoliday = holidayRepository.findById(holidayId);
		 // Check if the holiday  is exist or not
		 if(existHoliday.isEmpty()) 
			 throw new DataNotFoundException("Holiday Not Exist with holiday Id:"+holidayId);
		 
		 
			 HolidayAccessBean holidayAccessBean = existHoliday.get();
			 holidayAccessBean.setHolidayName(updateHolidayAccessBean.getHolidayName());
			 return holidayRepository.save(holidayAccessBean);
	}

	@Override
	public List<HolidayAccessBean> findAll() {
		
		List<HolidayAccessBean> holidayLists = holidayRepository.findAll();
		
		if(holidayLists.isEmpty())
			throw new DataNotFoundException("No Holiday Found");
		
		return holidayLists;
	}

}
