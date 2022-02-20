package com.akhil.whiterabittest.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class EmployeeRoomModel {

    @PrimaryKey
    @NonNull
    private String id;

    private String email;
    private String username;
    private String name;
    private String profile_image;
    private String address;
    private String phone;
    private String website;
    private String company;

    public EmployeeRoomModel(String id, String username, String name, String email, String profile_image, String address, String phone, String website, String company ) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.email = email;
        this.profile_image = profile_image;
        this.address = address;
        this.phone = phone;
        this.website = website;
        this.company = company;

    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
