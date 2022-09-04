package com.example.covidindonesia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.covidindonesia.Model.Covid.ResponseCovidIndonesia;
import com.example.covidindonesia.Model.Vaksin.ResponseVaksinIndonesia;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView tvTanggalK, tvPenambahanP, tvTotalP, tvPenambahanM, tvTotalM, tvPenambahanS, tvTotalS,
            tvPenambahanD, tvTotalD,
            tvTanggalV, tvPenambahanV1, tvTotalV1, tvPenambahanV2, tvTotalV2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTanggalK = findViewById(R.id.tv_tanggalK);
        tvPenambahanP = findViewById(R.id.tv_penambahan_positif);
        tvTotalP = findViewById(R.id.tv_total_positif);
        tvPenambahanM = findViewById(R.id.tv_penambahan_meninggal);
        tvTotalM = findViewById(R.id.tv_total_meninggal);
        tvPenambahanS = findViewById(R.id.tv_penambahan_sembuh);
        tvTotalS = findViewById(R.id.tv_total_sembuh);
        tvPenambahanD = findViewById(R.id.tv_penambahan_dirawat);
        tvTotalD = findViewById(R.id.tv_total_dirawat);
        tvTanggalV = findViewById(R.id.tv_tanggalV);
        tvPenambahanV1 = findViewById(R.id.tv_penambahan_vaksinasi1);
        tvTotalV1 = findViewById(R.id.tv_total_vaksinasi1);
        tvPenambahanV2 = findViewById(R.id.tv_penambahan_vaksinasi2);
        tvTotalV2 = findViewById(R.id.tv_total_vaksinasi2);

        getDataCovid();
        getDataVaksin();
    }

    private void getDataCovid() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constant.COVID)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIServiceKasus api = retrofit.create(APIServiceKasus.class);
        api.RESPONSE_COVID_INDONESIA().enqueue(new Callback<ResponseCovidIndonesia>() {
            @Override
            public void onResponse(Call<ResponseCovidIndonesia> call, Response<ResponseCovidIndonesia> response) {
                if (response.code() == 200) {
                    NumberFormat formatter = new DecimalFormat("#,###");
                    String penambahanDiRawat = formatter.format(response.body().getUpdate().getPenambahan().getJumlah_dirawat());
                    String penambahanMeninggal = formatter.format(response.body().getUpdate().getPenambahan().getJumlah_meninggal());
                    String penambahanSembuh = formatter.format(response.body().getUpdate().getPenambahan().getJumlah_sembuh());
                    String penambahanPositif = formatter.format(response.body().getUpdate().getPenambahan().getJumlah_positif());

                    String totalDiRawat = formatter.format(response.body().getUpdate().getTotal().getJumlah_dirawat());
                    String totalMeninggal = formatter.format(response.body().getUpdate().getTotal().getJumlah_meninggal());
                    String totalSembuh = formatter.format(response.body().getUpdate().getTotal().getJumlah_sembuh());
                    String totalPositif = formatter.format(response.body().getUpdate().getTotal().getJumlah_positif());

                    tvTanggalK.setText("Data di dapat per tanggal\n" + response.body().getUpdate().getPenambahan().getTanggal());

                    tvPenambahanP.setText("+ "+penambahanPositif);
                    tvPenambahanM.setText("+ "+penambahanMeninggal);
                    tvPenambahanS.setText("+ "+penambahanSembuh);
                    tvPenambahanD.setText("+ "+penambahanDiRawat);

                    tvTotalP.setText(totalPositif);
                    tvTotalM.setText(totalMeninggal);
                    tvTotalS.setText(totalSembuh);
                    tvTotalD.setText(totalDiRawat);

                } else {
                    Intent intent = new Intent(MainActivity.this, Error.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ResponseCovidIndonesia> call, Throwable t) {

            }
        });
    }

    private void getDataVaksin() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constant.COVID)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIServiceVaksin api1 = retrofit.create(APIServiceVaksin.class);
        api1.RESPONSE_VAKSIN_INDONESIA().enqueue(new Callback<ResponseVaksinIndonesia>() {
            @Override
            public void onResponse(Call<ResponseVaksinIndonesia> call, Response<ResponseVaksinIndonesia> response) {
                if (response.code() == 200) {
                    NumberFormat formatter = new DecimalFormat("#,###");
                    String totalVaksin1 = formatter.format(response.body().getVaksinasi().getTotal().getJumlah_vaksinasi_1());
                    String penambahanVaksin1 = formatter.format(response.body().getVaksinasi().getPenambahan().getJumlah_vaksinasi_1());

                    String totalVaksin2 = formatter.format(response.body().getVaksinasi().getTotal().getJumlah_vaksinasi_1());
                    String penambahanVaksin2 = formatter.format(response.body().getVaksinasi().getPenambahan().getJumlah_vaksinasi_2());

                    tvTanggalV.setText("Data di dapat per tanggal\n" + response.body().getVaksinasi().getPenambahan().getTanggal());

                    tvPenambahanV1.setText("+ "+penambahanVaksin1);
                    tvPenambahanV2.setText("+ "+penambahanVaksin2);

                    tvTotalV1.setText(totalVaksin1);
                    tvTotalV2.setText(totalVaksin2);

                } else {
                    Intent intent = new Intent(MainActivity.this, Error.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ResponseVaksinIndonesia> call, Throwable t) {

            }
        });
    }

}