package com.akhil.whiterabittest.repository;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.akhil.whiterabittest.Activity.MainActivity;
import com.akhil.whiterabittest.RoomDatabase.MasterRoomDatabase;
import com.akhil.whiterabittest.model.EmployeeModel;
import com.akhil.whiterabittest.model.EmployeeRoomModel;
import com.akhil.whiterabittest.network.ApiDataService;
import com.akhil.whiterabittest.network.RetrofitClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployeeRepository {

    private static final String TAG = "EmployeeRepository";
    private ArrayList<EmployeeModel> mmDeveloperModelmist = new ArrayList<>();
    private MutableLiveData<List<EmployeeModel>> mutableLiveData = new MutableLiveData<>();
    private MasterRoomDatabase masterRoomDatabase;

    public EmployeeRepository() {
    }

    public MutableLiveData<List<EmployeeModel>> getData(Context context) {
        masterRoomDatabase = MasterRoomDatabase.getInstance(context);
        MutableLiveData<List<EmployeeModel>> listMutableLiveData;
        Cursor cursor = getDBCurser();
        if (cursor.getCount() == 0){
            listMutableLiveData = getMutableLiveData();
        }else {
            listMutableLiveData = getRoomLiveData();
        }
        return listMutableLiveData;
    }

    private Cursor getDBCurser() {
        return masterRoomDatabase.employeeDao().getAllEmployeeDetails();
    }

    public MutableLiveData<List<EmployeeModel>> getData(Context context,String search) {
        masterRoomDatabase = MasterRoomDatabase.getInstance(context);
        MutableLiveData<List<EmployeeModel>> listMutableLiveData;
        Cursor cursor = masterRoomDatabase.employeeDao().getEmployeeDetailsByName(search);
        mmDeveloperModelmist.clear();
        try {

            if (cursor.getCount() != 0) {
                if (cursor.moveToFirst()) {
                    do {
                        EmployeeModel developerModel = new EmployeeModel();
                        developerModel.setId(cursor.getString(cursor.getColumnIndex("id")));
                        developerModel.setName(cursor.getString(cursor.getColumnIndex("name")));
                        developerModel.setUsername(cursor.getString(cursor.getColumnIndex("username")));
                        developerModel.setEmail(cursor.getString(cursor.getColumnIndex("email")));
                        developerModel.setProfile_image(cursor.getString(cursor.getColumnIndex("profile_image")));
                        developerModel.setAddress(cursor.getString(cursor.getColumnIndex("address")));
                        developerModel.setPhone(cursor.getString(cursor.getColumnIndex("phone")));
                        developerModel.setWebsite(cursor.getString(cursor.getColumnIndex("website")));
                        JsonElement jsonElement = new JsonParser().parse(cursor.getString(cursor.getColumnIndex("company")));
                        if (!jsonElement.isJsonNull()) {
                            JsonObject jsonObject = jsonElement.getAsJsonObject();
                            developerModel.setCompany(jsonObject.get("name").getAsString());
                        }else {
                            developerModel.setCompany("");
                        }
                        mmDeveloperModelmist.add(developerModel);
                    } while (cursor.moveToNext());
                }
                cursor.close();
            }
        }catch (Exception e){

        }
        mutableLiveData.setValue(mmDeveloperModelmist);
        return mutableLiveData;
    }


    public MutableLiveData<List<EmployeeModel>> getRoomLiveData() {
        Cursor cursor = getDBCurser();
        mmDeveloperModelmist.clear();
        try {

            if (cursor.getCount() != 0) {
                if (cursor.moveToFirst()) {
                    do {
                        EmployeeModel developerModel = new EmployeeModel();
                        developerModel.setId(cursor.getString(cursor.getColumnIndex("id")));
                        developerModel.setName(cursor.getString(cursor.getColumnIndex("name")));
                        developerModel.setUsername(cursor.getString(cursor.getColumnIndex("username")));
                        developerModel.setEmail(cursor.getString(cursor.getColumnIndex("email")));
                        developerModel.setProfile_image(cursor.getString(cursor.getColumnIndex("profile_image")));
                        developerModel.setAddress(cursor.getString(cursor.getColumnIndex("address")));
                        developerModel.setPhone(cursor.getString(cursor.getColumnIndex("phone")));
                        developerModel.setWebsite(cursor.getString(cursor.getColumnIndex("website")));
                        JsonElement jsonElement = new JsonParser().parse(cursor.getString(cursor.getColumnIndex("company")));
                        if (!jsonElement.isJsonNull()) {
                            JsonObject jsonObject = jsonElement.getAsJsonObject();
                            developerModel.setCompany(jsonObject.get("name").getAsString());
                        }else {
                            developerModel.setCompany("");
                        }
                        mmDeveloperModelmist.add(developerModel);
                    } while (cursor.moveToNext());
                }
                cursor.close();
            }
        }catch (Exception e){

        }
        mutableLiveData.setValue(mmDeveloperModelmist);
        return mutableLiveData;
    }
    public MutableLiveData<List<EmployeeModel>> getMutableLiveData() {

        final ApiDataService userDataService = RetrofitClient.getService();
        Call<JsonArray> call = userDataService.getApiRequestsArray();

        call.enqueue(new Callback<JsonArray>() {
            String message;

            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> resp) {
                Log.d(TAG," resp: "+resp.body().toString());
                if (resp != null && resp.body() != null) {
                    JsonArray jsonArray = new JsonParser().parse(resp.body().toString()).getAsJsonArray();
                    if (jsonArray != null) {
                        for (int i = 0; i <= jsonArray.size() - 1; i++) {
                            try {

                                JsonObject jsonobj = new JsonParser().parse(jsonArray.get(i).toString()).getAsJsonObject();
                                String id = getJsonValue(jsonobj,"id");
                                String name = getJsonValue(jsonobj,"name");
                                String username = getJsonValue(jsonobj,"username");
                                String profile_image = getJsonValue(jsonobj,"profile_image");
                                String email = getJsonValue(jsonobj,"email");
                                String website = getJsonValue(jsonobj,"website");
                                String phone = getJsonValue(jsonobj,"phone");
                                String address = getJsonObjValue(jsonobj,"address");
                                String company = getJsonObjValue(jsonobj,"company");
                                masterRoomDatabase.employeeDao().insert(new EmployeeRoomModel(id,username,name,email,profile_image,address,phone,website,company));

                            } catch (Exception ex) {
                                Log.d(TAG," Exception: "+ex.toString().toString());
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                t.printStackTrace();
                Log.d(TAG,"onFailure : "+t.getMessage());
            }
        });
        return getRoomLiveData();
    }

    private String getJsonValue(JsonObject jsonobj, String val){
        String strVal;
        if (!jsonobj.get(val).isJsonNull()) {
            strVal = jsonobj.get(val).getAsString();
        }else {
            strVal = "";
        }
        return strVal;
    }

    private String getJsonObjValue(JsonObject jsonobj, String val){
        String strVal;
        if (!jsonobj.get(val).isJsonNull()) {
            JsonObject jsonObject = jsonobj.get(val).getAsJsonObject();
            strVal = jsonObject.toString();
        }else {
            strVal = "";
        }
        return strVal;
    }
}
