package com.example.projectuts_uas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CrudMhsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud_mhs);

        Button simpanMhs = findViewById(R.id.btnSimpanMhs);

        simpanMhs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CrudMhsActivity.this, MenuAdminActivity.class); //intent untuk berpindah Helpactivity
                startActivity(intent);
            }
        });

    }
}
