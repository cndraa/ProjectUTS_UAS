package com.example.projectuts_uas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.projectuts_uas.Adapter.KrsAdapter;
import com.example.projectuts_uas.Adapter.MatkulAdapter;
import com.example.projectuts_uas.Model.Krs;
import com.example.projectuts_uas.Model.Matkul;

import java.util.ArrayList;

public class RecyclerKrsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private KrsAdapter krsAdapter;
    private ArrayList<Krs> krsArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_krs);

        tambahKrs();

        recyclerView = findViewById(R.id.rvKrs);
        krsAdapter = new KrsAdapter(krsArray);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(RecyclerKrsActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(krsAdapter);
    }

    private void tambahKrs(){
        krsArray = new ArrayList<>();
        krsArray.add(new Krs("726373","DDP", "Senin", "3", "3","Argo Wibowo","30"));
        krsArray.add(new Krs("726373","DDP", "Senin", "3", "3", "Pak Siang","24"));
    }
}
