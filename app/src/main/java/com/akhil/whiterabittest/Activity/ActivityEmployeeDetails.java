package com.akhil.whiterabittest.Activity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.akhil.whiterabittest.R;
import com.akhil.whiterabittest.databinding.ActivityEmpDetailsBinding;
import com.akhil.whiterabittest.databinding.ActivityMainBinding;
import com.akhil.whiterabittest.model.EmployeeModel;
import com.akhil.whiterabittest.viewmodel.DeveloperViewModel;
import com.akhil.whiterabittest.viewmodel.EmployeeDetailsModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class ActivityEmployeeDetails extends AppCompatActivity {

    ActivityEmpDetailsBinding EmpDetailsBinding;
    private EmployeeDetailsModel mainViewModel;
    public static Context context;
    public static Context instance(){
        return context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=this;
        EmpDetailsBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_emp_details);

                mainViewModel = ViewModelProviders.of(this).get(EmployeeDetailsModel.class);
        mainViewModel.setBinding(EmpDetailsBinding,this);
        Intent intent = getIntent();
        mainViewModel.setValue(intent.getStringExtra("empId"));


    }

}