package com.example.domingas.kiandamuzik;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import Fragments.NowPlayingCard;

public class Base2Activity extends AppCompatActivity {
    public class initNowPlayingControls extends AsyncTask<Bundle, Void, String> {

        @Override
        protected String doInBackground(Bundle... bundles) {
            NowPlayingCard nowplaying = new NowPlayingCard();
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.quick_controls_container, nowplaying).commitAllowingStateLoss();
            return "Executed";
        }
    }
}
