package com.practise1.model;

import java.time.LocalDate;

public class Revenue {
    private int day;

    private int month;
    private int year;
    private LocalDate date;
    private double money;

    public Revenue() {
    }

    public Revenue(LocalDate date, double money) {
        this.date = date;
        this.money = money;
        this.day = date.getDayOfMonth();
        this.month = date.getMonthValue();
        this.year = date.getYear();
    }

    public Revenue(int month, int year) {
        this.month = month;
        this.year = year;
    }

    public Revenue(int year) {
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}
