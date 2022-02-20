package com.akhil.whiterabittest.viewmodel;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.akhil.whiterabittest.Activity.MainActivity;
import com.akhil.whiterabittest.databinding.ActivityMainBinding;
import com.akhil.whiterabittest.listeners.EmployeeListener;
import com.akhil.whiterabittest.model.EmployeeModel;
import com.akhil.whiterabittest.repository.EmployeeRepository;

import java.util.List;


public class DeveloperViewModel extends AndroidViewModel  {
    private EmployeeRepository mDeveloperRepository;
    public DeveloperViewModel(@NonNull Application application) {
        super(application);
        mDeveloperRepository = new EmployeeRepository();
    }

    public LiveData<List<EmployeeModel>> getAllDeveloper(Context context,String search) {
        if (!search.equals("")){
            return mDeveloperRepository.getData(context,search);
        }else {
            return mDeveloperRepository.getData(context);
        }
    }
}
