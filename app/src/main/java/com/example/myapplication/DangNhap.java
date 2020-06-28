package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class DangNhap extends AppCompatActivity {
    private FirebaseAuth mAuth;
// ...
// Initialize Firebase Auth
   EditText user,matKhau;
   Button btn;
   String email;
   String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        mAuth = FirebaseAuth.getInstance();
            user=findViewById(R.id.user);
            matKhau=findViewById(R.id.password);
     btn=findViewById(R.id.dangnhap);
     btn.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             DangNhap();
         }
     });
    }
    private void DangNhap() {
      email=user.getText().toString();
      password=matKhau.getText().toString();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                           if(email.equals("admin@gmail.com")){
                               Intent intent=new Intent();
                               intent.setClass(getApplicationContext(),MainActivity.class);
                               startActivity(intent);
                           }else{
                               Intent intent=new Intent();
                               intent.setClass(getApplicationContext(),Main2Activity.class);
                               startActivity(intent);
                           }
                        } else {
                            // If sign in fails, display a message to the user.
                          Toast.makeText(DangNhap.this,"Dang nhap that bai",Toast.LENGTH_LONG).show();
                            // ...

                        }

                        // ...
                    }
                });
    }


}
