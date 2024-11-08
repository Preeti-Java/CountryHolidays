package com.rbc.holiday.country;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="holiday")
public class HolidayAccessBean {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "holidayId")
	private Long holidayId;
	
	@Column(name = "holidayName")
	private String holidayName;
	
	@Column(name="holidayDate")
	private String holidayDate;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "countryId", nullable = false)
    private CountryAccessBean countryAccessBean;

	
	public HolidayAccessBean(Long holidayId, String holidayName) {
		super();
		this.holidayId = holidayId;
		this.holidayName = holidayName;
	}
	
	

	public HolidayAccessBean() {
	}



	public Long getHolidayId() {
		return holidayId;
	}

	public void setHolidayId(Long holidayId) {
		this.holidayId = holidayId;
	}

	public String getHolidayName() {
		return holidayName;
	}

	public void setHolidayName(String holidayName) {
		this.holidayName = holidayName;
	}

	public CountryAccessBean getCountryAccessBean() {
		return countryAccessBean;
	}

	public void setCountryAccessBean(CountryAccessBean countryAccessBean) {
		this.countryAccessBean = countryAccessBean;
	}



	public String getHolidayDate() {
		return holidayDate;
	}



	public void setHolidayDate(String holidayDate) {
		this.holidayDate = holidayDate;
	}
	
	

}
