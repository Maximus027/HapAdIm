package com.example.hapadim.models;

import java.util.List;

/**
 * Created by meltemyildirim on 3/7/17.
 *
 *
 * {
 "placeId": 1,
 "category": "Monument",
 "placeName": "Statue Of Liberty",
 "description": "lorem alksdjflkahsd",
 "stepNumber": 354,
 "distance": 0,
 "calories": 0,
 "urlVR": "https://www.youtube.com/watch?v=HDe0WErg5UQ",
 "urlImg": "http://i67.tinypic.com/mcaj4l.jpg",
 "badges": []
 },
 */

public class Place {

    private int placeId;
    private String category;
    private String placeName;
    private String description;
    private int stepNumber;
    private int distance;
    private int calories;
    private String urlVR;
    private String urlImg;
    private List<Badge> badges;

    public int getPlaceId() {
        return placeId;
    }

    public void setPlaceId(int placeId) {
        this.placeId = placeId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStepNumber() {
        return stepNumber;
    }

    public void setStepNumber(int stepNumber) {
        this.stepNumber = stepNumber;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public String getUrlVR() {
        return urlVR;
    }

    public void setUrlVR(String urlVR) {
        this.urlVR = urlVR;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }

    public List<Badge> getBadges() {
        return badges;
    }

    public void setBadges(List<Badge> badges) {
        this.badges = badges;
    }
}