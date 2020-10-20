package com.example.bloodbank;

public class Doners_data {

    String sid;
    String mname;
    String mgender;
    String memail;
    String maddress;
    String mblodg;
    String mphone;


    public Doners_data(){

    }

    public Doners_data(String sid, String mname, String mgender, String memail, String maddress, String mblodg, String mphone) {

        if (mname.trim().equals("")){
            mname = "No Input";
        }
        if (mgender.trim().equals("")){
            mgender = "No Input";
        }
        if (memail.trim().equals("")){
            memail = "No Input";
        }
        if (maddress.trim().equals("")){
            maddress = "No Input";
        }

        if (mblodg.trim().equals("")){
            mblodg = "No Input";
        }

        this.sid = sid;
        this.mname = mname;
        this.mgender = mgender;
        this.memail = memail;
        this.maddress = maddress;
        this.mblodg = mblodg;
        this.mphone = mphone;

    }

    public String getSid() {
        return sid;
    }

    public String getMname() {
        return mname;
    }

    public String getMgender() {
        return mgender;
    }

    public String getMemail() {
        return memail;
    }

    public String getMaddress() {
        return maddress;
    }


    public String getMblodg() {
        return mblodg;
    }

    public String getMphone(){return mphone;}
}
