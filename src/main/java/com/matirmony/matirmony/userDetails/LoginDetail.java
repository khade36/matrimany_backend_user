package com.matirmony.matirmony.userDetails;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;


@Entity
@Table(name = "LoginDetail")
public class LoginDetail {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private int id;
    private String emailId;
    private String password;

    public LoginDetail(int id,String emailId, String password) {
        this.id=id;
        this.emailId = emailId;
        this.password = password;
    }

    public LoginDetail() {
    }
    public void setId(int id){
        this.id=id;
    }
    public int getId(){
        return id;
    }
    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginDetail{" +
                ", emailId='" + id + '\'' +
                ", emailId='" + emailId + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}