package com.project1_example.View.For_Resident.Citizen_Login_Register;

import com.project1_example.Controller.FirebaseAuthService;
import com.project1_example.Dao.FirebaseInitializer;
import com.project1_example.View.For_Resident.For_Resident;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class CitizenLogin {

    Scene scene2_a, CitizenLoginScene, CitizenSignUpScene, CitizenAfterHomePageScene, ForResidentScene;
    Stage stage2_a, primaryStage;

    public void setScene2_a(Scene scene2_a) {
        this.scene2_a = scene2_a;
        CitizenLoginScene = scene2_a;
    }

    public void setStage2_a(Stage stage2_a) {
        this.stage2_a = stage2_a;
        primaryStage = stage2_a;
    }

    public static String loggedInEmail = null;
    public static boolean isLoggedIn = false;

    public StackPane createsScrollPane(Runnable resident) {

        stage2_a.setTitle("Nagrik Nazar - Login");

        // ---------- TOP NAVIGATION BAR ----------
        HBox topNav = new HBox();
        topNav.setAlignment(Pos.CENTER_LEFT);
        topNav.setStyle("-fx-background-color: transparent;");
        topNav.setPadding(new Insets(10));
        topNav.setPrefWidth(Double.MAX_VALUE); // ✅ Ensure it expands
        topNav.setMaxWidth(Double.MAX_VALUE); // ✅ Fully expand

        Label titleLabel = new Label("NAGRIK NAZAR");

        titleLabel.setStyle("""
                        -fx-font-size: 30px;
                        -fx-font-weight: bold;
                        -fx-text-fill: linear-gradient(#ffffff, #000000ff);
                        -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.75), 4, 0.2, 2, 2);
                """);

        addHoverAnimationForLabel(titleLabel);

        Region spacerTop = new Region();
        HBox.setHgrow(spacerTop, javafx.scene.layout.Priority.ALWAYS);

        Button backGovernment = new Button("Resident Page");
        backGovernment.setStyle("""
                -fx-background-color: rgba(255, 255, 255, 0.15);
                -fx-text-fill: white;
                -fx-font-weight: bold;
                -fx-font-size: 14px;
                -fx-background-radius: 12;
                -fx-border-radius: 12;
                -fx-border-color: rgba(255, 255, 255, 0.5);
                -fx-border-width: 1;
                -fx-padding: 8 20 8 20;
                -fx-cursor: hand;
                """);
        backGovernment.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                try {
                    resident.run();
                } catch (Exception e) {
                    initializeResidentPage();
                    primaryStage.setScene(ForResidentScene);
                    e.printStackTrace();
                }
            }

        });

        addHoverAnimationForButton(backGovernment);

        topNav.getChildren().addAll(titleLabel, spacerTop, backGovernment);

        // ---------- MAIN CONTENT ----------
        VBox mainContent = new VBox();
        mainContent.setAlignment(Pos.CENTER);
        mainContent.setPadding(new Insets(40));
        mainContent.setSpacing(30);

        // Login Card
        VBox loginCard = new VBox();
        loginCard.setAlignment(Pos.CENTER);
        loginCard.setPadding(new Insets(40));
        loginCard.setSpacing(25);
        loginCard.setMaxWidth(400);
        loginCard.setStyle("""
                -fx-background-color: rgba(255, 255, 255, 0.91);
                -fx-background-radius: 15;
                -fx-border-radius: 15;
                -fx-border-color: rgba(255,255,255,0.4);
                -fx-border-width: 1;
                -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 20, 0, 0, 5);
                """);
        loginCard.setEffect(new GaussianBlur(10));

        ImageView logo = new ImageView();
        try {
            logo.setImage(new Image("Assets/Images/logo.jpg"));
            logo.setFitHeight(150);
            logo.setFitWidth(120);
            logo.setPreserveRatio(true);
            logo.setSmooth(true);
        } catch (Exception e) {
            logo.setFitHeight(150);
            logo.setFitWidth(120);
            System.out.println("Logo image not found: " + e.getMessage());
        }

        Text welcomeText = new Text("Citizen Login");
        welcomeText.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        welcomeText.setFill(Color.web("#000000ff"));

        Text subtitleText = new Text("Please sign in to your account");
        subtitleText.setFont(Font.font("Arial", 14));
        subtitleText.setFill(Color.web("#000000ff"));

        TextField usernameField = new TextField();
        usernameField.setPromptText("Email-id");
        usernameField.setPrefWidth(300);
        usernameField.setPrefHeight(45);
        usernameField.setFont(Font.font("Arial", 14));
        usernameField
                .setStyle("-fx-background-color: rgba(255, 255, 255, 0.3); -fx-border-color: rgba(14, 14, 14, 0.4); " +
                        "-fx-border-width: 1; -fx-border-radius: 8; -fx-background-radius: 8; " +
                        "-fx-padding: 10; -fx-font-size: 14px;-fx-prompt-text-fill: #000000ff;");
        usernameField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                usernameField.setStyle("-fx-background-color: white; -fx-border-color: #3498db; " +
                        "-fx-border-width: 2; -fx-border-radius: 8; -fx-background-radius: 8; " +
                        "-fx-padding: 10; -fx-font-size: 14px;");
            } else {
                usernameField.setStyle(
                        "-fx-background-color: rgba(255, 255, 255, 0.3); -fx-border-color: rgba(14, 13, 13, 0.4); " +
                                "-fx-border-width: 1; -fx-border-radius: 8; -fx-background-radius: 8; " +
                                "-fx-padding: 10; -fx-font-size: 14px;-fx-prompt-text-fill: #000000ff;");
            }
        });

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setPrefWidth(300);
        passwordField.setPrefHeight(45);
        passwordField.setFont(Font.font("Arial", 14));
        passwordField
                .setStyle("-fx-background-color: rgba(255, 255, 255, 0.3); -fx-border-color: rgba(18, 16, 16, 0.4); " +
                        "-fx-border-width: 1; -fx-border-radius: 8; -fx-background-radius: 8; " +
                        "-fx-padding: 10; -fx-font-size: 14px;-fx-prompt-text-fill: #000000ff;");
        passwordField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                passwordField.setStyle("-fx-background-color: white; -fx-border-color: #3498db; " +
                        "-fx-border-width: 2; -fx-border-radius: 8; -fx-background-radius: 8; " +
                        "-fx-padding: 10; -fx-font-size: 14px;");
            } else {
                passwordField.setStyle(
                        "-fx-background-color: rgba(255, 255, 255, 0.3); -fx-border-color: rgba(14, 13, 13, 0.4); " +
                                "-fx-border-width: 1; -fx-border-radius: 8; -fx-background-radius: 8; " +
                                "-fx-padding: 10; -fx-font-size: 14px;-fx-prompt-text-fill: #000000ff;");
            }
        });

        // loginbtn
        Button loginBtn = new Button("Login");
        loginBtn.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        loginBtn.setPrefHeight(45);
        loginBtn.setPrefWidth(200);
        loginBtn.setTextFill(Color.WHITE);
        loginBtn.setStyle("-fx-background-color: #3498db; -fx-background-radius: 8; -fx-cursor: hand;");
        loginBtn.setOnMouseEntered(
                e -> loginBtn.setStyle("-fx-background-color: #2980b9; -fx-background-radius: 8; -fx-cursor: hand;"));
        loginBtn.setOnMouseExited(
                e -> loginBtn.setStyle("-fx-background-color: #3498db; -fx-background-radius: 8; -fx-cursor: hand;"));
        addHoverAnimationForButton(loginBtn);
        loginBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                String email = usernameField.getText();
                String password = passwordField.getText();

                FirebaseInitializer.initialize();
                boolean authSuccess = FirebaseAuthService.authenticateUser(email, password);

                if (authSuccess) {
                    System.out.println("✅ Login Success");
                    initializeCitizenHomePage();
                    primaryStage.setScene(CitizenAfterHomePageScene);
                } else {
                    System.out.println("❌ Invalid username or password");
                    // On success
                    javafx.application.Platform.runLater(() -> {
                        showAlert(Alert.AlertType.INFORMATION, "Success", "Complaint submitted successfully.");

                    });
                }

            }

            private void showAlert(AlertType information, String string, String string2) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'showAlert'");
            }
        });

        // sigupbtn
        Button signupLink = new Button("Don't have an account? Sign up");
        signupLink.setFont(Font.font("Arial", 12));
        signupLink.setTextFill(Color.web("#3498db"));
        signupLink.setStyle("-fx-background-color: transparent; -fx-cursor: hand;");
        signupLink.setOnMouseEntered(e -> signupLink.setStyle(
                "-fx-background-color: transparent; -fx-cursor: hand; -fx-underline: true; -fx-text-fill: #2980b9;"));
        signupLink.setOnMouseExited(e -> signupLink.setStyle(
                "-fx-background-color: transparent; -fx-cursor: hand; -fx-underline: false; -fx-text-fill: #3498db;"));
        signupLink.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                initializeSignUp();
                primaryStage.setScene(CitizenLoginScene);
            }

        });

        Button forgotPasswordLink = new Button("Forgot Password?");
        forgotPasswordLink.setFont(Font.font("Arial", 12));
        forgotPasswordLink.setTextFill(Color.web("#3498db"));
        forgotPasswordLink.setStyle("-fx-background-color: transparent; -fx-cursor: hand;");
        forgotPasswordLink.setOnMouseEntered(e -> forgotPasswordLink.setStyle(
                "-fx-background-color: transparent; -fx-cursor: hand; -fx-underline: true; -fx-text-fill: #ff6702ff;"));
        forgotPasswordLink.setOnMouseExited(e -> forgotPasswordLink.setStyle(
                "-fx-background-color: transparent; -fx-cursor: hand; -fx-underline: false; -fx-text-fill: #3498db;"));
        forgotPasswordLink.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Reset Password");
            dialog.setHeaderText("Forgot Password");
            dialog.setContentText("Enter your registered email:");

            dialog.showAndWait().ifPresent(email -> {
                FirebaseInitializer.initialize();
                boolean success = FirebaseAuthService.sendPasswordResetEmail(email);

                if (success) {
                    showAlertInfo("Password Reset Email Sent", "Check your inbox for the reset link.");
                } else {
                    showAlert("Error", "Failed to send reset email. Please check the email address.");
                }
            });
        });

        HBox linksContainer = new HBox(20);
        linksContainer.setAlignment(Pos.CENTER);
        linksContainer.getChildren().addAll(signupLink, forgotPasswordLink);

        loginCard.getChildren().addAll(logo, welcomeText, subtitleText, usernameField, passwordField, loginBtn,
                linksContainer);
        mainContent.getChildren().add(loginCard);
        // ---------- FOOTER ----------
        HBox footer = new HBox();
        footer.setPadding(new Insets(10));
        footer.setStyle("-fx-background-color: #1a2c44;");
        footer.setAlignment(Pos.CENTER_LEFT);
        footer.setPrefHeight(80);
        footer.setSpacing(50);

        Label titleLabel1 = new Label("Contact Us:");
        Label emailLabel = new Label("✉ nagriknazar@gmail.com");
        Label phoneLabel = new Label("📞 9999988885");
        Label locationLabel = new Label("📍 Pune, Maharashtra, India");

        VBox contactInfo = new VBox(titleLabel1, emailLabel, phoneLabel, locationLabel);
        contactInfo.setAlignment(Pos.CENTER_LEFT);
        contactInfo.setMaxWidth(400);
        contactInfo.setPadding(new Insets(10));
        contactInfo.setStyle("-fx-background-color:  #1a2c44;-fx-text-fill: WHITE;");
        contactInfo.setPrefWidth(650);

        for (Label label : new Label[] { titleLabel1, emailLabel, phoneLabel, locationLabel }) {
            label.setTextFill(Color.WHITE);
            label.setWrapText(true);
            label.setMaxWidth(300);
        }

        Label copyright = new Label("© 2025 NAGRIK NAZAR Platform");
        copyright.setTextFill(Color.WHITE);
        copyright.setAlignment(Pos.CENTER_RIGHT);
        copyright.setMaxWidth(Double.MAX_VALUE);

        footer.getChildren().addAll(contactInfo, copyright);
        footer.setAlignment(Pos.BOTTOM_LEFT);

        // ---------- ROOT LAYOUT ----------
        VBox root = new VBox(topNav, mainContent, footer);
        root.setPadding(new Insets(10, 10, 10, 10));

        try {
            stage2_a.getIcons().add(new Image("Assets/Images/logo.jpg"));
        } catch (Exception e) {
            System.out.println("Logo image not found: " + e.getMessage());
        }

        // Load and configure background ImageView
        ImageView backgroundImageView = new ImageView(new Image("Assets/Images/for_resident1.jpeg"));
        backgroundImageView.setFitWidth(1200);
        backgroundImageView.setFitHeight(670);
        backgroundImageView.setPreserveRatio(false);

        backgroundImageView.setEffect(new javafx.scene.effect.GaussianBlur(10)); // Adjust radius as needed

        ScrollPane scrollPane = new ScrollPane(root);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        ScrollPane scrollPane1 = new ScrollPane(root);
        scrollPane1.setFitToWidth(true);
        scrollPane1.setStyle("-fx-background: transparent; -fx-background-color: transparent;");

        StackPane stackPane4 = new StackPane();
        stackPane4.getChildren().addAll(backgroundImageView, scrollPane);

        return stackPane4;

    }

    private void showForgotPasswordPopup() {
        Stage popupStage = new Stage();
        popupStage.setTitle("Reset Password");

        VBox popupLayout = new VBox(10);
        popupLayout.setPadding(new Insets(20));
        popupLayout.setAlignment(Pos.CENTER);

        Label instruction = new Label("Enter your registered email:");
        TextField emailField = new TextField();
        emailField.setPromptText("Email");

        Button sendButton = new Button("Send Reset Link");
        sendButton.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-cursor: hand;");
        sendButton.setOnAction(e -> {
            String email = emailField.getText();
            if (email.isEmpty()) {
                showAlert("Error", "Please enter your email.");
                return;
            }

            FirebaseInitializer.initialize(); // make sure Firebase is initialized
            boolean result = FirebaseAuthService.sendPasswordResetEmail(email);
            if (result) {
                showAlert("Success", "Password reset email sent. Check your inbox.");
                popupStage.close();
            } else {
                showAlert("Error", "Failed to send reset email. Please check the email and try again.");
            }
        });

        popupLayout.getChildren().addAll(instruction, emailField, sendButton);
        Scene popupScene = new Scene(popupLayout, 350, 180);
        popupStage.setScene(popupScene);
        popupStage.setResizable(false);
        popupStage.showAndWait();
    }

    private void showAlertInfo(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // HoverButton
    private void addHoverAnimationForButton(Button button) {
        ScaleTransition enlarge = new ScaleTransition(Duration.millis(200), button);
        enlarge.setToX(1.1);
        enlarge.setToY(1.1);
        ScaleTransition shrink = new ScaleTransition(Duration.millis(200), button);
        shrink.setToX(1.0);
        shrink.setToY(1.0);

        // Store original style
        String originalStyle = button.getStyle();

        // Hover behavior
        button.setOnMouseEntered(e -> {
            enlarge.playFromStart();
            button.setStyle(originalStyle + """
                        -fx-background-color: #2196F3;  /* Blue */
                    """);
        });

        button.setOnMouseExited(e -> {
            shrink.playFromStart();
            button.setStyle(originalStyle); // Reset to original
        });
    }

    // label
    private void addHoverAnimationForLabel(Label label) {
        ScaleTransition enlarge = new ScaleTransition(Duration.millis(200), label);
        enlarge.setToX(1.1);
        enlarge.setToY(1.1);
        ScaleTransition shrink = new ScaleTransition(Duration.millis(200), label);
        shrink.setToX(1.0);
        shrink.setToY(1.0);

        // Store original style
        String originalStyle = label.getStyle();

        // Hover behavior
        label.setOnMouseEntered(e -> {
            enlarge.playFromStart();
            label.setStyle(originalStyle + """
                        -fx-text-fill: #2196F3;  /* Blue color on hover */
                    """);
        });

        label.setOnMouseExited(e -> {
            shrink.playFromStart();
            label.setStyle(originalStyle); // Reset to original
        });
    }

    private void initializeCitizenHomePage() {
        CitizenHomePage citizenHomePage = new CitizenHomePage();
        citizenHomePage.setStageCH(primaryStage);
        CitizenAfterHomePageScene = new Scene(citizenHomePage.createCitizenHomeScrollPane(this::handleButtonHomePage),
                1200, 670);
        citizenHomePage.setSceneCH(CitizenAfterHomePageScene);
    }

    private void handleButtonHomePage() {
        System.out.println("Login Page");

        primaryStage.setScene(CitizenLoginScene);
    }

    private void initializeResidentPage() {
        For_Resident residentPage = new For_Resident();
        residentPage.setStage2(primaryStage);
        ForResidentScene = new Scene(residentPage.createScene2(this::handleButtonResidentPage), 1200, 670);
        residentPage.setScene2(ForResidentScene);
    }

    private void handleButtonResidentPage() {
        initializeResidentPage();
        primaryStage.setScene(ForResidentScene);
    }

    private void initializeSignUp() {
        CitizenSignUp citizenSignUp = new CitizenSignUp();
        citizenSignUp.setStage2_b(primaryStage);
        CitizenSignUpScene = new Scene(citizenSignUp.createScrollPane2(this::handleSignupBack), 1200, 670);
        citizenSignUp.setScene2_b(CitizenSignUpScene);
    }

    private void handleSignupBack() {
        primaryStage.setScene(CitizenLoginScene);
    }

}
