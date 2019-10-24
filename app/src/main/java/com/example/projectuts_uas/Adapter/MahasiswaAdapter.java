package com.example.projectuts_uas.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectuts_uas.Model.Dosen;
import com.example.projectuts_uas.Model.Mahasiswa;
import com.example.projectuts_uas.R;

import java.util.ArrayList;

public class MahasiswaAdapter extends RecyclerView.Adapter<MahasiswaAdapter.ViewHolder>{

    Context context;
    private ArrayList<Mahasiswa> dataList;
    public MahasiswaAdapter(ArrayList<Mahasiswa> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MahasiswaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_mahasiswa, parent, false);
        return new MahasiswaAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MahasiswaAdapter.ViewHolder holder, final int position) {
        holder.icon.setImageResource(dataList.get(position).getImage());
        holder.txtNim.setText(dataList.get(position).getNim());
        holder.txtEmail.setText(dataList.get(position).getEmail());
        holder.txtAlamat.setText(dataList.get(position).getAlamat());
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtNim,txtEmail, txtAlamat;
        private ImageView icon;

        public ViewHolder(View view){
            super(view);//Super --> akan mengambil
            icon = view.findViewById(R.id.imageMhs);
            txtNim = view.findViewById(R.id.txtNim);
            txtEmail = view.findViewById(R.id.txtEmail);
            txtAlamat = view.findViewById(R.id.txtAlamat);

        }
    }
}
