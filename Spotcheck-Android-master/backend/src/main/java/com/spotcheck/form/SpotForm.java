package com.spotcheck.form;

import com.google.appengine.api.datastore.Blob;

/**
 * Created by Matt on 5/30/16.
 */
public class SpotForm
{
    private Long profileId;
    private Blob photo;
    private String name;

    public Long getProfileId()
    {
        return profileId;
    }

    public void setProfileId(Long profileId)
    {
        this.profileId = profileId;
    }

    public Blob getPhoto()
    {
        return photo;
    }

    public void setPhoto(Blob photo)
    {
        this.photo = photo;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
