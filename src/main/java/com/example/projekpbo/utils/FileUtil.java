package com.example.projekpbo.utils;

import com.example.projekpbo.models.Guru ;
import com.example.projekpbo.models.Siswa ;
import com.example.projekpbo.models.MataPelajaran ;
import com.example.projekpbo.models.NilaiRapot ;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

    private static final String GURU_FILE = "guru_data.csv";
    private static final String SISWA_FILE = "siswa_data.csv";
    private static final String MAPEL_FILE = "mata_pelajaran.csv";
    private static final String NILAI_FILE = "nilai_rapot.csv";

    public static void simpanGuru(Guru guru) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(GURU_FILE, true))) {
            writer.write(guru.getNama() + "," + guru.getJenisKelamin() + "," + guru.getTanggalLahir() + "," + guru.getNip() + "," + guru.getKelas() + "," + guru.getMapel());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Guru> bacaGurus() {
        List<Guru> gurus = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(GURU_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    Guru guru = new Guru(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5]);
                    gurus.add(guru);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return gurus;
    }

    public static void simpanSiswa(Siswa siswa) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SISWA_FILE, true))) {
            writer.write(siswa.getNama() + "," + siswa.getJenisKelamin() + "," + siswa.getTanggalLahir() + "," + siswa.getUmur() + "," + siswa.getNis() + "," + siswa.getAngkatan());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Siswa> bacaSiswas() {
        List<Siswa> siswas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(SISWA_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    Siswa siswa = new Siswa(parts[0], parts[1], parts[2], Integer.parseInt(parts[3]), parts[4], parts[5]);
                    siswas.add(siswa);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return siswas;
    }

    public static void simpanMataPelajaran(MataPelajaran mapel) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(MAPEL_FILE, true))) {
            writer.write(mapel.getKodeMapel() + "," + mapel.getHari() + "," + mapel.getJam() + "," + mapel.getNip() + "," + mapel.getNamaGuru());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<MataPelajaran> bacaMataPelajaran() {
        List<MataPelajaran> mapels = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(MAPEL_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    MataPelajaran mapel = new MataPelajaran(parts[0], parts[1], parts[2], parts[3], parts[4]);
                    mapels.add(mapel);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mapels;
    }

    public static void simpanNilaiRapot(NilaiRapot nilaiRapot) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(NILAI_FILE, true))) {
            writer.write(nilaiRapot.getNis() + "," + nilaiRapot.getKodeMapel() + "," + nilaiRapot.getNilaiTugas() + "," + nilaiRapot.getNilaiUTS() + "," + nilaiRapot.getNilaiUAS() + "," + nilaiRapot.getRataRata());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<NilaiRapot> bacaNilaiRapot() {
        List<NilaiRapot> nilaiRapots = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(NILAI_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    NilaiRapot nilaiRapot = new NilaiRapot(parts[0], parts[1], parts[2], parts[3], parts[4], Integer.parseInt(parts[5]), Integer.parseInt(parts[6]), Integer.parseInt(parts[7]));
                    nilaiRapots.add(nilaiRapot);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nilaiRapots;
    }
}

