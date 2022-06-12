package com.example.khatatracker;

public class datamodel {

    String name , mobile, price, gave,id,paymentcheck,paymentmethod1,duedate,paymentcomplatedate,adress,whatsapp;

    public datamodel(String name, String mobile, String price, String gave, String id, String paymentcheck, String paymentmethod1, String duedate, String paymentcomplatedate, String adress, String whatsapp) {
        this.name = name;
        this.mobile = mobile;
        this.price = price;
        this.gave = gave;
        this.id = id;
        this.paymentcheck = paymentcheck;
        this.paymentmethod1 = paymentmethod1;
        this.duedate = duedate;
        this.paymentcomplatedate = paymentcomplatedate;
        this.adress = adress;
        this.whatsapp = whatsapp;
    }


    public String getPaymentcheck() {
        return paymentcheck;
    }

    public void setPaymentcheck(String paymentcheck) {
        this.paymentcheck = paymentcheck;
    }

    public String getPaymentmethod1() {
        return paymentmethod1;
    }

    public void setPaymentmethod1(String paymentmethod1) {
        this.paymentmethod1 = paymentmethod1;
    }

    public String getDuedate() {
        return duedate;
    }

    public void setDuedate(String duedate) {
        this.duedate = duedate;
    }

    public String getPaymentcomplatedate() {
        return paymentcomplatedate;
    }

    public void setPaymentcomplatedate(String paymentcomplatedate) {
        this.paymentcomplatedate = paymentcomplatedate;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getGave() {
        return gave;
    }

    public void setGave(String gave) {
        this.gave = gave;
    }
}
