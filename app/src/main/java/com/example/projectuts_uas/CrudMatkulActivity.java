package com.example.projectuts_uas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
                Intent intent = new Intent(CrudMatkulActivity.this, RecyclerMatkulActivity.class); //intent untuk berpindah Helpactivity
                startActivity(intent);
            }
        });

    }
}
