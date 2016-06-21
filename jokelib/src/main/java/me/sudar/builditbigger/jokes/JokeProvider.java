package me.sudar.builditbigger.jokes;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;

import me.sudar.builditbigger.backend.jokeApi.JokeApi;

public class JokeProvider {
    private static JokeApi apiService = null;

    public String getJoke() {
        if (apiService == null) {
            JokeApi.Builder builder = new JokeApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("https://build-it-bigger-udacity.appspot.com/_ah/api/");
            apiService = builder.build();
        }

        try {
            return apiService.sayAJoke().execute().getJoke();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
