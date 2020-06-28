package com.example.myapplication;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapter.RecycleViewAdapter;
import com.example.myapplication.model.Upload;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {
RecyclerView recyclerView;
RecycleViewAdapter adapter;
DatabaseReference mDatabase;
ProgressDialog progressDialog;
private List<Upload> uploads;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
         recyclerView=findViewById(R.id.recycleview_id);
         recyclerView.setLayoutManager(new GridLayoutManager(this,3));
         progressDialog=new ProgressDialog(this);
         uploads=new ArrayList<>();
         progressDialog.setMessage("please wait.....");
        progressDialog.show();
         mDatabase= FirebaseDatabase.getInstance().getReference("uploads");
         mDatabase.addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                 progressDialog.dismiss();
                 for (DataSnapshot postsnapshot:dataSnapshot.getChildren()){
                     Upload upload=postsnapshot.getValue(Upload.class);
                     uploads.add(upload);
                     uploads.size();
                 }
                 adapter=new RecycleViewAdapter(getApplicationContext(),uploads);
                 recyclerView.setAdapter(adapter);
                 adapter.notifyDataSetChanged();
             }

             @Override
             public void onCancelled(@NonNull DatabaseError databaseError) {
                   progressDialog.dismiss();
             }
         });

    }
}
