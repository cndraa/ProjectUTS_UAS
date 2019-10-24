package com.example.projectuts_uas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CrudKrsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud_krs);

        this.setTitle("SI KRS - Hai [Nama Admin]");

        Button simpaan = findViewById(R.id.btnSimpanKrs);

        simpaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CrudKrsActivity.this, RecyclerKrsActivity.class); //intent untuk berpindah Helpactivity
                startActivity(intent);
            }
        });

    }
}
