package com.example.projekpbo;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HelloController {

    @FXML
    private TextField UsernameTextField;

    @FXML
    private PasswordField passwordPasswordField;

    @FXML
    private Button LoginButton;

    @FXML
    private ImageView imageView;

    @FXML
    private AnchorPane rootPane;

    private Map<String, String> validCredentials;

    @FXML
    void initialize() {
        File image = new File("src/student.png");
        imageView.setImage(new Image(image.toURI().toString()));

        validCredentials = new HashMap<>();
        validCredentials.put("admin", "123");
        validCredentials.put("user1", "password1");
        validCredentials.put("user2", "password2");
        validCredentials.put("user3", "password3");


        LoginButton.setOnAction(event -> {
            String username = UsernameTextField.getText();
            String password = passwordPasswordField.getText();

            if (validateCredentials(username, password)) {
                System.out.println("Login berhasil!");
                navigateToMainMenu(event);
            } else {
                System.out.println("Login gagal. Username atau password salah.");
                disableLoginButton();
            }
        });
    }

    private boolean validateCredentials(String username, String password) {
        return validCredentials.containsKey(username) && validCredentials.get(username).equals(password);
    }

    private void navigateToMainMenu(ActionEvent event) {
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

    private void disableLoginButton() {
        LoginButton.setDisable(true);
        PauseTransition pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(event -> LoginButton.setDisable(false));
        pause.play();
    }
}
