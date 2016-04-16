package com.spotcheck.api;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.spotcheck.entity.Account;
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
@Api(name = "accountApi", version = "v1", namespace = @ApiNamespace(ownerDomain = "api.spotcheck.com", ownerName = "api.spotcheck.com"))
public class AccountApi
{
	private static final int HASH_BYTE_SIZE = 20; // 20 * 8 = 160 bits

	/**
	 * Cooks up a hash with a password and salt.
	 *
	 * @return The resulting hash.
	 * @param password the password to store
	 * @param salt the salt to spice things up
	 */
	private static byte[] hashPassword(final char[] password, final byte[] salt)
	{
		try
		{
			int bytesOfHash = HASH_BYTE_SIZE*8;
			int iterations = 10;
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
	private static Account getAccount(String email)
	{
		// load an Account Entity
		Account account = ofy().load().type(Account.class).filter("email", email).first().now();
		return account;
	}


	/**
	 * Checks if an accountForm has valid data.
	 *
	 * @return true if it is valid, else false.
	 * @param accountForm the accountForm to verify
	 */
	private static Boolean isValidAccount(AccountForm accountForm)
	{
		//TO DO: check accountForm data
		return true;
	}

	/**
	 * Creates an Account object.
	 *
	 * @param accountForm
	 *            A AccountForm object sent from the client form.
	 * @return Account object just created or null if account exists
	 */
	@ApiMethod(name = "createAccount", path = "createAccount", httpMethod = HttpMethod.POST)
	public Account createAccount(AccountForm accountForm)
	{
		// verify accountForm
		if (!isValidAccount(accountForm)) return null;

		//set account data from the accountForm input
		String firstName = accountForm.getFirstName();
		String lastName = accountForm.getLastName();
		String email = accountForm.getEmail();
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

			// create the account
			account = new Account(firstName, lastName, email, passwordHash, salt);

			// save the created account in the datastore
			ofy().save().entity(account).now();

			return account;
			// create a user for the created account
			//User user = new User(account.getAccountId());

			// save the created user in the datastore
			//ofy().save().entity(user).now();

			// return the new user
			//return user;
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
		// verify accountForm
		if (!isValidAccount(accountForm)) return;

		//set account data from form input
		String firstName = accountForm.getFirstName();
		String lastName = accountForm.getLastName();
		String email = accountForm.getEmail();
		String password = accountForm.getPassword();

		//long userId = accountForm.getUserId();
		//if (userId <= 0) return;

		//Key<User> userKey = Key.create(User.class, userId);

		//set account data
		Account account = getAccount(email);
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
	 * @param email the email of the account to deactivate.
	 */
	@ApiMethod(name = "deactivateAccount", path = "deactivateAccount", httpMethod = HttpMethod.POST)
	public void deactivateAccount(@Named("email") String email)
	{
		// verify account
		Account account = getAccount(email);
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
	public Account authenticateAccount(@Named("email") String email, @Named("password") String password)
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
				return account;
			}
		}

		// account cannot be authenticated
		return null;
	}
}
