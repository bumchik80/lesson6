package ru.bumchik.lesson6.network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import ru.bumchik.lesson6.model.UserResponse;

public interface RestAPI {
    @GET("users")
    Call<List<UserResponse>> loadUsers();

    @GET("users/{user}")
    Call<List<UserResponse>> loadUsers(@Path("user") String user);
}
