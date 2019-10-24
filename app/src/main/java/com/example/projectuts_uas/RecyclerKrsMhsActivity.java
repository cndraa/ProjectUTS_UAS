package com.example.projectuts_uas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.projectuts_uas.Adapter.KrsAdapter;
import com.example.projectuts_uas.Adapter.KrsMhsAdapter;
import com.example.projectuts_uas.Model.Krs;
import com.example.projectuts_uas.Model.KrsMhs;

import java.util.ArrayList;

public class RecyclerKrsMhsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private KrsMhsAdapter krsMhsAdapter;
    private ArrayList<KrsMhs> krsMhsArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_krs_mhs);

        this.setTitle("SI KRS - Hai [Nama MHS]");

        tambahKrsMhs();

        recyclerView = findViewById(R.id.rvKrsMhs);
        krsMhsAdapter = new KrsMhsAdapter(krsMhsArray);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(RecyclerKrsMhsActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(krsMhsAdapter);

    }


    private void tambahKrsMhs(){
        krsMhsArray = new ArrayList<>();
        krsMhsArray.add(new KrsMhs("DDP-1","DDP", "Senin", "3", "3", "Katon Wijana", "32"));
        krsMhsArray.add(new KrsMhs("ALPRO-1","ALPRO", "Selasa", "3", "3", "Katon Wijana", "25"));
        krsMhsArray.add(new KrsMhs("MUL-1","Multimedia", "Rabu", "3", "3", "Argp Wibowo", "25"));
    }
}
