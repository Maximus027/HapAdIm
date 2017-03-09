package com.example.hapadim.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by meltemyildirim on 3/7/17.
 */

public class UserProfile {

    private long userID;
    private String name;
    private List<Badge> badges;

    public long getUserID() {
        return userID;
    }

    public String getName() {
        return name;
    }

    public List<Badge> getBadges() {
        if(badges != null){
            return badges;
        }
        else{
            return new ArrayList<>();
        }
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBadges(List<Badge> badges) {
        this.badges = badges;
    }
}
