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

import com.example.projectuts_uas.Model.DefaultResult;
import com.example.projectuts_uas.Network.GetDataService;
import com.example.projectuts_uas.Network.RetrofitClientInstance;

import java.io.ByteArrayOutputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class CrudMhsActivity extends AppCompatActivity {

    private EditText nama_mhs, nim, email, alamat, foto;
    private String id_mhs, stringImg;
    private boolean isUpdate;
    private Button browse;
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
        setContentView(R.layout.activity_crud_mhs);

        this.setTitle("SI KRS - Hai [Nama MHS]");

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE
            }, FILE_ACCESS_REQUEST_CODE);
        }

        nama_mhs = (EditText) findViewById(R.id.txtNmaMhs);
        nim = (EditText) findViewById(R.id.txtEditNim);
        email = (EditText) findViewById(R.id.txtEditEmail2);
        alamat = (EditText) findViewById(R.id.txtEditAlmt2);
        foto = (EditText) findViewById(R.id.txtEditNamaDsn);

        browse = findViewById(R.id.btnBrowseMhs);
        setImg = findViewById(R.id.imageMhs2);
        imgCrud = findViewById(R.id.imageMhsCrud);

        checkUpdate();

        Button simpanMhs = findViewById(R.id.btnSimpanMhs);
        if (isUpdate) {
            simpanMhs.setText("Update");
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

        simpanMhs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CrudMhsActivity.this);

                if(nama_mhs.getText().toString().length()==0) {
                    //jika form Email belum di isi / masih kosong
                    nama_mhs.setError("Nama Mahasiswa Harus Diis!");
                }else if(nim.getText().toString().length()==0) {
                    //jika form Email belum di isi / masih kosong
                    nim.setError("NIM Mahasiswa Harus Diis!");
                }else if(alamat.getText().toString().length()==0) {
                    //jika form Email belum di isi / masih kosong
                    alamat.setError("Alamat Mahasiswa Harus Diis!");
                }else if(email.getText().toString().length()==0) {
                    //jika form Email belum di isi / masih kosong
                    email.setError("Email Mahasiswa Harus Diis!");
                }/*else if(foto.getText().toString().length()==0) {
                    //jika form Email belum di isi / masih kosong
                    foto.setError("Foto Mahasiswa Harus Diis!");
                }*/else{
                    builder.setMessage("Apakah anda yakin untuk simpan?").setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(CrudMhsActivity.this, "Tidak Jadi Simpan!", Toast.LENGTH_SHORT).show();
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
        id_mhs = extras.getString("id_mhs");
        nama_mhs.setText(extras.getString("nama"));
        nim.setText(extras.getString("nim"));
        alamat.setText(extras.getString("alamat"));
        email.setText(extras.getString("email"));
        foto.setText(extras.getString("foto"));
    }

    private void add_data() {
        if (!isUpdate) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Harap Menunggu...");
            progressDialog.show();

            GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
            Call<DefaultResult> call = service.insert_mhs(
                    nama_mhs.getText().toString(),
                    nim.getText().toString(),
                    alamat.getText().toString(),
                    email.getText().toString(),
                    stringImg,
                    "72170139");

            call.enqueue(new Callback<DefaultResult>() {
                @Override
                public void onResponse(Call<DefaultResult> call, Response<DefaultResult> response) {
                    progressDialog.dismiss();
                    Toast.makeText(CrudMhsActivity.this, "Berhasil Tersimpan!", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(CrudMhsActivity.this, RecyclerMahasiswaActivity.class); //intent untuk berpindah Helpactivity
                    startActivity(intent);
                    finish();
                }

                @Override
                public void onFailure(Call<DefaultResult> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(CrudMhsActivity.this, "Gagal Menambah Data!", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Harap Menunggu...");
            progressDialog.show();

            GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
            Call<DefaultResult> call = service.update_mhs(
                    id_mhs,
                    nama_mhs.getText().toString(),
                    nim.getText().toString(),
                    alamat.getText().toString(),
                    email.getText().toString(),
                    stringImg,
                    "72170139");

            call.enqueue(new Callback<DefaultResult>() {
                @Override
                public void onResponse(Call<DefaultResult> call, Response<DefaultResult> response) {
                    progressDialog.dismiss();
                    Toast.makeText(CrudMhsActivity.this, "Berhasil Tersimpan!", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(CrudMhsActivity.this, RecyclerMahasiswaActivity.class); //intent untuk berpindah Helpactivity
                    startActivity(intent);
                    finish();
                }

                @Override
                public void onFailure(Call<DefaultResult> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(CrudMhsActivity.this, "Gagal Menambah Data!", Toast.LENGTH_SHORT).show();
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
