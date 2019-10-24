package com.example.projectuts_uas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MenuAdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_admin);

        this.setTitle("SI KRS - Hai [Nama Admin]");

        ImageView daftarDosen = findViewById(R.id.daftarDsnView);
        ImageView daftarMhs = findViewById(R.id.daftarMhsView);
        ImageView kelolaKrs = findViewById(R.id.kelolaKrsView);
        ImageView daftarMatkul = findViewById(R.id.daftarMatkulView);
        ImageView dataDiri = findViewById(R.id.dataDiriView);

        daftarDosen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuAdminActivity.this, RecyclerDosenActivity.class); //intent untuk berpindah Helpactivity
                startActivity(intent);
            }
        });

        dataDiri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuAdminActivity.this, RecyclerMahasiswaActivity.class); //intent untuk berpindah Helpactivity
                startActivity(intent);
            }
        });

        daftarMhs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuAdminActivity.this, RecyclerMahasiswaActivity.class); //intent untuk berpindah Helpactivity
                startActivity(intent);
            }
        });

        kelolaKrs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuAdminActivity.this, CrudKrsActivity.class); //intent untuk berpindah Helpactivity
                startActivity(intent);
            }
        });

        daftarMatkul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuAdminActivity.this, RecyclerMatkulActivity.class); //intent untuk berpindah Helpactivity
                startActivity(intent);
            }
        });

    }
}
