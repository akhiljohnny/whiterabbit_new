package com.akhil.whiterabittest.Adatper;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.akhil.whiterabittest.Activity.MainActivity;
import com.akhil.whiterabittest.R;
import com.akhil.whiterabittest.databinding.SingleListItemBinding;
import com.akhil.whiterabittest.listeners.EmployeeListener;
import com.akhil.whiterabittest.model.EmployeeModel;

import java.util.ArrayList;

public class EnployeeAdapter
        extends RecyclerView.Adapter<EnployeeAdapter.DeveloperViewHolder> {
    private static final String TAG = "EnployeeAdapter";
    private ArrayList<EmployeeModel> mDeveloperModel;
    private EmployeeListener employeeListener;

    public EnployeeAdapter(EmployeeListener employeeListener) {
        this.employeeListener  = employeeListener;
    }

    @NonNull
    @Override
    public DeveloperViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        SingleListItemBinding mDeveloperListItemBinding =
                DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                        R.layout.single_list_item, viewGroup, false);

        return new DeveloperViewHolder(mDeveloperListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull DeveloperViewHolder mDeveloperViewHolder, int i) {
        EmployeeModel currentStudent = mDeveloperModel.get(i);
        mDeveloperViewHolder.mDeveloperListItemBinding.setDeveloperModel(currentStudent);
        Log.d(TAG," currentStudent : "+currentStudent.getName()+"  position : "+i);
        mDeveloperViewHolder.mDeveloperListItemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                employeeListener.onListClick(currentStudent.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mDeveloperModel != null) {
            return mDeveloperModel.size();
        } else {
            return 0;
        }
    }

    public void setDeveloperList(ArrayList<EmployeeModel> mDeveloperModel) {
        this.mDeveloperModel = mDeveloperModel;
        Log.d(TAG," mDeveloperModel size : "+mDeveloperModel.size());
        notifyDataSetChanged();
    }

    class DeveloperViewHolder extends RecyclerView.ViewHolder {
        SingleListItemBinding mDeveloperListItemBinding;
        public DeveloperViewHolder(@NonNull SingleListItemBinding mDeveloperListItemBinding) {
            super(mDeveloperListItemBinding.getRoot());
            this.mDeveloperListItemBinding = mDeveloperListItemBinding;
        }
    }
}
