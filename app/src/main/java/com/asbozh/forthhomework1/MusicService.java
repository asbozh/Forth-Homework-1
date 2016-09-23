package com.asbozh.forthhomework1;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.os.PowerManager;
import android.util.Log;

import java.io.IOException;

public class MusicService extends Service implements MediaPlayer.OnErrorListener, MediaPlayer.OnPreparedListener {

    private MediaPlayer mMediaPlayer;

    private final IBinder mBinder = new LocalBinder();


    public class LocalBinder extends Binder {
        MusicService getService() {
            return MusicService.this;
        }
    }


    @Override
    public void onCreate() {
        super.onCreate();
        initMediaPlayer();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopForeground(true);
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

    private void initMediaPlayer() {
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMediaPlayer.setWakeMode(getApplicationContext(), PowerManager.PARTIAL_WAKE_LOCK);
        mMediaPlayer.setOnErrorListener(this);
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {

        mMediaPlayer.reset();
        return false;
    }

    public void playMusic(String song, String title) {
        AssetFileDescriptor afd = getApplication().getResources().openRawResourceFd(this.getResources().getIdentifier(song, "raw", this.getPackageName()));
        try {
            mMediaPlayer.reset();
            mMediaPlayer.setLooping(true);
            mMediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            mMediaPlayer.setOnPreparedListener(this);
            mMediaPlayer.prepareAsync();

        } catch (IOException e) {
            e.printStackTrace();
        }

        PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0,
                new Intent(getApplicationContext(), MainActivity.class),
                PendingIntent.FLAG_UPDATE_CURRENT);
        Notification noti = new Notification.Builder(getApplicationContext())
                .setContentTitle("Spotify")
                .setContentText("Playing: " + title)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setOngoing(true)
                .setContentIntent(pi)
                .build();
        startForeground(1, noti);
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();
    }

    public void pauseMusic() {
        if (mMediaPlayer.isPlaying()) {
            mMediaPlayer.pause();
        }
    }

    public void startMusic() {
        if (!mMediaPlayer.isPlaying()) {
            mMediaPlayer.start();
        }
    }

    public void reverseSong() {
        mMediaPlayer.seekTo(0);
    }

    public void fastForwardSong() {
        int length = mMediaPlayer.getDuration();
        int currentPosition = mMediaPlayer.getCurrentPosition();

        if (length != -1) {
            mMediaPlayer.seekTo(currentPosition + 500);
        }
    }
}
