package com.example.projekpbo;

import com.example.projekpbo.models.NilaiRapot;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.*;

public class DataRapot {

    @FXML
    private  Button savebutton;

    @FXML
    private  Button updatebutton;

    @FXML
    private  Button deletebutton;

    @FXML
    private TextField searchField;


    @FXML
    private TextField rataRataField;

    @FXML
    private TextField nisField;
    @FXML
    private TextField kodeMapelField;
    @FXML
    private TextField nilaiTugasField;
    @FXML
    private TextField nilaiUTSField;
    @FXML
    private TextField nilaiUASField;

    @FXML
    private TableView<NilaiRapot> tableView;
    @FXML
    private TableColumn<NilaiRapot, String> nisColumn;
    @FXML
    private TableColumn<NilaiRapot, String> kodeMapelColumn;
    @FXML
    private TableColumn<NilaiRapot, Integer> nilaiTugasColumn;
    @FXML
    private TableColumn<NilaiRapot, Integer> nilaiUTSColumn;
    @FXML
    private TableColumn<NilaiRapot, Integer> nilaiUASColumn;
    @FXML
    private TableColumn<NilaiRapot, Double> rataRataColumn;

    private ObservableList<NilaiRapot> nilaiRapotList;

    @FXML
    public void initialize() {
        nilaiRapotList = FXCollections.observableArrayList();
        loadDataFromFile();

        nisColumn.setCellValueFactory(new PropertyValueFactory<>("nis"));
        kodeMapelColumn.setCellValueFactory(new PropertyValueFactory<>("kodeMapel"));
        nilaiTugasColumn.setCellValueFactory(new PropertyValueFactory<>("nilaiTugas"));
        nilaiUTSColumn.setCellValueFactory(new PropertyValueFactory<>("nilaiUTS"));
        nilaiUASColumn.setCellValueFactory(new PropertyValueFactory<>("nilaiUAS"));
        rataRataColumn.setCellValueFactory(new PropertyValueFactory<>("rataRata"));

        tableView.setItems(nilaiRapotList);

        tableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> populateInputFields(newValue)
        );
    }

    private void populateInputFields(NilaiRapot nilai) {
        if (nilai != null) {
            nisField.setText(nilai.getNis());
            kodeMapelField.setText(nilai.getKodeMapel());
            nilaiTugasField.setText(String.valueOf(nilai.getNilaiTugas()));
            nilaiUTSField.setText(String.valueOf(nilai.getNilaiUTS()));
            nilaiUASField.setText(String.valueOf(nilai.getNilaiUAS()));
        } else {
            clearInputFields();
        }
    }

    @FXML
    private void handleSaveButtonAction() {
        try {
            String nis = nisField.getText();
            String kodeMapel = kodeMapelField.getText();
            int nilaiTugas = Integer.parseInt(nilaiTugasField.getText());
            int nilaiUTS = Integer.parseInt(nilaiUTSField.getText());
            int nilaiUAS = Integer.parseInt(nilaiUASField.getText());

            if (validateInput(nis, kodeMapel, nilaiTugas, nilaiUTS, nilaiUAS)) {
                NilaiRapot nilaiBaru = new NilaiRapot(nis, kodeMapel, nilaiTugas, nilaiUTS, nilaiUAS);
                nilaiRapotList.add(nilaiBaru);
                clearInputFields();
                saveDataToFile();
                tableView.refresh();
            } else {
                showAlert("Input Tidak Valid", "Silakan isi semua kolom dengan benar.");
            }
        } catch (NumberFormatException e) {
            showAlert("Input Tidak Valid", "Nilai harus berupa angka.");
        }
    }

    @FXML
    private void handleUpdateButtonAction() {
        NilaiRapot selectedNilai = tableView.getSelectionModel().getSelectedItem();
        if (selectedNilai != null) {
            try {
                String nis = nisField.getText();
                String kodeMapel = kodeMapelField.getText();
                int nilaiTugas = Integer.parseInt(nilaiTugasField.getText());
                int nilaiUTS = Integer.parseInt(nilaiUTSField.getText());
                int nilaiUAS = Integer.parseInt(nilaiUASField.getText());

                if (validateInput(nis, kodeMapel, nilaiTugas, nilaiUTS, nilaiUAS)) {
                    selectedNilai.setNis(nis);
                    selectedNilai.setKodeMapel(kodeMapel);
                    selectedNilai.setNilaiTugas(nilaiTugas);
                    selectedNilai.setNilaiUTS(nilaiUTS);
                    selectedNilai.setNilaiUAS(nilaiUAS);
                    tableView.refresh();
                    saveDataToFile();
                } else {
                    showAlert("Input Tidak Valid", "Silakan isi semua kolom dengan benar.");
                }
            } catch (NumberFormatException e) {
                showAlert("Input Tidak Valid", "Nilai harus berupa angka.");
            }
        } else {
            showAlert("Tidak ada pilihan", "Silakan pilih data di tabel.");
        }
    }

    @FXML
    private void handleDeleteButtonAction() {
        NilaiRapot selectedNilai = tableView.getSelectionModel().getSelectedItem();
        if (selectedNilai != null) {
            nilaiRapotList.remove(selectedNilai);
            clearInputFields();
            saveDataToFile();
            tableView.refresh();
        } else {
            showAlert("Tidak ada pilihan", "Silakan pilih data di tabel.");
        }
    }


    @FXML
    private void handleHitungButtonAction() {
        try {
            int nilaiTugas = Integer.parseInt(nilaiTugasField.getText());
            int nilaiUTS = Integer.parseInt(nilaiUTSField.getText());
            int nilaiUAS = Integer.parseInt(nilaiUASField.getText());

            double rataRata = (nilaiTugas + nilaiUTS + nilaiUAS) / 3.0;
            rataRataField.setText(String.format("%.2f", rataRata));
        } catch (NumberFormatException e) {
            showAlert("Input Tidak Valid", "Nilai harus berupa angka.");
        }
    }


    @FXML
    private void handleCariButtonAction() {
        String searchNIS = searchField.getText().trim();
        if (!searchNIS.isEmpty()) {
            ObservableList<NilaiRapot> filteredList = FXCollections.observableArrayList();
            for (NilaiRapot nilai : nilaiRapotList) {
                if (nilai.getKodeMapel().equalsIgnoreCase(searchNIS)) {
                    filteredList.add(nilai);
                }
            }
            tableView.setItems(filteredList);
        } else {
            tableView.setItems(nilaiRapotList); // Jika tidak ada input, tampilkan semua data
        }
    }



    private boolean validateInput(String nis, String kodeMapel, int nilaiTugas, int nilaiUTS, int nilaiUAS) {
        return !nis.isEmpty() && !kodeMapel.isEmpty() && nilaiTugas >= 0 && nilaiUTS >= 0 && nilaiUAS >= 0;
    }

    private void clearInputFields() {
        nisField.clear();
        kodeMapelField.clear();
        nilaiTugasField.clear();
        nilaiUTSField.clear();
        nilaiUASField.clear();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void loadDataFromFile() {
        File file = new File("nilai_rapot.csv");
        if (!file.exists()) {
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                NilaiRapot nilai = new NilaiRapot(data[0], data[1], Integer.parseInt(data[2]), Integer.parseInt(data[3]), Integer.parseInt(data[4]));
                nilaiRapotList.add(nilai);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveDataToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("nilai_rapot.csv"))) {
            for (NilaiRapot nilai : nilaiRapotList) {
                String line = String.format("%s,%s,%d,%d,%d",
                        nilai.getNis(), nilai.getKodeMapel(), nilai.getNilaiTugas(), nilai.getNilaiUTS(), nilai.getNilaiUAS());
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
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

}
