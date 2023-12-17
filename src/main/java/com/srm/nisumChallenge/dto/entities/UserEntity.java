package com.srm.nisumChallenge.dto.entities;

import java.util.ArrayList;
import java.util.Collection;

import com.srm.nisumChallenge.dto.ParentUser;
import com.srm.nisumChallenge.dto.request.PhoneRequest;
import com.srm.nisumChallenge.dto.request.UserRequest;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Nisum Challenge
 * 
 * Contain user representartion in the data base
 * 
 * @author Sebastian Retamal Morales
 * @since 2023-12-15
 * @version 0.0.1-SNAPSHOT
 */
@Entity
public class UserEntity extends ParentUser {

	@ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinTable(name = "user_phone", joinColumns = @JoinColumn(name = "fk_user", referencedColumnName = "email"), 
	inverseJoinColumns = @JoinColumn(name = "fk_phone", referencedColumnName = "id"))
	private Collection<PhoneEntity> phoneEntities;

	public UserEntity() {
		super();
	}


	public UserEntity(
			@Size(min = 17, max = 19, message = "&{size.email} '${validatedValue}' ") @NotBlank(message = "{notblank.email}") @Email(message = "{notvalid.email}") String email,
			@NotBlank(message = "{notblank.name}") String name,
			@NotBlank(message = "{notblank.password}") String password, Boolean isactive) {
		super(email, name, password, isactive);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Collection<PhoneEntity> getPhoneEntities() {
		return phoneEntities;
	}

	public void setPhoneEntities(Collection<PhoneEntity> phoneEntities) {
		this.phoneEntities = phoneEntities;
	}



	@Override
	public UserRequest getUserRequest() {

		UserRequest userRequest = new UserRequest(email, name, password, isActive);

		userRequest.setPhonesRequest(new ArrayList<PhoneRequest>());

		this.phoneEntities.forEach((phoneEntity) -> {
			userRequest.getPhonesRequest().add(phoneEntity.getPhonesRequest());
		});

		return userRequest;
	}

	@Override
	public UserEntity getUserEntity() {
		return this;
	}

}