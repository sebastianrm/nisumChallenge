package com.srm.nisumChallenge.dto;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.srm.nisumChallenge.dto.entities.LogUserEntity;
import com.srm.nisumChallenge.dto.request.OnSuccessUserResgister;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

/**
 * Nisum Challenge
 * 
 * For use Factory Patron design in UserLog, OnSuccessUserResgister
 * 
 * @author Sebastian Retamal Morales
 * @since 2023-12-15
 * @version 0.0.1-SNAPSHOT
 */

@Entity
@Table(name = "log_user", schema = "usersdb")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)

//@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
@JsonSubTypes({ @Type(value = OnSuccessUserResgister.class), @Type(value = LogUserEntity.class) })

//@JsonTypeInfo(
//		  use = JsonTypeInfo.Id.NAME, 
//		  include = JsonTypeInfo.As.PROPERTY, 
//		  property = "type")
//		@JsonSubTypes({ 
//		  @Type(value = OnSuccessUserResgister.class, name = "car"), 
//		  @Type(value = LogUserEntity.class, name = "truck") 
//		})
public abstract class ParentUserLog {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "log_id")
	protected String logId;

	@Schema(description = "Client UUID Number")
	@JsonProperty("id")
	@Column(name = "user_id")
	protected String userId;

	@Schema(description = "Created data time")
	protected Timestamp created;

	@Schema(description = "Modifid data time")
	protected Timestamp modified;

	@Schema(description = "Last login")
	@Column(name = "last_login")
	protected Timestamp lastLogin;

	@Schema(description = "Tokken")
	protected String token;

	@Schema(description = "Is Active?")
	@Column(name = "active")
	protected Boolean isActive;

	public ParentUserLog() {
		super();
	}

	/**
	 * 
	 * @param logId
	 * @param userId
	 * @param created
	 * @param modified
	 * @param lastLogin
	 * @param token
	 * @param isActive
	 */
	public ParentUserLog(String logId, String userId, Timestamp created, Timestamp modified, Timestamp lastLogin,
			String token, Boolean isActive) {
		super();
		this.logId = logId;
		this.userId = userId;
		this.created = created;
		this.modified = modified;
		this.lastLogin = lastLogin;
		this.token = token;
		this.isActive = isActive;
	}

	public Timestamp getCreated() {
		return created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public Timestamp getModified() {
		return modified;
	}

	public void setModified(Timestamp modified) {
		this.modified = modified;
	}

	public Timestamp getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Timestamp lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getLogId() {
		return logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public abstract LogUserEntity factoryGetLogUserEntity();
	
	public abstract OnSuccessUserResgister factorygetOnSuccessUserResgister();

}
