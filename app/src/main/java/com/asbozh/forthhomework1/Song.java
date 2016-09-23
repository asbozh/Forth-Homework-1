package com.asbozh.forthhomework1;


public class Song {

    private String mTitle, mArtist, mSongName;

    public Song(String mTitle, String mArtist, String mSongName) {
        this.mTitle = mTitle;
        this.mArtist = mArtist;
        this.mSongName = mSongName;
    }

    public String getmSongName() {
        return mSongName;
    }

    public void setmSongName(String mSongName) {
        this.mSongName = mSongName;
    }

    public String getmArtist() {
        return mArtist;
    }

    public void setmArtist(String mArtist) {
        this.mArtist = mArtist;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }
}
