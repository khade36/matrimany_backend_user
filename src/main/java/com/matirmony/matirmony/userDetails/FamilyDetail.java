package com.matirmony.matirmony.userDetails;

import jakarta.persistence.*;

@Entity
@Table(name = "FamilyDetail")
public class FamilyDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "familyDetailId")
    private int id;
    private String familyType;
    private String fatherOccupation;
    private String motherOccupation;
    private String sibling; //need to add the string array in the front end
    private String homeTown;
    private String aboutFamily;

    public FamilyDetail(int id, String fatherOccupation, String motherOccupation, String sibling, String homeTown, String aboutFamily, String familyType) {
        this.id = id;
        this.fatherOccupation = fatherOccupation;
        this.motherOccupation = motherOccupation;
        this.sibling = sibling;
        this.homeTown = homeTown;
        this.aboutFamily = aboutFamily;
        this.familyType=familyType;
    }

    public FamilyDetail() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFamilyType() {
        return familyType;
    }

    public void setFamilyType(String familyType) {
        this.familyType = familyType;
    }

    public String getFatherOccupation() {
        return fatherOccupation;
    }

    public void setFatherOccupation(String fatherOccupation) {
        this.fatherOccupation = fatherOccupation;
    }

    public String getMotherOccupation() {
        return motherOccupation;
    }

    public void setMotherOccupation(String motherOccupation) {
        this.motherOccupation = motherOccupation;
    }

    public String getSibling() {
        return sibling;
    }

    public void setSibling(String sibling) {
        this.sibling = sibling;
    }

    public String getHomeTown() {
        return homeTown;
    }

    public void setHomeTown(String homeTown) {
        this.homeTown = homeTown;
    }

    public String getAboutFamily() {
        return aboutFamily;
    }

    public void setAboutFamily(String aboutFamily) {
        this.aboutFamily = aboutFamily;
    }

    @Override
    public String toString() {
        return "FamilyDetail{" +
                "id=" + id +
                ", familyType='" + familyType + '\'' +
                ", fatherOccupation='" + fatherOccupation + '\'' +
                ", motherOccupation='" + motherOccupation + '\'' +
                ", sibling='" + sibling + '\'' +
                ", homeTown='" + homeTown + '\'' +
                ", aboutFamily='" + aboutFamily + '\'' +
                '}';
    }
}
