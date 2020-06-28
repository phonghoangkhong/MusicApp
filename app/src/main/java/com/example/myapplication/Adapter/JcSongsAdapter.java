package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.UploadSong;
import com.example.myapplication.model.Utility;

import java.util.List;

public class JcSongsAdapter extends RecyclerView.Adapter<JcSongsAdapter.SongsAdapterViewHolder> {
    private int selectPosition;
    Context context;
    List<UploadSong> arrayListSongs;
    private RecyclerItemClickListener listener;

    public JcSongsAdapter(Context context, List<UploadSong> arrayListSongs, RecyclerItemClickListener listener) {
        this.context = context;
        this.arrayListSongs = arrayListSongs;
        this.listener = listener;

    }

    @NonNull
    @Override
    public JcSongsAdapter.SongsAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(context).inflate(R.layout.songs_row,parent,false);
       return new  SongsAdapterViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull JcSongsAdapter.SongsAdapterViewHolder holder, int position) {

        UploadSong uploadSong=arrayListSongs.get(position);
        if(uploadSong!=null){
            if(selectPosition==position){
                holder.itemView.setBackgroundColor(ContextCompat.getColor(context,R.color.colorPrimary));
                holder.tv_play_active.setVisibility(View.VISIBLE);

            }else{
                holder.itemView.setBackgroundColor(ContextCompat.getColor(context,R.color.mp3));
                holder.tv_play_active.setVisibility(View.VISIBLE);

            }
        }
        holder.tv_title.setText(uploadSong.getSongtitle());
        holder.tv_artist.setText(uploadSong.getArtist());
           String duration= Utility.convertDuration(Long.parseLong(uploadSong.getSongDuration()));
           holder.tv_duration.setText(duration);
           holder.bind(uploadSong,listener);
    }

    @Override
    public int getItemCount() {
        return arrayListSongs.size();
    }

    public class SongsAdapterViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_title, tv_artist, tv_duration;
        ImageView tv_play_active;

        public SongsAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_artist = itemView.findViewById(R.id.tv_artist);
            tv_duration = itemView.findViewById(R.id.tv_duration);
            tv_play_active = itemView.findViewById(R.id.tv_play_active);

        }

        public void bind(final UploadSong uploadSong, final RecyclerItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClickListener(uploadSong,getAdapterPosition());
                }
            });
        }
    }

    public interface RecyclerItemClickListener {
               void onClickListener(UploadSong uploadSong,int position);
    }
    public int getSelectPosition(){
        return selectPosition;
    }
    public void setSelectPosition(int selectPosition){
        this.selectPosition=selectPosition;
    }
}
