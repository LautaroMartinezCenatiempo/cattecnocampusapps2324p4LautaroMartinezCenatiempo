package com.tecnocampus.cattecnocampusapps2324p4lautaromartinezcenatiempo.View.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.tecnocampus.cattecnocampusapps2324p4lautaromartinezcenatiempo.R;
import com.tecnocampus.cattecnocampusapps2324p4lautaromartinezcenatiempo.View.Utils.Callback;
import com.tecnocampus.cattecnocampusapps2324p4lautaromartinezcenatiempo.model.UserRepository;

public class LoginActivity extends AppCompatActivity {

    private static final String SHARED_PREFS_FILE = "login_state";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";

    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonLogin;
    private Button buttonRegister;

    private FirebaseAuth auth;
    private UserRepository userRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance();
        userRepository = new UserRepository();

        // Bind views
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonRegister = findViewById(R.id.buttonRegister);

        // Set click listeners
        buttonLogin.setOnClickListener(v -> loginUser());
        buttonRegister.setOnClickListener(v -> registerUser());

        // Check if user is already logged in
        if (isLoggedIn()) {
            navigateToMainActivity();
        }
    }

    private void loginUser() {
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();

        // Reset errors
        editTextEmail.setError(null);
        editTextPassword.setError(null);

        userRepository.login(email, password, new Callback<FirebaseUser>() {
            @Override
            public void onSuccess(FirebaseUser user) {
                // Login successful, save login state and navigate to main activity or home screen
                saveLoginState(true);
                Toast.makeText(LoginActivity.this, getString(R.string.login_succesful), Toast.LENGTH_SHORT).show();
                navigateToMainActivity();
            }

            @Override
            public void onFailure(Exception exception) {
                // Login failed, show error message
                Toast.makeText(LoginActivity.this, getString(R.string.login_failed) + exception.getMessage(), Toast.LENGTH_SHORT).show();
                // Show specific errors for email and password fields
                if (exception.getMessage().contains("email")) {
                    editTextEmail.setError(getString(R.string.invalid_email));
                    editTextEmail.requestFocus();
                } else if (exception.getMessage().contains("password")) {
                    editTextPassword.setError(getString(R.string.invalid_password));
                    editTextPassword.requestFocus();
                }
            }
        });
    }

    private void registerUser() {
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();

        // Reset errors
        editTextEmail.setError(null);
        editTextPassword.setError(null);

        userRepository.register(email, password, new Callback<FirebaseUser>() {
            @Override
            public void onSuccess(FirebaseUser user) {
                // Registration successful, save login state and navigate to main activity or home screen
                saveLoginState(true);
                Toast.makeText(LoginActivity.this, getString(R.string.register_succesfull), Toast.LENGTH_SHORT).show();
                navigateToMainActivity();
            }

            @Override
            public void onFailure(Exception exception) {
                // Registration failed, show error message
                Toast.makeText(LoginActivity.this, getString(R.string.register_failed) + exception.getMessage(), Toast.LENGTH_SHORT).show();
                // Show specific errors for email and password fields
                if (exception.getMessage().contains("email")) {
                    editTextEmail.setError(getString(R.string.invalid_email));
                    editTextEmail.requestFocus();
                } else if (exception.getMessage().contains("password")) {
                    editTextPassword.setError(getString(R.string.invalid_password));
                    editTextPassword.requestFocus();
                }
            }
        });
    }

    private void navigateToMainActivity() {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish(); // Finish LoginActivity so user cannot go back
    }

    private void saveLoginState(boolean isLoggedIn) {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS_FILE, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(KEY_IS_LOGGED_IN, isLoggedIn);
        editor.apply();
    }

    private boolean isLoggedIn() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS_FILE, MODE_PRIVATE);
        return sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false);
    }
}
