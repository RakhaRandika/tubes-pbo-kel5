package com.example.projekpbo.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Guru extends Civitas {
    private StringProperty nip;
    private StringProperty kelas;
    private StringProperty mapel;

    public Guru(String nama, String jenisKelamin, String tanggalLahir, String nip, String kelas, String mapel) {
        super(nama, jenisKelamin, tanggalLahir);
        this.nip = new SimpleStringProperty(nip);
        this.kelas = new SimpleStringProperty(kelas);
        this.mapel = new SimpleStringProperty(mapel);
    }


    public String getNip() {
        return nip.get();
    }

    public void setNip(String nip) {
        this.nip.set(nip);
    }

    public StringProperty nipProperty() {
        return nip;
    }

    public String getKelas() {
        return kelas.get();
    }

    public void setKelas(String kelas) {
        this.kelas.set(kelas);
    }

    public StringProperty kelasProperty() {
        return kelas;
    }

    public String getMapel() {
        return mapel.get();
    }

    public void setMapel(String mapel) {
        this.mapel.set(mapel);
    }

    public StringProperty mapelProperty() {
        return mapel;
    }
}
