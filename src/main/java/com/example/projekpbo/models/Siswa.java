package com.example.projekpbo.models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class Siswa extends Civitas {
    private IntegerProperty umur;
    private StringProperty nis;
    private StringProperty angkatan;

    public Siswa(String nama, String jenisKelamin, String tanggalLahir, int umur, String nis, String angkatan) {
        super(nama, jenisKelamin, tanggalLahir);
        this.umur = new SimpleIntegerProperty(umur);
        this.nis = new SimpleStringProperty(nis);
        this.angkatan = new SimpleStringProperty(angkatan);
    }

    public int getUmur() {
        return umur.get();
    }

    public void setUmur(int umur) {
        this.umur.set(umur);
    }

    public IntegerProperty umurProperty() {
        return umur;
    }

    public String getNis() {
        return nis.get();
    }

    public void setNis(String nis) {
        this.nis.set(nis);
    }

    public StringProperty nisProperty() {
        return nis;
    }

    public String getAngkatan() {
        return angkatan.get();
    }

    public void setAngkatan(String angkatan) {
        this.angkatan.set(angkatan);
    }

    public StringProperty angkatanProperty() {
        return angkatan;
    }

    // Convert to CSV format
    public String toCSV() {
        return String.format("%s,%s,%s,%d,%s,%s",getNis(),  getNama(), getJenisKelamin(), getTanggalLahir(), getUmur(), getAngkatan());
    }

    // Create Siswa from CSV format
    public static Siswa fromCSV(String csv) {
        String[] parts = csv.split(",");
        String nama = parts[0];
        String jenisKelamin = parts[1];
        String tanggalLahir = parts[2];
        int umur = Integer.parseInt(parts[3]);
        String nis = parts[4];
        String angkatan = parts[5];
        return new Siswa(nama, jenisKelamin, tanggalLahir, umur, nis, angkatan);
    }
}
