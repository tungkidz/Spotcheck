package com.spotcheck.tasks;

import android.os.AsyncTask;


import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.spotcheck.Constants;
import com.spotcheck.api.userApi.UserApi;

import java.io.IOException;

/**
 * Handles the connection with the API
 */
public abstract class AccountAsyncTask extends AsyncTask<Void,Void,Boolean>
{
    public UserApi userApi;
    public String error = "";

    public AccountAsyncTask()
    {
        super();
        initializeAPI();
    }

    public void initializeAPI()
    {
        String rootURL = "https://"+ Constants.APPSPOT+"/_ah/api/";
        UserApi.Builder builder = new UserApi.Builder(AndroidHttp.newCompatibleTransport(),
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

        userApi = builder.build();
    }
}
