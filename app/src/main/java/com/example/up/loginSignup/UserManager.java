package com.example.up.loginSignup;

import com.google.firebase.database.Exclude;

public class UserManager {
    public String userName;
    public String email;
    public String userPassword;
    public String userPhoneNo;
    public String userId;
    private String uKey;

    public UserManager(){ }

    public UserManager(String userName, String email, String userPassword, String userPhoneNo, String userId){
        if(userName.isEmpty())
            userName = "None";
        else
            this.userName = userName;

        if(email.isEmpty())
            email = "None";
        else
            this.email = email;

        if(userPassword.isEmpty())
            userPassword = "None";
        else
            this.userPassword = userPassword;

        if(userPhoneNo.isEmpty())
            userPhoneNo = "None";
        else
            this.userPhoneNo = userPhoneNo;

        this.userId = userId;
    }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { userName = userName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { email = email; }

    public String getUserPassword() { return userPassword; }
    public void setUserPassword(String userPassword) { userPassword = userPassword; }

    public String getUserPhoneNo() { return userPhoneNo; }
    public void setUserPhoneNo(String userPhoneNo) { userPhoneNo = userPhoneNo; }

    public String getUserId(){return userId;}


    @Exclude
    public String getuKey() { return uKey; }
    @Exclude
    public void setuKey(String ukey) { uKey = ukey; }
}
