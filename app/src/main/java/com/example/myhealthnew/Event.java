package com.example.myhealthnew;

public class Event {
    enum Category{
        sport,
        nutrition
    }
    public String name;
    public Category category;
    public String registerLink;
    public String city;
    public String street;
    public int houseNum;
    public int day_date;
    public int month_date;
    public int year_date;
    public int minute_startTime;
    public int hour_startTime;
    public int minute_endTime;
    public int hour_endTime;
    public String description;
    //picture

    public  Event( String name, Category category, String registerLink, String city, String street,
                   int houseNum, int day_date, int month_date, int year_date, int minute_startTime,
                   int hour_startTime, int minute_endTime, int hour_endTime){
        this.name = name;
        this.category = category;
        this.registerLink = registerLink;
        this.city = city;
        this.street = street;
        this.houseNum = houseNum;
        this.day_date = day_date;
        this.month_date = month_date;
        this.year_date = year_date;
        this.minute_startTime= minute_startTime;
        this.hour_startTime = hour_startTime;
        this.minute_endTime = minute_endTime;
        this.hour_endTime = hour_endTime;
    }


}
