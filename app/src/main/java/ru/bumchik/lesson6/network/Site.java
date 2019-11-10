package ru.bumchik.lesson6.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Site {
    private static Site singleton = null;
    private RestAPI restAPI;

    private Site() {
        restAPI = createAdapter();
    }


    public static Site getSingleton() {
        if(singleton == null) {
            singleton = new Site();
        }

        return singleton;
    }

    public RestAPI getAPI() {
        return restAPI;
    }

    private RestAPI createAdapter() {
        Retrofit adapter = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return adapter.create(RestAPI.class);
    }
}