package com.example.projectuts_uas.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectuts_uas.CrudKrsActivity;
import com.example.projectuts_uas.CrudMatkulActivity;
import com.example.projectuts_uas.Model.Dosen;
import com.example.projectuts_uas.Model.Matkul;
import com.example.projectuts_uas.R;

import java.util.ArrayList;

public class MatkulAdapter extends RecyclerView.Adapter<MatkulAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Matkul> dataList;
    public MatkulAdapter(ArrayList<Matkul> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MatkulAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_matkul, parent, false);
        context = parent.getContext();
        return new MatkulAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MatkulAdapter.ViewHolder holder, final int position) {
        holder.txtKode.setText(dataList.get(position).getKode());
        holder.txtMatkul.setText(dataList.get(position).getMatkul());
        holder.txtHari.setText(dataList.get(position).getHari());
        holder.txtSesi.setText(dataList.get(position).getSesi());
        holder.txtSks.setText(dataList.get(position).getSks());
        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(context!= null){
                    Intent intent = new Intent(context, CrudMatkulActivity.class);
                    context.startActivities(new Intent[]{intent});
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtKode, txtMatkul, txtHari, txtSesi, txtSks;
        private CardView cv;

        public ViewHolder(View view){
            super(view);//Super --> akan mengambil
            txtKode = view.findViewById(R.id.txtKode);
            txtMatkul = view.findViewById(R.id.txtMatkul);
            txtHari = view.findViewById(R.id.txtHari);
            txtSesi = view.findViewById(R.id.txtSesi);
            txtSks = view.findViewById(R.id.txtJmlhSks);
            cv = view.findViewById(R.id.cvMatkul);
        }
    }
}
