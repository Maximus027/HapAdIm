package com.example.hapadim.models;

/**
 * Created by meltemyildirim on 3/7/17.
 */

import org.parceler.Parcel;

import java.io.Serializable;

/****/

@Parcel
public class Badge implements Serializable{
    private int badgeId;
    private String badgedName;
    private String badgeImg;
    private String badgeDesc;

    public int getBadgeId() {
        return badgeId;
    }

    public String getBadgedName() {
        return badgedName;
    }

    public String getBadgeImg() {
        return badgeImg;
    }

    public String getBadgeDesc() {
        return badgeDesc;
    }

    public void setBadgeId(int badgeId) {
        this.badgeId = badgeId;
    }

    public void setBadgedName(String badgedName) {
        this.badgedName = badgedName;
    }

    public void setBadgeImg(String badgeImg) {
        this.badgeImg = badgeImg;
    }

    public void setBadgeDesc(String badgeDesc) {
        this.badgeDesc = badgeDesc;
    }
}
