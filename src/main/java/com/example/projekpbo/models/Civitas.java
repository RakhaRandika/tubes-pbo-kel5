package com.example.projekpbo.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Civitas {
    private StringProperty nama;
    private StringProperty jenisKelamin;
    private StringProperty tanggalLahir;

    public Civitas(String nama, String jenisKelamin, String tanggalLahir) {
        this.nama = new SimpleStringProperty(nama);
        this.jenisKelamin = new SimpleStringProperty(jenisKelamin);
        this.tanggalLahir = new SimpleStringProperty(tanggalLahir);
    }

    public String getNama() {
        return nama.get();
    }

    public void setNama(String nama) {
        this.nama.set(nama);
    }

    public StringProperty namaProperty() {
        return nama;
    }

    public String getJenisKelamin() {
        return jenisKelamin.get();
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin.set(jenisKelamin);
    }

    public StringProperty jenisKelaminProperty() {
        return jenisKelamin;
    }

    public String getTanggalLahir() {
        return tanggalLahir.get();
    }

    public void setTanggalLahir(String tanggalLahir) {
        this.tanggalLahir.set(tanggalLahir);
    }

    public StringProperty tanggalLahirProperty() {
        return tanggalLahir;
    }
}
