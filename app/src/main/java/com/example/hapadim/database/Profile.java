package com.example.hapadim.database;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by meltemyildirim on 3/1/17.
 */

public class Profile extends RealmObject {
    @PrimaryKey
    private long userID;
    private String name;
    private RealmList<Badge> badges;

    //getter
    public long getUserID() {
        return userID;
    }

    public String getName() {
        return name;
    }

    public RealmList<Badge> getBadges() {
        return badges;
    }

    //setter
    public void setUserID(long userID) {
        this.userID = userID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBadges(RealmList<Badge> badges) {
        this.badges = badges;
    }
}
