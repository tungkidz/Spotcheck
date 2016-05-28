package com.spotcheck.entity;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

/**
 * Created by Matt on 4/16/16.
 */
@Entity
public class User
{
    @Id long userId;
    long accountId;

    private User(){}

    public User(long accountId)
    {
        this.accountId = accountId;
    }

    public void setAccountId(long accountId)
    {
        this.accountId = accountId;
    }

    public long getUserId() { return userId; }

    public long getAccountId() { return accountId; }
}
