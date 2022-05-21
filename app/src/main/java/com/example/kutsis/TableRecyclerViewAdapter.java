package com.example.kutsis;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class TableRecyclerViewAdapter extends RecyclerView.Adapter<TableRecyclerViewAdapter.TableViewHolder> {

    private List<Table> tableList;
    
    class TableViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private SwitchMaterial switchMaterial;

        TableViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.tableId);
            switchMaterial = view.findViewById(R.id.isReserved);
        }
    }

    public TableRecyclerViewAdapter(List<Table> tableList) {
        this.tableList = tableList;
    }

    @Override
    public TableViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.element_tables, parent, false);
        return new TableViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TableViewHolder holder, int position) {
        Table table = tableList.get(position);
        holder.textView.setText(table.getTableId());
        holder.switchMaterial.setChecked(table.getIsReserved());
    }

    @Override
    public int getItemCount() {
        return tableList.size();
    }
}