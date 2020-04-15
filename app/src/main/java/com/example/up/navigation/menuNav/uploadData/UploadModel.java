package com.example.up.navigation.menuNav.uploadData;

import com.google.firebase.database.Exclude;

public class UploadModel {
    private String mName;
    private String mLink;
    private String mKey;

    public UploadModel() {}

    public UploadModel(String name, String link) {
        if (name.trim().equals("")) {
            name = "None";
        }
        if (link.trim().equals("")) {
            link = "None";
        }

        mName = name;
        mLink = link;
    }

    public String getName() { return mName; }
    public void setName(String name) { mName = name; }

    public String getLink() { return mLink; }
    public void setLink(String link) { mLink = link; }

    @Exclude
    public String getKey() { return mKey; }
    @Exclude
    public void setKey(String key) { mKey = key; }
}