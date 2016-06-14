package me.sudar.builditbigger.androidlib;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeDisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_display);

        TextView jokeTextView = (TextView) findViewById(R.id.jokeTextView);

        String joke = getIntent().getStringExtra("joke");
        if(joke != null){
            jokeTextView.setText(joke);
        }
    }
}
