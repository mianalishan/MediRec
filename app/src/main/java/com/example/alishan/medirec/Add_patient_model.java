package com.example.alishan.medirec;

import android.view.View;

import java.util.ArrayList;

/**
 * Created by AliShan on 4/23/2018.
 */

public class Add_patient_model {

    private int  p_id;
    private String p_Name;
    private String p_Contact;
    private String p_Gender;
    private String p_Email;
    private String p_Address;
    private String p_EmergencyContact;
    private View.OnClickListener requestBtnClickListener;
public Add_patient_model(){};
    public Add_patient_model(int p_id ,String p_Name, String p_Contact,  String p_Gender , String p_Email, String p_Address, String p_EmergencyContact) {
       this.p_id= p_id;
        this.p_Name = p_Name;
        this.p_Contact = p_Contact;
        this.p_Gender= p_Gender;
        this.p_Email = p_Email;
        this.p_Address = p_Address;
        this.p_EmergencyContact = p_EmergencyContact;
    }

    public String getP_Name() {
        return p_Name;
    }

    public int getP_id() {
        return p_id;
    }

    public void setP_id(int p_id) {
        this.p_id = p_id;
    }

    public void setP_Name(String p_Name) {
        this.p_Name = p_Name;
    }

    public String getP_Contact() {
        return p_Contact;
    }

    public void setP_Contact(String p_Contact) {
        this.p_Contact = p_Contact;
    }

    public String getP_Email() {
        return p_Email;
    }

    public void setP_Email(String p_Email) {
        this.p_Email = p_Email;
    }

    public String getP_Address() {
        return p_Address;
    }

    public void setP_Address(String p_Address) {
        this.p_Address = p_Address;
    }

    public String getP_EmergencyContact() {
        return p_EmergencyContact;
    }

    public void setP_EmergencyContact(String p_EmergencyContact) {
        this.p_EmergencyContact = p_EmergencyContact;
    }

    public String getP_Gender() {
        return p_Gender;
    }

    public void setP_Gender(String p_Gender) {
        this.p_Gender = p_Gender;
    }
    public View.OnClickListener getRequestBtnClickListener() {
        return requestBtnClickListener;
    }

    public void setRequestBtnClickListener(View.OnClickListener requestBtnClickListener) {
        this.requestBtnClickListener = requestBtnClickListener;
    }
    public static ArrayList<Add_patient_model> getTestingList() {
        ArrayList<Add_patient_model> items = new ArrayList<>();
        items.add(new Add_patient_model(1,"123","2sss","sssss","ssss","ssss","dddd"));

    return items;
    }
}
