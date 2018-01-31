package com.example.domingas.kiandamuzik;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import adapters.PopularTrackConstants;
import adapters.PopularTracksAdapter;
import api.Client;
import api.Models.PopularTracks;
import api.Services;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PricipalActivity extends Base2Activity {

      private static final String TAG = "PricipalActivity";

        RecyclerView listaDeMusicaPopular;
        ArrayList<PopularTracks> popularTracksList = new ArrayList<>();
        LinearLayoutManager linearLayoutManager;
        boolean popularLoaded = true;

        Bundle songInfo;
        ProgressBar mStatusProgress;
        NestedScrollView nestedScrollView;
        ImageView popularCover;

        private TextView mPopularArtistNameTextView, mPopularTrackTextView;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_pricipal);
            listaDeMusicaPopular = findViewById(R.id.listasDeMusicas);
            linearLayoutManager = new LinearLayoutManager(this);
            getPopularTracks();

            mStatusProgress = findViewById(R.id.progressBar2);
            nestedScrollView = findViewById(R.id.scroll);
            popularCover = findViewById(R.id.capa);
            mPopularArtistNameTextView = findViewById(R.id.artistName);
            mPopularTrackTextView = findViewById(R.id.principleTrackTitle);

            // popularCover.setText(listMusic.get(0).getArtist().get(0).getName());


            updateViews(false);

            // new initNowPlayControls().execute(popularTracksList);
        }

        private void InitializeRecycleView() {

            listaDeMusicaPopular.setLayoutManager(linearLayoutManager);
            PopularTracksAdapter adapter = new PopularTracksAdapter(this, popularTracksList);
            listaDeMusicaPopular.setAdapter(adapter);

            Picasso.with(PricipalActivity.this)
                    .load(popularTracksList.get(0).getTrackCoverArt()).resize(512,512).into(popularCover);

            mPopularTrackTextView.setText(popularTracksList.get(0).getArtist().get(0).getName());
            mPopularTrackTextView.setText(popularTracksList.get(0).getTrackTitle());
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.menu_principal, menu);
            return super.onCreateOptionsMenu(menu);
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            if (item.getItemId() == R.id.action_acerca){
                startActivity(new Intent(this, AboutActivity.class));
            }
            return super.onOptionsItemSelected(item);
        }

        private void updateViews(boolean tracksLoaded)
        {
            if (!tracksLoaded)
            {
                mStatusProgress.setVisibility(View.VISIBLE);
                nestedScrollView.setVisibility(View.GONE);
            }
            else
            {
                nestedScrollView.setVisibility(View.VISIBLE);
                mStatusProgress.setVisibility(View.GONE);
            }
        }

        public void getPopularTracks(){
            final Client.PopularTracks popularTracks = Services.createService(Client.PopularTracks.class);
            popularTracks.getTracks().enqueue(new Callback<ArrayList<PopularTracks>>() {
                @Override
                public void onResponse(Call<ArrayList<PopularTracks>> call, Response<ArrayList<PopularTracks>> response) {
                    if (response.code() == 200)
                    {
                        popularTracksList = response.body();
                        popularLoaded = true;

                        updateViews(popularLoaded);
                        Log.d(TAG, "onResponse: " +response.body().size());
                        InitializeRecycleView();
                        songInfo = new Bundle();
                        songInfo.putParcelableArrayList("popular", popularTracksList);
                        new initNowPlayControls().doInBackground(songInfo);
                    }else{
                        Log.e(TAG, "onResponse: code=> " +response.code() );

                    }
                }

                @Override
                public void onFailure(Call<ArrayList<PopularTracks>> call, Throwable t) {
                    Log.e(TAG, "onFailure: Ocorreu um erro!");
                }
            });
        }
    }