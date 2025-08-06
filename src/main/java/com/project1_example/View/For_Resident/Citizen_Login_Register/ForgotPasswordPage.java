package com.project1_example.View.For_Resident.Citizen_Login_Register;

import com.project1_example.Controller.FirebaseAuthService;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class ForgotPasswordPage {
    Scene sceneForgot,forgotPage,loginPageScene;
    Stage stageForgot,primaryStage;
    

    public void setSceneForgot(Scene sceneForgot) {
        this.sceneForgot = sceneForgot;
         forgotPage = sceneForgot;
    }

    public void setStageForgot(Stage stageForgot) {
        this.stageForgot = stageForgot;
        primaryStage = stageForgot;
    }

    public VBox createForgotPasswordScene() {
        VBox root = new VBox(20);
        root.setPadding(new Insets(40));
        root.setAlignment(Pos.CENTER);

        Label title = new Label("Forgot Password");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 26));
        title.setTextFill(Color.web("#2c3e50"));

        TextField emailField = new TextField();
        emailField.setPromptText("Enter your registered email");
        emailField.setMaxWidth(300);

        Button resetButton = new Button("Send Reset Email");
        resetButton.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        resetButton.setStyle("-fx-background-color: #3498db; -fx-text-fill: white;");
        resetButton.setOnAction(e -> {
            String email = emailField.getText();
            if (email.isEmpty()) {
                showAlert("Input Error", "Please enter your email.");
                return;
            }

            boolean result = FirebaseAuthService.sendPasswordResetEmail(email);
            if (result) {
                showAlert("Success", "Password reset email sent successfully.");
            } else {
                showAlert("Failed", "Failed to send reset email. Please check the email address.");
            }
        });

        Button backButton = new Button("Back to Login");
        backButton.setFont(Font.font("Arial", 12));
        backButton.setTextFill(Color.web("#3498db"));
        backButton.setStyle("-fx-background-color: transparent;");
        backButton.setOnAction(e -> {
           initializeCitizenLoginPage();
           primaryStage.setScene(loginPageScene);
        });

        root.getChildren().addAll(title, emailField, resetButton, backButton);
        return root;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void initializeCitizenLoginPage(){
 CitizenLogin loginPage = new CitizenLogin();
           loginPage.setStage2_a(primaryStage);
           loginPageScene=new Scene(loginPage.createsScrollPane(null),1200,670);
           loginPage.setScene2_a(loginPageScene);
    }
}
