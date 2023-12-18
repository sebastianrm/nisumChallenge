package com.srm.nisumChallenge.dto.request;

import com.srm.nisumChallenge.dto.ParentPhone;
import com.srm.nisumChallenge.dto.entities.PhoneEntity;

import jakarta.validation.constraints.NotBlank;

/**
 * Nisum Challenge
 * 
 * Represents Phone number from request
 * 
 * @author Sebastian Retamal Morales
 * @since 2023-12-15
 * @version 0.0.1-SNAPSHOT
 * 
 */
public class PhoneRequest extends ParentPhone{

	
	public PhoneRequest() {
		super();
	}

	public PhoneRequest(@NotBlank(message = "Number is mandatory") String number,
			@NotBlank(message = "City code is mandatory") String citycode,
			@NotBlank(message = "Contry code is mandatory") String countryCode) {
		super(number, citycode, countryCode);
	}

	@Override
	public PhoneRequest getPhonesRequest() {
		
		return this;
	}

	@Override
	public PhoneEntity getPhoneEntity() {

		return new PhoneEntity(number,cityCode,countryCode);
		
	}

}
