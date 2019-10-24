package com.example.projectuts_uas.Model;

public class KrsMhs {

    private String kode2;
    private String matkul2;
    private String hari2;
    private String sesi2;
    private String dosbing2;
    private String jmlMhs2;

    public KrsMhs(String kode, String matkul, String hari, String sesi, String sks, String dosbing, String jmlMhs){
        this.kode2 = kode;
        this.matkul2 = matkul;
        this.hari2 = hari;
        this.sesi2 = sesi;
        this.dosbing2 = dosbing;
        this.jmlMhs2 = jmlMhs;
    }

    public String getKode2() {
        return kode2;
    }

    public void setKode2(String kode2) {
        this.kode2 = kode2;
    }

    public String getMatkul2() {
        return matkul2;
    }

    public void setMatkul2(String matkul2) {
        this.matkul2 = matkul2;
    }

    public String getHari2() {
        return hari2;
    }

    public void setHari2(String hari2) {
        this.hari2 = hari2;
    }

    public String getSesi2() {
        return sesi2;
    }

    public void setSesi2(String sesi2) {
        this.sesi2 = sesi2;
    }

    public String getDosbing2() {
        return dosbing2;
    }

    public void setDosbing2(String dosbing2) {
        this.dosbing2 = dosbing2;
    }

    public String getJmlMhs2() {
        return jmlMhs2;
    }

    public void setJmlMhs2(String jmlMhs2) {
        this.jmlMhs2 = jmlMhs2;
    }
}
