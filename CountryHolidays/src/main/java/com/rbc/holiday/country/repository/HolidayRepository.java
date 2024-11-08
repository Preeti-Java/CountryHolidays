package com.rbc.holiday.country.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rbc.holiday.country.HolidayAccessBean;

@Repository
public interface HolidayRepository extends JpaRepository<HolidayAccessBean, Long> {


	// holidays by countryId and where the country is active
    @Query("SELECT h FROM HolidayAccessBean h WHERE h.countryAccessBean.id = :countryId AND h.countryAccessBean.status = true")
	List<HolidayAccessBean> findAllByCountryId(Long countryId);

	boolean existsByHolidayName(String holidayName);

	List<HolidayAccessBean> findAllByHolidayDate(String holidayDate);

}
