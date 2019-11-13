package com.example.projectuts_uas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MenuDosennActivity extends AppCompatActivity {

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
                AlertDialog.Builder builder = new AlertDialog.Builder(MenuDosennActivity.this);

                builder.setMessage("Apakah anda yakin untuk Keluar?").setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MenuDosennActivity.this, "Tidak Jadi Keluar!", Toast.LENGTH_SHORT).show();
                    }
                }).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SharedPreferences prefs = MenuDosennActivity.this.getSharedPreferences("prefs_file",MODE_PRIVATE);
                        String statusLogin = prefs.getString("isLogin",null); SharedPreferences.Editor edit = prefs.edit();
                        edit.putString("isLogin", null);
                        edit.commit();
                        Intent intent = new Intent(MenuDosennActivity.this, MainActivity.class); //intent untuk berpindah Helpactivity
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
        setContentView(R.layout.activity_menu_dosenn);

        this.setTitle("SI KRS - Hai [Nama MHS]");

        ImageView dftrKrs = findViewById(R.id.dftarKrsView);
        ImageView lhatKelas = findViewById(R.id.lihatKelasView2);

        dftrKrs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuDosennActivity.this, RecyclerKrsActivity.class); //intent untuk berpindah Helpactivity
                startActivity(intent);
            }
        });

        lhatKelas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuDosennActivity.this, RecyclerKrsMhsActivity.class); //intent untuk berpindah Helpactivity
                startActivity(intent);
            }
        });
    }
}
