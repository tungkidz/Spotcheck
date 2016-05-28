package com.spotcheck.tasks;

import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.spotcheck.Constants;
import com.spotcheck.api.profileApi.ProfileApi;

import java.io.IOException;

/**
 * Created by Matt on 4/16/16.
 */
public abstract class ProfileAsyncTask extends AsyncTask<Void, Void, Boolean>
{
    public ProfileApi profileAPI;
    public String error = "";

    public ProfileAsyncTask()
    {
        super();
        initializeAPI();
    }

    public void initializeAPI()
    {
        String rootURL = "https://" + Constants.APPSPOT + "/_ah/api/";
        ProfileApi.Builder builder = new ProfileApi.Builder(AndroidHttp.newCompatibleTransport(),
                new AndroidJsonFactory(), null)
                .setRootUrl(rootURL)
                .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer()
                {
                    @Override
                    public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException
                    {
                        abstractGoogleClientRequest.setDisableGZipContent(true);
                    }
                });

        profileAPI = builder.build();
    }
}