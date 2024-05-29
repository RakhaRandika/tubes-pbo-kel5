package com.example.projekpbo;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import java.io.IOException;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DataSiswa {
    @FXML
    private ComboBox<String> jenisKelaminComboBox;

    @FXML
    private TextField namaSiswaTextField;

    @FXML
    private TextField tempatLahirTextField;

    @FXML
    private TextField umurTextField;

    @FXML
    private TextField angkatanTextField;

    @FXML
    private Button saveButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button exitButton;

    @FXML
    private Button cariButton;

    @FXML
    private TableView<Siswa> siswaTableView;

    @FXML
    private TableColumn<Siswa, String> columnC1;

    @FXML
    private TableColumn<Siswa, String> columnC2;

    @FXML
    private TableColumn<Siswa, String> columnC3;

    @FXML
    private TableColumn<Siswa, String> columnC4;

    @FXML
    private TableColumn<Siswa, String> columnC5;

    @FXML
    private TableColumn<Siswa, String> columnC6;

    private ObservableList<Siswa> siswaList;

    @FXML
    public void initialize() {
        // Inisialisasi combo box, text field, dan lain-lain
    }
    @FXML
    private void handleExitButtonAction(ActionEvent event) {
        try {
            // Load file FXML untuk halaman utama
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menu-utama.fxml"));
            Parent root = fxmlLoader.load();

            // Mendapatkan stage dari event
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Mengatur scene utama dengan halaman utama dan menampilkan stage
            stage.setScene(new Scene(root));
            stage.setTitle("MenuUtama");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void handleSaveButtonAction() {
        String nama = namaSiswaTextField.getText();
        String jenisKelamin = jenisKelaminComboBox.getValue();
        String tempatLahir = tempatLahirTextField.getText();
        String umur = umurTextField.getText();
        String angkatan = angkatanTextField.getText();

        // Membuat objek Siswa baru
        Siswa siswaBaru = new Siswa(nama, jenisKelamin, tempatLahir, umur, angkatan);

        // Menambahkan objek siswaBaru ke dalam list
        siswaList.add(siswaBaru);

        // Membersihkan input fields setelah data disimpan
        clearInputFields();
    }

    private void clearInputFields() {
        namaSiswaTextField.clear();
        jenisKelaminComboBox.getSelectionModel().clearSelection();
        tempatLahirTextField.clear();
        umurTextField.clear();
        angkatanTextField.clear();
    }

    // Method-method lain seperti update, delete, exit, dan cari

    // Kelas Siswa
    public static class Siswa {
        private String nama;
        private String jenisKelamin;
        private String tempatLahir;
        private String umur;
        private String angkatan;

        public Siswa(String nama, String jenisKelamin, String tempatLahir, String umur, String angkatan) {
            this.nama = nama;
            this.jenisKelamin = jenisKelamin;
            this.tempatLahir = tempatLahir;
            this.umur = umur;
            this.angkatan = angkatan;
        }
    }
}