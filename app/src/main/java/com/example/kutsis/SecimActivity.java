package com.example.kutsis;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.divider.MaterialDividerItemDecoration;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SecimActivity extends AppCompatActivity {

    private TextView textView;
    private RecyclerView recyclerView, tableRecycleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secim);
//        commented();
//        recieveMessageFromIntent();

        initComponents();
        loadData();
    }

    private void loadData() {
        ArrayList<Kat> katListesi = new ArrayList<>();

        katListesi.add(new Kat(2, "Kat 2", R.drawable.ic_settings));
        katListesi.add(new Kat(3, "Kat 3", R.drawable.ic_profile));
        katListesi.add(new Kat(4, "Kat 4", R.drawable.ic_settings));
        katListesi.add(new Kat(5, "Kat 5", R.drawable.ic_profile));
        katListesi.add(new Kat(6, "Kat 6", R.drawable.ic_settings));
        katListesi.add(new Kat(7, "Kat 7", R.drawable.ic_profile));

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(false);

        KatRecyclerViewAdapter adapter = new KatRecyclerViewAdapter(katListesi);
        recyclerView.setAdapter(adapter);

        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
    }

    private void initComponents() {
        recyclerView = findViewById(R.id.floorRecycleView);
        textView = findViewById(R.id.textView);
    }

    private void recieveMessageFromIntent() {
        Intent intent = getIntent();
        String message = intent.getStringExtra(QRActivity.MESSAGE_LIBRARYID);
        textView.setText(message);
    }

    private void commented() {
        FirebaseApp.initializeApp(this);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference().child("libraries");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}