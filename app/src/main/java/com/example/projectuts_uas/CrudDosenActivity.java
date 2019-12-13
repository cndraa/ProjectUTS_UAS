package com.example.projectuts_uas;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
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

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import okio.BufferedSink;
import pl.aprilapps.easyphotopicker.EasyImage;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CrudDosenActivity extends AppCompatActivity {

    private EditText nama_dsn, nidn, almt_dsn, email_dsn, gelar_dsn, foto;
    private String id_dsn, stringImg;
    private boolean isUpdate;
    private Button browse, simpan;
    private ImageView setImg, imgCrud;


    static final int IMG_REQ = 777;
    private Bitmap bitmap;

    ProgressDialog progressDialog;

    //Request Code Digunakan Untuk Menentukan Permintaan dari User
    public static final int GALLERY_REQUEST_CODE = 58;
    public static final int FILE_ACCESS_REQUEST_CODE = 58;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud_dosen);

        this.setTitle("SI KRS - Hai [Nama Admin]");

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE
            }, FILE_ACCESS_REQUEST_CODE);
        }

        nama_dsn = (EditText) findViewById(R.id.txtEditNamaDsn);
        nidn = (EditText) findViewById(R.id.txtEditNidn);
        almt_dsn = (EditText) findViewById(R.id.txtEditAlmtDsn);
        email_dsn = (EditText) findViewById(R.id.txtEditEmailDsn);
        gelar_dsn = (EditText) findViewById(R.id.txtEditGelar);
        foto = (EditText) findViewById(R.id.txtEditFoto);

        browse = findViewById(R.id.btnBrowse);
        setImg = findViewById(R.id.imageMhs);
        imgCrud = findViewById(R.id.imageDosen);

        checkUpdate();

        simpan = findViewById(R.id.btnSimpan);
        if (isUpdate) {
            simpan.setText("Update");
        }

        browse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                String[] mimeTypes = {"image/jpeg", "image/png"};
                intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
                startActivityForResult(intent, GALLERY_REQUEST_CODE);
            }
        });

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                AlertDialog.Builder builder = new AlertDialog.Builder(CrudDosenActivity.this);

                if(nama_dsn.getText().toString().length()==0) {
                    //jika form Email belum di isi / masih kosong
                    nama_dsn.setError("Nama Dosen Harus Diis!");
                }else if(nidn.getText().toString().length()==0) {
                    //jika form Email belum di isi / masih kosong
                    nidn.setError("NIDN Dosen Harus Diis!");
                }else if(almt_dsn.getText().toString().length()==0) {
                    //jika form Email belum di isi / masih kosong
                    almt_dsn.setError("Alamat Dosen Harus Diis!");
                }else if(email_dsn.getText().toString().length()==0) {
                    //jika form Email belum di isi / masih kosong
                    email_dsn.setError("Email Dosen Harus Diis!");
                }else if(gelar_dsn.getText().toString().length()==0) {
                    //jika form Email belum di isi / masih kosong
                    gelar_dsn.setError("Gelar Dosen Harus Diis!");
                }/*else if(foto.getText().toString().length()==0) {
                    //jika form Email belum di isi / masih kosong
                    foto.setError("Foto Dosen Harus Diis!");
                }*/else {
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
                    stringImg,
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
                    stringImg,
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) { //Untuk memilih gambar yang aan dipilih
        if(resultCode == Activity.RESULT_OK)
            switch (requestCode){
                case GALLERY_REQUEST_CODE:
                    Uri pilihGambar = data.getData();
                imgCrud.setImageURI(pilihGambar);

                String[] filePathColumn = { MediaStore.Images.Media.DATA};

                Cursor cursor = getContentResolver().query(pilihGambar, filePathColumn, null,null, null);

                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);

                String imgDecodableString = cursor.getString(columnIndex);
                foto.setText(imgDecodableString);
                cursor.close();

                Bitmap bm = BitmapFactory.decodeFile(imgDecodableString);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bm.compress(Bitmap.CompressFormat.JPEG,100, baos);
                byte[] b = baos.toByteArray();

                stringImg = Base64.encodeToString(b, Base64.DEFAULT);
                break;
            }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case FILE_ACCESS_REQUEST_CODE:
                if(grantResults.length > 0 && grantResults[0] == PERMISSION_GRANTED){

                }
                break;
        }
    }
}
