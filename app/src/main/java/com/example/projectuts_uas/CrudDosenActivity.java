package com.example.projectuts_uas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class CrudDosenActivity extends AppCompatActivity {

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

                builder.setMessage("Apakah anda yakin untuk simpan?").setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(CrudDosenActivity.this, "Tidak Jadi Simpan!", Toast.LENGTH_SHORT).show();
                    }
                }).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(CrudDosenActivity.this, RecyclerDosenActivity.class); //intent untuk berpindah Helpactivity
                        startActivity(intent);
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }
}
