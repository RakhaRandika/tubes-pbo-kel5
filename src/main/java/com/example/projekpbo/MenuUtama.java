package com.example.projekpbo;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class MenuUtama {

    @FXML
    private ImageView imgDataSiswa;

    @FXML
    private ImageView imgDataGuru;

    @FXML
    private ImageView imgMataPelajaran;

    @FXML
    private ImageView imgHasilRapot;

    @FXML
    private Label lblMenuUtama;

    public static void setDataSiswaController() {
    }

    public void initialize() {
        File gambarsiswa = new File("src/students.png");
        imgDataSiswa.setImage(new Image(gambarsiswa.toURI().toString()));
        File file = new File("src/classroom.png");
        imgDataGuru.setImage(new Image(file.toURI().toString()));
        File pelajaran = new File("src/learn.png");
        imgMataPelajaran.setImage(new Image(pelajaran.toURI().toString()));
        File rapot = new File("src/graduation.png");
        imgHasilRapot.setImage(new Image(rapot.toURI().toString()));
//        lblMenuUtama.setText("menu-utama");
    }

    @FXML
    public void buttonDiKlik(){
        System.out.println("button di klik");
    }
    // Tambahkan metode atau logika lain sesuai kebutuhan aplikasi Anda

    @FXML
    public void navigateToSiswa(Event event){
        try {
            // Load file FXML untuk halaman utama
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("halaman-siswa.fxml"));
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

    private DataSiswa dataSiswaController;
    public void setDataSiswaController(DataSiswa dataSiswaController) {
        this.dataSiswaController = dataSiswaController;
    }



}
