package com.akhil.whiterabittest.viewmodel;


import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.akhil.whiterabittest.R;
import com.akhil.whiterabittest.RoomDatabase.MasterRoomDatabase;
import com.akhil.whiterabittest.databinding.ActivityEmpDetailsBinding;
import com.akhil.whiterabittest.model.EmployeeRoomModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.security.PrivateKey;

public class EmployeeDetailsModel extends AndroidViewModel {

    private ActivityEmpDetailsBinding empDetailsBinding;
    private Context context;

    public EmployeeDetailsModel(@NonNull Application application) {
        super(application);
    }


    public void setBinding(ActivityEmpDetailsBinding empDetailsBinding, Context context) {
        this.empDetailsBinding = empDetailsBinding;
        this.context = context;
    }

    public void setValue(String s) {
        EmployeeRoomModel employeeDetails = MasterRoomDatabase.getInstance(context).employeeDao().getEmployeeDetails(s);
        Glide.with(context.getApplicationContext())
                .setDefaultRequestOptions(new RequestOptions()
                        .circleCrop())
                .load(employeeDetails.getProfile_image())
                .placeholder(R.mipmap.ic_launcher)
                .into(empDetailsBinding.imgPic);

        empDetailsBinding.name.setText(": "+employeeDetails.getName());
        empDetailsBinding.tvUsernme.setText(": "+employeeDetails.getUsername());
        empDetailsBinding.tvEmail.setText(": "+employeeDetails.getEmail());
        empDetailsBinding.tvPhone.setText(": "+employeeDetails.getPhone());
        empDetailsBinding.tvWebsite.setText(": "+employeeDetails.getWebsite());
        empDetailsBinding.tvAddress.setText(": "+employeeDetails.getAddress());
        JsonElement jsonElementAddress = new JsonParser().parse(employeeDetails.getAddress());
        if (!jsonElementAddress.isJsonNull()) {
            String addr="";

            JsonObject jsonObject = jsonElementAddress.getAsJsonObject();
            addr=jsonObject.get("street").getAsString()+"\n"+jsonObject.get("suite").getAsString()+"\n"
                    +jsonObject.get("city").getAsString()+"\n"+jsonObject.get("zipcode").getAsString();
            empDetailsBinding.tvAddress.setText(": "+addr);

        }else {
            empDetailsBinding.tvAddress.setText(": ");
        }

        JsonElement jsonElementCompany = new JsonParser().parse(employeeDetails.getCompany());
        if (!jsonElementCompany.isJsonNull()) {
            String comp="";
            JsonObject jsonObject = jsonElementCompany.getAsJsonObject();
            comp=jsonObject.get("name").getAsString()+"\n"+jsonObject.get("catchPhrase").getAsString()+"\n"
                    +jsonObject.get("bs").getAsString();
            empDetailsBinding.tvCompanyDetails.setText(": "+comp);

        }else {
            empDetailsBinding.tvCompanyDetails.setText(": ");
        }



    }
}