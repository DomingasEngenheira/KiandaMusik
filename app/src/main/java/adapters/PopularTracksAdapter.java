package adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.domingas.kiandamuzik.R;
import com.example.domingas.kiandamuzik.TelaArtistaDetalhada;
import com.squareup.picasso.Picasso;

import java.util.List;

import api.Client;
import api.Models.PopularTracks;
import models.Artista;
import models.PopularTrackList;
import models.Track;

/**
 * Created by DOMINGAS on 21/12/2017.
 */

    public class PopularTracksAdapter extends RecyclerView.Adapter<PopularTracksAdapter.ViewHolder> {

        private List<PopularTracks> mTrack;
        private Context context;

        public PopularTracksAdapter (Context context, List<PopularTracks> Track) {
            this.context = context;
            this.mTrack = Track;

        }


        public static class ViewHolder extends RecyclerView.ViewHolder{
            public ImageView trackCover;
            public TextView ArtistName;
            public TextView TrackName;

            public ImageView ArtistVerifiedBadge;
            //public ImageView mTrackDetails;
            public ViewHolder(View itemView){
                super(itemView);
                //Criamos os itens do nosso list_item layout
                trackCover = itemView.findViewById(R.id.trackCover);
                //mTrackDetails = itemView.findViewById(R.id.trackDetails);
                TrackName = itemView.findViewById(R.id.trackName);
                ArtistVerifiedBadge = itemView.findViewById(R.id.verifiedAccount);
                ArtistName = itemView.findViewById(R.id.artistName);

            }


        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // instanciado outra classe neste caso list_item
            View view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
            PopularTracksAdapter.ViewHolder holder = new PopularTracksAdapter.ViewHolder(view);
            return holder;
        }



        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            holder.ArtistName.setText(mTrack.get(position).getArtist().get(0).getName());
           holder.TrackName.setText(mTrack.get(position).getTrackTitle());
           Picasso.with(context).load(mTrack.get(position).getTrackCoverArt()).resize(512, 512).into(holder.trackCover);
          // holder.trackCover.setImageResource(mTrack.getArtist().get(position).getTrackCover());



            //holder.trackCover.setImageResource(mTrack.getArtist().get(position).getTrackCover());

            //Codigo para pegar o nome do artista
            Bundle musicData = new Bundle();
            musicData.putString(PopularTrackConstants.ARTIST_TITLE, mTrack.get(position).getArtist().get(0).getName());
            //Titulo da musica
            musicData.putString(PopularTrackConstants.TRACK_TITLE, mTrack.get(position).getTrackTitle());
            // capa da musica

            musicData.putString(PopularTrackConstants.TRACK_COVER, mTrack.get(position).getTrackCoverArt());
            musicData.putString(PopularTrackConstants.ARTIST_DESCRIPTION, mTrack.get(position).getArtist().get(0).getDescription());

            final Intent intent = new Intent(context, TelaArtistaDetalhada.class);
            intent.putExtras(musicData);
           // holder.itemView.setOnClickListener(new View(View.OnClickListener()){
             //   @Override

            holder.itemView.setOnClickListener(new View.OnClickListener(){
                                                   @Override
                                                   public void onClick(View view) {
                                                       context.startActivity(intent);
                                                   }


            });

        }

        /*if(mPopularList.getPopularTracks().get(position).getArtist().isVerified()){
            holder.ArtistVerifiedBadge.setImageResource(R.drawable.ic_verified_user_black_18dp);
        }else {
            //Não apresentamos o bagde pois o artista não foi verificado
        }*/


        @Override
        public int getItemCount() {//serve para conter a lista de itens. é implementado pelo lyoutManeger

                return mTrack.size();
        }


    }
