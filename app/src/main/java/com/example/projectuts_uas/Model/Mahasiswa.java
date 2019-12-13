package com.example.projectuts_uas.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Mahasiswa {
    @SerializedName("foto")
    @Expose
    private String image;
    @SerializedName("nim")
    @Expose
    private String nim;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("alamat")
    @Expose
    private String alamat;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("nim_progmob")
    @Expose
    private String nim_prog;

    /*public Mahasiswa(String image, String nim, String email, String alamat){
        this.image = image;
        this.nim = nim;
        this.email = email;
        this.alamat = alamat;
    }*/

    public Mahasiswa(String image, String nim, String email, String nama, String alamat, String id, String nim_prog) {
        this.image = image;
        this.nim = nim;
        this.email = email;
        this.nama = nama;
        this.alamat = alamat;
        this.id = id;
        this.nim_prog = nim_prog;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNim_prog() {
        return nim_prog;
    }

    public void setNim_prog(String nim_prog) {
        this.nim_prog = nim_prog;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
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
}
