package com.matirmony.matirmony.userDetails;

import jakarta.persistence.*;

@Entity
@Table(name = "ProfileDetail")
public class ProfileDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ProfileDetailId")
    private int id;
    private String dob;
    private String subCaste;
    private String motherTongue;
    private String maritalStatus;
    private String phoneNumber;


    public ProfileDetail(int id, String dob, String subCaste, String motherTongue, String maritalStatus, String phoneNumber) {
        this.id = id;
        this.dob = dob;
        this.subCaste = subCaste;
        this.motherTongue = motherTongue;
        this.maritalStatus = maritalStatus;
        this.phoneNumber=phoneNumber;
    }

    public ProfileDetail() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getSubCaste() {
        return subCaste;
    }

    public void setSubCaste(String subCaste) {
        this.subCaste = subCaste;
    }

    public String getMotherTongue() {
        return motherTongue;
    }

    public void setMotherTongue(String motherTongue) {
        this.motherTongue = motherTongue;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "ProfileDetail{" +
                "id=" + id +
                ", dob='" + dob + '\'' +
                ", subCaste='" + subCaste + '\'' +
                ", motherTongue='" + motherTongue + '\'' +
                ", maritalStatus='" + maritalStatus + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
