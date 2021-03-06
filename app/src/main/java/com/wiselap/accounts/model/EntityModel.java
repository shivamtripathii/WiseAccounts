package com.wiselap.accounts.model;

public class EntityModel {

        private long accountingProfileId;
        private String officeName;
        private String name;
        private String contactNo;
        private String ownerName;
        private String address;
        private String profileEntity;
        private String applicationTypeName;
        private long applicationUserId;
        private long shopAgentId;


    public EntityModel(long accountingProfileId, String officeName, String name, String contactNo,
                       String ownerName, String address, String profileEntity,
                       long applicationUserId, long shopAgentId, String applicationTypeName) {
        this.accountingProfileId = accountingProfileId;
        this.officeName = officeName;
        this.name = name;
        this.contactNo = contactNo;
        this.ownerName = ownerName;
        this.address = address;
        this.profileEntity = profileEntity;
        this.applicationUserId = applicationUserId;
        this.shopAgentId = shopAgentId;
        this.applicationTypeName = applicationTypeName;
    }
    public long getAccountingProfileId() {
        return accountingProfileId;
    }

    public void setAccountingProfileId(long accountingProfileId) {
        this.accountingProfileId = accountingProfileId;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProfileEntity() {
        return profileEntity;
    }

    public void setProfileEntity(String profileEntity) {
        this.profileEntity = profileEntity;
    }


    public long getApplicationUserId() {
        return applicationUserId;
    }

    public void setApplicationUserId(long applicationUserId) {
        this.applicationUserId = applicationUserId;
    }

    public long getShopAgentId() {
        return shopAgentId;
    }

    public void setShopAgentId(long shopAgentId) {
        this.shopAgentId = shopAgentId;
    }

    public String getApplicationTypeName() {
        return applicationTypeName;
    }

    public void setApplicationTypeName(String applicationTypeName) {
        this.applicationTypeName = applicationTypeName;
    }
}
