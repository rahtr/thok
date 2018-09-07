package com.thok.model;
/**
 * @author Rahul Tripathi
 *
 * thok(Two Hundred OK)
 */
public class Users {
	// id will be used for primary key in MongoDB
	// We could use ObjectId, but I am keeping it
	// independent of MongoDB API classes
	private String id;

	private String ldap;

	private String password;

	public String getLDAP() {
		return ldap;
	}

	public void setLDAP(String ldap) {
		this.ldap = ldap;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
