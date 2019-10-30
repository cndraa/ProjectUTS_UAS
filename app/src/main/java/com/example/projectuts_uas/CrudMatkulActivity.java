package com.example.projectuts_uas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class CrudMatkulActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud_matkul);

        this.setTitle("SI KRS - Hai [Nama Admin]");

        Button save = findViewById(R.id.btnSimpanMatkul);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CrudMatkulActivity.this);

                builder.setMessage("Apakah anda yakin untuk simpan?").setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(CrudMatkulActivity.this, "Tidak Jadi Simpan!", Toast.LENGTH_SHORT).show();
                    }
                }).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(CrudMatkulActivity.this, RecyclerMatkulActivity.class); //intent untuk berpindah Helpactivity
                        startActivity(intent);
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

    }
}
