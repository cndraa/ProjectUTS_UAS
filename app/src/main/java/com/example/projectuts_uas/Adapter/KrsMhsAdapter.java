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
import com.example.projectuts_uas.Model.Krs;
import com.example.projectuts_uas.Model.KrsMhs;
import com.example.projectuts_uas.R;

import java.util.ArrayList;

public class KrsMhsAdapter extends RecyclerView.Adapter<KrsMhsAdapter.ViewHolder> {

    Context context;
    private ArrayList<KrsMhs> dataList;
    public KrsMhsAdapter(ArrayList<KrsMhs> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public KrsMhsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_krs_mhs, parent, false);
        return new KrsMhsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KrsMhsAdapter.ViewHolder holder, final int position) {
        holder.txtKode2.setText(dataList.get(position).getKode2());
        holder.txtMatkul2.setText(dataList.get(position).getMatkul2());
        holder.txtHari2.setText(dataList.get(position).getHari2());
        holder.txtSesi2.setText(dataList.get(position).getSesi2());
        holder.txtDosbing2.setText(dataList.get(position).getDosbing2());
        holder.txtJmlMhs2.setText(dataList.get(position).getJmlMhs2());
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtKode2, txtMatkul2, txtHari2, txtSesi2, txtDosbing2, txtJmlMhs2;

        public ViewHolder(View view){
            super(view);//Super --> akan mengambil
            txtKode2 = view.findViewById(R.id.txtKode2);
            txtMatkul2 = view.findViewById(R.id.txtMatkul2);
            txtHari2 = view.findViewById(R.id.txtHari2);
            txtSesi2 = view.findViewById(R.id.txtSesi2);
            txtDosbing2 = view.findViewById(R.id.txtPengampu2);
            txtJmlMhs2 = view.findViewById(R.id.txtJmlMhs2);
        }
    }
}
