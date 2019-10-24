package com.example.projectuts_uas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.projectuts_uas.Adapter.MahasiswaAdapter;
import com.example.projectuts_uas.Adapter.MatkulAdapter;
import com.example.projectuts_uas.Model.Mahasiswa;
import com.example.projectuts_uas.Model.Matkul;

import java.util.ArrayList;

public class RecyclerMatkulActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MatkulAdapter matkulAdapter;
    private ArrayList<Matkul> matkulArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_matkul);

        this.setTitle("SI KRS - Hai [Nama Admin]");

        tambahMatkul();

        recyclerView = findViewById(R.id.rvMatkul);
        matkulAdapter = new MatkulAdapter(matkulArray);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(RecyclerMatkulActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(matkulAdapter);
    }

    private void tambahMatkul(){
        matkulArray = new ArrayList<>();
        matkulArray.add(new Matkul("DDP-1","DDP", "Senin", "3", "6"));
        matkulArray.add(new Matkul("ALPRO-1","Alpro & StrukDat", "Selasa", "1", "5"));
        matkulArray.add(new Matkul("IMK-1","IMK", "Rabu", "3", "3"));
    }
}
