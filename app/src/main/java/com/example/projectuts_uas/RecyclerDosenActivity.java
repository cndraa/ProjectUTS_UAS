package com.example.projectuts_uas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.example.projectuts_uas.Network.GetDataService;
import com.example.projectuts_uas.Network.RetrofitClientInstance;

import com.example.projectuts_uas.Adapter.DosenAdapter;
import com.example.projectuts_uas.Model.Dosen;

import java.util.ArrayList;
import java.util.List;

public class RecyclerDosenActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DosenAdapter dosenAdapter;
    private ArrayList<Dosen> dosenArray;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_dosen);

        this.setTitle("SI KRS - Hai [Nama Admin]");

        //tambahDosen();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Sek Dulu Yaa");
        progressDialog.show();


        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<ArrayList<Dosen>> call = service.getDosenAll("721600012");
        call.enqueue(new Callback<ArrayList<Dosen>>() {
            @Override
            public void onResponse(Call<ArrayList<Dosen>> call, Response<ArrayList<Dosen>> response) {
                progressDialog.dismiss();

                recyclerView = findViewById(R.id.rvDosen);
                dosenAdapter = new DosenAdapter(response.body());
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(RecyclerDosenActivity.this);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(dosenAdapter);
            }

            @Override
            public void onFailure(Call<ArrayList<Dosen>> call, Throwable t) {
                Toast.makeText(RecyclerDosenActivity.this, "Login Gagal, Coba Lagi", Toast.LENGTH_SHORT);
            }
        });

    }

    /*private void tambahDosen() {
        dosenArray = new ArrayList<>();
        dosenArray.add(new Dosen(R.drawable.ic_launcher_background, "000001 - Didimus Candra", "Sarjana Komputer", "candra@gmail.com", "Jalan Niaga 1"));
        dosenArray.add(new Dosen(R.drawable.ic_launcher_background, "000002 - Beni Mulia", "Sarjana Humaniora", "benii@gmail.com", "Jalan Wonosari"));
    }*/
}
