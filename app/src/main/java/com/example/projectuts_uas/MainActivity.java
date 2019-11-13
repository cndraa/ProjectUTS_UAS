package com.example.projectuts_uas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.getSupportActionBar().hide();

        Button signButton = findViewById(R.id.signButton);
        Button dosen = findViewById(R.id.btnDosen);
        final TextView email = findViewById(R.id.emailText);

        SharedPreferences prefs = MainActivity.this.getSharedPreferences("prefs_file",MODE_PRIVATE);
        String statusLogin = prefs.getString("isLogin",null);

        signButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences prefs = MainActivity.this.getSharedPreferences("prefs_file",MODE_PRIVATE);
                String statusLogin = prefs.getString("isLogin",null); SharedPreferences.Editor edit = prefs.edit();

                Button btnLogin = (Button)findViewById(R.id.signButton);
                if (email.getText().toString().contains("si.ukdw.ac.id")){
                    edit.putString("isLogin","Mahasiswa");
                    Intent intent = new Intent(MainActivity.this, MenuDosennActivity.class);
                    startActivity(intent);
                }else if (email.getText().toString().contains("staff.ukdw.ac.id")){
                    edit.putString("isLogin","Admin");
                    Intent intent = new Intent(MainActivity.this, MenuAdminActivity.class);
                    startActivity(intent);
                }
                edit.commit();
            }
        });


        dosen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MenuDosennActivity.class); //intent untuk berpindah Helpactivity
                startActivity(intent);
            }
        });
    }
}
