package com.drolegames.jespercv.models;

/**
 * Created by Jesper Turesson on 2015-01-29.
 */
public class GooglePlayApplication {

    public String packageName;
    public int imageResource;

    public GooglePlayApplication(int imageResource, String packageName) {
        this.imageResource = imageResource;
        this.packageName = packageName;
    }
}
