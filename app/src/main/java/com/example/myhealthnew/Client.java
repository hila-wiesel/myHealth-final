package com.example.myhealthnew;


import java.util.ArrayList;
import java.util.Date;

public class Client extends User {

    public String userType;
    public String  name;
    public double height;
    public double weight;
    public String city;
    public String country;
    public int birthYear;
    public String gender;
    public int phoneNumber;
    public ArrayList<Integer> waterReminderFrequency;
    public ArrayList<Integer> sportReminderFrequency;
    public double recommendedCaloriesPerDay;
    public double leftCaloriesPerDay;
    public String lastRestart;


    public Client(String email, String password, String name, int height, int weight, String city,
                  String country, int birthYear,String  gender, int phoneNumber, double recommendedCaloriesPerDay) {
        super(email, password);
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.city = city;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.birthYear = birthYear;
        this.gender = gender;
        this.recommendedCaloriesPerDay = recommendedCaloriesPerDay;
        this.leftCaloriesPerDay = recommendedCaloriesPerDay;
        this.userType = "Client";
        this.lastRestart = new Date().toString();
    }
}
