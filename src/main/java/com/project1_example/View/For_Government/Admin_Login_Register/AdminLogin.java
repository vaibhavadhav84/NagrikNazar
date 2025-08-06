package com.project1_example.View.For_Government.Admin_Login_Register;

import com.project1_example.View.For_Government.AdminPage.AdminPage;

import com.project1_example.Controller.*;

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
import com.project1_example.Dao.FirebaseInitializer;

public class AdminLogin {

    FirebaseAuthService authlogin = new FirebaseAuthService();
    Scene scene1_a, AdminLoginPageScene, AdminRegisterPageScene, AdminMainPageScene, GovermentPageScene;
    Stage stage1_a, primaryStage;

    public Scene getScene1_a() {
        return scene1_a;
    }

    public Stage getStage1_a() {
        return stage1_a;
    }

    public void setScene1_a(Scene scene1_a) {
        this.scene1_a = scene1_a;
        AdminLoginPageScene = scene1_a;

    }

    public void setStage1_a(Stage stage1_a) {
        this.stage1_a = stage1_a;
        primaryStage = stage1_a;
        stage1_a.setResizable(false);
    }

    public StackPane createsAdminLoginScrollPane(Runnable back) {
        FirebaseInitializer.initialize();

        stage1_a.setTitle("Nagrik Nazar - Login");

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
        mainContent.setPadding(new Insets(40));
        mainContent.setSpacing(30);

        // Login Card
        VBox loginCard = new VBox();
        loginCard.setAlignment(Pos.CENTER);
        loginCard.setPadding(new Insets(40));
        loginCard.setSpacing(25);
        loginCard.setMaxWidth(400);
        loginCard.setStyle("""
                -fx-background-color: rgba(255, 255, 255, 0.94);
                -fx-background-radius: 15;
                -fx-border-radius: 15;
                -fx-border-color: rgba(255,255,255,0.4);
                -fx-border-width: 1;
                -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 20, 0, 0, 5);
                """);
        // loginCard.setEffect(new GaussianBlur(10));

        ImageView logo = new ImageView();
        try {
            logo.setImage(new Image("Assets/Images/logo.jpg"));
            logo.setFitHeight(100);
            logo.setFitWidth(120);
            logo.setPreserveRatio(true);
            logo.setSmooth(true);
        } catch (Exception e) {
            logo.setFitHeight(100);
            logo.setFitWidth(120);
            System.out.println("Logo image not found: " + e.getMessage());
        }

        Text welcomeText = new Text("Admin Login");
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
                .setStyle("-fx-background-color: rgba(255, 255, 255, 0.3); -fx-border-color: rgba(18, 17, 17, 0.92); " +
                        "-fx-border-width: 1; -fx-border-radius: 8; -fx-background-radius: 8; " +
                        "-fx-padding: 10; -fx-font-size: 14px;-fx-prompt-text-fill: #000000ff;");
        usernameField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                usernameField.setStyle("-fx-background-color: white; -fx-border-color: #3498db; " +
                        "-fx-border-width: 2; -fx-border-radius: 8; -fx-background-radius: 8; " +
                        "-fx-padding: 10; -fx-font-size: 14px;");
            } else {
                usernameField.setStyle(
                        "-fx-background-color: rgba(255, 255, 255, 0.3); -fx-border-color: rgba(255,255,255,0.4); " +
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
                .setStyle("-fx-background-color: rgba(255, 255, 255, 0.3); -fx-border-color: rgba(11, 10, 10, 0.4); " +
                        "-fx-border-width: 1; -fx-border-radius: 8; -fx-background-radius: 8; " +
                        "-fx-padding: 10; -fx-font-size: 14px;-fx-prompt-text-fill: #000000ff;");
        passwordField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                passwordField.setStyle("-fx-background-color: white; -fx-border-color: #3498db; " +
                        "-fx-border-width: 2; -fx-border-radius: 8; -fx-background-radius: 8; " +
                        "-fx-padding: 10; -fx-font-size: 14px;");
            } else {
                passwordField.setStyle(
                        "-fx-background-color: rgba(255, 255, 255, 0.3); -fx-border-color: rgba(255,255,255,0.4); " +
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
        Label resultLabel1 = new Label();
        loginBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                String username = usernameField.getText();
                String password = passwordField.getText();

                if (username.equals("admin") && password.equals("admin123")) {
                    initializeAdminPage();
                    primaryStage.setScene(AdminMainPageScene);
                    resultLabel1.setText("Login successful!");
                } else {
                    System.out.println("Invalid username or password");
                    showAlert("Login Failed", "Email id ani password barobar tak");
                }

                // boolean success = authlogin.signInWithEmailAndPassword(username, password);
                // if (success) {
                // resultLabel1.setText(success ? "Login successful!" : "Login failed");
                // initializeAdminPage();
                // primaryStage.setScene(AdminLoginPageScene);

                // } else {
                // System.out.println("Invalid username or password");
                // showAlert("Login Failed", "Email id ani password barobar tak");
                // }
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
        signupLink.setOnMouseExited(e -> signupLink.setStyle(
                "-fx-background-color: transparent; -fx-cursor: hand; -fx-underline: false; -fx-text-fill: #3498db;"));

        signupLink.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                initializeAdminRegisterPage();
                primaryStage.setScene(AdminRegisterPageScene);
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

        HBox linksContainer = new HBox(20);
        linksContainer.setAlignment(Pos.CENTER);
        linksContainer.getChildren().addAll(signupLink, forgotPasswordLink);

        loginCard.getChildren().addAll(logo, welcomeText, subtitleText, usernameField, passwordField, loginBtn
                );
        mainContent.getChildren().add(loginCard);

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

        // Set fully opaque white text for each label
        titleLabel.setTextFill(Color.WHITE);
        emailLabel.setTextFill(Color.WHITE);
        phoneLabel.setTextFill(Color.WHITE);
        locationLabel.setTextFill(Color.WHITE);

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
            stage1_a.getIcons().add(new Image("Assets/Images/logo.jpg"));
        } catch (Exception e) {
            System.out.println("Logo image not found: " + e.getMessage());
        }

        // Load and configure background ImageView
        ImageView backgroundImageView = new ImageView(new Image("Assets\\Images\\loginBackground.jpg"));
        backgroundImageView.setFitWidth(1200);
        backgroundImageView.setFitHeight(670);
        backgroundImageView.setPreserveRatio(false);

        backgroundImageView.setEffect(new javafx.scene.effect.GaussianBlur(10)); // Adjust radius as needed

        // scrollable content VBox
        ScrollPane scrollPane = new ScrollPane(root);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        StackPane stackPane9 = new StackPane();
        stackPane9.getChildren().addAll(backgroundImageView, scrollPane);



        return stackPane9;

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

    private void initializeAdminPage() {
        AdminPage admin = new AdminPage();
        admin.setStage2(primaryStage);
        AdminMainPageScene = new Scene(admin.CreateScene7(this::handleButtonAdminPage), 1200, 670);
        admin.setScene2(AdminMainPageScene);
    }

    private void handleButtonAdminPage() {
        primaryStage.setScene(AdminLoginPageScene);
    }

    private void initializeAdminRegisterPage() {
        AdminSignUp adminSignup = new AdminSignUp();
        adminSignup.setStage1_b(primaryStage);
        AdminRegisterPageScene = new Scene(
                adminSignup.createsAdminSignUpScrollPane(this::handleButtonAdminRegisterPage), 1200, 670);
        adminSignup.setScene1_b(AdminRegisterPageScene);
    }

    private void handleButtonAdminRegisterPage() {
        primaryStage.setScene(GovermentPageScene);
    }

}
