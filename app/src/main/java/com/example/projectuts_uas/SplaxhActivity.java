package com.example.projectuts_uas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class SplaxhActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splaxh);

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
