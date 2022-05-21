package com.example.kutsis;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {

    View view;
    RecyclerView kutuphanelerimiz;
    RecyclerviewKutuphaneAdapter adapter;
    private FirebaseDatabase database;

    ArrayList<String> kutuphaneIsimleri = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        initComponents();

        return view;

    }
    private void initComponents(){
        database = FirebaseDatabase.getInstance("https://kutsis-b0fd4-default-rtdb.europe-west1.firebasedatabase.app/");
        DatabaseReference dbRef = database.getReference();
        kutuphanelerimiz = (RecyclerView) view.findViewById(R.id.recyclerView);
//test başlangıç



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

    private void setupRecyclerView(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        kutuphanelerimiz.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(kutuphanelerimiz.getContext(),
                linearLayoutManager.getOrientation());
        kutuphanelerimiz.addItemDecoration(dividerItemDecoration);
        adapter = new RecyclerviewKutuphaneAdapter(getActivity(), kutuphaneIsimleri);
        kutuphanelerimiz.setAdapter(adapter);
    }

}