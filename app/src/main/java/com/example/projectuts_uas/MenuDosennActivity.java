package com.example.projectuts_uas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MenuDosennActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_dosenn);

        ImageView dftrKrs = findViewById(R.id.dftarKrsView);

        dftrKrs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuDosennActivity.this, RecyclerKrsActivity.class); //intent untuk berpindah Helpactivity
                startActivity(intent);
            }
        });

    }
}
