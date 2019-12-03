package com.example.projectuts_uas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.projectuts_uas.Model.Dosen;
import com.example.projectuts_uas.Network.GetDataService;
import com.example.projectuts_uas.Network.RetrofitClientInstance;
import com.example.projectuts_uas.Model.DefaultResult;
import com.example.projectuts_uas.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;

import pl.aprilapps.easyphotopicker.EasyImage;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CrudDosenActivity extends AppCompatActivity {

    private EditText nama_dsn, nidn, almt_dsn, email_dsn, gelar_dsn, foto;
    private String id_dsn;
    private boolean isUpdate;
    private Button browse, simpan;
    private ImageView setImg;

    static final int IMG_REQ = 777;
    private Bitmap bitmap;

    ProgressDialog progressDialog;

    //Request Code Digunakan Untuk Menentukan Permintaan dari User
    public static final int REQUEST_CODE_CAMERA = 001;
    public static final int REQUEST_CODE_GALLERY = 002;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud_dosen);

        this.setTitle("SI KRS - Hai [Nama Admin]");

        nama_dsn = (EditText) findViewById(R.id.txtEditNamaDsn);
        nidn = (EditText) findViewById(R.id.txtEditNidn);
        almt_dsn = (EditText) findViewById(R.id.txtEditAlmtDsn);
        email_dsn = (EditText) findViewById(R.id.txtEditEmailDsn);
        gelar_dsn = (EditText) findViewById(R.id.txtEditGelar);
        foto = (EditText) findViewById(R.id.txtEditFoto);

        browse = findViewById(R.id.btnBrowse);
        setImg = findViewById(R.id.imageMhs);

        checkUpdate();

        simpan = findViewById(R.id.btnSimpan);
        if (isUpdate) {
            simpan.setText("Update");
        }

        browse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setRequestImage();
            }
        });

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CrudDosenActivity.this);

                builder.setMessage("Apakah anda yakin untuk simpan?")
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(CrudDosenActivity.this, "Tidak Jadi Simpan!", Toast.LENGTH_SHORT).show();
                            }
                        }).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        add_data();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

    }


    void checkUpdate() {
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            return;
        }

        isUpdate = extras.getBoolean("is_update");
        id_dsn = extras.getString("id_dosen");
        nama_dsn.setText(extras.getString("nama_dosen"));
        nidn.setText(extras.getString("nidn"));
        almt_dsn.setText(extras.getString("alamat"));
        email_dsn.setText(extras.getString("email"));
        gelar_dsn.setText(extras.getString("gelar"));
        foto.setText(extras.getString("foto"));
    }

    private void add_data() {
        if (!isUpdate) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Harap Menunggu...");
            progressDialog.show();

            GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
            Call<DefaultResult> call = service.insert_dosen(
                    nama_dsn.getText().toString(),
                    nidn.getText().toString(),
                    almt_dsn.getText().toString(),
                    email_dsn.getText().toString(),
                    gelar_dsn.getText().toString(),
                    "https://picsum.photos/200", "72170139");

            call.enqueue(new Callback<DefaultResult>() {
                @Override
                public void onResponse(Call<DefaultResult> call, Response<DefaultResult> response) {
                    progressDialog.dismiss();
                    Toast.makeText(CrudDosenActivity.this, "Berhasil Tersimpan!", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(CrudDosenActivity.this, RecyclerDosenActivity.class); //intent untuk berpindah Helpactivity
                    startActivity(intent);
                    finish();
                }

                @Override
                public void onFailure(Call<DefaultResult> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(CrudDosenActivity.this, "Gagal Menambah Data!", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Harap Menunggu...");
            progressDialog.show();

            GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
            Call<DefaultResult> call = service.update_dosen(
                    id_dsn,
                    nama_dsn.getText().toString(),
                    nidn.getText().toString(),
                    almt_dsn.getText().toString(),
                    email_dsn.getText().toString(),
                    gelar_dsn.getText().toString(),
                    foto.getText().toString(),
                    "72170139");

            call.enqueue(new Callback<DefaultResult>() {
                @Override
                public void onResponse(Call<DefaultResult> call, Response<DefaultResult> response) {
                    progressDialog.dismiss();
                    Toast.makeText(CrudDosenActivity.this, "Berhasil Tersimpan!", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(CrudDosenActivity.this, RecyclerDosenActivity.class); //intent untuk berpindah Helpactivity
                    startActivity(intent);
                    finish();
                }

                @Override
                public void onFailure(Call<DefaultResult> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(CrudDosenActivity.this, "Gagal Menambah Data!", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }


    //Method Ini Digunakan Untuk Membuka Image dari Galeri atau Kamera
    private void setRequestImage(){
        CharSequence[] item = {"Kamera", "Galeri"};
        AlertDialog.Builder request = new AlertDialog.Builder(this)
                .setTitle("Add Image")
                .setItems(item, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0:
                                //Membuka Kamera Untuk Mengambil Gambar
                                EasyImage.openCamera(CrudDosenActivity.this, REQUEST_CODE_CAMERA);
                                break;
                            case 1:
                                //Membuaka Galeri Untuk Mengambil Gambar
                                EasyImage.openGallery(CrudDosenActivity.this, REQUEST_CODE_GALLERY);
                                break;
                        }
                    }
                });
        request.create();
        request.show();
    }

    //Method Ini Digunakan Untuk Menapatkan Hasil pada Activity, dari Proses Yang kita buat sebelumnya
    //Dan Mendapatkan Hasil File Photo dari Galeri atau Kamera
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        EasyImage.handleActivityResult(requestCode, resultCode, data, this, new EasyImage.Callbacks() {
            @Override
            public void onImagePickerError(Exception e, EasyImage.ImageSource source, int type) {

            }

            @Override
            public void onImagePicked(File imageFile, EasyImage.ImageSource source, int type) {
                //Method Ini Digunakan Untuk Menghandle Image
                switch (type) {
                    case REQUEST_CODE_CAMERA:
                        Glide.with(CrudDosenActivity.this)
                                .load(imageFile)
                                .centerCrop()
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into(setImg);
                        break;

                    case REQUEST_CODE_GALLERY:
                        Glide.with(CrudDosenActivity.this)
                                .load(imageFile)
                                .centerCrop()
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into(setImg);
                        break;
                }
            }

            @Override
            public void onCanceled(EasyImage.ImageSource source, int type) {
                //Batalkan penanganan, Anda mungkin ingin menghapus foto yang diambil jika dibatalkan
            }
        });
    }
}
