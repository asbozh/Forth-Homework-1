package com.asbozh.forthhomework1;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<Song> mSongList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private RVAdapter mAdapter;
    private Button mButtonPlayPause, mButtonReverse, mButtonFastForward;
    private TextView mTextViewTitle;
    private RelativeLayout mRelativeLayoutMusicControl;
    private MusicService mService;
    boolean mBound = false;
    boolean isPlaying = false;

    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            MusicService.LocalBinder binder = (MusicService.LocalBinder) service;
            mService = binder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.rvList);
        setViews();

        mAdapter = new RVAdapter(mSongList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new RVDecorator(MainActivity.this));
        mRecyclerView.addOnItemTouchListener(new RecyclerClickListener(this, mRecyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Song song = mSongList.get(position);
                mRelativeLayoutMusicControl.setVisibility(View.VISIBLE);
                mTextViewTitle.setText(song.getmTitle());
                mService.playMusic(song.getmSongName(), song.getmTitle());
                isPlaying = true;
                mButtonPlayPause.setText("Pause");
            }
        }));

        mRecyclerView.setAdapter(mAdapter);

        addSongs();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!mBound) {
            Intent intent = new Intent(this, MusicService.class);
            bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onStop();
        if (mBound) {
            unbindService(mConnection);
            mBound = false;
        }
    }

    private void setViews() {
        mRelativeLayoutMusicControl = (RelativeLayout) findViewById(R.id.rlMusicControl);
        mTextViewTitle = (TextView) findViewById(R.id.tvMusicTitle);
        mButtonPlayPause = (Button) findViewById(R.id.btnPlayPause);
        mButtonReverse = (Button) findViewById(R.id.btnReverse);
        mButtonFastForward = (Button) findViewById(R.id.btnFastForward);
        mButtonPlayPause.setOnClickListener(this);
        mButtonReverse.setOnClickListener(this);
        mButtonFastForward.setOnClickListener(this);
    }

    private void addSongs() {
        Song newSong;

        newSong = new Song("Let Me Love You", "DJ Snake, Justin Bieber", "cold");
        mSongList.add(newSong);

        newSong = new Song("All I Ever Wanted", "Michael Brun, Louie", "easy");
        mSongList.add(newSong);

        newSong = new Song("Kiss It Better - R3hab Remix", "Rihanna - Kiss It Better (Dance Remix)", "flashback");
        mSongList.add(newSong);

        newSong = new Song("Treat You Better - Ashworth Remix", "Shawn Mendes, Treat You Better (Ashworth Remix)", "froyo");
        mSongList.add(newSong);

        newSong = new Song("CAN'T STOP THE FEELING! (ORIGINAL VERSION)", "Justin Timberlake", "happy_days");
        mSongList.add(newSong);

        newSong = new Song("Broken", "Tritonal, Jenaux, Adam Lambert", "lonely_but_okay");
        mSongList.add(newSong);

        newSong = new Song("Can't Fight It", "Quintino, Cheat Codes - Can't Fight It", "low_dosage_flow_potent");
        mSongList.add(newSong);

        newSong = new Song("Colors - Audien Remix", "Halsey - Complementary Colors", "sugar_villain2");
        mSongList.add(newSong);

        newSong = new Song("Off The Ground", "Bit Funk, Shae Jacobs - Off The Ground", "taste_makerz_2");
        mSongList.add(newSong);

        newSong = new Song("Fool's Gold", "Aaron Carter - Fool's Gold", "ydig");
        mSongList.add(newSong);

        newSong = new Song("Let Me Love You", "DJ Snake, Justin Bieber", "cold");
        mSongList.add(newSong);

        newSong = new Song("All I Ever Wanted", "Michael Brun, Louie", "easy");
        mSongList.add(newSong);

        newSong = new Song("Kiss It Better - R3hab Remix", "Rihanna - Kiss It Better (Dance Remix)", "flashback");
        mSongList.add(newSong);

        newSong = new Song("Treat You Better - Ashworth Remix", "Shawn Mendes, Treat You Better (Ashworth Remix)", "froyo");
        mSongList.add(newSong);

        newSong = new Song("CAN'T STOP THE FEELING! (ORIGINAL VERSION)", "Justin Timberlake", "happy_days");
        mSongList.add(newSong);

        newSong = new Song("Broken", "Tritonal, Jenaux, Adam Lambert", "lonely_but_okay");
        mSongList.add(newSong);

        newSong = new Song("Can't Fight It", "Quintino, Cheat Codes - Can't Fight It", "low_dosage_flow_potent");
        mSongList.add(newSong);

        newSong = new Song("Colors - Audien Remix", "Halsey - Complementary Colors", "sugar_villain2");
        mSongList.add(newSong);

        newSong = new Song("Off The Ground", "Bit Funk, Shae Jacobs - Off The Ground", "taste_makerz_2");
        mSongList.add(newSong);

        newSong = new Song("Fool's Gold", "Aaron Carter - Fool's Gold", "ydig");
        mSongList.add(newSong);

        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnPlayPause:
                    if (isPlaying) {
                        mService.pauseMusic();
                        isPlaying = false;
                        mButtonPlayPause.setText("Play");
                    } else {
                        mService.startMusic();
                        isPlaying = true;
                        mButtonPlayPause.setText("Pause");
                    }
                break;
            case R.id.btnReverse:
                    mService.reverseSong();
                break;
            case R.id.btnFastForward:
                    mService.fastForwardSong();
                break;
        }
    }

    public interface ClickListener {
        void onClick(View view, int position);
    }
}
