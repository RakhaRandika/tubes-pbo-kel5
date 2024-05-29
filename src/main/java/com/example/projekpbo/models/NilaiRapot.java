package com.example.projekpbo.models;

import javafx.beans.property.*;

public class NilaiRapot {
    private StringProperty nis;
    private StringProperty kodeMapel;
    private IntegerProperty nilaiTugas;
    private IntegerProperty nilaiUTS;
    private IntegerProperty nilaiUAS;
    private DoubleProperty rataRata;

    public NilaiRapot(String nis, String kodeMapel, int nilaiTugas, int nilaiUTS, int nilaiUAS) {
        this.nis = new SimpleStringProperty(nis);
        this.kodeMapel = new SimpleStringProperty(kodeMapel);
        this.nilaiTugas = new SimpleIntegerProperty(nilaiTugas);
        this.nilaiUTS = new SimpleIntegerProperty(nilaiUTS);
        this.nilaiUAS = new SimpleIntegerProperty(nilaiUAS);
        this.rataRata = new SimpleDoubleProperty((nilaiTugas + nilaiUTS + nilaiUAS) / 3.0);
    }

    public NilaiRapot(String part, String part1, String part2, String part3, String part4, int i, int i1, int i2) {
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

    public String getKodeMapel() {
        return kodeMapel.get();
    }

    public void setKodeMapel(String kodeMapel) {
        this.kodeMapel.set(kodeMapel);
    }

    public StringProperty kodeMapelProperty() {
        return kodeMapel;
    }

    public int getNilaiTugas() {
        return nilaiTugas.get();
    }

    public void setNilaiTugas(int nilaiTugas) {
        this.nilaiTugas.set(nilaiTugas);
        updateRataRata();
    }

    public IntegerProperty nilaiTugasProperty() {
        return nilaiTugas;
    }

    public int getNilaiUTS() {
        return nilaiUTS.get();
    }

    public void setNilaiUTS(int nilaiUTS) {
        this.nilaiUTS.set(nilaiUTS);
        updateRataRata();
    }

    public IntegerProperty nilaiUTSProperty() {
        return nilaiUTS;
    }

    public int getNilaiUAS() {
        return nilaiUAS.get();
    }

    public void setNilaiUAS(int nilaiUAS) {
        this.nilaiUAS.set(nilaiUAS);
        updateRataRata();
    }

    public IntegerProperty nilaiUASProperty() {
        return nilaiUAS;
    }

    public double getRataRata() {
        return rataRata.get();
    }

    public DoubleProperty rataRataProperty() {
        return rataRata;
    }

    private void updateRataRata() {
        this.rataRata.set((getNilaiTugas() + getNilaiUTS() + getNilaiUAS()) / 3.0);
    }
}
