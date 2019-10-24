package com.example.projectuts_uas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.projectuts_uas.Adapter.DosenAdapter;
import com.example.projectuts_uas.Adapter.MahasiswaAdapter;
import com.example.projectuts_uas.Model.Dosen;
import com.example.projectuts_uas.Model.Mahasiswa;

import java.util.ArrayList;

public class RecyclerMahasiswaActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MahasiswaAdapter mahasiswaAdapter;
    private ArrayList<Mahasiswa> mhsArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_mahasiswa);

        tambahMhs();

        recyclerView = findViewById(R.id.rvMahasiswa);
        mahasiswaAdapter = new MahasiswaAdapter(mhsArray);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(RecyclerMahasiswaActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mahasiswaAdapter);
    }

    private void tambahMhs(){
        mhsArray = new ArrayList<>();
        mhsArray.add(new Mahasiswa(R.drawable.ic_launcher_background,"72170001 - Candra", "didi@gmail.com", "Klitreen"));
        mhsArray.add(new Mahasiswa(R.drawable.ic_launcher_background,"72170001 - Candra Didimus", "didimusss@gmail.com", "Klitreen"));
    }
}
