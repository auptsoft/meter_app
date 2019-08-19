package com.app.auptsoft.meterutililty.services;

import com.app.auptsoft.meterutililty.services.parts.FormInterface;
import com.app.auptsoft.meterutililty.services.parts.MediaInterface;
import com.app.auptsoft.meterutililty.services.parts.SearchInterface;

import com.app.auptsoft.meterutililty.services.response.GeneralResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Andrew on 6.3.19.
 */

public class RESTUtil {
    public static class SearchQueryItem {
        private String column;
        private String operator;
        private String value;

        public SearchQueryItem(String column, String operator, String value) {
            this.column = column;
            this.operator = operator;
            this.value = value;
        }

        public String getColumn() {
            return column;
        }

        public void setColumn(String column) {
            this.column = column;
        }

        public String getOperator() {
            return operator;
        }

        public void setOperator(String operator) {
            this.operator = operator;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }


    public enum Method{GET, POST, PUT, DELETE};
    private static String accessKey;

    static {
        accessKey = "";
    }
    public static String getAccessKey() {
        return accessKey;
    }

    public static void setAccessKey(String accessKey) {
        RESTUtil.accessKey = accessKey;
    }

    public interface RestResponse<T> {
        void onResponse(Call<String> call, Response<String> response, T data);
        void onFailure(Call<String> call, Throwable throwable);
    }

    //@SuppressWarnings("unchecked")
    public static <T, E> void send(String baseUrl, String path, Method method, T data, Callback<ResponseBody> responseCallback) {
        Gson gson = new GsonBuilder().create();
        String jsonString = gson.toJson(data);
        MultipartBody.Part dataPart = MultipartBody.Part.createFormData("data", jsonString);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        FormInterface formInterface = retrofit.create(FormInterface.class);
        Call<ResponseBody> generalResponseCall;
        switch (method) {
            case GET:
                generalResponseCall = formInterface.get(path, getAccessKey());
                break;

            case POST:
                generalResponseCall = formInterface.post(path, dataPart, getAccessKey());
                break;

            case PUT:
                generalResponseCall = formInterface.put(path, dataPart, getAccessKey());
                break;

            case DELETE:
                generalResponseCall = formInterface.delete(path, getAccessKey());
                break;

            default:
                generalResponseCall = formInterface.get(path, getAccessKey());
        }
        generalResponseCall.enqueue(responseCallback);
    }

    public static <T, E> void sendString(String baseUrl, String path, Method method, String data, Callback<ResponseBody> responseCallback) {
        Gson gson = new GsonBuilder().create();
        //String jsonString = gson.toJson(data);
        MultipartBody.Part dataPart = MultipartBody.Part.createFormData("data", data);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        FormInterface formInterface = retrofit.create(FormInterface.class);
        Call<ResponseBody> generalResponseCall;
        switch (method) {
            case GET:
                generalResponseCall = formInterface.get(path, getAccessKey());
                break;

            case POST:
                generalResponseCall = formInterface.post(path, dataPart, getAccessKey());
                break;

            case PUT:
                generalResponseCall = formInterface.put(path, dataPart, getAccessKey());
                break;

            case DELETE:
                generalResponseCall = formInterface.delete(path, getAccessKey());
                break;

            default:
                generalResponseCall = formInterface.get(path, getAccessKey());
        }
        generalResponseCall.enqueue(responseCallback);
    }

    public static <T, E> void get(String baseUrl, String path, Callback<ResponseBody> responseCallback) {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        FormInterface formInterface = retrofit.create(FormInterface.class);
        Call<ResponseBody> generalResponseCall;
        generalResponseCall = formInterface.get(path, getAccessKey());
        generalResponseCall.enqueue(responseCallback);
    }


    public static void search(String baseUrl, String path, List<SearchQueryItem> data, Callback<ResponseBody> responseCallback){
        Gson gson = new GsonBuilder().create();
        String jsonString = gson.toJson(data);
        MultipartBody.Part dataPart = MultipartBody.Part.createFormData("data", jsonString);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        SearchInterface searchInterface = retrofit.create(SearchInterface.class);
        Call<ResponseBody> generalResponseCall = searchInterface.post(path, dataPart, getAccessKey());
        generalResponseCall.enqueue(responseCallback);
    }
}