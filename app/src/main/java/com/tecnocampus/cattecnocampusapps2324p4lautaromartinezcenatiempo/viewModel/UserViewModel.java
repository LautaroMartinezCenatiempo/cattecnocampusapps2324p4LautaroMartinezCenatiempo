package com.tecnocampus.cattecnocampusapps2324p4lautaromartinezcenatiempo.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.tecnocampus.cattecnocampusapps2324p4lautaromartinezcenatiempo.View.Utils.Callback;
import com.tecnocampus.cattecnocampusapps2324p4lautaromartinezcenatiempo.model.User;
import com.tecnocampus.cattecnocampusapps2324p4lautaromartinezcenatiempo.model.UserRepository;
import com.google.firebase.auth.FirebaseUser;

public class UserViewModel extends ViewModel {
    private MutableLiveData<FirebaseUser> user;
    private MutableLiveData<String> errorMessage;
    private UserRepository userRepository;

    public UserViewModel() {
        userRepository = new UserRepository();
        errorMessage = new MutableLiveData<>();
    }

    public LiveData<FirebaseUser> getUser() {
        if (user == null) {
            user = new MutableLiveData<>();
        }
        return user;
    }

    public void register(String email, String password) {
        userRepository.register(email, password, new Callback<FirebaseUser>() {
            @Override
            public void onSuccess(FirebaseUser data) {
                user.setValue(data);
            }

            @Override
            public void onFailure(Exception e) {
                errorMessage.setValue("Error registering user: " + e.getMessage());
            }
        });
    }

    public void login(String email, String password) {
        userRepository.login(email, password, new Callback<FirebaseUser>() {
            @Override
            public void onSuccess(FirebaseUser data) {
                user.setValue(data);
            }

            @Override
            public void onFailure(Exception e) {
                errorMessage.setValue("Error when login user: " + e.getMessage());
            }
        });
    }
}
