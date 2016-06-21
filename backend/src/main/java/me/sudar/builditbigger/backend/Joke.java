package me.sudar.builditbigger.backend;

/** The object model for the data we are sending through endpoints */
public class Joke {

    private String theJoke;

    public String getJoke() {
        return theJoke;
    }

    public void setJoke(String joke) {
        theJoke = joke;
    }
}