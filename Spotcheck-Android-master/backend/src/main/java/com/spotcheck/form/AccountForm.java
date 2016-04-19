package com.spotcheck.form;

/**
 * Pojo representing a profile form on the client side.
 */
public class AccountForm
{
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private long userId = 0;

    private AccountForm () {}

    /**
     * Constructor for AccountForm, solely for unit test.
     */
    public AccountForm(String firstName, String lastName, String email, String password)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public void setUserId(long userId) { this.userId = userId; }

    public long getUserId() { return userId; }

    public String getFirstName() { return firstName; }

    public String getLastName() { return lastName; }

    public String getEmail() { return email; }

    public String getPassword() { return password; }
}
