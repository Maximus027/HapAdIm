package com.example.hapadim.models;

import java.util.List;

/**
 * Created by meltemyildirim on 3/9/17.
 * wrapper
 */

public class Places {

    private List<Place> places;
    private static Places instance;

    public Places getinstance() {
        if (instance == null) {
            instance = new Places();
        }
        return instance;
    }

    public List<Place> getPlaces() {

        return places;
    }

    public void populatePlaces(List<Place> places) {
        this.places = places;
    }
}
