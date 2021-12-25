package com.example.myhealthnew;

public class User {
    public String firstPassword;
    public String email;


    public User(String email, String password){
        this.firstPassword = password;
        this.email = email;
    }
    public User(){}
}
