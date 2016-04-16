package com.spotcheck.entity;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;


@Entity
public class Account
{
	@Id Long accountId;
	String firstName;
	String lastName;
	@Index String email;
	byte[] password;
	byte[] salt;
	Boolean isActive;
	int loginCount;

	/**
	 * Just making the default constructor private.
	 */
	private Account() {}

	/**
	 * Public constructor for Profile.
	 * @param firstName Any string user wants us to display him/her on this system.
	 * @param lastName Any string user wants us to display him/her on this system.
	 * @param email User's main e-mail address.
	 * @param password The User's account password
	 *
	 */
	public Account(String firstName, String lastName, String email, byte[] password, byte[] salt)
	{
		if (email == null) email = "public user";
		update(firstName,lastName,email,password, salt);
		isActive = true;
		loginCount = 1;
	}

	/**
	 * Updates the Account variables.
	 *
	 * @param firstName the first name
	 * @param lastName the last name
	 * @param email the email
	 * @param password the password
	 * @param salt the salt
	 */
	public void update(String firstName, String lastName, String email, byte[] password, byte[] salt)
	{
		if (firstName != null) this.firstName = firstName;
		if (lastName != null) this.lastName = lastName;
		if (email != null) this.email = email;
		if (password != null) this.password = password;
		if (password != null) this.salt = salt;
	}

	/**
	 * Sets an account's active state.
	 * @param isActive true if logging in, false if deactivating
	 */
	public void setIsActive(Boolean isActive)
	{
		if (this.isActive = isActive)
		{
			loginCount++;
		}
	}

	public Long getAccountId() { return accountId; }

	public String getFirstName() { return firstName; }

	public String getLastName() { return lastName; }

	public String getEmail() { return email; }

	public byte[] getPassword() { return password; }

	public byte[] getSalt() { return salt; }

	public Boolean isActive() { return isActive; }

}
