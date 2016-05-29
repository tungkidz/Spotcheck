package com.spotcheck.service;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;
import com.spotcheck.entity.Account;
import com.spotcheck.entity.Profile;
import com.spotcheck.entity.Spot;
import com.spotcheck.entity.User;

/**
 * Custom Objectify Service that this application should use.
 */
public class OfyService
{
    /**
     * This static block ensure the entity registration.
     */
    static
    {
        factory().register(Account.class);
        factory().register(Spot.class);
        factory().register(User.class);
        factory().register(Profile.class);
    }

    /**
     * Use this static method for getting the Objectify service object in order to make sure the
     * above static block is executed before using Objectify.
     * @return Objectify service object.
     */
    public static Objectify ofy()
    {
        return ObjectifyService.ofy();
    }

    /**
     * Use this static method for getting the Objectify service factory.
     * @return ObjectifyFactory.
     */
    public static ObjectifyFactory factory()
    {
        return ObjectifyService.factory();
    }
}
