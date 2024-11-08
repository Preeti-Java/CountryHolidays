package com.rbc.holiday.country.services;

import java.util.List;

import com.rbc.holiday.country.HolidayAccessBean;

public interface HolidayService {

	HolidayAccessBean createHoliday(HolidayAccessBean holidayAccessBean);

	List<HolidayAccessBean> findAllHolidayByCountryId(Long countryId);

	HolidayAccessBean updateHoliday(Long holidayId, HolidayAccessBean holidayAccessBean);

	List<HolidayAccessBean> findAll();

	List<HolidayAccessBean> findAllHolidayByHolidayDate(String holidayDate);

}
