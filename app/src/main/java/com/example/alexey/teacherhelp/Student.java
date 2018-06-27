package com.example.alexey.teacherhelp;

public class Student {

    private int id;
    private int price;
    private int duration;
    private String day;
    private String FIO;
    private String address;
    private String time;
    private String subject;

    public Student(){

    }

    public Student(int id,String FIO,String subject,int duration,
                   String time, String day, String address,int price){
        this.id = id;
        this.FIO = FIO;
        this.subject = subject;
        this.duration = duration;
        this.time = time;
        this.day = day;
        this.address = address;
        this.price = price;
    }

    public void addSubject(String subject){

    }

    public String getDay(){
        return day;
    }
    public int getId(){
        return id;
    }
    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
