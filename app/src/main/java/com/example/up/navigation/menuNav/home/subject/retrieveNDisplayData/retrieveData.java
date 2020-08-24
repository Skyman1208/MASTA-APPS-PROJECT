package com.example.up.navigation.menuNav.home.subject.retrieveNDisplayData;

import com.google.firebase.database.Exclude;

public class retrieveData {
        private String rdName;
        private String rdLink;
        private String rdKey;

        public retrieveData() {}

        public retrieveData(String name, String link) {
            if (name.trim().equals("")) {
                name = "None";
            }
            if (link.trim().equals("")) {
                link = "None";
            }

            rdName = name;
            rdLink = link;
        }

        public String getRdName() { return rdName; }
        public void setRdName(String name) { rdName = name; }

        public String getRdLink() { return rdLink; }
        public void setRdLink(String link) { rdLink = link; }

        @Exclude
        public String getRdKey() { return rdKey; }
        @Exclude
        public void setRdKey(String key) { rdKey = key; }
}
