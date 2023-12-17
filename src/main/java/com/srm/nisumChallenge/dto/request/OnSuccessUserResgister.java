/**
 * 
 */
package com.srm.nisumChallenge.dto.request;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.srm.nisumChallenge.dto.ParentUserLog;
import com.srm.nisumChallenge.dto.entities.LogUserEntity;

/**
 * Nisum Challenge
 * @author Sebastian Eduardo Retamal Morales
 * @since 2023-12-17
 * @version 0.0.1-SNAPSHOT
 */
@JsonIgnoreProperties({ "logId", "modified" })
public class OnSuccessUserResgister extends ParentUserLog {

	
	
	public OnSuccessUserResgister() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OnSuccessUserResgister(String logId, String userId, Timestamp created, Timestamp modified,
			Timestamp lastLogin, String token, Boolean isActive) {
		super(logId, userId, created, modified, lastLogin, token, isActive);
		// TODO Auto-generated constructor stub
	}

	@Override
	public LogUserEntity factoryGetLogUserEntity() {

		new LogUserEntity(this.logId, this.userId, this.created, this.modified, this.lastLogin, this.token, this.isActive);
		return null;
	}

	@Override
	public OnSuccessUserResgister factorygetOnSuccessUserResgister() {

		
		return this;
	}

}
