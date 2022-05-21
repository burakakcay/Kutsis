package com.example.kutsis;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class SecimActivity extends AppCompatActivity {

    private View view;
    private TextView textView;
    private RecyclerView kutuphanelerimiz, tableRecycleView;
    private RecyclerviewKutuphaneAdapter adapter;

    ArrayList<String> kutuphaneIsimleri = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secim);
//        recieveMessageFromIntent();

        initComponents();
        loadData();
    }

    private void loadData() {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference dbRef = database.getReference();
        kutuphanelerimiz = (RecyclerView) view.findViewById(R.id.recyclerView);


        dbRef.child("libraries").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    //Log.d("firebase", String.valueOf(task.getResult().getValue()));
                    //Log.d("firebase", String.valueOf(task.getResult().child("1").child("name").getValue()));
                    DataSnapshot result = task.getResult();
                    for(DataSnapshot data: result.getChildren()){
                        kutuphaneIsimleri.add(String.valueOf(data.child("name").getValue()));
                        Log.d("firebase", String.valueOf(data.child("name").getValue()));
                    }
                    setupRecyclerView();
                }
            }
        });
    }

    private void setupRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        kutuphanelerimiz.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(kutuphanelerimiz.getContext(),
                linearLayoutManager.getOrientation());
        kutuphanelerimiz.addItemDecoration(dividerItemDecoration);
        adapter = new RecyclerviewKutuphaneAdapter(this, kutuphaneIsimleri);
        kutuphanelerimiz.setAdapter(adapter);
    }

    private void initComponents() {
        kutuphanelerimiz = findViewById(R.id.recyclerView);
    }

    private void recieveMessageFromIntent() {
        Intent intent = getIntent();
        String message = intent.getStringExtra(QRActivity.MESSAGE_LIBRARYID);
        textView.setText(message);
    }
}