package com.example.projekpbo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class DataRapot {
    @FXML
    private TextField textFieldMataPelajaran;

    @FXML
    private TextField textFieldKelas;

    @FXML
    private TextField textFieldNIP;

    @FXML
    private TextField textFieldNamaGuru;

    @FXML
    private TextField textFieldHari;

    @FXML
    private TextField textFieldJam;

    @FXML
    private Button buttonSave;

    @FXML
    private Button buttonUpdate;

    @FXML
    private Button buttonDelete;

    @FXML
    private TableView tableView;

    @FXML
    private TextField textFieldSearch;

    @FXML
    private Button buttonSearch;


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
}