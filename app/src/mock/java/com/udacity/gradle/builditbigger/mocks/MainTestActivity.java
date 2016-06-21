package com.udacity.gradle.builditbigger.mocks;

import com.udacity.gradle.builditbigger.MainActivity;

/**
 * Created by sudar on 21/6/16.
 * Email : hey@sudar.me
 */
public class MainTestActivity extends MainActivity {

    private JokeAsyncTaskCallback callback;

    public void setCallback(JokeAsyncTaskCallback callback){
        this.callback = callback;
    }

    public interface JokeAsyncTaskCallback{
        void onHandleResponse(String joke);
    }

    @Override
    public void responseHandler(String joke) {
        this.callback.onHandleResponse(joke);
    }
}
