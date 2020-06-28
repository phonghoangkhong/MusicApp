package com.example.myapplication.model;

public class UploadSong {
    public String songscategory, songtitle,artist,album_art,songDuration,songLink,mKey;

    public UploadSong(String songscategory, String songtitle, String artist, String album_art, String songDuration, String songLink) {
      if(songtitle.trim().equals("")){
          songtitle="No title";
      }
        this.songscategory = songscategory;
        this.songtitle = songtitle;
        this.artist = artist;
        this.album_art = album_art;
        this.songDuration = songDuration;
        this.songLink = songLink;

    }

    public UploadSong() {
    }

    public String getSongscategory() {
        return songscategory;
    }

    public void setSongscategory(String songscategory) {
        this.songscategory = songscategory;
    }

    public String getSongtitle() {
        return songtitle;
    }

    public void setSongtitle(String songtitle) {
        this.songtitle = songtitle;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum_art() {
        return album_art;
    }

    public void setAlbum_art(String album_art) {
        this.album_art = album_art;
    }

    public String getSongDuration() {
        return songDuration;
    }

    public void setSongDuration(String songDuration) {
        this.songDuration = songDuration;
    }

    public String getSongLink() {
        return songLink;
    }

    public void setSongLink(String songLink) {
        this.songLink = songLink;
    }

    public String getmKey() {
        return mKey;
    }

    public void setmKey(String mKey) {
        this.mKey = mKey;
    }
}
