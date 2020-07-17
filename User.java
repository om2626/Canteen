package com.example.canteenapp;


public class User {
    private String uname, upass , upasscon;


    public void setUname(String uname) {
        this.uname = uname;
    }

    public User(){
        //
    }

    public String getUname() {
        return uname;
    }

    public String getUpass() {
        return upass;
    }

    public void setUpass(String upass) {
        this.upass = upass;
    }

    public String getUpasscon() {
        return upasscon;
    }

    public void setUpasscon(String upasscon) {
        this.upasscon = upasscon;
    }
}
