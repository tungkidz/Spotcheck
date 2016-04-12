package com.spotcheck.api;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.UnauthorizedException;
import com.googlecode.objectify.Key;
import com.spotcheck.entity.Account;
import com.spotcheck.form.AccountForm;

import javax.inject.Named;

import static com.spotcheck.service.OfyService.ofy;

/**
 * Defines spotcheck APIs.
 */
@Api(name = "spotcheck", version = "v1", namespace = @ApiNamespace(ownerDomain = "api.spotcheck.com", ownerName = "api.spotcheck.com"))
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
	 * Creates an Account object associated with the given user
	 * object.
	 *
	 * @param accountForm
	 *            A AccountForm object sent from the client form.
	 * @return Account object just created.
	 * @throws UnauthorizedException
	 *             when the User object is null.
	 */
	@ApiMethod(name = "createAccount", path = "createAccount", httpMethod = HttpMethod.POST)
	public Account createAccount(AccountForm accountForm) throws UnauthorizedException
	{
		//set account data from form input
		String firstName = accountForm.getFirstName();
		String lastName = accountForm.getLastName();
		String email = accountForm.getEmail();
		String password = accountForm.getPassword(); // TO DO: hash and salt

		//set account data
		Account account = getAccount(email);
		if (account == null)
		{
			account = new Account(firstName, lastName, email, password);

			//save the updated account in the datastore
			ofy().save().entity(account).now();
			return account;
		} else
		{
			return null;
		}

	}

	/**
	 * Updates an Account object associated with the given user
	 * object.
	 *
	 * @param accountForm
	 *            A AccountForm object sent from the client form.
	 * @return Account object just created.
	 * @throws UnauthorizedException
	 *             when the User object is null.
	 */
	@ApiMethod(name = "updateAccount", path = "updateAccount", httpMethod = HttpMethod.POST)
	public Account updateAccount(AccountForm accountForm) throws UnauthorizedException
	{
		//set account data from form input
		String firstName = accountForm.getFirstName();
		String lastName = accountForm.getLastName();
		String email = accountForm.getEmail();
		String password = accountForm.getPassword(); // TO DO: hash and salt

		//set account data
		Account account = getAccount(email);
		if (account != null)
		{
			account.update(firstName, lastName, email, password);

			//save the updated account in the datastore
			ofy().save().entity(account).now();
		}

		return account;
	}

	/**
	 * Returns an Account object associated with the given user object.
	 *
	 * @return Account object.
	 * @param email the email of the account to retrieve
	 */
	private Account getAccount(String email)
	{
		// load an Account Entity
		Key key = Key.create(Account.class, email);
		Account account = (Account) ofy().load().key(key).now();
		return account;
	}

	/**
	 * Returns an Account object associated with the given user object.
	 *
	 * @return Account object.
	 * @throws UnauthorizedException
	 *             when the User object is null.
	 */
	@ApiMethod(name = "authenticateAccount", path = "authenticateAccount", httpMethod = HttpMethod.POST)
	public Account authenticateAccount(@Named("email") String email, @Named("password") String password) throws UnauthorizedException
	{
		// load an Account Entity
		Account account = getAccount(email);

		// Authenticate user
		String passwordHash = password; // TO DO: hash and salt
		if (account != null && account.getPassword().equals(passwordHash))
			return account;
		else return null;
	}
}
