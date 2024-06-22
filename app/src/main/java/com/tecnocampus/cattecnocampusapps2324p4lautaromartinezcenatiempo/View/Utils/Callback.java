package com.tecnocampus.cattecnocampusapps2324p4lautaromartinezcenatiempo.View.Utils;

public interface Callback<T> {
    void onSuccess(T data);
    void onFailure(Exception e);
}