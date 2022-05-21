package com.example.kutsis;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthEmailException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private TextInputLayout emailWrapper,passwordWrapper;
    private Button loginBtn,signupBtn;
    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();
        registerHandlers();
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    private void updateUI(FirebaseUser currentUser) {
        if (currentUser != null) {
            Toast.makeText(this, currentUser.getEmail() + " giriş yaptı", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, SelectionActivity.class);
            intent.putExtra("user", currentUser);
            startActivity(intent);
        }
    }

    private void initComponents () {
        mAuth = FirebaseAuth.getInstance();
        emailWrapper = findViewById(R.id.emailWrapper);
        passwordWrapper = findViewById(R.id.passwordWrapper);
        loginBtn = findViewById(R.id.loginBtn);
        signupBtn = findViewById(R.id.signupLoginBtn);
    }

    private void registerHandlers(){
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String eposta = emailWrapper.getEditText().getText().toString();
                String sifre = passwordWrapper.getEditText().getText().toString();

                mAuth.signInWithEmailAndPassword(eposta, sifre)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    updateUI(user);
                                }
                                if(!task.isSuccessful()) {
                                    emailWrapper.setError(null);
                                    passwordWrapper.setError(null);
                                    try {
                                        throw task.getException();
                                    }
                                    catch (FirebaseAuthEmailException e) {
                                        emailWrapper.setError(getString(R.string.error_invalid_email));
                                        emailWrapper.requestFocus();
                                    }
                                    catch(FirebaseAuthInvalidCredentialsException e) {
                                        passwordWrapper.setError(getString(R.string.error_invalid_password));
                                        passwordWrapper.requestFocus();
                                    }
                                    catch (FirebaseAuthInvalidUserException e) {
                                        emailWrapper.setError(getString(R.string.user_doesnt_exist));
                                        emailWrapper.requestFocus();
                                    }
                                    catch(Exception e) {
                                        Log.e(TAG, e.getMessage());
                                    }
                                }
                            }
                        });
            }
        });

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}