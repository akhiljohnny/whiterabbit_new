package com.akhil.whiterabittest.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.akhil.whiterabittest.Adatper.EnployeeAdapter;
import com.akhil.whiterabittest.R;
import com.akhil.whiterabittest.databinding.ActivityMainBinding;
import com.akhil.whiterabittest.listeners.EmployeeListener;
import com.akhil.whiterabittest.model.EmployeeModel;
import com.akhil.whiterabittest.viewmodel.DeveloperViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements EmployeeListener {

    private ActivityMainBinding activityMainBinding;
    private ProgressBar loadBar;
    private  RecyclerView recyclerView;
    private DeveloperViewModel mainViewModel;
    private EnployeeAdapter enployeeAdapter;
    public static Context context;
    public static Context instance(){
        return context;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=this;
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        recyclerView = activityMainBinding.viewdeveloper;
        loadBar = activityMainBinding.loadBar;
        mainViewModel = ViewModelProviders.of(this).get(DeveloperViewModel.class);
        init();
        getAllDev("");
    }

    @Override
    public void onListClick(String id) {
        callIntent(id);

    }

    private void init(){
        enployeeAdapter = new EnployeeAdapter(this::onListClick);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(enployeeAdapter);

        activityMainBinding.editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    getAllDev(v.getText().toString());
                    return true;
                }
                return false;
            }
        });
    }

    private void getAllDev(String search) {
        mainViewModel.getAllDeveloper(this,search).observe(this, new Observer<List<EmployeeModel>>() {
            @Override
            public void onChanged(@Nullable List<EmployeeModel> mDeveloperModel) {
                enployeeAdapter.setDeveloperList((ArrayList<EmployeeModel>) mDeveloperModel);
                loadBar.setVisibility(View.GONE);
            }
        });
    }

    private void callIntent(String empId){
        Intent intent = new Intent(context, ActivityEmployeeDetails.class);
        intent.putExtra("empId", empId);
        context.startActivity(intent);
    }
}