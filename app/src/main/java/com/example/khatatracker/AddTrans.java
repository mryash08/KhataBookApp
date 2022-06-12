package com.example.khatatracker;

public class AddTrans {
    String date;
    String recive;
    String paid;
    String note;
    String time;
    String phone;
    Integer curbal;

    public AddTrans(String phone, String recive, String paid, String date, String time, String note) {
        this.date = date;
        this.recive = recive;
        this.paid = paid;
        this.note = note;
        this.time = time;
        this.phone = phone;
    }

    public Integer getCurbal() {
        return curbal;
    }

    public void setCurbal(Integer curbal) {
        this.curbal = curbal;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRecive() {
        return recive;
    }

    public void setRecive(String recive) {
        this.recive = recive;
    }

    public String getPaid() {
        return paid;
    }

    public void setPaid(String paid) {
        paid = paid;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
