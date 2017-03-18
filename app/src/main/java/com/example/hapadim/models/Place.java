package com.example.hapadim.models;

import org.parceler.Parcel;

import java.util.List;

/**
 * Created by meltemyildirim on 3/7/17.
 */
@Parcel(Parcel.Serialization.BEAN)
public class Place {

    private int placeId;
    private String category;
    private String placeName;
    public String description;
    private int stepNumber;
    private int distance;
    private int calories;
    private String urlVR;
    public String urlImg;
    public String urlImg2;
    public String urlImg3;
    public String panoImg;

    public String getPanoImg() {
        return panoImg;
    }

    public void setPanoImg(String panoImg) {
        this.panoImg = panoImg;
    }

    public void setUrlImg2(String urlImg2) {
        this.urlImg2 = urlImg2;
    }

    public void setUrlImg3(String urlImg3) {
        this.urlImg3 = urlImg3;
    }

    public String getUrlImg2() {
        return urlImg2;
    }

    public String getUrlImg3() {
        return urlImg3;
    }

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