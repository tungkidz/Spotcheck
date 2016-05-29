package com.spotcheck.api;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.spotcheck.entity.Account;
import com.spotcheck.entity.Profile;
import com.spotcheck.entity.User;
import com.spotcheck.form.AccountForm;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.inject.Named;

import static com.spotcheck.service.OfyService.ofy;

/**
 * Defines the Spotcheck API.
 */
@Api(name = "userApi", version = "v1", namespace = @ApiNamespace(ownerDomain = "api.spotcheck.com", ownerName = "api.spotcheck.com"))
public class UserApi
{
	private static final int HASH_BYTE_SIZE = 20; // 20 * 8 = 160 bits

	/**
	 * Cooks up a hash with a password and salt.
	 *
	 * @return The resulting hash.
	 * @param password the password to store
	 * @param salt the salt to spice things up
	 */
	private byte[] hashPassword(final char[] password, final byte[] salt)
	{
		try
		{
			int bytesOfHash = HASH_BYTE_SIZE*8;
			int iterations = 100;
			SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			PBEKeySpec spec = new PBEKeySpec( password, salt, iterations, bytesOfHash );
			byte[] hash = skf.generateSecret(spec).getEncoded();
			return hash;
		} catch( NoSuchAlgorithmException | InvalidKeySpecException e )
		{
			throw new RuntimeException( e );
		}
	}

	/**
	 * Returns an Account object associated with the given email.
	 *
	 * @return Account object.
	 * @param email the email of the account to retrieve
	 */
	private Account getAccount(String email)
	{
		// load an Account Entity
		Account account = ofy().load().type(Account.class).filter("email", email).first().now();
		return account;
	}

	/**
	 * Returns an Account object associated with the given account id.
	 *
	 * @return Account object.
	 * @param accountId the id of the account to retrieve
	 */
	private Account getAccount(Long accountId)
	{
		Account account = ofy().load().type(Account.class).id(accountId).now();
		return account;
	}

	/**
	 * Returns a User object associated with the given account id.
	 *
	 * @return User object.
	 * @param accountId the account id of the user to retrieve
	 */
	private User getUser(Long accountId)
	{
		User user = ofy().load().type(User.class).filter("accountId", accountId).first().now();
		return user;
	}

	/**
	 * Checks if an accountForm has valid data.
	 *
	 * @return true if it is valid, else false.
	 * @param accountForm the accountForm to verify
	 */
	private Boolean isValidAccount(AccountForm accountForm)
	{
		//TO DO: check accountForm data
		return true;
	}

	/**
	 * Creates an Account object.
	 *
	 * @param accountForm
	 *            A AccountForm object sent from the client form.
	 * @return User object just created or null if User exists
	 */
	@ApiMethod(name = "createAccount", path = "createAccount", httpMethod = HttpMethod.POST)
	public User createAccount(AccountForm accountForm)
	{
		// verify accountForm
		if (!isValidAccount(accountForm)) return null;

		//set account data from the accountForm input
		String firstName = accountForm.getFirstName();
		String lastName = accountForm.getLastName();
		String email = accountForm.getEmail().toLowerCase();
		String password = accountForm.getPassword();


		//set account data
		Account account = getAccount(email);
		if (account == null) //check if email is taken
		{
			// generate random salt
			SecureRandom random = new SecureRandom();
			byte salt[] = new byte[HASH_BYTE_SIZE];
			random.nextBytes(salt);

			// generate hash from password + salt
			byte[] passwordHash = hashPassword(password.toCharArray(),salt);

			// create an account
			account = new Account(firstName, lastName, email, passwordHash, salt);
			ofy().save().entity(account).now();

			// create a profile
			Profile profile = new Profile();
			ofy().save().entity(profile).now();

			// create a user
			User user = new User(account.getAccountId(), profile.getProfileId());
			ofy().save().entity(user).now();

			// return the new user
			return user;
		}

		// account couldn't be created
		return null;
	}

	/**
	 * Updates an Account object, if it is valid
	 *
	 * @param accountForm
	 *            A AccountForm object sent from the client form.
	 * @return Account object just created or null if account doesn't exist
	 */
	@ApiMethod(name = "updateAccount", path = "updateAccount", httpMethod = HttpMethod.POST)
	public void updateAccount(AccountForm accountForm)
	{
		// user must be logged in to set accountId with user object
		if (accountForm.getAccountId() == null || accountForm.getAccountId() <= 0) return;

		// verify accountForm
		if (!isValidAccount(accountForm)) return;

		// set account data from form input
		String firstName = accountForm.getFirstName();
		String lastName = accountForm.getLastName();
		String email = accountForm.getEmail().toLowerCase();
		String password = accountForm.getPassword();

		//set account data
		Account account = getAccount(accountForm.getAccountId());
		if (account != null && account.isActive())
		{
			byte[] salt = null;
			byte[] passwordHash = null;
			if (password != null && !password.isEmpty()) // if password is being updated
			{
				// generate random salt
				SecureRandom random = new SecureRandom();
				salt = new byte[HASH_BYTE_SIZE];
				random.nextBytes(salt);

				// generate hash from password + salt
				passwordHash = hashPassword(password.toCharArray(),salt);
			}

			//update account data
			account.update(firstName, lastName, email, passwordHash, salt);

			//save the updated account in the datastore
			ofy().save().entity(account).now();
		}
	}


	/**
	 * Deactivates an Account object.
	 *
	 * @param user the email of the account to deactivate.
	 */
	@ApiMethod(name = "deactivateAccount", path = "deactivateAccount", httpMethod = HttpMethod.POST)
	public void deactivateAccount(User user)
	{
		// verify account
		Account account = getAccount(user.getAccountId());
		if (account != null)
		{
			account.setIsActive(false);
			ofy().save().entity(account).now();
		}

	}



	/**
	 * Authenticates a user account with an email and password.
	 *
	 * @return Account object when successful, else null.
	 */
	@ApiMethod(name = "authenticateAccount", path = "authenticateAccount", httpMethod = HttpMethod.POST)
	public User authenticateAccount(@Named("email") String email, @Named("password") String password)
	{
		// load an Account Entity
		Account account = getAccount(email);
		if (account != null)
		{
			// Authenticate account
			byte[] salt = account.getSalt();
			byte[] passwordHash = hashPassword(password.toCharArray(), salt);
			if (Arrays.equals(account.getPassword(),passwordHash))
			{
				// authentication is successful
				account.setIsActive(true);
				ofy().save().entity(account).now();
				User user = getUser(account.getAccountId());
				return user;
			}
		}

		// account cannot be authenticated
		return null;
	}
}
