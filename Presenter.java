package ru.bumchik.lesson6;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.bumchik.lesson6.db.RoomModel;
import ru.bumchik.lesson6.model.UserResponse;
import ru.bumchik.lesson6.network.Site;

import static ru.bumchik.lesson6.App.appDatabase;

public class Presenter {

    private List<UserResponse> userList = new ArrayList<>();
    private List<RoomModel> userRoom = new ArrayList<>();

    public Presenter () {
    }

    public void loadUsers() {
        Observable<UserResponse> observable = new Observable<UserResponse>() {
            @Override
            protected void subscribeActual(final Observer<? super UserResponse> observer) {
                Site.getSingleton().getAPI().loadUsers().enqueue(new Callback<List<UserResponse>>() {
                    @Override
                    public void onResponse(Call<List<UserResponse>> call, Response<List<UserResponse>> response) {
                        if (response.body() != null && response.isSuccessful()) {
                            for (int i = 0; i < response.body().size(); i++) {
                                UserResponse userModel = response.body().get(i);
                                RoomModel model = new RoomModel();
                                model.setLogin(userModel.getLogin());
                                model.setUserId(userModel.getId());
                                userRoom.add(model);
                                System.out.println("User: " + userModel.getLogin());
                            }
                            saveUsers();
                        }
                    }
                    @Override
                    public void onFailure(Call<List<UserResponse>> call, Throwable t) {
                        System.out.println("Failure: " + t.getMessage());
                    }
                });
            }
        };
        observable.subscribe();
    }

    public void saveUsers() {
        Observable<UserResponse> observable = new Observable<UserResponse>() {
            @Override
            protected void subscribeActual(final Observer<? super UserResponse> observer) {
                appDatabase.roomModelDAO().deleteAll();
                appDatabase.roomModelDAO().insertAll(userRoom);
            }
        };
        observable.subscribe();
    }
}
