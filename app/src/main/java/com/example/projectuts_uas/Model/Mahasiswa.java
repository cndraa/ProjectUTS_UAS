package com.example.projectuts_uas.Model;

public class Mahasiswa {

    private int image;
    private String nim;
    private String email;
    private String alamat;

    public Mahasiswa(int image, String nim, String email, String alamat){
        this.image = image;
        this.nim = nim;
        this.email = email;
        this.alamat = alamat;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
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
