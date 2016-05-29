package com.spotcheck.entity;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

/**
 * Created by Matt on 4/16/16.
 */
@Entity
public class Profile
{
    @Id Long profileId;

    public Profile() {}

    public Long getProfileId() { return profileId; }

}
