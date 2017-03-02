package com.example.hapadim.database;

import java.util.List;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

import static android.R.attr.id;

/**
 * Created by meltemyildirim on 3/2/17.
 */

public class Places extends RealmObject {
    @PrimaryKey
    private int placeId;
    private String category;
    private String placeName;
    private long stepNumber;
    private String distance;
    private String urlVR;
    private String urlIMG;
    private List<Badge> badges;

    //getter

    public List<Badge> getBadges() {
        return badges;
    }

    public int getId() {
        return id;
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

    public String getDistance() {
        return distance;
    }

    public String getUrlVR() {
        return urlVR;
    }

    public String getUrlIMG() {
        return urlIMG;
    }


   //setter
    public void setPlaceId(int placeId) {
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

    public void setDistance(String distance) {
        this.distance = distance;
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
