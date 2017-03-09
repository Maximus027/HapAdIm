package com.example.hapadim.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by meltemyildirim on 3/7/17.
 */

public class Place {

    private long placeId;
    private String category;
    private String placeName;
    private long stepNumber;
    private long distance;
    private long caloriesBurned;
    private String urlVR;
    private String urlIMG;
    private List<Badge> badges;

    public long getPlaceId() {
        return placeId;
    }

    public String getCategory() {
        return category;
    }

    public String getPlaceName() {
        return placeName;
    }

    public long getStepNumber() {
        return stepNumber;
    }

    public long getDistance() {
        return distance;
    }

    public long getCaloriesBurned() {
        return caloriesBurned;
    }

    public String getUrlVR() {
        return urlVR;
    }

    public String getUrlIMG() {
        return urlIMG;
    }

    public List<Badge> getBadges() {
        if(badges != null){
            return badges;
        }
        else{
            return new ArrayList<>();
        }
    }

    public void setPlaceId(long placeId) {
        this.placeId = placeId;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public void setStepNumber(long stepNumber) {
        this.stepNumber = stepNumber;
    }

    public void setDistance(long distance) {
        this.distance = distance;
    }

    public void setCaloriesBurned(long caloriesBurned) {
        this.caloriesBurned = caloriesBurned;
    }

    public void setUrlVR(String urlVR) {
        this.urlVR = urlVR;
    }

    public void setUrlIMG(String urlIMG) {
        this.urlIMG = urlIMG;
    }

    public void setBadges(List<Badge> badges) {
        this.badges = badges;
    }
}
