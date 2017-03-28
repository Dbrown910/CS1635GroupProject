package cs1635.g8.hello;

import android.graphics.Bitmap;

public class User {
    String name;
    String cell;
    String email;
    String company;
    String website;
    Bitmap profilePicture;

    public User(String name, String cell, String email, String company, String website, Bitmap profilePic) {
        this.name = name;
        this.cell = cell;
        this.email = email;
        this.company = company;
        this.website = website;
        this.profilePicture = profilePic;
    }
}
