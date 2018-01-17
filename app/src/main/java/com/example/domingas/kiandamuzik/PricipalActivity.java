package com.example.domingas.kiandamuzik;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import adapters.ArtistTrackAdapter;
import adapters.PopularTracksAdapter;
import models.Album;
import models.ArtistTrackList;
import models.Artista;
import models.PopularTrackList;
import models.Track;

public class PricipalActivity extends Base2Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pricipal);

        RecyclerView recyclerView = findViewById(R.id.artistList);
        Artista artist = new Artista();
        artist.setArtistCover(R.drawable.big_shaq_track);
        artist.setId(0);
        artist.setDescription("");
        artist.setMusicStyle("RNB");
        artist.setName("Big Shag");

        Album bigOne = new Album(0,"Big One",artist.getId(),"20-11-16","500");
        Track track = new Track();
        track.setAlbum(bigOne);
        track.setArtist(artist);
        track.setTrackCover(R.drawable.big_shaq_track);
        //track.setTrackId(0);

        /*Album bigOne1 = new Album(1,"Big One",artist.getId(),"20-11-16","500");
        Track track1 = new Track();
        track1.setAlbum(bigOne);
        track1.setArtist(artist);
        track1.setTrackCover(R.drawable.big_shaq_track);
        //track.setTrackId(0);

        Album bigOne3 = new Album(2,"Big One",artist.getId(),"20-11-16","500");
        Track track3= new Track();
        track3.setAlbum(bigOne);
        track3.setArtist(artist);
        track3.setTrackCover(R.drawable.big_shaq_track);
        //track.setTrackId(0);
*/
        ArrayList<Track> tracks = new ArrayList<>();
        tracks.add(track);


        PopularTrackList popularTrackList = new PopularTrackList(0, artist.getId(), tracks);
        popularTrackList.setPopularTracks(tracks);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        PopularTracksAdapter tracksAdapter = new PopularTracksAdapter(this, popularTrackList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(tracksAdapter);
        new initNowPlayingControls().execute("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_acerca){
            startActivity(new Intent(this, AboutActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }   }
