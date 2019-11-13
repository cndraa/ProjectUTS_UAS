package com.example.projectuts_uas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class SplaxhActivity extends AppCompatActivity {

    private int waktu_loading=2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splaxh);

        TextView email = findViewById(R.id.emailText);

        this.getSupportActionBar().hide(); //menghilangkan tittle bar



        ImageView logo = findViewById(R.id.logoView);

        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SplaxhActivity.this, MainActivity.class); //intent untuk berpindah Helpactivity
                startActivity(intent);
            }
        });

        SharedPreferences prefs = SplaxhActivity.this.getSharedPreferences("prefs_file",MODE_PRIVATE);
        String statusLogin = prefs.getString("isLogin",null);

        if(statusLogin != null){
            if (statusLogin.equals("Mahasiswa"))
            {
                Intent intent = new Intent(SplaxhActivity.this, MenuDosennActivity.class);
                startActivity(intent);
            }else if (statusLogin.equals("Admin")){
                Intent intent = new Intent(SplaxhActivity.this, MenuAdminActivity.class);
                startActivity(intent);
            }
        }else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    //setelah loading maka akan langsung berpindah ke home activity
                    Intent home = new Intent(SplaxhActivity.this, MainActivity.class);
                    startActivity(home);
                    finish();
                }
            }, waktu_loading);
        }

    }
}
