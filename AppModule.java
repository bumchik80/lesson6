package ru.bumchik.lesson6;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    @Provides
    Presenter providePresenter() {
        return new Presenter();
    }
}
