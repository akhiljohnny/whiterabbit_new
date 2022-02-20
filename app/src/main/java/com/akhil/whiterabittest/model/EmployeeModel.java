package com.akhil.whiterabittest.model;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.akhil.whiterabittest.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.annotations.SerializedName;


public class EmployeeModel {

    @SerializedName("id")
    private String id;

    @SerializedName("phone")
    private String phone;


    @SerializedName("website")
    private String website;


    @SerializedName("company")
    private String company;


    @SerializedName("email")
    private String email;

    @SerializedName("address")
    private String address;

    @SerializedName("username")
    private String username;

    @SerializedName("name")
    private String name;

    @SerializedName("profile_image")
    private String profile_image;

    @BindingAdapter({"profile_image"})
    public static void loadImage(ImageView imageView, String imageURL) {

        Glide.with(imageView.getContext())
                .setDefaultRequestOptions(new RequestOptions()
                        .circleCrop())
                .load(imageURL)
                .placeholder(R.mipmap.ic_launcher)
                .into(imageView);
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

    public void setName(String name) {
        this.name = name;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
