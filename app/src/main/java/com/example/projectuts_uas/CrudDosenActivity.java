package com.example.projectuts_uas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projectuts_uas.Model.Dosen;
import com.example.projectuts_uas.Network.GetDataService;
import com.example.projectuts_uas.Network.RetrofitClientInstance;
import com.example.projectuts_uas.Model.DefaultResult;
import com.example.projectuts_uas.R;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CrudDosenActivity extends AppCompatActivity {

    private EditText nama_dsn;
    private EditText nidn;
    private EditText almt_dsn;
    private EditText email_dsn;
    private EditText gelar_dsn;


    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud_dosen);

        this.setTitle("SI KRS - Hai [Nama Admin]");

        Button simpan = findViewById(R.id.btnSimpan);

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CrudDosenActivity.this);

                builder.setMessage("Apakah anda yakin untuk simpan?")
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(CrudDosenActivity.this, "Tidak Jadi Simpan!", Toast.LENGTH_SHORT).show();
                    }
                }).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        add_data();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

    }

    private void add_data(){
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Harap Menunggu...");
        progressDialog.show();

        nama_dsn = (EditText) findViewById(R.id.txtEditNamaDsn);
        nidn = (EditText) findViewById(R.id.txtEditNidn);
        almt_dsn = (EditText) findViewById(R.id.txtEditAlmtDsn);
        email_dsn = (EditText) findViewById(R.id.txtEditEmailDsn);
        gelar_dsn = (EditText) findViewById(R.id.txtEditGelar);

        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<DefaultResult> call =  service.insert_dosen(
                nama_dsn.getText().toString(),
                nidn.getText().toString(),
                almt_dsn.getText().toString(),
                email_dsn.getText().toString(),
                gelar_dsn.getText().toString(),
                "https://picsum.photos/200","72170139");

        call.enqueue(new Callback<DefaultResult>() {
            @Override
            public void onResponse(Call<DefaultResult> call, Response<DefaultResult> response) {
                progressDialog.dismiss();
                Toast.makeText(CrudDosenActivity.this, "Berhasil Tersimpan!", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(CrudDosenActivity.this, RecyclerDosenActivity.class); //intent untuk berpindah Helpactivity
                startActivity(intent);
                finish();
            }

            @Override
            public void onFailure(Call<DefaultResult> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(CrudDosenActivity.this, "Gagal Menambah Data!", Toast.LENGTH_SHORT).show();
            }
        });
    }




}
