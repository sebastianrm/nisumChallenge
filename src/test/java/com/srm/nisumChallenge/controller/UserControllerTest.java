
package com.srm.nisumChallenge.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.srm.nisumChallenge.dto.request.PhoneRequest;
import com.srm.nisumChallenge.dto.request.UserRequest;

/**
 * Nisum Challenge
 * @author Sebastian Eduardo Retamal Morales
 * @since 2023-12-16
 * @version 0.0.1-SNAPSHOT
 */
@ActiveProfiles(profiles = "test")
class UserControllerTest {

	@Autowired
	private TestRestTemplate testRestTemplate;
	
	/**
	 * Insert Correct user Registrer
	 * Test method for {@link com.srm.nisumChallenge.controller.UserController#addNewUser(com.srm.nisumChallenge.dto.request.UserRequest)}.
	 */
	@Test
	void testAddNewUser_OK() {
		
		UserRequest userRequest = new UserRequest("Juan Rodriguez","juan@rodriguez.com","hunter2", null);
		
		PhoneRequest phonesRequest = new PhoneRequest("1234567","01","056");
		
		userRequest.setPhonesRequest(new ArrayList<>());
		userRequest.getPhonesRequest().add(phonesRequest);
		
		ResponseEntity<String> response = testRestTemplate
				.postForEntity("http://localhost:8080/api/v1/users/add",userRequest, String.class);
		
		
		assertNotNull(response.getBody());
		
	}

}
