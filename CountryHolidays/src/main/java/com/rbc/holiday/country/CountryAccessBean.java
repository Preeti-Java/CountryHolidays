package com.rbc.holiday.country;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="country")
public class CountryAccessBean {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "countryId")
	private Long countryId;
	
	@Column(name = "countryName")
	private String countryName;
	
	@Column(name = "status")
	private Boolean status;//Active or Inactive
	
	public CountryAccessBean(Long countryId, String countryName, Boolean status) {
		super();
		this.countryId = countryId;
		this.countryName = countryName;
		this.status = status;
	}

	public CountryAccessBean() {
	}

	public Long getCountryId() {
		return countryId;
	}

	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
	
	

}
