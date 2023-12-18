/**
 * 
 */
package com.srm.nisumChallenge.Exceptions;

/**
 * Nisum Challenge
 * @author Sebastian Eduardo Retamal Morales
 * @since 2023-12-17
 * @version 0.0.1-SNAPSHOT
 */
public class CustomNoValidPasswordException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomNoValidPasswordException(String message) {
		super(message);
	}
	

}
