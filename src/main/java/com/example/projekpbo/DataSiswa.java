package com.example.projekpbo;

import com.example.projekpbo.models.Siswa;
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

public class  DataSiswa {

    @FXML
    private TextField searchTextField;

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
    private TextField nisTextField;

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
    private TableColumn<Siswa, String> columnNama;

    @FXML
    private TableColumn<Siswa, String> columnJenisKelamin;

    @FXML
    private TableColumn<Siswa, String> columnTempatLahir;

    @FXML
    private TableColumn<Siswa, String> columnUmur;

    @FXML
    private TableColumn<Siswa, String> columnAngkatan;

    @FXML
    private TableColumn<Siswa, String> columnNis;

    private ObservableList<Siswa> siswaList;

    private Siswa selectedSiswa;

    @FXML
    public void initialize() {
        siswaList = FXCollections.observableArrayList();
        loadDataFromFile();
        siswaTableView.setItems(siswaList);

        columnNama.setCellValueFactory(cellData -> cellData.getValue().namaProperty());
        columnJenisKelamin.setCellValueFactory(cellData -> cellData.getValue().jenisKelaminProperty());
        columnTempatLahir.setCellValueFactory(cellData -> cellData.getValue().tanggalLahirProperty());
        columnUmur.setCellValueFactory(cellData -> cellData.getValue().umurProperty().asObject().asString());
        columnAngkatan.setCellValueFactory(cellData -> cellData.getValue().angkatanProperty());
        columnNis.setCellValueFactory(cellData -> cellData.getValue().nisProperty());

        jenisKelaminComboBox.getItems().addAll("Laki-Laki", "Perempuan" ,"boti");

        siswaTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                selectedSiswa = newSelection;
                populateInputFields(newSelection);
            } else {
                clearInputFields();
            }
        });

        // Add listeners to ensure only numeric input for umur, angkatan, and nis fields
        addNumericValidation(umurTextField);
        addNumericValidation(angkatanTextField);
        addNumericValidation(nisTextField);
    }

    @FXML
    private void handleSaveButtonAction() {
        String nama = namaSiswaTextField.getText();
        String jenisKelamin = jenisKelaminComboBox.getValue();
        String tempatLahir = tempatLahirTextField.getText();
        String umur = umurTextField.getText();
        String angkatan = angkatanTextField.getText();
        String nis = nisTextField.getText();

        if (validateInput(nama, jenisKelamin, tempatLahir, umur, angkatan, nis)) {
            Siswa siswaBaru = new Siswa(nama, jenisKelamin, tempatLahir, Integer.parseInt(umur), nis, angkatan);
            siswaList.add(siswaBaru);
            saveDataToFile();
            siswaTableView.refresh(); // Refresh table to show new data
            clearInputFields();
        } else {
            showAlert("Masukan Tidak Valid", "Silakan isi semua kolom dengan benar.");
        }
    }

    @FXML
    private void handleUpdateButtonAction() {
        if (selectedSiswa != null) {
            String nama = namaSiswaTextField.getText();
            String jenisKelamin = jenisKelaminComboBox.getValue();
            String tempatLahir = tempatLahirTextField.getText();
            String umur = umurTextField.getText();
            String angkatan = angkatanTextField.getText();
            String nis = nisTextField.getText();

            if (validateInput(nama, jenisKelamin, tempatLahir, umur, angkatan, nis)) {
                selectedSiswa.setNama(nama);
                selectedSiswa.setJenisKelamin(jenisKelamin);
                selectedSiswa.setTanggalLahir(tempatLahir);
                selectedSiswa.setUmur(Integer.parseInt(umur));
                selectedSiswa.setNis(nis);
                selectedSiswa.setAngkatan(angkatan);
                siswaTableView.refresh();
                saveDataToFile();
                clearInputFields();
            } else {
                showAlert("Masukan Tidak Valid", "Silakan isi semua kolom dengan benar.");
            }
        } else {
            showAlert("Tidak ada pilihan", "Silakan pilih siswa yang akan diperbarui.");
        }
    }

    @FXML
    private void handleCariButtonAction() {
        String keyword = searchTextField.getText().trim().toLowerCase();
        if (!keyword.isEmpty()) {
            List<Siswa> searchResults = siswaList.stream()
                    .filter(siswa -> siswa.getNama().toLowerCase().contains(keyword))
                    .collect(Collectors.toList());

            if (!searchResults.isEmpty()) {
                siswaTableView.setItems(FXCollections.observableArrayList(searchResults));
            } else {
                showAlert("Tidak ditemukan", "Tidak ditemukan siswa dengan nama yang diberikan.");
            }
        } else {
            showAlert("Tanpa Kata Kunci", "Silakan masukkan nama siswa untuk mencari.");
        }
    }

    @FXML
    private void handleDeleteButtonAction() {
        if (selectedSiswa != null) {
            siswaList.remove(selectedSiswa);
            saveDataToFile();
            clearInputFields();
            siswaTableView.getSelectionModel().clearSelection();
            siswaTableView.refresh();
            selectedSiswa = null;
        } else {
            showAlert("Tidak ada pilihan", "Silakan pilih siswa yang akan dihapus.");
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

    private boolean validateInput(String nama, String jenisKelamin, String tempatLahir, String umur, String angkatan, String nis) {
        if (nama.isEmpty() || jenisKelamin == null || tempatLahir.isEmpty() || umur.isEmpty() || angkatan.isEmpty() || nis.isEmpty()) {
            showAlert("Masukan Tidak Valid", "Silakan isi semua kolom.");
            return false;
        }
        if (!isNumeric(umur) || !isNumeric(angkatan) || !isNumeric(nis)) {
            showAlert("Masukan Tidak Valid", "Kolom umur, angkatan, dan NIS harus berupa angka.");
            return false;
        }
        return true;
    }

    private void clearInputFields() {
        namaSiswaTextField.clear();
        jenisKelaminComboBox.getSelectionModel().clearSelection();
        tempatLahirTextField.clear();
        umurTextField.clear();
        angkatanTextField.clear();
        nisTextField.clear();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void populateInputFields(Siswa siswa) {
        namaSiswaTextField.setText(siswa.getNama());
        jenisKelaminComboBox.setValue(siswa.getJenisKelamin());
        tempatLahirTextField.setText(siswa.getTanggalLahir());
        umurTextField.setText(String.valueOf(siswa.getUmur()));
        angkatanTextField.setText(siswa.getAngkatan());
        nisTextField.setText(siswa.getNis());
    }

    private void loadDataFromFile() {
        File file = new File("siswa_data.csv");
        if (!file.exists()) {
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 6 && isNumeric(data[3])) {
                    Siswa siswa = new Siswa(data[0], data[1], data[2], Integer.parseInt(data[3]), data[4], data[5]);
                    siswaList.add(siswa);
                } else {
                    System.err.println("Format data tidak valid: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveDataToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("siswa_data.csv"))) {
            for (Siswa siswa : siswaList) {
                String line = String.format("%s,%s,%s,%d,%s,%s",
                        siswa.getNama(), siswa.getJenisKelamin(), siswa.getTanggalLahir(), siswa.getUmur(), siswa.getNis(), siswa.getAngkatan());
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addNumericValidation(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                textField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }

    private boolean isNumeric(String str) {
        return str != null && str.matches("\\d+");
    }
}
