package com.rbc.holiday.country.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rbc.holiday.country.CountryAccessBean;

@Repository
public interface CountryRepository extends JpaRepository<CountryAccessBean, Long> {

	boolean existsByCountryName(String countryName);

}
