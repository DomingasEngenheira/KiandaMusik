package com.example.domingas.kiandamuzik;

import android.content.Context;
import android.media.AudioManager;
import android.media.Image;
import android.media.MediaPlayer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.domingas.kiandamuzik.custom_views.PlayPauseButton;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;

import api.Models.PopularTracks;

public class NowPlayingActivity extends android.support.v4.app.Fragment implements MediaPlayer.OnPreparedListener{
    ImageView albumArt;
    ProgressBar progressBar;
    TextView mSongTitle;
    TextView mArtistTitle;
    PlayPauseButton play;
    Bundle songInfo;
    MediaPlayer mediaPlayer;
    Boolean tocando = false;
    ArrayList<PopularTracks> tracks;

    Context context;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_now_playing, container, false);

        albumArt = v.findViewById(R.id.capa);
        progressBar = v.findViewById(R.id.progressBar2);
        play = v.findViewById(R.id.controls_play_pause);
        mSongTitle = v.findViewById(R.id.controls_song_title);
        mArtistTitle = v.findViewById(R.id.principleTrackTitle);

        mSongTitle.setText(tracks.get(0).getTrackTitle());
        mArtistTitle.setText(tracks.get(0).getArtist().get(0).getName());
        Picasso.with(this.getContext()).load(tracks.get(0).getTrackCoverArt()).resize(512, 512).into(albumArt);

        mediaPlayer = new MediaPlayer();

        try {
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setDataSource(tracks.get(0).getTrackUrl());
            mediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return v;


    }


    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        mediaPlayer.start();
    }
}

