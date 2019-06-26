package com.wiselap.accounts.model;

import java.io.Serializable;

public class AccountModel implements Serializable {
    private String officeName;
    private String address;
    private String ownerName;
    private String name;
    private String email;
    private String contactNo;
    private  String profileEntity;

    public AccountModel(String officeName, String address, String ownerName, String name, String email, String contactNo, String profileEntity) {
        this.officeName = officeName;
        this.address = address;
        this.ownerName = ownerName;
        this.name = name;
        this.email = email;
        this.contactNo = contactNo;
        this.profileEntity = profileEntity;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getProfileEntity() {
        return profileEntity;
    }

    public void setProfileEntity(String profileEntity) {
        this.profileEntity = profileEntity;
    }
}
