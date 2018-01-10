package Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.example.domingas.kiandamuzik.R;
import com.example.domingas.kiandamuzik.custom_views.PlayPauseButton;

import static com.example.domingas.kiandamuzik.R.*;

/**
 * Created by DOMINGAS on 04/01/2018.
 */

public class NowPlayingCard extends Fragment {
    ImageView albumArt;
    TextView song_title;
    TextView artist_title;
    PlayPauseButton play_pause;
    ProgressBar song_progess;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(layout.activity_now_playing, container, false);

        albumArt = v.findViewById(R.id.contols_album_art);
        song_title = v.findViewById(id.controls_song_title);
        artist_title = v.findViewWithTag(R.id.controls_artist_title);
        play_pause = v.findViewById(R.id.controls_play_pause);
        song_progess = v.findViewById(R.id.controls_song_t);

        return v;

    }

}
