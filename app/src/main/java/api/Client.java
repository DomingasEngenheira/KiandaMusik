package api;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import api.Models.PopularTracks;
import retrofit2.http.GET;

/**
 * Created by DOMINGAS on 25/01/2018.
 */

public class Client {
    public interface PopularTracks {
        //Endpoint
        @GET("popular")
        retrofit2.Call<ArrayList<api.Models.PopularTracks>> getTracks();


    }
}
