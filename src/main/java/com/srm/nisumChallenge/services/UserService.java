package com.srm.nisumChallenge.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srm.nisumChallenge.dto.entities.LogUserEntity;
import com.srm.nisumChallenge.dto.entities.UserEntity;
import com.srm.nisumChallenge.dto.request.OnSuccessUserResgister;
import com.srm.nisumChallenge.dto.request.UserRequest;
import com.srm.nisumChallenge.repository.UserRepository;
import com.srm.nisumChallenge.utils.AESEncryptionDecryptionUtil;

/**
 * Nisum Challenge
 * 
 * Contains logic for user's
 * 
 * @author Sebastian Eduardo Retamal Morales
 * @since 2023-12-16
 * @version 0.0.1-SNAPSHOT
 */
@Service
public class UserService {

	@Autowired
	AESEncryptionDecryptionUtil AesEncryptionDecryptionService;

	@Autowired
	UserRepository userRepository;

	@Autowired
	LogUserService logUserService;

	/**
	 * save new user From userRequest
	 * 
	 * @param userRequest
	 * @return OnsuccesUserRegister || Exception
	 */
	public OnSuccessUserResgister registerUser(UserRequest userRequest) {

		userRequest = encryptPassword(userRequest);

		UserEntity saveUserEntity = saveUserEntity(castUserRequestToUserEntity(userRequest));

		LogUserEntity logUserEntity = logUserService.registrerUserLog(saveUserEntity);

		
		return logUserEntity.factorygetOnSuccessUserResgister();

	}

	/**
	 * Encryt password
	 * 
	 * @param userRequest
	 * @return
	 */
	private UserRequest encryptPassword(UserRequest userRequest) {

		userRequest.setPassword(AesEncryptionDecryptionService.encrypt(userRequest.getPassword()));

		return userRequest;
	}

	/**
	 * Save User Entity
	 * 
	 * @param userEntity
	 * @return
	 */
	private UserEntity saveUserEntity(UserEntity userEntity) {

		return userRepository.save(userEntity);

	}

	/**
	 * Helper Method to Cast <b>use Factory Patron Desing</b>
	 * 
	 * @param userRequest
	 * @return UserEntity
	 */
	private UserEntity castUserRequestToUserEntity(UserRequest userRequest) {

		return userRequest.getUserEntity();
	}

}
