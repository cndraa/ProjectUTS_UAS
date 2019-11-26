package com.example.projectuts_uas.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Dosen {

    @SerializedName("foto")
    @Expose
    private String image;

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("nama")
    @Expose
    private String nama;

    @SerializedName("nidn")
    @Expose
    private String nidn;

    @SerializedName("gelar")
    @Expose
    private String gelar;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("alamat")
    @Expose
    private String alamat;


    @SerializedName("nim_progmob")
    @Expose
    private String nim;

    public Dosen(String image, String nidn, String gelar, String email, String alamat){ //PARAMETER 1
        this.image = image;
        this.nidn = nidn;
        this.gelar = gelar;
        this.email = email;
        this.alamat = alamat;
    }

    public Dosen(String image, String id, String nama, String nidn, String gelar, String email, String alamat, String nim) { //PARAMETER 2
        this.image = image;
        this.id = id;
        this.nama = nama;
        this.nidn = nidn;
        this.gelar = gelar;
        this.email = email;
        this.alamat = alamat;
        this.nim = nim;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNidn() {
        return nidn;
    }

    public void setNidn(String nidn) {
        this.nidn = nidn;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getGelar() {
        return gelar;
    }

    public void setGelar(String gelar) {
        this.gelar = gelar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }
}
