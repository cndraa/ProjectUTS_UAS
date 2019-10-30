package com.example.projectuts_uas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MenuAdminActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.signout,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.openBrowser:
                AlertDialog.Builder builder = new AlertDialog.Builder(MenuAdminActivity.this);

                builder.setMessage("Apakah anda yakin untuk Keluar?").setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MenuAdminActivity.this, "Tidak Jadi Keluar!", Toast.LENGTH_SHORT).show();
                    }
                }).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(MenuAdminActivity.this, MainActivity.class); //intent untuk berpindah Helpactivity
                        startActivity(intent);
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();

                return true;

        }
        return false;
    }

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
