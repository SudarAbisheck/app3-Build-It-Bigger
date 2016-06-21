package com.udacity.gradle.builditbigger.tests;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.udacity.gradle.builditbigger.mocks.MainTestActivity;
import com.udacity.gradle.builditbigger.R;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
@RunWith(AndroidJUnit4.class)
public class ApplicationTest  {

    @Rule
    public ActivityTestRule<MainTestActivity> activityTestRule = new ActivityTestRule<>(MainTestActivity.class);

    @Test
    public void testJokeAsyncTaskResponse() throws InterruptedException {
        MainTestActivity mainActivity = activityTestRule.getActivity();
        mainActivity.setCallback(new MainTestActivity.JokeAsyncTaskCallback() {
            @Override
            public void onHandleResponse(String joke) {
                assertNotNull(joke);
            }
        });

        Espresso.onView(ViewMatchers.withId(R.id.jokeButton)).perform(ViewActions.click());

    }
}