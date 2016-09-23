package com.asbozh.forthhomework1;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.MyViewHolder> {

    private List<Song> songsList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, artist;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.tvTitle);
            artist = (TextView) view.findViewById(R.id.tvArtist);
        }
    }


    public RVAdapter(List<Song> songsList) {
        this.songsList = songsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.song_list_row, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Song song = songsList.get(position);
        holder.title.setText(song.getmTitle());
        holder.artist.setText(song.getmArtist());
    }

    @Override
    public int getItemCount() {
        return songsList.size();
    }
}
