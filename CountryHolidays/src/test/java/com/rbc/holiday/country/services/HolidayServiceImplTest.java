package com.rbc.holiday.country.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.rbc.holiday.country.CountryAccessBean;
import com.rbc.holiday.country.HolidayAccessBean;
import com.rbc.holiday.country.repository.CountryRepository;
import com.rbc.holiday.country.repository.HolidayRepository;
import com.rbc.holiday.exception.DataNotFoundException;
import com.rbc.holiday.exception.ResourceAlreadyExistsException;

//@ExtendWith(SpringExtension.class)
class HolidayServiceImplTest {
	
	@Mock
	private CountryRepository countryRepository;
	
	@Mock
	HolidayRepository holidayRepository;
	
	@InjectMocks
	HolidayServiceImpl holidayService;
	 
	private CountryAccessBean countryAccessBean ;
	private HolidayAccessBean holidayAccessBean ;
	 
	 
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this); // Initializes mocks
		//Check countryId
		countryAccessBean = new CountryAccessBean();
		countryAccessBean.setCountryId(1L);
		countryAccessBean.setCountryName("Canada");
		countryAccessBean.setStatus(true);
		
		//Now check holiday is present with same name or not
		  holidayAccessBean = new HolidayAccessBean();
		 holidayAccessBean.setCountryAccessBean(countryAccessBean);
		 holidayAccessBean.setHolidayDate("10-08-2024");
		 holidayAccessBean.setHolidayId(1L);
		 holidayAccessBean.setHolidayName("Holiday Canada-1");
		 
	}
	

	@Test
	void testCreateHoliday() {
		
		
		
		when(countryRepository.findById(countryAccessBean.getCountryId())).thenReturn(Optional.of(countryAccessBean));
		 when(holidayRepository.existsByHolidayName(holidayAccessBean.getHolidayName())).thenReturn(false);
		 when(holidayRepository.save(holidayAccessBean)).thenReturn(holidayAccessBean);
	
		 
		 HolidayAccessBean newHoliday = holidayService.createHoliday(holidayAccessBean);
		 
		 //Assert
		 assertNotNull(newHoliday);
		 assertEquals(holidayAccessBean.getHolidayName(), newHoliday.getHolidayName());
		 assertEquals(holidayAccessBean.getCountryAccessBean().getCountryName(), newHoliday.getCountryAccessBean().getCountryName());
		 
		 verify(countryRepository,times(1)).findById(countryAccessBean.getCountryId());
		 verify(holidayRepository,times(1)).existsByHolidayName(holidayAccessBean.getHolidayName());
		 verify(holidayRepository,times(1)).save(holidayAccessBean);
		
	}
	
	//DataNotFoundException
	//@Test()
	public void testCreateCountryHolidayNotFound() {
		
	//	when(countryRepository.findById(countryAccessBean.getCountryId())).thenReturn(Optional.empty());
		try {
		holidayService.createHoliday(holidayAccessBean);//Exception throw
		}
		catch(DataNotFoundException ex) {
			assertEquals("Country Not Exist with Country Id:"+holidayAccessBean.getCountryAccessBean().getCountryId(), ex.getMessage());
		}
		
		
	}
	
	//ResourceAlreadyExistsException
	@Test()
	public void testHolidayNameResourceAlreadyExists() {
		when(countryRepository.findById(countryAccessBean.getCountryId())).thenReturn(Optional.of(countryAccessBean));
		when(holidayRepository.existsByHolidayName(holidayAccessBean.getHolidayName())).thenReturn(true);
		try {
			holidayService.createHoliday(holidayAccessBean);
		}
		catch(ResourceAlreadyExistsException ex) {
			System.out.println(ex);
			assertEquals("Holiday Name already exists with name: " + holidayAccessBean.getHolidayName(), ex.getMessage());
		}
	}
	
	@Test
	void testfindAllHolidayByCountryId() {
		when(holidayRepository.findAllByCountryId(countryAccessBean.getCountryId())).thenReturn(List.of(holidayAccessBean));
		
		List<HolidayAccessBean> holidayList = holidayRepository.findAllByCountryId(countryAccessBean.getCountryId());
		
		assertNotNull(holidayList);
		assertEquals(1, holidayList.size());
		
	}
	
	@Test
	void testfindAllHolidayByCountryIdDataNotFoundException() {
		when(holidayRepository.findAllByCountryId(countryAccessBean.getCountryId())).thenReturn(Collections.emptyList());
		
		try {
			 holidayRepository.findAllByCountryId(countryAccessBean.getCountryId());
		}
		catch(DataNotFoundException ex) {
			assertEquals("Holiday Not Exist with Country Id:"+countryAccessBean.getCountryId(), ex.getMessage());
		}
	}
	
	@Test
	void testUpdateHoliday() {
		when(holidayRepository.findById(holidayAccessBean.getHolidayId())).thenReturn(Optional.of(holidayAccessBean));
		when(holidayRepository.save(holidayAccessBean)).thenReturn(holidayAccessBean);
		
		HolidayAccessBean updateHoliday = new HolidayAccessBean();
		updateHoliday.setHolidayName("Holiday Canada-2");
		
		HolidayAccessBean result = holidayService.updateHoliday(holidayAccessBean.getHolidayId(), updateHoliday);
		
		assertNotNull(result);
		assertEquals("Holiday Canada-2", result.getHolidayName());
	}
	
	@Test
	void testUpdateHolidayDataNotFoundException() {
		when(holidayRepository.findById(holidayAccessBean.getHolidayId())).thenReturn(Optional.empty());
		
		try {
			
		}
		catch(DataNotFoundException ex) {
			assertEquals("Holiday Not Exist with holiday Id:"+holidayAccessBean.getHolidayId(), ex.getMessage());
		}
	}
	
	@Test
	void testfindAll() {
		when(holidayRepository.findAll()).thenReturn(List.of(holidayAccessBean));
		
		List<HolidayAccessBean> list = holidayRepository.findAll();
		
		assertNotNull(list);
		assertEquals(1, list.size());
		
	}
	
}
