package com.example.up.navigation.menuNav.uploadData;

import com.google.firebase.database.Exclude;

public class UploadModel {
    private String mName;
    private String mImageUrl;
    private String mKey;
    private String mLink;

    public UploadModel() { }

    public UploadModel(String name, String link, String imageUrl) {
        if (name.trim().equals("")) {
            name = "No title";
        }
        mName = name;
        mImageUrl = imageUrl;
        mLink = link;
    }

    public String getName() { return mName; }
    public void setName(String name) { mName = name; }

    public String getImageUrl() { return mImageUrl; }
    public void setImageUrl(String imageUrl) { mImageUrl = imageUrl; }

    public String getLink() { return mLink; }
    public void setLink(String link) { mLink = link; }

    @Exclude
    public String getKey() { return mKey; }
    @Exclude
    public void setKey(String key) { mKey = key; }
}