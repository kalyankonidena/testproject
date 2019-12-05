package com.urban.dictionary.network;

import com.urban.dictionary.model.UrbanDictionaryModel;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface UrbanDisctionalryListService {

    @Headers({
            "x-rapidapi-host: mashape-community-urban-dictionary.p.rapidapi.com",
            "x-rapidapi-key: 5562e53b7cmshefa13cba0068776p19dbd7jsn43807cbe41fb"
    })
    @GET("define")
    Observable<UrbanDictionaryModel> getDictionaryResults(@Query("term") String term);

}
