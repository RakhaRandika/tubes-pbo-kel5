package com.example.projekpbo;

import com.example.projekpbo.models.MataPelajaran;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class DataMataPelajaran {

    @FXML
    private TextField kodeMapelField;

    @FXML
    private TextField hariField;

    @FXML
    private TextField jamField;

    @FXML
    private TextField nipField;

    @FXML
    private TextField namaGuruField;


    @FXML
    private Button saveButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button cariButton;


    @FXML
    private TextField searchTextField;


    @FXML
    private TableView<MataPelajaran> tableView;

    @FXML
    private TableColumn<MataPelajaran, String> kodeMapelColumn;

    @FXML
    private TableColumn<MataPelajaran, String> hariColumn;

    @FXML
    private TableColumn<MataPelajaran, String> jamColumn;

    @FXML
    private TableColumn<MataPelajaran, String> nipColumn;

    @FXML
    private TableColumn<MataPelajaran, String> namaGuruColumn;

    private ObservableList<MataPelajaran> mataPelajaranList;

    @FXML
    public void initialize() {
        mataPelajaranList = FXCollections.observableArrayList();
        loadDataFromFile();
        kodeMapelColumn.setCellValueFactory(new PropertyValueFactory<>("kodeMapel"));
        hariColumn.setCellValueFactory(new PropertyValueFactory<>("hari"));
        jamColumn.setCellValueFactory(new PropertyValueFactory<>("jam"));
        nipColumn.setCellValueFactory(new PropertyValueFactory<>("nip"));
        namaGuruColumn.setCellValueFactory(new PropertyValueFactory<>("namaGuru"));

        tableView.setItems(mataPelajaranList);

        tableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> populateInputFields(newValue)
        );
    }

    private void populateInputFields(MataPelajaran mapel) {
        if (mapel != null) {
            kodeMapelField.setText(mapel.getKodeMapel());
            hariField.setText(mapel.getHari());
            jamField.setText(mapel.getJam());
            nipField.setText(mapel.getNip());
            namaGuruField.setText(mapel.getNamaGuru());
        } else {
            clearInputFields();
        }
    }

    @FXML
    private void handleSaveButtonAction() {
        String kodeMapel = kodeMapelField.getText();
        String hari = hariField.getText();
        String jam = jamField.getText();
        String nip = nipField.getText();
        String namaGuru = namaGuruField.getText();

        if (validateInput(kodeMapel, hari, jam, nip, namaGuru)) {
            MataPelajaran mapelBaru = new MataPelajaran(kodeMapel, hari, jam, nip, namaGuru);
            mataPelajaranList.add(mapelBaru);
            clearInputFields();
            saveDataToFile();
            tableView.refresh();
        } else {
            showAlert("Masukan Tidak Valid", "Silakan isi semua kolom dengan benar.");
        }
    }

    @FXML
    private void handleUpdateButtonAction(ActionEvent event) {
        MataPelajaran selectedMatpel = tableView.getSelectionModel().getSelectedItem();

        if (selectedMatpel != null && validateInput(kodeMapelField.getText(), hariField.getText(), jamField.getText(), nipField.getText(), namaGuruField.getText())) {
            selectedMatpel.setKodeMapel(kodeMapelField.getText());
            selectedMatpel.setHari(hariField.getText());
            selectedMatpel.setJam(jamField.getText());
            selectedMatpel.setNip(nipField.getText());
            selectedMatpel.setNamaGuru(namaGuruField.getText());
            tableView.refresh();
            saveDataToFile();
        } else {
            showAlert("Input Tidak Valid", "Pastikan semua kolom diisi dengan benar.");
        }
    }

    @FXML
    private void handleDeleteButtonAction() {
        MataPelajaran selectedMatapelajaran = tableView.getSelectionModel().getSelectedItem();
        if (selectedMatapelajaran != null) {
            mataPelajaranList.remove(selectedMatapelajaran);
            clearInputFields();
            saveDataToFile();
            tableView.refresh();
        } else {
            showAlert("Tidak ada pilihan", "Silakan pilih guru di tabel.");
        }
    }

    @FXML
    private void handleCariButtonAction() {
        String keyword = searchTextField.getText().trim().toLowerCase();
        if (!keyword.isEmpty()) {
            List<MataPelajaran> searchResults = mataPelajaranList.stream()
                    .filter(mapel -> mapel.getNamaGuru().toLowerCase().contains(keyword))
                    .collect(Collectors.toList());

            if (!searchResults.isEmpty()) {
                tableView.setItems(FXCollections.observableArrayList(searchResults));
            } else {
                showAlert("Tidak ditemukan", "Tidak ditemukan guru dengan nama yang diberikan.");
            }
        } else {
            showAlert("Tanpa Kata Kunci", "Silakan masukkan nama guru untuk mencari.");
        }
    }

    @FXML
    private void handleKeluarButtonAction(ActionEvent event) {
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

    private boolean validateInput(String kodemapel, String hari, String jam, String nip, String namaGuru) {
        return !jam.isEmpty() && !kodemapel.isEmpty() && !hari.isEmpty() && !nip.isEmpty() && !namaGuru.isEmpty();
    }

    private void clearInputFields() {
        kodeMapelField.clear();
        hariField.clear();
        jamField.clear();
        nipField.clear();
        namaGuruField.clear();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void loadDataFromFile() {
        File file = new File("mata_pelajaran.csv");
        if (!file.exists()) {
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                MataPelajaran mapel = new MataPelajaran(data[0], data[1], data[2], data[3], data[4]);
                mataPelajaranList.add(mapel);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveDataToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("mata_pelajaran.csv"))) {
            for (MataPelajaran mapel : mataPelajaranList) {
                String line = String.format("%s,%s,%s,%s,%s",
                        mapel.getKodeMapel(), mapel.getHari(), mapel.getJam(), mapel.getNip(), mapel.getNamaGuru());
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
