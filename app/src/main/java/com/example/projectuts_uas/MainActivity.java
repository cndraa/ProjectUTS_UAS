package com.example.projectuts_uas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.getSupportActionBar().hide();

        Button signButton = findViewById(R.id.signButton);
        Button dosen = findViewById(R.id.btnDosen);

        signButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MenuAdminActivity.class); //intent untuk berpindah Helpactivity
                startActivity(intent);
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
