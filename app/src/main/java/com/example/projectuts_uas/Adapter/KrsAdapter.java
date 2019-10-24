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

import com.example.projectuts_uas.CrudDosenActivity;
import com.example.projectuts_uas.CrudKrsActivity;
import com.example.projectuts_uas.Model.Dosen;
import com.example.projectuts_uas.Model.Krs;
import com.example.projectuts_uas.R;

import java.util.ArrayList;

public class KrsAdapter extends RecyclerView.Adapter<KrsAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Krs> dataList;
    public KrsAdapter(ArrayList<Krs> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public KrsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_krs, parent, false);
        context = parent.getContext();
        return new KrsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KrsAdapter.ViewHolder holder, final int position) {
        holder.txtKode1.setText(dataList.get(position).getKode1());
        holder.txtMatkul1.setText(dataList.get(position).getMatkul1());
        holder.txtHari1.setText(dataList.get(position).getHari1());
        holder.txtSesi1.setText(dataList.get(position).getSesi1());
        holder.txtSks1.setText(dataList.get(position).getSks1());
        holder.txtDosbing.setText(dataList.get(position).getDosbing());
        holder.txtJmlMhs.setText(dataList.get(position).getJmlMhs());
        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(context!= null){
                    Intent intent = new Intent(context, CrudKrsActivity.class);
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
        private TextView txtKode1, txtMatkul1, txtHari1, txtSesi1, txtSks1, txtDosbing, txtJmlMhs;
        private CardView cv;

        public ViewHolder(View view){
            super(view);//Super --> akan mengambil
            txtKode1 = view.findViewById(R.id.txtKode1);
            txtMatkul1 = view.findViewById(R.id.txtMatkul1);
            txtHari1 = view.findViewById(R.id.txtHari1);
            txtSesi1 = view.findViewById(R.id.txtSesi1);
            txtSks1 = view.findViewById(R.id.txtJmlhSks1);
            txtDosbing = view.findViewById(R.id.txtPengampu);
            txtJmlMhs = view.findViewById(R.id.txtJmlMhs);
            cv = view.findViewById(R.id.cvKrs);
        }
    }
}
