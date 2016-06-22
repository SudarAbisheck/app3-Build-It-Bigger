package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import me.sudar.builditbigger.androidlib.JokeDisplayActivity;
import me.sudar.builditbigger.jokes.JokeProvider;


public class MainActivity extends AppCompatActivity {

    private ContentLoadingProgressBar loadingBar;
    private Button jokeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadingBar = (ContentLoadingProgressBar) findViewById(R.id.loading);
        jokeButton = (Button) findViewById(R.id.jokeButton);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view){
        jokeButton.setVisibility(View.GONE);
        loadingBar.setVisibility(View.VISIBLE);
        new JokeAsyncTask().execute();
    }

    public void responseHandler(String joke){

        jokeButton.setVisibility(View.VISIBLE);
        loadingBar.setVisibility(View.GONE);

        if(joke != null) {
            Intent intent = new Intent(MainActivity.this, JokeDisplayActivity.class);
            intent.putExtra("joke", joke);
            startActivity(intent);
        }else{
            Toast.makeText(MainActivity.this, "Sorry!! Couldn't get the Joke.", Toast.LENGTH_SHORT).show();
        }
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
