package com.matirmony.matirmony.userDetails;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PersonalDetail")
public class PersonalDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String profileFor;
    private String name;
    private String emailId;
    private String password;
    private String otp;
    private boolean idActivated;
    private boolean acceptInvite;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ProfileDetailId")
    private ProfileDetail profileDetail;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Image")
    private Image image;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "horoscopeDetailId")
    private HoroscopeDetail horoscopeDetail;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "familyDetailId")
    private FamilyDetail familyDetail;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "careerDetailId")
    private CareerDetail careerDetail;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "loginDetailId")
    private LoginDetail loginDetail;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "friendListId")
    private FriendList friends;

    public PersonalDetail() {
    }

    public PersonalDetail(int id, String profileFor, String name, String emailId, String password, String otp, boolean idActivated, boolean acceptInvite, ProfileDetail profileDetail, Image image, HoroscopeDetail horoscopeDetail, FamilyDetail familyDetail, CareerDetail careerDetail, LoginDetail loginDetail, FriendList friends) {
        this.id = id;
        this.profileFor = profileFor;
        this.name = name;
        this.emailId = emailId;
        this.password = password;
        this.otp = otp;
        this.idActivated = idActivated;
        this.acceptInvite = acceptInvite;
        this.profileDetail = profileDetail;
        this.image = image;
        this.horoscopeDetail = horoscopeDetail;
        this.familyDetail = familyDetail;
        this.careerDetail = careerDetail;
        this.loginDetail = loginDetail;
        this.friends = friends;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProfileFor() {
        return profileFor;
    }

    public void setProfileFor(String profileFor) {
        this.profileFor = profileFor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public boolean isIdActivated() {
        return idActivated;
    }

    public void setIdActivated(boolean idActivated) {
        this.idActivated = idActivated;
    }

    public boolean isAcceptInvite() {
        return acceptInvite;
    }

    public void setAcceptInvite(boolean acceptInvite) {
        this.acceptInvite = acceptInvite;
    }

    public ProfileDetail getProfileDetail() {
        return profileDetail;
    }

    public void setProfileDetail(ProfileDetail profileDetail) {
        this.profileDetail = profileDetail;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public HoroscopeDetail getHoroscopeDetail() {
        return horoscopeDetail;
    }

    public void setHoroscopeDetail(HoroscopeDetail horoscopeDetail) {
        this.horoscopeDetail = horoscopeDetail;
    }

    public FamilyDetail getFamilyDetail() {
        return familyDetail;
    }

    public void setFamilyDetail(FamilyDetail familyDetail) {
        this.familyDetail = familyDetail;
    }

    public CareerDetail getCareerDetail() {
        return careerDetail;
    }

    public void setCareerDetail(CareerDetail careerDetail) {
        this.careerDetail = careerDetail;
    }

    public LoginDetail getLoginDetail() {
        return loginDetail;
    }

    public void setLoginDetail(LoginDetail loginDetail) {
        this.loginDetail = loginDetail;
    }

    public FriendList getFriends() {
        return friends;
    }

    public void setFriends(FriendList friends) {
        this.friends = friends;
    }

    @Override
    public String toString() {
        return "PersonalDetail{" +
                "id=" + id +
                ", profileFor='" + profileFor + '\'' +
                ", name='" + name + '\'' +
                ", emailId='" + emailId + '\'' +
                ", password='" + password + '\'' +
                ", otp='" + otp + '\'' +
                ", idActivated=" + idActivated +
                ", acceptInvite=" + acceptInvite +
                ", profileDetail=" + profileDetail +
                ", image=" + image +
                ", horoscopeDetail=" + horoscopeDetail +
                ", familyDetail=" + familyDetail +
                ", careerDetail=" + careerDetail +
                ", loginDetail=" + loginDetail +
                ", friends=" + friends +
                '}';
    }
}