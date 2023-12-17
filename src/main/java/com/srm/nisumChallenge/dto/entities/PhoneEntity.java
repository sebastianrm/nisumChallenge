package com.srm.nisumChallenge.dto.entities;

import java.util.Collection;

import com.srm.nisumChallenge.dto.ParentPhone;
import com.srm.nisumChallenge.dto.request.PhoneRequest;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;

/**
 * Nisum Challenge
 * 
 * Represent one phone in data base raltioned with many users
 * 
 * @author Sebastian Retamal Morales
 * @since 2023-12-15
 * @version 0.0.1-SNAPSHOT
 */
@Entity
public class PhoneEntity extends ParentPhone {

	@ManyToMany(mappedBy = "phoneEntities")
	private Collection<UserEntity> userEntities;

	
	public PhoneEntity() {
		super();
	}

	/**
	 * 
	 * @param number
	 * @param cityCode
	 * @param countryCode
	 */
	public PhoneEntity(@NotBlank(message = "Number is mandatory") String number,
			@NotBlank(message = "City code is mandatory") String cityCode,
			@NotBlank(message = "Contry code is mandatory") String countryCode) {
		super(number, cityCode, countryCode);
	}

	public Collection<UserEntity> getUserEntities() {
		return userEntities;
	}

	public void setUserEntities(Collection<UserEntity> userEntities) {
		this.userEntities = userEntities;
	}

	@Override
	public PhoneRequest getPhonesRequest() {
		new PhoneRequest(number, cityCode, countryCode);
		return null;
	}

	@Override
	public PhoneEntity getPhoneEntity() {
		return this;
	}

}
