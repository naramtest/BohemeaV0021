package com.emargystudio.bohemeav0021.Model;

public class Reservation {

    private int res_id;
    private int user_id;
    private int table_id;
    private int year;
    private int month;
    private int day;
    private double hours;
    private double end_hour;
    private int chairNumber;


    public Reservation(int res_id, int user_id, int table_id, int year, int month, int day, double hours, double end_hour, int chairNumber) {
        this.res_id = res_id;
        this.user_id = user_id;
        this.table_id = table_id;
        this.year = year;
        this.month = month;
        this.day = day;
        this.hours = hours;
        this.end_hour = end_hour;
        this.chairNumber = chairNumber;
    }

    public int getRes_id() {
        return res_id;
    }

    public void setRes_id(int res_id) {
        this.res_id = res_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getTable_id() {
        return table_id;
    }

    public void setTable_id(int table_id) {
        this.table_id = table_id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public double getHours() {
        return hours;
    }

    public void setHours(double hours) {
        this.hours = hours;
    }

    public double getEnd_hour() {
        return end_hour;
    }

    public void setEnd_hour(double end_hour) {
        this.end_hour = end_hour;
    }

    public int getChairNumber() {
        return chairNumber;
    }

    public void setChairNumber(int chairNumber) {
        this.chairNumber = chairNumber;
    }
}
