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

import models.Artista;
import models.PopularTrackList;

/**
 * Created by DOMINGAS on 21/12/2017.
 */

    public class PopularTracksAdapter extends RecyclerView.Adapter<PopularTracksAdapter.ViewHolder> {

        private PopularTrackList mPopularList;
        private Context context;

        public PopularTracksAdapter (Context context, PopularTrackList mPopularList) {
            this.context = context;
            this.mPopularList = mPopularList;

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
            holder.ArtistName.setText(mPopularList.getPopularTracks().get(position).getArtist().getName());
            holder.TrackName.setText(mPopularList.getPopularTracks().get(position).getaName());
            holder.trackCover.setImageResource(mPopularList.getPopularTracks().get(position).getTrackCover());
            //Codigo para pegar o nome do artista
            Bundle musicData = new Bundle();
            musicData.putString(PopularTrackConstants.ARTIST_TITLE, mPopularList.getPopularTracks().get(position).getArtist().getName());
            //Titulo da musica
            musicData.putString(PopularTrackConstants.TRACK_TITLE, mPopularList.getPopularTracks().get(position).getaName());
            // capa da musica
            musicData.putInt(PopularTrackConstants.TRACK_COVER, mPopularList.getPopularTracks().get(position).getTrackCover());
            musicData.putString(PopularTrackConstants.ARTIST_DESCRIPTION, mPopularList.getPopularTracks().get(position).getArtist().getDescription());

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

                return mPopularList.getPopularTracks().size();
        }


    }
