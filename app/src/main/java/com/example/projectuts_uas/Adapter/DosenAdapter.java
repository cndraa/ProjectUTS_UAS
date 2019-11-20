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
import com.example.projectuts_uas.Model.Dosen;
import com.example.projectuts_uas.R;
import com.squareup.picasso.Picasso;

import java.net.URL;
import java.util.ArrayList;


public class DosenAdapter extends RecyclerView.Adapter<DosenAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Dosen> dataList;
    public DosenAdapter(ArrayList<Dosen> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_dosen, parent, false);
        context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.icon.getLayoutParams().width = 100;
        holder.icon.getLayoutParams().height = 100;
        if (dataList.get(position).getImage()!= null){
            Picasso.with(this.context)
                    .load(dataList.get(position).getImage())
                    .into(holder.icon);
        }
        holder.txtNidn.setText(dataList.get(position).getNidn());
        holder.txtGelar.setText(dataList.get(position).getGelar());
        holder.txtEmail.setText(dataList.get(position).getEmail());
        holder.txtAlamat.setText(dataList.get(position).getAlamat());
        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(context!= null){
                    Intent intent = new Intent(context, CrudDosenActivity.class);
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
        private TextView txtNidn, txtGelar, txtEmail, txtAlamat;
        private CardView cv;
        private ImageView icon;

        public ViewHolder(View view){
            super(view);//Super --> akan mengambil
            icon = view.findViewById(R.id.imageMhs);
            txtNidn = view.findViewById(R.id.txtNim);
            txtGelar = view.findViewById(R.id.txtGelar);
            txtEmail = view.findViewById(R.id.txtEmail);
            txtAlamat = view.findViewById(R.id.txtAlamat);
            cv = view.findViewById(R.id.card_view_dosen);

        }
    }
}
