package com.spotcheck.tasks;

import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.spotcheck.Constants;
import com.spotcheck.api.spotApi.SpotApi;

import java.io.IOException;

public abstract class SpotAsyncTask extends AsyncTask<Void,Void,Boolean> {
    public SpotApi spotAPI;
    public String error = "";

    public SpotAsyncTask()
    {
        super();
        initializeAPI();
    }

    public void initializeAPI()
    {
        String rootURL = "https://"+ Constants.APPSPOT+"/_ah/api/";
        SpotApi.Builder builder = new SpotApi.Builder(AndroidHttp.newCompatibleTransport(),
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

        spotAPI = builder.build();
    }
}
