package com.example.projectuts_uas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.projectuts_uas.Model.DefaultResult;
import com.example.projectuts_uas.Network.GetDataService;
import com.example.projectuts_uas.Network.RetrofitClientInstance;
import com.example.projectuts_uas.Model.DefaultResult;

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

        recyclerView = (RecyclerView) findViewById(R.id.rvDosen);

        //tambahDosen();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Sek Dulu Yaa");
        progressDialog.show();


        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<ArrayList<Dosen>> call = service.getDosenAll("72170139");
        call.enqueue(new Callback<ArrayList<Dosen>>() {
            @Override
            public void onResponse(Call<ArrayList<Dosen>> call, Response<ArrayList<Dosen>> response) {
                progressDialog.dismiss();

                recyclerView = findViewById(R.id.rvDosen);
                dosenArray = response.body();
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

        registerForContextMenu(recyclerView);//buat mendaftarkan registermenu (yg dipencet lama) ke recycler

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        Dosen dosen = dosenArray.get(item.getGroupId());
        if(item.getTitle() == "Ubah Data Dosen"){
            Intent intent = new Intent(RecyclerDosenActivity.this, CrudDosenActivity.class);
            intent.putExtra("id_dosen", dosen.getId());
            intent.putExtra("nama_dosen", dosen.getNama());
            intent.putExtra("nidn", dosen.getNidn());
            intent.putExtra("alamat", dosen.getAlamat());
            intent.putExtra("email", dosen.getEmail());
            intent.putExtra("gelar", dosen.getGelar());
            intent.putExtra("foto", dosen.getImage());
            intent.putExtra("is_update", true);
            startActivity(intent);
        }else if(item.getTitle() == "Hapus Data Dosen"){
            progressDialog = new ProgressDialog(RecyclerDosenActivity.this);
            progressDialog.show();

            GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
            Call<DefaultResult> call = service.delete_dosen(
                    dosen.getId(),
                    "72170139"
            );

            call.enqueue(new Callback<DefaultResult>() {
                @Override
                public void onResponse(Call<DefaultResult> call, Response<DefaultResult> response) {
                    progressDialog.dismiss();
                    Toast.makeText(RecyclerDosenActivity.this, "Berhasil Dihapus!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RecyclerDosenActivity.this, RecyclerDosenActivity.class);
                    finish();
                    startActivity(intent);
                }

                @Override
                public void onFailure(Call<DefaultResult> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(RecyclerDosenActivity.this, "Gagal Hapus, Harap Coba Lagi", Toast.LENGTH_SHORT).show();
                }
            });
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menucruddosen,menu);
        return true;
    }

    //isi dari menu titik tiga
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == R.id.item1){
            Intent intent = new Intent(RecyclerDosenActivity.this, CrudDosenActivity.class); //intent untuk berpindah Main3activity
            startActivity(intent);
        }
        return true;
    }

    /*private void tambahDosen() {
        dosenArray = new ArrayList<>();
        dosenArray.add(new Dosen(R.drawable.ic_launcher_background, "000001 - Didimus Candra", "Sarjana Komputer", "candra@gmail.com", "Jalan Niaga 1"));
        dosenArray.add(new Dosen(R.drawable.ic_launcher_background, "000002 - Beni Mulia", "Sarjana Humaniora", "benii@gmail.com", "Jalan Wonosari"));
    }*/
}
