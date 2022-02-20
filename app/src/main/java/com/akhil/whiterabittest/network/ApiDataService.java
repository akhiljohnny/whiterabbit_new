package com.akhil.whiterabittest.network;


import com.google.gson.JsonArray;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiDataService {


    @GET("5d565297300000680030a986")
    Call<JsonArray> getApiRequestsArray();

}
