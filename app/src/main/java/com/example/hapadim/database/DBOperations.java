package com.example.hapadim.database;

import android.support.annotation.Nullable;

import io.realm.Realm;
import io.realm.RealmList;

/**
 * Created by meltemyildirim on 3/3/17.
 */

public class DBOperations {

    private static DBOperations instance;

    public static DBOperations getInstance() {
        if (instance == null) {
            instance = new DBOperations();
        }
        return instance;
    }

    //constructor
    DBOperations(){

    }



    //Insert record to PlacesDB
    public void insertPlace(Realm realm, final int id, final String category, final String placeName, final long stepNumber, final String distance, final String urlVR, @Nullable final String urlIMG, @Nullable RealmList<Badge> badges){
        final Places p =  new Places();
        p.setPlaceId(id);
        p.setCategory(category);
        p.setPlaceName(placeName);
        p.setStepNumber(stepNumber);
        p.setDistance(distance);
        p.setUrlVR(urlVR);
        p.setUrlIMG(urlIMG);

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
//                Badge[] badgesArray;
//                badgesArray = (badges == null) ? null : (Badge[]) badges.toArray();
                realm.insertOrUpdate(p);
//                realm.createObjectFromJson(Places.class, "{category:"+category+", id:"+id+", placeName:"+placeName+", stepNumber:"+stepNumber+",distance:"+distance+", urlVR:"+urlVR+", urlIMG:"+urlIMG+"}");
            }
        });
    }
}
