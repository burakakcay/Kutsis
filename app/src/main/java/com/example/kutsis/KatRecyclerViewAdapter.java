package com.example.kutsis;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class KatRecyclerViewAdapter extends RecyclerView.Adapter<KatRecyclerViewAdapter.KatViewHolder> {

    private List<Kat> katListesi;

    class KatViewHolder extends RecyclerView.ViewHolder {
        private TextView lblAdi;
        private ImageView imgLogo;

        KatViewHolder(View view) {
            super(view);
            lblAdi = view.findViewById(R.id.lblAdi);
            imgLogo = view.findViewById(R.id.imgLogo);
        }
    }

    public KatRecyclerViewAdapter(List<Kat> katListesi) {
        this.katListesi = katListesi;
    }

    @Override
    public KatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.element_katlar, parent, false);
        return new KatViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(KatViewHolder viewHolder, int position) {
        Kat kat = katListesi.get(position);
        viewHolder.lblAdi.setText(kat.getAdi());
        viewHolder.imgLogo.setImageResource(kat.getLogo());
    }

    @Override
    public int getItemCount() {
        return katListesi.size();
    }

}