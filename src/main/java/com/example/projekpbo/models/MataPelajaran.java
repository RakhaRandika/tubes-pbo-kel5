package com.example.projekpbo.models;


public class MataPelajaran {
    private String kodeMapel;
    private String hari;
    private String jam;
    private String nip;
    private String namaGuru;

    public MataPelajaran(String kodeMapel, String hari, String jam, String nip, String namaGuru) {
        this.kodeMapel = kodeMapel;
        this.hari = hari;
        this.jam = jam;
        this.nip = nip;
        this.namaGuru = namaGuru;
    }

    public String getKodeMapel() {
        return kodeMapel;
    }

    public void setKodeMapel(String kodeMapel) {
        this.kodeMapel = kodeMapel;
    }
    public String getHari() {
        return hari;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getNamaGuru() {
        return namaGuru;
    }

    public void setNamaGuru(String namaGuru) {
        this.namaGuru = namaGuru;
    }
}

