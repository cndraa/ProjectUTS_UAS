package com.example.projectuts_uas.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectuts_uas.Model.Dosen;
import com.example.projectuts_uas.Model.Matkul;
import com.example.projectuts_uas.R;

import java.util.ArrayList;

public class MatkulAdapter extends RecyclerView.Adapter<MatkulAdapter.ViewHolder> {

    Context context;
    private ArrayList<Matkul> dataList;
    public MatkulAdapter(ArrayList<Matkul> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MatkulAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_matkul, parent, false);
        return new MatkulAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MatkulAdapter.ViewHolder holder, final int position) {
        holder.txtKode.setText(dataList.get(position).getKode());
        holder.txtMatkul.setText(dataList.get(position).getMatkul());
        holder.txtHari.setText(dataList.get(position).getHari());
        holder.txtSesi.setText(dataList.get(position).getSesi());
        holder.txtSks.setText(dataList.get(position).getSks());
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtKode, txtMatkul, txtHari, txtSesi, txtSks;

        public ViewHolder(View view){
            super(view);//Super --> akan mengambil
            txtKode = view.findViewById(R.id.txtKode);
            txtMatkul = view.findViewById(R.id.txtMatkul);
            txtHari = view.findViewById(R.id.txtHari);
            txtSesi = view.findViewById(R.id.txtSesi);
            txtSks = view.findViewById(R.id.txtJmlhSks);
        }
    }
}
