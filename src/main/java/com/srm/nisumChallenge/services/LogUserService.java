package com.srm.nisumChallenge.services;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srm.nisumChallenge.dto.entities.LogUserEntity;
import com.srm.nisumChallenge.dto.entities.UserEntity;
import com.srm.nisumChallenge.repository.LogUserRepository;
import com.srm.nisumChallenge.utils.JwtUtils;

/**
 * Nisum Challenge
 *
 * For Log Services
 * 
 * @author Sebastian Eduardo Retamal Morales
 * @since 2023-12-15
 * @version 0.0.1-SNAPSHOT
 */
@Service
public class LogUserService {

	@Autowired
	LogUserRepository logUserRepository;

	@Autowired
	private JwtUtils jwtUtils;

	/**
	 * Registrer User Log form UserEntity
	 * 
	 * @param userEntity
	 * @return LogUserEntity
	 */
	public LogUserEntity registrerUserLog(UserEntity userEntity) {

		String generateJwtToken = jwtUtils.generateJwtToken(userEntity.getEmail());
		LogUserEntity logUserEntity = new LogUserEntity(generateJwtToken, new Timestamp(System.currentTimeMillis()),
				userEntity);
		LogUserEntity saveLogUserEntity = saveLogUserEntity(logUserEntity);

		return saveLogUserEntity;
	}

	/**
	 * Save LogUserEntity
	 * 
	 * @param logUserEntity
	 * @return
	 */
	public LogUserEntity saveLogUserEntity(LogUserEntity logUserEntity) {

		return logUserRepository.save(logUserEntity);

	}
}
