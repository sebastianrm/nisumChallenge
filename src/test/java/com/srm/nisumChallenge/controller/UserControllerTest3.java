/**
 * Nisum Challenge
 * @author Sebastian Retamal Morales
 * @since 15/12/2023
 */
package com.srm.nisumChallenge.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

class UserControllerTest3 {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@Test
	void testOK_AddNewUser() {
		
//		ArrayList<PhonesRequest> phones = new ArrayList<>();
//		
//		phones.add(new PhonesRequest("738043565","0001","000"));
//		
//		UserRequest userRequest = new UserRequest("name","email@email.com","password",phones);
//		
//		ResponseEntity<String> response = this.testRestTemplate
//				.postForEntity("http://localhost:" + port + "/api/v1/user/add", String.class);
	}

}