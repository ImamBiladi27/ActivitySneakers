package com.example.activitysneakers;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CreditActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edNama, edInstitusi;
    Button btnPindahTanpaData, btnPindahData;

    public static final String EXTRA_NAMA = "EXTRA_NAMA";
    public static final String EXTRA_Institusi = "EXTRA_Institusi";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit);

        edNama = findViewById(R.id.ed_nama);
        edInstitusi = findViewById(R.id.ed_institusi);

        btnPindahData = findViewById(R.id.btn_pindah_data);
        btnPindahTanpaData = findViewById(R.id.btn_pindah_tanpa_data);

        btnPindahTanpaData.setOnClickListener(this);
        btnPindahData.setOnClickListener(this);


        //setContentView(snakeAnimView);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_pindah_data:
                Log.d("test","test");
                //pindaActivityData();
                break;
            case R.id.btn_pindah_tanpa_data:
//                pindahActivityTanpaData();
                break;
            default:
                break;
        }
    }
}
