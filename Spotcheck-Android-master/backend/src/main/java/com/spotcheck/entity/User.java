package com.spotcheck.entity;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

/**
 * Created by Matt on 4/16/16.
 */
@Entity
public class User
{
    @Id Long userId;
    @Index Long accountId;
    @Index Long profileId;

    private User(){}

    public User(Long accountId, Long profileId)
    {
        this.accountId = accountId;
        this.profileId = profileId;
    }

    public Long getUserId() { return userId; }

    public Long getAccountId() { return accountId; }

    public Long getProfileId() { return profileId; }
}
