package com.example.projectuts_uas.Model;

public class Dosen {

    private int image;
    private String nidn;
    private String gelar;
    private String email;
    private String alamat;

    public Dosen(int image, String nidn, String gelar, String email, String alamat){
        this.image = image;
        this.nidn = nidn;
        this.gelar = gelar;
        this.email = email;
        this.alamat = alamat;
    }

    public String getNidn() {
        return nidn;
    }

    public void setNidn(String nidn) {
        this.nidn = nidn;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
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
}
