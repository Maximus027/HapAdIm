package com.example.hapadim.database;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by meltemyildirim on 3/1/17.
 */
public class Badge extends RealmObject {

    @PrimaryKey
    private long badgeId;

    private String name;
    private int badgeImg;
    private String description;

    //getter
    public long getBadgeId() {
        return badgeId;
    }

    public String getName() {
        return name;
    }

    public int getBadgeImg() {
        return badgeImg;
    }

    public String getDescription() {
        return description;
    }


    //setter
    public void setBadgeId(long badgeId) {
        this.badgeId = badgeId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBadgeImg(int badgeImg) {
        this.badgeImg = badgeImg;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
