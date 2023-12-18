package com.srm.nisumChallenge.payloads.request;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Iterator;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.TestPropertySource;

import com.srm.nisumChallenge.dto.request.UserRequest;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

@TestPropertySource("classpath:messages-test.yml")
class UserRequestTest {

	private static Validator validator;

	@BeforeAll
	public static void setup() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	/**
	 * Para name Null Valido la cantidad de mensajes de error
	 */
	@Test
	void testUserRequest_NullEmailPasswordListOfPhonesRequest() {

		UserRequest userRequest = new UserRequest(null, "email@email.com", "password", null);

		Set<ConstraintViolation<UserRequest>> violations = validator.validate(userRequest);

		assertEquals(violations.size(), 1);
	}

	/**
	 * Para name Empty Valido la cantidad de mensajes de error
	 */
	@Test
	void testUserRequest_EmptyEmailPasswordListOfPhonesRequest() {

		UserRequest userRequest = new UserRequest("", "email@email.com", "password", null);

		Set<ConstraintViolation<UserRequest>> violations = validator.validate(userRequest);

		assertEquals(violations.size(), 1);
	}

	/**
	 * Valido en mensaje de respuesta error Valido la cantidad de mensajes de error
	 */
	@Test
	void testUserRequest_Message_EmptyEmailPasswordListOfPhonesRequest() {

		UserRequest userRequest = new UserRequest("", "email@email.com", "password", null);

		Set<ConstraintViolation<UserRequest>> violations = validator.validate(userRequest);
		Iterator<ConstraintViolation<UserRequest>> iterator = violations.iterator();

		String errorMessage = "";

		while (iterator.hasNext()) {
			ConstraintViolation<UserRequest> next = iterator.next();

			errorMessage = next.getMessage();
		}

		assertEquals("Name is mandatory", errorMessage);
	}

}
