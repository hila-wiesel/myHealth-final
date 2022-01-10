package com.example.myhealthnew;

public class Administrator extends User{
    public String userType;


    public Administrator(String email, String password) {
        super(email, password);
        this.userType = "Admin";
    }
}