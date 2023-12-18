/**
 * 
 */
package com.srm.nisumChallenge.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.srm.nisumChallenge.dto.entities.PhoneEntity;
import com.srm.nisumChallenge.dto.entities.UserEntity;

/**
 * Nisum Challenge
 * 
 * @author Sebastian Eduardo Retamal Morales
 * @since 2023-12-17
 * @version 0.0.1-SNAPSHOT
 */
@DataJpaTest
class UserRepositoryIntegrationTest {

//	@Autowired
//	private TestEntityManager entityManager;

	@Autowired
	private UserRepository userRepository;

	@Test
	void test_OK() {

		UserEntity userEntity = new UserEntity("Juan Rodriguez", "juan@rodriguez.com", "hunter2", null);

		userEntity.setPhoneEntities(new ArrayList<>());

		PhoneEntity phonesEntity = new PhoneEntity("1234567", "01", "056");

		userEntity.getPhoneEntities().add(phonesEntity);
		
		UserEntity save = userRepository.save(userEntity);

		assertNotNull(save);
		
		
	}
	
	@Test
	void test_User_existe() {

		UserEntity userEntity = new UserEntity("Juan Rodriguez", "juan@rodriguez.com", "hunter2", null);

		userEntity.setPhoneEntities(new ArrayList<>());

		PhoneEntity phonesEntity = new PhoneEntity("1234567", "01", "056");

		userEntity.getPhoneEntities().add(phonesEntity);
		
		userRepository.save(userEntity);	
	    Exception exception = assertThrows(RuntimeException.class, () -> {
	    	userRepository.save(userEntity);
	    });
		

		assertNotNull(exception);
		
		
	}

}
