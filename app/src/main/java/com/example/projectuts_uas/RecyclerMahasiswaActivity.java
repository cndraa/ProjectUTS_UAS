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

import com.example.projectuts_uas.Adapter.DosenAdapter;
import com.example.projectuts_uas.Adapter.MahasiswaAdapter;
import com.example.projectuts_uas.Model.DefaultResult;
import com.example.projectuts_uas.Model.Dosen;
import com.example.projectuts_uas.Model.Mahasiswa;
import com.example.projectuts_uas.Network.GetDataService;
import com.example.projectuts_uas.Network.RetrofitClientInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecyclerMahasiswaActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MahasiswaAdapter mahasiswaAdapter;
    private ArrayList<Mahasiswa> mhsArray;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_mahasiswa);

        this.setTitle("SI KRS - Hai [Nama MHS]");

        //tambahMhs();

        recyclerView = findViewById(R.id.rvMahasiswa);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Sek Dulu Yaa");
        progressDialog.show();

        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<ArrayList<Mahasiswa>> call = service.getMahasiswaAll("72170139");
        call.enqueue(new Callback<ArrayList<Mahasiswa>>() {
            @Override
            public void onResponse(Call<ArrayList<Mahasiswa>> call, Response<ArrayList<Mahasiswa>> response) {
                progressDialog.dismiss();

                recyclerView = findViewById(R.id.rvMahasiswa);
                mhsArray = response.body();
                mahasiswaAdapter = new MahasiswaAdapter(response.body());
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(RecyclerMahasiswaActivity.this);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(mahasiswaAdapter);
            }

            @Override
            public void onFailure(Call<ArrayList<Mahasiswa>> call, Throwable t) {
                Toast.makeText(RecyclerMahasiswaActivity.this, "Login Gagal, Coba Lagi", Toast.LENGTH_SHORT);
            }
        });

        registerForContextMenu(recyclerView);//buat mendaftarkan registermenu (yg dipencet lama) ke recycler

        /*mahasiswaAdapter = new MahasiswaAdapter(mhsArray);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(RecyclerMahasiswaActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mahasiswaAdapter);*/
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        Mahasiswa mhs = mhsArray.get(item.getGroupId());
        if(item.getTitle() == "Ubah Data Mahasiswa"){
            Intent intent = new Intent(RecyclerMahasiswaActivity.this, CrudMhsActivity.class);
            intent.putExtra("id_mhs", mhs.getId());
            intent.putExtra("nama", mhs.getNama());
            intent.putExtra("nim", mhs.getNim());
            intent.putExtra("alamat", mhs.getAlamat());
            intent.putExtra("email", mhs.getEmail());
            intent.putExtra("foto", mhs.getImage());
            intent.putExtra("is_update", true);
            startActivity(intent);
        }else if(item.getTitle() == "Hapus Data Mahasiswa"){
            progressDialog = new ProgressDialog(RecyclerMahasiswaActivity.this);
            progressDialog.show();

            GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
            Call<DefaultResult> call = service.delete_mhs(
                    mhs.getId(),
                    "72170139"
            );

            call.enqueue(new Callback<DefaultResult>() {
                @Override
                public void onResponse(Call<DefaultResult> call, Response<DefaultResult> response) {
                    progressDialog.dismiss();
                    Toast.makeText(RecyclerMahasiswaActivity.this, "Berhasil Dihapus!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RecyclerMahasiswaActivity.this, RecyclerMahasiswaActivity.class);
                    finish();
                    startActivity(intent);
                }

                @Override
                public void onFailure(Call<DefaultResult> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(RecyclerMahasiswaActivity.this, "Gagal Hapus, Harap Coba Lagi", Toast.LENGTH_SHORT).show();
                }
            });
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menucrudmhs,menu);
        return true;
    }

    //isi dari menu titik tiga
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == R.id.item1){
            Intent intent = new Intent(RecyclerMahasiswaActivity.this, CrudMhsActivity.class); //intent untuk berpindah Main3activity
            startActivity(intent);
        }
        return true;
    }

    /*private void tambahMhs(){
        mhsArray = new ArrayList<>();
        mhsArray.add(new Mahasiswa(R.drawable.ic_launcher_background,"72170001 - Didimus Candra", "didi@gmail.com", "Klitreen"));
        mhsArray.add(new Mahasiswa(R.drawable.ic_launcher_background,"72170002 - Beni Mulia", "benn@gmail.com", "Wonosari"));
        mhsArray.add(new Mahasiswa(R.drawable.ic_launcher_background,"72170003 - Aryawan Ravato", "aryy@gmail.com", "Dr Wahidin"));
    }*/
}
