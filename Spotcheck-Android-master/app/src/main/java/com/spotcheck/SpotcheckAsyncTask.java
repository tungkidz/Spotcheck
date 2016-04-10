package com.spotcheck;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Pair;
import android.widget.Toast;


import java.io.IOException;

/**
 * Created by Matt on 2/15/16.
 */
public class SpotcheckAsyncTask extends AsyncTask<Pair<Context, String>, Void, String>
{
    //private static MyApi myApiService = null;
    private Context context;

    @Override
    protected String doInBackground(Pair<Context, String>... params) {
        //if(myApiService == null) {  // Only do this once
        //    MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
        //            .setRootUrl("https://something-1223.appspot.com/_ah/api/"); //TODO: Change this line to be a link to the actual backend
        //    // end options for devappserver

        //    myApiService = builder.build();
        //}

        //context = params[0].first;
        //String name = params[0].second;

        //try {
            //return myApiService.sayHi(name).execute().getData();
        //} catch (IOException e) {
            //return e.getMessage();
        //}
        return "";
    }

    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(context, result, Toast.LENGTH_LONG).show();
    }
}
