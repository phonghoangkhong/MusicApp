package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class TrangChu extends AppCompatActivity {
 Button btndk,btndn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);
        btndk=findViewById(R.id.btndk);
        btndn=findViewById(R.id.btnDN);
         btndk.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent=new Intent(TrangChu.this,DangKy.class);
                 startActivity(intent);
             }
         });
        btndn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TrangChu.this,DangNhap.class);
                startActivity(intent);
            }
        });
    }
}
