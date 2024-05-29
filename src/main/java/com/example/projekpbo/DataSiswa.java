package com.example.projekpbo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class DataSiswa {
    @FXML
    public static void  initialize() {
        MenuUtama.setDataSiswaController();
    }

    @FXML
    private ComboBox<String> jenisKelaminComboBox;

    @FXML
    private TextField namaSiswaTextField;

    // Definisikan variabel lain sesuai kebutuhan

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
    private TableView<?> siswaTableView;

    // Tambahkan logika perilaku GUI di sini, misalnya event handlers
}
