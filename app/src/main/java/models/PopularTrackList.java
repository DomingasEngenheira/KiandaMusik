package models;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.domingas.kiandamuzik.R;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by DOMINGAS on 21/12/2017.
 */

public class PopularTrackList {

    private int trackListId;
    private int artistId;
    private ArrayList<Track> popularTracks;

    public PopularTrackList(int trackListId, int artistId, ArrayList<Track> popularTracks) {
        this.trackListId = trackListId;
        this.artistId = artistId;
        this.popularTracks = popularTracks;
    }

    public int getTrackListId() {
        return trackListId;
    }

    public void setTrackListId(int trackListId) {
        this.trackListId = trackListId;
    }

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public ArrayList<Track> getPopularTracks() {
        return popularTracks;
    }

    public void setPopularTracks(ArrayList<Track> popularTracks) {
        this.popularTracks = popularTracks;
    }
}