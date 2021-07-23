package com.example.alishan.medirec;

public class Report_Model {
    private int c_id;
    private String Time;
    private  String Date;
    private String bloodPressure;
    private String Temperature;
    private String Respiration;
    private String Sugar;
    private String SleepHours;

    public String getPluse() {
        return pluse;
    }

    public void setPluse(String pluse) {
        this.pluse = pluse;
    }

    private String pluse;

    public Report_Model() {
    }

    public int getP_id() {
        return p_id;

    }

    public void setP_id(int p_id) {
        this.p_id = p_id;
    }

    private int p_id;

    public Report_Model(int c_id,int P_id, String time, String date, String bloodPressure, String temperature, String respiration, String sugar, String sleepHours,String pluse) {
        this.c_id = c_id;
        this.p_id=P_id;
        Time = time;
        Date = date;
        this.bloodPressure = bloodPressure;
        Temperature = temperature;
        Respiration = respiration;
        Sugar = sugar;
        SleepHours = sleepHours;
        this.pluse=pluse;
    }

    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public String getTemperature() {
        return Temperature;
    }

    public void setTemperature(String temperature) {
        Temperature = temperature;
    }

    public String getRespiration() {
        return Respiration;
    }

    public void setRespiration(String respiration) {
        Respiration = respiration;
    }

    public String getSugar() {
        return Sugar;
    }

    public void setSugar(String sugar) {
        Sugar = sugar;
    }

    public String getSleepHours() {
        return SleepHours;
    }

    public void setSleepHours(String sleepHours) {
        SleepHours = sleepHours;
    }
}
