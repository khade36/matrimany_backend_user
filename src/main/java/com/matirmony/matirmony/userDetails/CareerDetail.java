package com.matirmony.matirmony.userDetails;

import jakarta.persistence.*;

@Entity
@Table(name = "CareerDetail")
public class CareerDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "careerDetailId")
    private int id;
    private String countryDetails;
    private String state;
    private String city;
    private String highestEducation;
    private String employedIn;
    private String annualIncome;
    private String about;

    public CareerDetail(int id, String countryDetails, String state, String city, String highestEducation, String employedIn, String annualIncome, String about) {
        this.id = id;
        this.countryDetails = countryDetails;
        this.state = state;
        this.city = city;
        this.highestEducation = highestEducation;
        this.employedIn = employedIn;
        this.annualIncome = annualIncome;
        this.about = about;
    }

    public CareerDetail() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountryDetails() {
        return countryDetails;
    }

    public void setCountryDetails(String countryDetails) {
        this.countryDetails = countryDetails;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHighestEducation() {
        return highestEducation;
    }

    public void setHighestEducation(String highestEducation) {
        this.highestEducation = highestEducation;
    }

    public String getEmployedIn() {
        return employedIn;
    }

    public void setEmployedIn(String employedIn) {
        this.employedIn = employedIn;
    }

    public String getAnnualIncome() {
        return annualIncome;
    }

    public void setAnnualIncome(String annualIncome) {
        this.annualIncome = annualIncome;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    @Override
    public String toString() {
        return "CareerDetail{" +
                "id=" + id +
                ", countryDetails='" + countryDetails + '\'' +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", highestEducation='" + highestEducation + '\'' +
                ", employedIn='" + employedIn + '\'' +
                ", annualIncome='" + annualIncome + '\'' +
                ", about='" + about + '\'' +
                '}';
    }
}
