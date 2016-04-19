package com.spotcheck.tasks;

import android.os.AsyncTask;


import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.spotcheck.Constants;
import com.spotcheck.api.accountApi.AccountApi;

import java.io.IOException;

/**
 * Handles the connection with the API
 */
public abstract class AccountAsyncTask extends AsyncTask<Void,Void,Boolean>
{
    public AccountApi accountAPI;
    public String error = "";

    public AccountAsyncTask()
    {
        super();
        initializeAPI();
    }

    public void initializeAPI()
    {
        String rootURL = "https://"+ Constants.APPSPOT+"/_ah/api/";
        AccountApi.Builder builder = new AccountApi.Builder(AndroidHttp.newCompatibleTransport(),
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

        accountAPI = builder.build();
    }
}
