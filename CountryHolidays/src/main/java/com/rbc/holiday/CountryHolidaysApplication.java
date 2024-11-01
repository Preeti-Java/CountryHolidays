package com.rbc.holiday;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@SpringBootApplication
@EnableWebMvc
public class CountryHolidaysApplication {

	public static void main(String[] args) {
		SpringApplication.run(CountryHolidaysApplication.class, args);
	}

}
