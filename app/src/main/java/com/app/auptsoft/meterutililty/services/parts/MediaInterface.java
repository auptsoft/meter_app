package com.app.auptsoft.meterutililty.services.parts;

import com.app.auptsoft.meterutililty.services.response.GeneralResponse;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;


/**
 * Created by Andrew on 1.3.19.
 */

public interface MediaInterface {
    @Headers({"Accept:application/json"})
    @Multipart
    @POST("{urlPath}")
    Call<GeneralResponse<String>> uploadVideoToServer(@Path(value = "urlPath", encoded = true) String urlPath, @Part MultipartBody.Part file, @Part MultipartBody.Part data, @Header("Authorization") String access_token);
}
