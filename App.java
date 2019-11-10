package ru.bumchik.lesson6;

import android.app.Application;

import ru.bumchik.lesson6.db.AppDatabase;

public class App extends Application {

    public static AppDatabase appDatabase;
    private static AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerAppComponent.create();
        appDatabase = AppDatabase.getAppDatabase(this);
    }

    public static AppComponent getComponent() {
        return component;
    }
}
