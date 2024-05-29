package com.example.projekpbo;

import com.example.projekpbo.models.Guru;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class DataGuru {

    @FXML
    private TextField searchTextField;

    @FXML
    private Button caributton;

    @FXML
    private Label searchLabel;

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
    private Button saveButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button deleteButton;

    @FXML
    private TableView<Guru> guruTableView;

    @FXML
    private TableColumn<Guru, String> columnNip;

    @FXML
    private TableColumn<Guru, String> columnNama;

    @FXML
    private TableColumn<Guru, String> columnTanggalLahir;

    @FXML
    private TableColumn<Guru, String> columnKelas;

    @FXML
    private TableColumn<Guru, String> columnMapel;

    private ObservableList<Guru> guruList;

    @FXML
    public void initialize() {
        guruList = FXCollections.observableArrayList();
        loadDataFromFile();
        guruTableView.setItems(guruList);

        columnNip.setCellValueFactory(cellData -> cellData.getValue().nipProperty());
        columnNama.setCellValueFactory(cellData -> cellData.getValue().namaProperty());
        columnTanggalLahir.setCellValueFactory(cellData -> cellData.getValue().tanggalLahirProperty());
        columnKelas.setCellValueFactory(cellData -> cellData.getValue().kelasProperty());
        columnMapel.setCellValueFactory(cellData -> cellData.getValue().mapelProperty());

        // Add listener for TableView selection
        guruTableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> populateInputFields(newValue)
        );
    }

    private void populateInputFields(Guru guru) {
        if (guru != null) {
            nipTextField.setText(guru.getNip());
            namaTextField.setText(guru.getNama());
            tanggalLahirTextField.setText(guru.getTanggalLahir());
            mengajarKelasTextField.setText(guru.getKelas());
            mataPelajaranTextField.setText(guru.getMapel());
        } else {
            clearInputFields();
        }
    }

    @FXML
    private void handleSaveButtonAction() {
        String nip = nipTextField.getText();
        String nama = namaTextField.getText();
        String tanggalLahir = tanggalLahirTextField.getText();
        String kelas = mengajarKelasTextField.getText();
        String mapel = mataPelajaranTextField.getText();

        if (validateInput(nip, nama, tanggalLahir, kelas, mapel)) {
            Guru guruBaru = new Guru(nama, kelas, tanggalLahir, nip, kelas, mapel);
            guruList.add(guruBaru);
            clearInputFields();
            saveDataToFile();
        } else {
            showAlert("Masukan Tidak Valid", "Silakan isi semua kolom dengan benar.");
        }
    }

    @FXML
    private void handleUpdateButtonAction() {
        Guru selectedGuru = guruTableView.getSelectionModel().getSelectedItem();

        if (selectedGuru != null) {
            // Memastikan bahwa semua kolom input diisi
            if ( isInputValid()) {
                selectedGuru.setNip(nipTextField.getText());
                selectedGuru.setNama(namaTextField.getText());
                selectedGuru.setTanggalLahir(tanggalLahirTextField.getText());
                selectedGuru.setKelas(mengajarKelasTextField.getText());
                selectedGuru.setMapel(mataPelajaranTextField.getText());
                guruTableView.refresh();
                saveDataToFile();
            } else {
                showAlert("Input Tidak Valid", "Pastikan semua kolom diisi dengan benar.");
            }
        } else {
            showAlert("Tidak ada Pilihan", "Silakan pilih guru di tabel.");
        }
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (nipTextField.getText() == null || nipTextField.getText().isEmpty()) {
            errorMessage += "NIP tidak boleh kosong!\n";
        }
        if (namaTextField.getText() == null || namaTextField.getText().isEmpty()) {
            errorMessage += "Nama tidak boleh kosong!\n";
        }
        if (tanggalLahirTextField.getText() == null || tanggalLahirTextField.getText().isEmpty()) {
            errorMessage += "Tanggal Lahir tidak boleh kosong!\n";
        }
        if (mengajarKelasTextField.getText() == null || mengajarKelasTextField.getText().isEmpty()) {
            errorMessage += "Kelas Mengajar tidak boleh kosong!\n";
        }
        if (mataPelajaranTextField.getText() == null || mataPelajaranTextField.getText().isEmpty()) {
            errorMessage += "Mata Pelajaran tidak boleh kosong!\n";
        }

        if (errorMessage.isEmpty()) {
            return true;
        } else {
            showAlert("Input Tidak Valid", errorMessage);
            return false;
        }
    }

    @FXML
    private void handleDeleteButtonAction() {
        Guru selectedGuru = guruTableView.getSelectionModel().getSelectedItem();
        if (selectedGuru != null) {
            guruList.remove(selectedGuru);
            clearInputFields();
            saveDataToFile();
        } else {
            showAlert("Tidak ada pilihan", "Silakan pilih guru di tabel.");
        }
    }

    @FXML
    private void handleCariButtonAction() {
        String keyword = searchTextField.getText().trim().toLowerCase();
        if (!keyword.isEmpty()) {
            List<Guru> searchResults = guruList.stream()
                    .filter(guru -> guru.getNama().toLowerCase().contains(keyword))
                    .collect(Collectors.toList());

            if (!searchResults.isEmpty()) {
                guruTableView.setItems(FXCollections.observableArrayList(searchResults));
            } else {
                showAlert("Tidak ditemukan", "Tidak ditemukan guru dengan nama yang diberikan.");
            }
        } else {
            showAlert("Tanpa Kata Kunci", "Silakan masukkan nama guru untuk mencari.");
        }
    }

    @FXML
    private void handleExitButtonAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menu-utama.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("MenuUtama");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean validateInput(String nip, String nama, String tanggalLahir, String kelas, String mapel) {
        return !nip.isEmpty() && !nama.isEmpty() && !tanggalLahir.isEmpty() && !kelas.isEmpty() && !mapel.isEmpty();
    }

    private void clearInputFields() {
        nipTextField.clear();
        namaTextField.clear();
        tanggalLahirTextField.clear();
        mengajarKelasTextField.clear();
        mataPelajaranTextField.clear();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void loadDataFromFile() {
        File file = new File("guru_data.csv");
        if (!file.exists()) {
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                Guru guru = new Guru(data[0], data[1], data[2], data[3], data[4], data[5]);
                guruList.add(guru);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveDataToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("guru_data.csv"))) {
            for (Guru guru : guruList) {
                String line = String.format("%s,%s,%s,%s,%s,%s",
                        guru.getNama(), guru.getJenisKelamin(), guru.getTanggalLahir(), guru.getNip(), guru.getKelas(), guru.getMapel());
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
