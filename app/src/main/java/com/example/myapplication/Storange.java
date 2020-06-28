package com.example.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.storage.FirebaseStorage;

public class Storange extends AppCompatActivity {
    FirebaseStorage storage = FirebaseStorage.getInstance();
    Button btnUp;
    ImageView imgHinh;
    int result = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storange);

        AnhXa();
        imgHinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, result);
            }
        });
    }

    public void AnhXa() {
        btnUp = findViewById(R.id.btnup);
        imgHinh = findViewById(R.id.anh);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imgHinh.setImageBitmap(bitmap);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


// cach xu ly
// 3 map
    // gmail -> device
    // facebook -> device
    // sdt -> device
// gans device --> ve chung 1 userID trong firebase
// tuong tu voi facebook va sdt
// thuc hien select userID va acvity
   //  userID -> activity
    // gan lai trong mysql


}
