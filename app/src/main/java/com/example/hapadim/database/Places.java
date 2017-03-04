package com.example.hapadim.database;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by meltemyildirim on 3/2/17.
 */

public class Places extends RealmObject {
    @PrimaryKey
    private long placeId;
    private String category;
    private String placeName;
    private long stepNumber;
    private String distance;
    private String urlVR;
    private String urlIMG;
    private RealmList<Badge> badges;

    //getter


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

    public String getDistance() {
        return distance;
    }

    public String getUrlVR() {
        return urlVR;
    }

    public String getUrlIMG() {
        return urlIMG;
    }

    public RealmList<Badge> getBadges() {
        return badges;
    }


    //setter

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

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public void setUrlVR(String urlVR) {
        this.urlVR = urlVR;
    }

    public void setUrlIMG(String urlIMG) {
        this.urlIMG = urlIMG;
    }

    public void setBadges(RealmList<Badge> badges) {
        this.badges = badges;
    }
}
