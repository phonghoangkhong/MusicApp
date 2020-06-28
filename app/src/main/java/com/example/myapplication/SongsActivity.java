package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jean.jcplayer.model.JcAudio;
import com.example.jean.jcplayer.view.JcPlayerView;
import com.example.myapplication.Adapter.JcSongsAdapter;
import com.example.myapplication.model.UploadSong;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SongsActivity extends AppCompatActivity {
  RecyclerView recyclerView;
  ProgressBar progressBar;
  Boolean checkin=false;
  List<UploadSong> mupload;
  JcSongsAdapter adapter;
  DatabaseReference databaseReference;
  ValueEventListener valueEventListener;
  JcPlayerView jcPlayerView;
  ArrayList<JcAudio> jcAudios=new ArrayList<>();
  private int currentIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songs_activity);
        recyclerView=findViewById(R.id.recycleview_id);
        progressBar=findViewById(R.id.processbarshowsong);
        jcPlayerView=findViewById(R.id.jcplayer);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mupload=new ArrayList<>();
        recyclerView.setAdapter(adapter);
        adapter=new JcSongsAdapter(getApplicationContext(), mupload, new JcSongsAdapter.RecyclerItemClickListener() {
            @Override
            public void onClickListener(UploadSong uploadSong, int position) {
                changeSelectedSong(position);
              jcPlayerView.playAudio(jcAudios.get(position));
               jcPlayerView.setVisibility(View.VISIBLE);
               jcPlayerView.createNotification();
            }

        });
       databaseReference= FirebaseDatabase.getInstance().getReference("songs");
       valueEventListener=databaseReference.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               mupload.clear();
               for(DataSnapshot dss:dataSnapshot.getChildren()){
                   UploadSong uploadSong=dss.getValue(UploadSong.class);
                   uploadSong.setmKey(dss.getKey());
                   currentIndex=0;
                   final String s=getIntent().getExtras().getString("SongsCategory");
                   if(s.equals(uploadSong.getSongscategory())){
                       mupload.add(uploadSong);
                       checkin=true;
                       jcAudios.add(JcAudio.createFromURL(uploadSong.getSongtitle(),uploadSong.getSongLink()));

                   }
               }
               adapter.setSelectPosition(0);
               recyclerView.setAdapter(adapter);
               adapter.notifyDataSetChanged();
               progressBar.setVisibility(View.GONE);
               if(checkin){
                   jcPlayerView.initPlaylist(jcAudios,null);

               }else{
                   Toast.makeText(SongsActivity.this,"there is songs!",Toast.LENGTH_LONG).show();
               }
           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {
                  progressBar.setVisibility(View.GONE);
           }
       });
    }
    public void changeSelectedSong(int index){
        adapter.notifyItemChanged(adapter.getSelectPosition());
        currentIndex=index;
        adapter.setSelectPosition(currentIndex);
        adapter.notifyItemChanged(currentIndex);
    }
}
