/**
 * 
 */
package com.srm.nisumChallenge.utils;

import java.util.regex.Pattern;

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
@PropertySource("classpath:patterns.properties")
public class RegExPatterValidatorUtils {

	@Value(value = "${regexp.password}")
	private String REGEX_PASS;
	
	
	public boolean validPasswordPattern(String string) {
		
		Pattern p = Pattern.compile(REGEX_PASS);
		
		return p.matcher(string).matches();
	}
	
	
}