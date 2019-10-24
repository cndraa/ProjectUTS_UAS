package com.example.projectuts_uas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.projectuts_uas.Adapter.DosenAdapter;
import com.example.projectuts_uas.Model.Dosen;

import java.util.ArrayList;

public class RecyclerDosenActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DosenAdapter dosenAdapter;
    private ArrayList<Dosen> dosenArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_dosen);

        this.setTitle("SI KRS - Hai [Nama Admin]");

        tambahDosen();

        recyclerView = findViewById(R.id.rvDosen);
        dosenAdapter = new DosenAdapter(dosenArray);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(RecyclerDosenActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(dosenAdapter);
    }

    private void tambahDosen() {
        dosenArray = new ArrayList<>();
        dosenArray.add(new Dosen(R.drawable.ic_launcher_background, "000001 - Didimus Candra", "Sarjana Komputer", "candra@gmail.com", "Jalan Niaga 1"));
        dosenArray.add(new Dosen(R.drawable.ic_launcher_background, "000002 - Beni Mulia", "Sarjana Humaniora", "benii@gmail.com", "Jalan Wonosari"));
    }
}
