package com.example.covidindonesia;

import com.example.covidindonesia.Model.Covid.ResponseCovidIndonesia;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIServiceKasus {
    @GET("update.json")
    Call<ResponseCovidIndonesia> RESPONSE_COVID_INDONESIA();
}
