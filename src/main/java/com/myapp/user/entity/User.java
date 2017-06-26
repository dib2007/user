package com.myapp.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "USR_DETAILS_TBL", catalog = "myapp", uniqueConstraints = { @UniqueConstraint(columnNames = "user_id"),
		@UniqueConstraint(columnNames = "user_email"), @UniqueConstraint(columnNames = "user_display_name") })
public class User {
	private long userId;
	private String userEmail;
	private String userFName;
	private String userLName;
	private String userMName;
	private String userDisplayName;
	private String userPassword;
	private String userSalt;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id", unique = true, nullable = false)
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	@Column(name = "user_email", unique = true, nullable = false, length = 60)
	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	@Column(name = "user_fname", nullable = false, length = 60)
	public String getUserFName() {
		return userFName;
	}

	public void setUserFName(String userFName) {
		this.userFName = userFName;
	}

	@Column(name = "user_lname", nullable = false, length = 60)
	public String getUserLName() {
		return userLName;
	}

	public void setUserLName(String userLName) {
		this.userLName = userLName;
	}

	@Column(name = "user_mname", nullable = true, length = 45)
	public String getUserMName() {
		return userMName;
	}

	public void setUserMName(String userMName) {
		this.userMName = userMName;
	}

	@Column(name = "user_display_name", nullable = false, length = 20)
	public String getUserDisplayName() {
		return userDisplayName;
	}

	public void setUserDisplayName(String userDisplayName) {
		this.userDisplayName = userDisplayName;
	}

	@Column(name = "user_password", unique = false, nullable = false, length = 60)
	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	@Column(name = "user_salt", unique = false, nullable = false, length = 60)
	public String getUserSalt() {
		return userSalt;
	}

	public void setUserSalt(String userSalt) {
		this.userSalt = userSalt;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userEmail=" + userEmail + ", userFName=" + userFName + ", userLName="
				+ userLName + ", userMName=" + userMName + ", userDisplayName=" + userDisplayName + "]";
	}

}
