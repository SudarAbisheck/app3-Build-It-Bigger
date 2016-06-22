package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import me.sudar.builditbigger.androidlib.JokeDisplayActivity;
import me.sudar.builditbigger.jokes.JokeProvider;


public class MainActivity extends AppCompatActivity {

    private InterstitialAd interstitialAd;
    private String jokeString;
    private ContentLoadingProgressBar loadingBar;
    private Button jokeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadingBar = (ContentLoadingProgressBar) findViewById(R.id.loading);
        jokeButton = (Button) findViewById(R.id.jokeButton);

        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                loadJokeDisplayActivity();
            }
        });

        requestNewInterstitial();
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("29BD746C55DC31605317826563B6BE47")
                .build();

        interstitialAd.loadAd(adRequest);
    }


    public void tellJoke(View view){
        jokeButton.setVisibility(View.GONE);
        loadingBar.setVisibility(View.VISIBLE);
        new JokeAsyncTask().execute();
    }

    public void responseHandler(String joke){
        if(joke != null) {
            jokeString = joke;
            if(interstitialAd.isLoaded()){
                interstitialAd.show();
            }else {
                loadJokeDisplayActivity();
            }
        }else{
            jokeButton.setVisibility(View.VISIBLE);
            loadingBar.setVisibility(View.GONE);
            Toast.makeText(MainActivity.this, "Sorry!! Couldn't get the Joke.", Toast.LENGTH_SHORT).show();
        }
    }

    public void loadJokeDisplayActivity(){
        jokeButton.setVisibility(View.VISIBLE);
        loadingBar.setVisibility(View.GONE);
        Intent intent = new Intent(MainActivity.this, JokeDisplayActivity.class);
        intent.putExtra("joke", jokeString);
        startActivity(intent);
    }


    public class JokeAsyncTask extends AsyncTask<Void, Void, String>{

        @Override
        protected String doInBackground(Void... params) {
            return new JokeProvider().getJoke();
        }

        @Override
        protected void onPostExecute(String joke) {
            super.onPostExecute(joke);
            responseHandler(joke);
        }
    }

}
