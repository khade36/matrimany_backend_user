package com.matirmony.matirmony.userDetails;

import jakarta.persistence.*;

@Entity
@Table(name = "HoroscopeDetail")
public class HoroscopeDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "horoscopeDetailId")
    private int id;
    private String star;
    private String rasi;
    private String gothra;
    private String manglik;
    private String birthTime;
    private String countryOfBirth;
    private String stateOfBirth;
    private String cityOfBirth;

    public HoroscopeDetail(int id, String star, String rasi, String gothra, String manglik, String birthTime, String countryOfBirth, String stateOfBirth, String cityOfBirth) {
        this.id = id;
        this.star = star;
        this.rasi = rasi;
        this.gothra = gothra;
        this.manglik = manglik;
        this.birthTime = birthTime;
        this.countryOfBirth = countryOfBirth;
        this.stateOfBirth = stateOfBirth;
        this.cityOfBirth=cityOfBirth;
    }

    public HoroscopeDetail() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getRasi() {
        return rasi;
    }

    public void setRasi(String rasi) {
        this.rasi = rasi;
    }

    public String getGothra() {
        return gothra;
    }

    public void setGothra(String gothra) {
        this.gothra = gothra;
    }

    public String getManglik() {
        return manglik;
    }

    public void setManglik(String manglik) {
        this.manglik = manglik;
    }

    public String getBirthTime() {
        return birthTime;
    }

    public void setBirthTime(String birthTime) {
        this.birthTime = birthTime;
    }

    public String getCountryOfBirth() {
        return countryOfBirth;
    }

    public void setCountryOfBirth(String countryOfBirth) {
        this.countryOfBirth = countryOfBirth;
    }

    public String getStateOfBirth() {
        return stateOfBirth;
    }

    public void setStateOfBirth(String stateOfBirth) {
        this.stateOfBirth = stateOfBirth;
    }

    public String getCityOfBirth() {
        return cityOfBirth;
    }

    public void setCityOfBirth(String cityOfBirth) {
        this.cityOfBirth = cityOfBirth;
    }

    @Override
    public String toString() {
        return "HoroscopeDetail{" +
                "id=" + id +
                ", star='" + star + '\'' +
                ", rasi='" + rasi + '\'' +
                ", gothra='" + gothra + '\'' +
                ", manglik='" + manglik + '\'' +
                ", birthTime='" + birthTime + '\'' +
                ", countryOfBirth='" + countryOfBirth + '\'' +
                ", stateOfBirth='" + stateOfBirth + '\'' +
                ", cityOfBirth='" + cityOfBirth + '\'' +
                '}';
    }
}
