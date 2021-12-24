package com.example.myhealthnew;


import java.util.ArrayList;

public class Client extends User {
//    enum Gender{
//        male,
//        female
//    }
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
    public int leftCaloriesPerDay;


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
    }
}
