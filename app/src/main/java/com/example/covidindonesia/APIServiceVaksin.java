package com.example.covidindonesia;

import com.example.covidindonesia.Model.Vaksin.ResponseVaksinIndonesia;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIServiceVaksin {
    @GET("pemeriksaan-vaksinasi.json")
    Call<ResponseVaksinIndonesia> RESPONSE_VAKSIN_INDONESIA();
}
