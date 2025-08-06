// Corrected AdminSignUp.java
package com.project1_example.View.For_Government.Admin_Login_Register;

import java.util.HashMap;
import java.util.Map;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

import com.project1_example.Controller.FirebaseAuthService;
import com.project1_example.Dao.FirebaseInitializer;
import com.project1_example.View.For_Government.For_Government;

import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
// import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class AdminSignUp {
    FirebaseAuthService authController = new FirebaseAuthService();
   
    Scene scene1_b, signUpPage, loginPage, GovermentPageScene;
    Stage stage1_b, primaryStage;

    public void setScene1_b(Scene scene1_b) {
        this.scene1_b = scene1_b;
        signUpPage = scene1_b;
    }

    public void setStage1_b(Stage stage1_b) {
        this.stage1_b = stage1_b;
        this.primaryStage = stage1_b; // ✅ Fix: assign primaryStage
    }


    public AdminSignUp() {
          FirebaseInitializer.initialize();
    }
    public StackPane createsAdminSignUpScrollPane(Runnable back) {

        stage1_b.setTitle("Nagrik Nazar - Login");

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

        Button backGovernment = new Button("Goverment Page");
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
                back.run();
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
                "-fx-background-color: rgba(255,255,255,0.15); " +
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
        logo.setFitHeight(70);
        logo.setFitWidth(70);
        logo.setPreserveRatio(true);
        logo.setSmooth(true);

        Text welcomeText = new Text("Admin Sign Up");
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

        ComboBox<String> stateComboBox = new ComboBox<>();
        stateComboBox.setPromptText("Select Department");
        stateComboBox.getItems().addAll("Roads & Infrastructure", "Water Supply", "Drainage & Sewerage",
                "Solid Waste Management", "Street Lighting", "Electricity Department", "Public Toilets & Hygiene");

        HBox nameRow = new HBox(10, firstNameField, lastNameField);
        nameRow.setAlignment(Pos.CENTER);

        HBox addressRow = new HBox(10, cityField, pincodeField);
        addressRow.setAlignment(Pos.CENTER);

        formContainer.getChildren().addAll(nameRow, emailField, phoneField, addressField, stateComboBox, addressRow,
                usernameField, passwordField, confirmPasswordField);

        Button signUpBtn = createStyledButton("Create Account", "#27ae60", "#229954");
        signUpBtn.setPrefWidth(250);
        Label resLabel1 = new Label();

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
            String department = stateComboBox.getValue();

            if (fname.isEmpty() || lname.isEmpty() || email.isEmpty() || phone.isEmpty() ||
                    address.isEmpty() || city.isEmpty() || pincode.isEmpty() ||
                    username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() ||
                    department == null || department.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Form Error!", "Please fill all the fields.");
                return;
            }

            if (!password.equals(confirmPassword)) {
                showAlert(Alert.AlertType.ERROR, "Password Mismatch", "Passwords do not match.");
                return;
            }
          authController.signUpUser(email, confirmPassword);
            
            String result = adduserToFirestore(fname, lname, email, phone, address, city, pincode, username,
                    department);
            resLabel1.setText(result);
            showAlert(Alert.AlertType.INFORMATION, "Registration Successful",
                    "Your account has been created successfully!");

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
            stateComboBox.setValue(null);

            initializeadminloginpage();
            primaryStage.setScene(loginPage);
        });

        Button loginLink = createLinkButton("Already have an account? Login");
        loginLink.setStyle("-fx-text-fill: red;");
        loginLink.setOnAction(e -> {
            initializeadminloginpage();
            stage1_b.setScene(loginPage);
        });

        signUpCard.getChildren().addAll(logo, welcomeText, subtitleText, formContainer, signUpBtn, loginLink);
        mainContent.getChildren().addAll(signUpCard);

        // ---------- FOOTER ----------
        HBox footer = new HBox();
        footer.setPadding(new Insets(10));
        footer.setStyle("-fx-background-color: transparent;");
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
            stage1_b.getIcons().add(new Image("Assets/Images/logo.jpg"));
        } catch (Exception e) {
            System.out.println("Logo image not found: " + e.getMessage());
        }

        BackgroundSize backgroundSize = new BackgroundSize(
                1200, 670, true, true, true, false);

        // CREATE BACKGROUND IMAGE
        BackgroundImage backgroundImage = new BackgroundImage(
                new Image("Assets\\Images\\homeBackground.jpeg"), // Background image path
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                backgroundSize);

        Background background = new Background(backgroundImage);
        root.setBackground(background);

        ScrollPane scrollPane = new ScrollPane(root);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        // scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        // scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        StackPane sp = new StackPane(scrollPane);
        sp.setBackground(background);

        // Scene setup
        Scene scene = new Scene(sp, 1200, 670);
        stage1_b.setScene(scene);
        signUpPage = scene;
        primaryStage = stage1_b;
        stage1_b.setResizable(false);
    

        return null;

    }

    public String adduserToFirestore(String fname, String lname, String email, String phone,
            String address, String city, String pincode,
            String username, String department) {
        Firestore db = FirestoreClient.getFirestore();

        Map<String, Object> data = new HashMap<>();
        data.put("firstName", fname);
        data.put("lastName", lname);
        data.put("email", email);
        data.put("phone", phone);
        data.put("address", address);
        data.put("city", city);
        data.put("pincode", pincode);
        data.put("username", username);
        data.put("department", department);

        try {
            ApiFuture<WriteResult> result = db.collection("Admins").document(email).set(data);
            return "User added at: " + result.get().getUpdateTime();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }

    public void adduserToFirestore(String name, String email, String password, String department) {
        Firestore db = FirestoreClient.getFirestore();

        Map<String, Object> user = new HashMap<>();
        user.put("name", name);
        user.put("email", email);
        user.put("password", password);
        user.put("department", department);

        ApiFuture<WriteResult> result = db.collection("Admins").document(email).set(user);
        try {
            System.out.println("Update time: " + result.get().getUpdateTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        label.setStyle(originalStyle);  // Reset to original
    });
}

    private void initializeGovermentMainpage() {
        For_Government govermentPage = new For_Government();
        govermentPage.setStage1(primaryStage);
        GovermentPageScene = new Scene(govermentPage.createScene2(this::handleButtonGP), 1200, 670);
        govermentPage.setScene1(GovermentPageScene);
    }

    private void handleButtonGP() {
        primaryStage.setScene(loginPage);
    }

    private void initializeadminloginpage() {
        AdminLogin adminLogin = new AdminLogin();
        adminLogin.setStage1_a(primaryStage);
        loginPage = new Scene(adminLogin.createsAdminLoginScrollPane(this::handleButtonGovernmentPage), 1520, 900);
        adminLogin.setScene1_a(loginPage);
    }

    private void handleButtonGovernmentPage() {
        primaryStage.setScene(GovermentPageScene);
    }

}