/**
 * 
 */
package com.srm.nisumChallenge.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Nisum Challenge
 * @author Sebastian Eduardo Retamal Morales
 * @since 2023-12-17
 * @version 0.0.1-SNAPSHOT
 */
@Component
@PropertySource("classpath:error-messages.properties")
public class MessagesErrorUtil {
	
	@Value(value = "${error.user.exist}")
	private String CORREO_EXISTE;

	public String getCORREO_EXISTE() {
		return CORREO_EXISTE;
	}

}
