package com.example.projectuts_uas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class CrudKrsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud_krs);

        this.setTitle("SI KRS - Hai [Nama Admin]");

        Button simpaan = findViewById(R.id.btnSimpanKrs);

        simpaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CrudKrsActivity.this);

                builder.setMessage("Apakah anda yakin untuk simpan?").setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(CrudKrsActivity.this, "Tidak Jadi Simpan!", Toast.LENGTH_SHORT).show();
                    }
                }).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(CrudKrsActivity.this, RecyclerKrsActivity.class); //intent untuk berpindah Helpactivity
                        startActivity(intent);
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

    }
}
