package com.example.projectuts_uas.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectuts_uas.CrudKrsActivity;
import com.example.projectuts_uas.CrudMhsActivity;
import com.example.projectuts_uas.Model.Dosen;
import com.example.projectuts_uas.Model.Mahasiswa;
import com.example.projectuts_uas.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MahasiswaAdapter extends RecyclerView.Adapter<MahasiswaAdapter.ViewHolder>{

    private Context context;
    private ArrayList<Mahasiswa> dataList;
    public MahasiswaAdapter(ArrayList<Mahasiswa> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MahasiswaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_mahasiswa, parent, false);
        context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MahasiswaAdapter.ViewHolder holder, final int position) {
        holder.icon.getLayoutParams().width = 600;
        holder.icon.getLayoutParams().height = 600;
        if (dataList.get(position).getImage()!= null){
            Picasso.with(this.context)
                    .load("https://kpsi.fti.ukdw.ac.id/progmob/" + dataList.get(position).getImage())
                    .into(holder.icon);
        }
        holder.txtNama.setText(dataList.get(position).getNama());
        holder.txtNim.setText(dataList.get(position).getNim());
        holder.txtEmail.setText(dataList.get(position).getEmail());
        holder.txtAlamat.setText(dataList.get(position).getAlamat());
        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(context!= null){
                    Intent intent = new Intent(context, CrudMhsActivity.class);
                    context.startActivities(new Intent[]{intent});
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{
        private TextView txtNama, txtNim,txtEmail, txtAlamat;
        private ImageView icon;
        private CardView cv;

        public ViewHolder(View view){
            super(view);//Super --> akan mengambil
            icon = view.findViewById(R.id.imageMhs2);
            txtNama = view.findViewById(R.id.txtNamaMhs2);
            txtNim = view.findViewById(R.id.txtNim);
            txtEmail = view.findViewById(R.id.txtEmail);
            txtAlamat = view.findViewById(R.id.txtAlamat);
            cv = view.findViewById(R.id.cvMahasiswa);
            view.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            contextMenu.setHeaderTitle("Pilih Aksi");
            contextMenu.add(this.getAdapterPosition(), view.getId(), 0, "Ubah Data Mahasiswa");
            contextMenu.add(this.getAdapterPosition(), view.getId(), 0, "Hapus Data Mahasiswa");
        }
    }
}
