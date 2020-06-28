package com.example.myapplication;

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

public class DangKy extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText user,matKhau;
    Button btnDK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
         btnDK=findViewById(R.id.dangky);
        user=findViewById(R.id.username);
         matKhau=findViewById(R.id.password);
        mAuth = FirebaseAuth.getInstance();
        btnDK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DangKy();
            }
        });
    }

    private void DangKy(){
        String email=user.getText().toString();
        String password=matKhau.getText().toString();
        mAuth.createUserWithEmailAndPassword( email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                       if(task.isSuccessful()){

                           Toast.makeText(DangKy.this,"Dang ky thanh cong",Toast.LENGTH_LONG).show();

                       }else{
//                           Exception e=task.getException();
//                           e.printStackTrace();
                           Toast.makeText(DangKy.this,"Dang ky that bai",Toast.LENGTH_LONG).show();
                       }

                        // ...
                    }
                });

    }
}
