package com.example.projectuts_uas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

public class SplaxhActivity extends AppCompatActivity {

    private int waktu_loading=2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splaxh);

        this.getSupportActionBar().hide(); //menghilangkan tittle bar

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //setelah loading maka akan langsung berpindah ke home activity
                Intent home = new Intent(SplaxhActivity.this, MainActivity.class);
                startActivity(home);
                finish();
            }
        }, waktu_loading);

        ImageView logo = findViewById(R.id.logoView);

        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SplaxhActivity.this, MainActivity.class); //intent untuk berpindah Helpactivity
                startActivity(intent);
            }
        });
    }
}
