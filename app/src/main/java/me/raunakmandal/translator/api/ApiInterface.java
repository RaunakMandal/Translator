package me.raunakmandal.translator.api;

import java.util.Map;

import me.raunakmandal.translator.data.Translation;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("translate")
    Call<Translation> getTranslation(@Body Map<String, String> body);
}
