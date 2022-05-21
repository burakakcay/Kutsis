package com.example.kutsis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private TextInputLayout emailWrapper,passwordWrapper;
    private Button registerBtn;
    private static final String TAG = "RegisterActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initComponents();
        registerHandlers();
    }

    private void registerHandlers() {
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eposta = emailWrapper.getEditText().getText().toString();
                String sifre = passwordWrapper.getEditText().getText().toString();

                mAuth.createUserWithEmailAndPassword(eposta, sifre)
                        .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(RegisterActivity.this, mAuth.getCurrentUser().getEmail() + " kullanıcısı kayıt yaptı.", Toast.LENGTH_SHORT).show();
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                    startActivity(intent);
                                }
                                else {
                                    emailWrapper.setError(null);
                                    passwordWrapper.setError(null);

                                    try {
                                        throw task.getException();
                                    }
                                    catch(FirebaseAuthUserCollisionException e) {
                                        emailWrapper.setError(getString(R.string.error_user_exists));
                                        emailWrapper.requestFocus();
                                    }
                                    catch(FirebaseAuthWeakPasswordException e) {
                                        passwordWrapper.setError(getString(R.string.error_weak_password));
                                        passwordWrapper.requestFocus();
                                    }
                                    catch(Exception e) {
                                        Log.e(TAG, e.getMessage());
                                    }                                }
                            }
                        });
            }
        });
    }

    private void initComponents () {
        mAuth = FirebaseAuth.getInstance();
        emailWrapper = findViewById(R.id.mailWrapper);
        passwordWrapper = findViewById(R.id.passwordWrapper);
        registerBtn = findViewById(R.id.btnRegister);
    }
}