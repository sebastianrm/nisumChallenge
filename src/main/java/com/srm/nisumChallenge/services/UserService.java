package com.srm.nisumChallenge.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srm.nisumChallenge.Exceptions.CustomDataConstraintException;
import com.srm.nisumChallenge.dto.entities.LogUserEntity;
import com.srm.nisumChallenge.dto.entities.UserEntity;
import com.srm.nisumChallenge.dto.request.UserRequest;
import com.srm.nisumChallenge.dto.response.OnSuccessUserResgister;
import com.srm.nisumChallenge.repository.UserRepository;
import com.srm.nisumChallenge.utils.MessagesErrorUtil;

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
	UserRepository userRepository;

	@Autowired
	LogUserService logUserService;
	
	@Autowired
	MessagesErrorUtil messagesErrorUtil;

	/**
	 * save new user From userRequest
	 * 
	 * @param userRequest
	 * @return OnsuccesUserRegister || Exception
	 */
	public OnSuccessUserResgister registerUser(UserRequest userRequest) throws CustomDataConstraintException {

		/**
		 * El susario Existe?
		 */
		UserEntity UserEntity = castUserRequestToUserEntity(userRequest);

		if (userEntityExist(UserEntity)) {
			throw new CustomDataConstraintException(messagesErrorUtil.getCORREO_EXISTE(),null,null);
		}

		UserEntity saveUserEntity = saveUserEntity(UserEntity);

		LogUserEntity logUserEntity = logUserService.registrerUserLog(saveUserEntity);

		return logUserEntity.factorygetOnSuccessUserResgister();

	}

	/**
	 * @param userEntity
	 */
	private boolean userEntityExist(UserEntity userEntity) {

		return !userRepository.findByEmail(userEntity.getEmail()).isEmpty();
			
		
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

		return userRequest.factoryUserEntity();
	}

}
