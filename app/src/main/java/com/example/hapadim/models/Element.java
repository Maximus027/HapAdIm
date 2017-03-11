package com.example.hapadim.models;

import java.io.Serializable;

/**
 * Created by Nesada Koca on 3/4/17.
 */

public class Element implements Serializable {
    private String name;
    private int elevation;
    private int images;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getElevation() {
        return elevation;
    }

    public void setElevation(int elevation) {
        this.elevation = elevation;
    }

    public int getImages() {
        return images;
    }

    public void setImages(int images) {
        this.images = images;
    }
}
