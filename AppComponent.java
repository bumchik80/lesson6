package ru.bumchik.lesson6;

import dagger.Component;

@Component(modules = {AppModule.class})
public interface AppComponent {
    Presenter getPresenter();
}
