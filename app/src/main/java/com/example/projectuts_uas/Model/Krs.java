package com.example.projectuts_uas.Model;

public class Krs {

    private String kode1;
    private String matkul1;
    private String hari1;
    private String sesi1;
    private String sks1;
    private String dosbing;
    private String jmlMhs;

    public Krs(String kode, String matkul, String hari, String sesi, String sks, String dosbing, String jmlMhs){
        this.kode1 = kode;
        this.matkul1 = matkul;
        this.hari1 = hari;
        this.sesi1 = sesi;
        this.sks1 = sks;
        this.dosbing = dosbing;
        this.jmlMhs = jmlMhs;
    }

    public String getKode1() {
        return kode1;
    }

    public void setKode1(String kode1) {
        this.kode1 = kode1;
    }

    public String getMatkul1() {
        return matkul1;
    }

    public void setMatkul1(String matkul1) {
        this.matkul1 = matkul1;
    }

    public String getHari1() {
        return hari1;
    }

    public void setHari1(String hari1) {
        this.hari1 = hari1;
    }

    public String getSesi1() {
        return sesi1;
    }

    public void setSesi1(String sesi1) {
        this.sesi1 = sesi1;
    }

    public String getSks1() {
        return sks1;
    }

    public void setSks1(String sks1) {
        this.sks1 = sks1;
    }

    public String getDosbing() {
        return dosbing;
    }

    public void setDosbing(String dosbing) {
        this.dosbing = dosbing;
    }

    public String getJmlMhs() {
        return jmlMhs;
    }

    public void setJmlMhs(String jmlMhs) {
        this.jmlMhs = jmlMhs;
    }
}
