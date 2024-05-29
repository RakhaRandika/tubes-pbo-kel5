package com.example.projekpbo;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class DataGuru {

    @FXML
    private Button keluarButton;

    @FXML
    private TextField nipTextField;

    @FXML
    private TextField namaTextField;

    @FXML
    private TextField tanggalLahirTextField;

    @FXML
    private TextField mengajarKelasTextField;

    @FXML
    private TextField mataPelajaranTextField;

    @FXML
    public void initialize() {
        // Inisialisasi combo box, text field, dan lain-lain
    }

    @FXML
    private void handleKeluarButtonAction(ActionEvent event) {
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
}
