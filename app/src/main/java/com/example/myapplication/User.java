package com.example.myapplication;

import java.io.Serializable;

public class User implements Serializable {
   public String userName;
   public String password;

    public User() {
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }


}
