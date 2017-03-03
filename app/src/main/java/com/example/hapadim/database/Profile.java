package com.example.hapadim.database;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by meltemyildirim on 3/1/17.
 */

public class Profile extends RealmObject {
    @PrimaryKey
    private long userID;

    private String name;
    private int avatarImg;
    private int badges;

    //getter


    public long getUserID() {
        return userID;
    }

    public int getBadges() {
        return badges;
    }

    public String getName() {
        return name;
    }

    public int getAvatarImg() {
        return avatarImg;
    }

    //setter
    public void setUserID(long userID) {
        this.userID = userID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAvatarImg(int avatarImg) {
        this.avatarImg = avatarImg;
    }

    public void setBadges(int badges) {
        this.badges = badges;
    }
}
