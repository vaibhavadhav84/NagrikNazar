package com.project1_example.View.For_Resident.Citizen_Login_Register;

import com.project1_example.Controller.FirebaseAuthService;

import com.project1_example.Dao.*;

import javafx.animation.ScaleTransition;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
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

public class CitizenSignUp {

    Scene scene2_b, CitizenSignUpScene, CitizenLoginScene, CitizenHomePageScene;
    Stage stage2_b, primaryStage;

    public void setScene2_b(Scene scene2_b) {
        this.scene2_b = scene2_b;
        CitizenSignUpScene = scene2_b;

    }

    public void setStage2_b(Stage stage2_b) {
        this.stage2_b = stage2_b;
        primaryStage = stage2_b;
    }

    public StackPane createScrollPane2(Runnable back) {

        stage2_b.setTitle("Nagrik Nazar - Login");

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

        Button backGovernment = new Button("Citizen Page");
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
                    back.run();
                } catch (Exception e) {
                    initializeCitizenloginpage();
                    primaryStage.setScene(CitizenSignUpScene);
                    e.printStackTrace();
                }
            }

        });

        addHoverAnimationForButton(backGovernment);

        topNav.getChildren().addAll(titleLabel, spacerTop, backGovernment);

        // ---------- MAIN CONTENT ----------
        VBox mainContent = new VBox();
        mainContent.setAlignment(Pos.CENTER);
        mainContent.setPadding(new Insets(20));
        mainContent.setSpacing(10);
        mainContent.setStyle("-fx-background: transparent; -fx-background-color: transparent;");

        VBox signUpCard = new VBox();
        signUpCard.setAlignment(Pos.CENTER);
        signUpCard.setPadding(new Insets(30));
        signUpCard.setSpacing(10);
        signUpCard.setMaxWidth(400);
        signUpCard.setStyle(
                "-fx-background-color: rgba(255, 255, 255, 0.91); " +
                        "-fx-background-radius: 30; " +
                        "-fx-border-color: rgba(255,255,255,0.4); " +
                        "-fx-border-width: 1.5; " +
                        "-fx-border-radius: 30; " +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0.1, 0, 4);");

        ImageView logo = new ImageView();
        try {
            logo.setImage(new Image("Assets/Images/logo.jpg"));
        } catch (Exception e) {
            System.out.println("Logo image not found: " + e.getMessage());
        }
        logo.setFitHeight(150);
        logo.setFitWidth(120);
        logo.setPreserveRatio(true);
        logo.setSmooth(true);

        Text welcomeText = new Text("Citizen Sign Up");
        welcomeText.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        welcomeText.setFill(Color.web("#2c3e50"));

        Text subtitleText = new Text("Join Nagrik Nazar community today");
        subtitleText.setFont(Font.font("Arial", 14));
        subtitleText.setFill(Color.web("#151717ff"));

        VBox formContainer = new VBox(15);
        formContainer.setAlignment(Pos.CENTER);

        TextField firstNameField = createStyledTextField("First Name");
        TextField lastNameField = createStyledTextField("Last Name");
        TextField emailField = createStyledTextField("Email Address");
        TextField phoneField = createStyledTextField("Phone Number");
        TextField addressField = createStyledTextField("Address");
        TextField cityField = createStyledTextField("City");
        TextField pincodeField = createStyledTextField("Pincode");
        TextField usernameField = createStyledTextField("Username");
        PasswordField passwordField = createStyledPasswordField("Password");
        PasswordField confirmPasswordField = createStyledPasswordField("Confirm Password");

        HBox nameRow = new HBox(10, firstNameField, lastNameField);
        nameRow.setAlignment(Pos.CENTER);

        HBox addressRow = new HBox(10, cityField, pincodeField);
        addressRow.setAlignment(Pos.CENTER);

        formContainer.getChildren().addAll(nameRow, emailField, phoneField, addressField, addressRow,
                usernameField, passwordField, confirmPasswordField);

        Button signUpBtn = createStyledButton("Create Account", "#27ae60", "#229954");
        signUpBtn.setPrefWidth(250);
        signUpBtn.setOnAction(event -> {
            String fname = firstNameField.getText().trim();
            String lname = lastNameField.getText().trim();
            String email = emailField.getText().trim();
            String phone = phoneField.getText().trim();
            String address = addressField.getText().trim();
            String city = cityField.getText().trim();
            String pincode = pincodeField.getText().trim();
            String username = usernameField.getText().trim();
            String password = passwordField.getText().trim();
            String confirmPassword = confirmPasswordField.getText().trim();

            if (fname.isEmpty() || lname.isEmpty() || email.isEmpty() || phone.isEmpty() ||
                    address.isEmpty() || city.isEmpty() || pincode.isEmpty() ||
                    username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Form Error!", "Please fill all the fields.");
                return;
            }

            if (!password.equals(confirmPassword)) {
                showAlert(Alert.AlertType.ERROR, "Password Mismatch", "Passwords do not match.");
                return;
            }

            // Initialize Firebase if not already
            FirebaseInitializer.initialize();

            // Step 1: Sign up user
            System.out.println("📤 Sending signup request...");
            boolean isSignedUp = FirebaseAuthService.signUpUser(email, password);

            if (!isSignedUp) {
                System.out.println("❌ Signup failed");
                showAlert(Alert.AlertType.ERROR, "Registration Failed", "Failed to create account. Try again.");
                return;
            } else {
                System.out.println("✅ Signup successful");
                showAlert(Alert.AlertType.ERROR, "Registration Successfully", "Created your account.");
            }

            // Step 2: Save additional user data to Firestore
            String fullName = fname + " " + lname;
            String uid = email; // using email as doc ID for now
            try {
                FirestoreService.saveUserData(uid, fullName, email);
            } catch (Exception e) {
                System.err.println("❌ Firestore write failed: " + e.getMessage());
            }

            showAlert(Alert.AlertType.INFORMATION, "Registration Successful",
                    "Your account has been created successfully!");

            // Reset fields
            firstNameField.clear();
            lastNameField.clear();
            emailField.clear();
            phoneField.clear();
            addressField.clear();
            cityField.clear();
            pincodeField.clear();
            usernameField.clear();
            passwordField.clear();
            confirmPasswordField.clear();

            initializeCitizenloginpage();
            primaryStage.setScene(CitizenLoginScene);
        });

        Button loginLink = createLinkButton("Already have an account? Login");
        loginLink.setStyle("-fx-text-fill: red;");
        loginLink.setOnAction(e -> {
            initializeCitizenloginpage();
            stage2_b.setScene(CitizenLoginScene);
        });

        signUpCard.getChildren().addAll(logo, welcomeText, subtitleText, formContainer, signUpBtn, loginLink);
        // signUpCard.setStyle("-fx-background: transperent; -fx-background-color: WHITE;");
        mainContent.getChildren().addAll(signUpCard);

        // ---------- FOOTER ----------
        HBox footer = new HBox();
        footer.setPadding(new Insets(10));
        footer.setStyle("-fx-background-color: #1a2c44;");
        footer.setAlignment(Pos.CENTER_LEFT);
        footer.setSpacing(50);
        footer.setPrefHeight(80);
        footer.setPrefWidth(Double.MAX_VALUE); // ✅ Ensure it expands
        footer.setMaxWidth(Double.MAX_VALUE); // ✅ Fully expand

        // Contact Info (Footer)
        Label titleLabel2 = new Label("Contact Us:");
        Label emailLabel = new Label("✉ nagriknazar@gmail.com");
        Label phoneLabel = new Label("📞 9999988885");
        Label locationLabel = new Label("📍 Pune, Maharashtra, India");

        VBox contactInfo = new VBox(titleLabel2, emailLabel, phoneLabel, locationLabel);
        contactInfo.setAlignment(Pos.CENTER_LEFT);
        contactInfo.setPadding(new Insets(10));
        contactInfo.setMaxWidth(400);
        contactInfo.setPrefWidth(650);

        // Set text color of each label manually
        for (Label label : new Label[] { titleLabel, emailLabel, phoneLabel, locationLabel }) {
            label.setTextFill(Color.WHITE);
            label.setWrapText(true); // This enables text to wrap if it's too long
            label.setMaxWidth(300); // Set the max width for wrapping
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
            stage2_b.getIcons().add(new Image("Assets/Images/logo.jpg"));
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

        StackPane stackPane5 = new StackPane();
        stackPane5.getChildren().addAll(backgroundImageView, scrollPane);

        // Scene setup
        Scene scene = new Scene(stackPane5, 1200, 670);
        stage2_b.setScene(scene);
        stage2_b.setResizable(false);

        return stackPane5;

    }

    private Button createLinkButton(String text) {
        Button button = new Button(text);
        button.setFont(Font.font("Arial", 12));
        button.setTextFill(Color.web("#3498db"));
        button.setStyle("-fx-background-color: transparent; " +
                "-fx-cursor: hand; " +
                "-fx-underline: false;");

        button.setOnMouseEntered(e -> {
            button.setStyle("-fx-background-color: transparent; " +
                    "-fx-cursor: hand; " +
                    "-fx-underline: true; " +
                    "-fx-text-fill: #2980b9;");
        });

        button.setOnMouseExited(e -> {
            button.setStyle("-fx-background-color: transparent; " +
                    "-fx-cursor: hand; " +
                    "-fx-underline: false; " +
                    "-fx-text-fill: #3498db;");
        });

        return button;

    }

    private TextField createStyledTextField(String placeholder) {
        TextField textField = new TextField();
        textField.setPromptText(placeholder);
        textField.setPrefWidth(300);
        textField.setPrefHeight(45);
        textField.setFont(Font.font("Arial", 14));
        textField.setStyle("-fx-background-color: #f8f9fa; " +
                "-fx-border-color: #dee2e6; " +
                "-fx-border-width: 1; " +
                "-fx-border-radius: 8; " +
                "-fx-background-radius: 8; " +
                "-fx-padding: 10; " +
                "-fx-font-size: 14px;");

        textField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                textField.setStyle("-fx-background-color: white; " +
                        "-fx-border-color: #3498db; " +
                        "-fx-border-width: 2; " +
                        "-fx-border-radius: 8; " +
                        "-fx-background-radius: 8; " +
                        "-fx-padding: 10; " +
                        "-fx-font-size: 14px;");
            } else {
                textField.setStyle("-fx-background-color: #f8f9fa; " +
                        "-fx-border-color: #dee2e6; " +
                        "-fx-border-width: 1; " +
                        "-fx-border-radius: 8; " +
                        "-fx-background-radius: 8; " +
                        "-fx-padding: 10; " +
                        "-fx-font-size: 14px;");
            }
        });

        return textField;
    }

    private PasswordField createStyledPasswordField(String placeholder) {
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText(placeholder);
        passwordField.setPrefWidth(300);
        passwordField.setPrefHeight(45);
        passwordField.setFont(Font.font("Arial", 14));
        passwordField.setStyle("-fx-background-color: #f8f9fa; " +
                "-fx-border-color: #dee2e6; " +
                "-fx-border-width: 1; " +
                "-fx-border-radius: 8; " +
                "-fx-background-radius: 8; " +
                "-fx-padding: 10; " +
                "-fx-font-size: 14px;");

        passwordField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                passwordField.setStyle("-fx-background-color: white; " +
                        "-fx-border-color: #3498db; " +
                        "-fx-border-width: 2; " +
                        "-fx-border-radius: 8; " +
                        "-fx-background-radius: 8; " +
                        "-fx-padding: 10; " +
                        "-fx-font-size: 14px;");
            } else {
                passwordField.setStyle("-fx-background-color: #f8f9fa; " +
                        "-fx-border-color: #dee2e6; " +
                        "-fx-border-width: 1; " +
                        "-fx-border-radius: 8; " +
                        "-fx-background-radius: 8; " +
                        "-fx-padding: 10; " +
                        "-fx-font-size: 14px;");
            }
        });

        return passwordField;
    }

    private Button createStyledButton(String text, String normalColor, String hoverColor) {
        Button button = new Button(text);
        button.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        button.setPrefHeight(45);
        button.setTextFill(Color.WHITE);
        button.setStyle("-fx-background-color: " + normalColor + "; " +
                "-fx-background-radius: 8; " +
                "-fx-cursor: hand; " +
                "-fx-font-weight: bold;");

        button.setOnMouseEntered(e -> {
            button.setStyle("-fx-background-color: " + hoverColor + "; " +
                    "-fx-background-radius: 8; " +
                    "-fx-cursor: hand; " +
                    "-fx-font-weight: bold; " +
                    "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 5, 0, 0, 2);");
        });

        button.setOnMouseExited(e -> {
            button.setStyle("-fx-background-color: " + normalColor + "; " +
                    "-fx-background-radius: 8; " +
                    "-fx-cursor: hand; " +
                    "-fx-font-weight: bold;");
        });

        return button;
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
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

    private void initializeCitizenMainpage() {
        CitizenHomePage citizenPage = new CitizenHomePage();
        citizenPage.setStageCH(primaryStage);
        CitizenHomePageScene = new Scene(citizenPage.createCitizenHomeScrollPane(this::handleButtonGP), 1200, 670);
        citizenPage.setSceneCH(CitizenHomePageScene);
    }

    private void handleButtonGP() {
        primaryStage.setScene(CitizenLoginScene);
    }

    private void initializeCitizenloginpage() {
        CitizenLogin citizenLogin = new CitizenLogin();
        citizenLogin.setStage2_a(primaryStage);
        CitizenLoginScene = new Scene(citizenLogin.createsScrollPane(this::handleButtonGovernmentPage), 1200, 670);
        citizenLogin.setScene2_a(CitizenLoginScene);
    }

    private void handleButtonGovernmentPage() {
        primaryStage.setScene(CitizenHomePageScene);
    }
}