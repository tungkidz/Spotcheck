package com.spotcheck.api;

import static com.spotcheck.service.OfyService.ofy;

import com.spotcheck.Constants;
import com.spotcheck.entity.Account;
import com.spotcheck.form.AccountForm;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.response.UnauthorizedException;
import com.google.appengine.api.users.User;
import com.googlecode.objectify.Key;

import javax.inject.Named;

/**
 * Defines spotcheck APIs.
 */
@Api(name = "spotcheck", version = "v1", clientIds =
{
	Constants.WEB_CLIENT_ID,
	Constants.API_EXPLORER_CLIENT_ID
}, description = "API for the Spotcheck Backend application.")

public class SpotcheckApi
{
	/*
	 * Get the display name from the user's email. For example, if the email is
	 * lemoncake@example.com, then the display name becomes "lemoncake."
	 */
	private static String extractDefaultDisplayNameFromEmail(String email)
	{
		return email == null ? null : email.substring(0, email.indexOf("@"));
	}

	/**
	 * Creates or updates an Account object associated with the given user
	 * object.
	 *
	 * @param user
	 *            A User object injected by the cloud endpoints.
	 * @param accountForm
	 *            A AccountForm object sent from the client form.
	 * @return Account object just created.
	 * @throws UnauthorizedException
	 *             when the User object is null.
	 */
	@ApiMethod(name = "saveAccount", path = "saveAccount", httpMethod = HttpMethod.POST)
	public Account saveAccount(AccountForm accountForm) throws UnauthorizedException
	{

		//get an existing user account
		String userId = null;//user.getUserId();
		Account account = null;//getAccount(user);

		//set account data from form input
		String firstName = accountForm.getFirstName();
		String lastName = accountForm.getLastName();
		String email = accountForm.getEmail();
		String password = accountForm.getPassword();

		//check for empty form data
		if (firstName == "") firstName = null;
		if (lastName == "") lastName = null;
		if (email == "") email = null;
		if (password == "") password = null;

		//set account data
		if (account == null)
			account = new Account(userId, firstName, lastName, email, password);
		else
			account.update(firstName, lastName, email, password);

		//save the account in the datastore
		ofy().save().entity(account).now();

		return account;
	}

	/**
	 * Returns an Account object associated with the given user object. The cloud
	 * endpoints system automatically inject the User object.
	 *
	 * @param user
	 *            A User object injected by the cloud endpoints.
	 * @return Account object.
	 * @throws UnauthorizedException
	 *             when the User object is null.
	 */
	@ApiMethod(name = "getAccount", path = "getAccount", httpMethod = HttpMethod.GET)
	public Account getAccount() throws UnauthorizedException
	{
		String userId = "default";

		// load the Account Entity
		Key key = Key.create(Account.class, userId);
		Account account = (Account) ofy().load().key(key).now();

		return account;
	}


	/** A simple endpoint method that takes a name and says Hi back */
	@ApiMethod(name = "sayHi")
	public Account sayHi(@Named("name") String name)
	{
		Account a = new Account("2", "Hi, " + name, "last", "email", "password");
		ofy().save().entity(a).now();
		return a;
	}
}
