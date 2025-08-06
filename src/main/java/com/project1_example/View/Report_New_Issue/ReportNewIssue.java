package com.project1_example.View.Report_New_Issue;

import java.io.File;


import com.project1_example.Controller.SessionManager;
import com.project1_example.Dao.FirebaseInitializer;
import com.project1_example.View.For_Resident.Citizen_Login_Register.CitizenLogin;
import com.project1_example.View.For_Resident.Citizen_Login_Register.CitizenSignUp;

import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ReportNewIssue {
    Scene scene4, ReportNewIssueScene, CitizenLoginScene, CitizenRegisterScene;
    Stage stage4, primaryStage;

    public Scene getScene4() {
        return scene4;
    }

    public Stage getStage4() {
        return stage4;
    }

    public void setScene4(Scene scene4) {
        this.scene4 = scene4;
        ReportNewIssueScene = scene4;
    }

    public void setStage4(Stage stage4) {
        this.stage4 = stage4;
        primaryStage = stage4;
        stage4.setResizable(false);
    }

    public ReportNewIssue() {
        FirebaseInitializer.initialize(); // Initialize Firebase only once
    }

    private File selectedFile;

    public ScrollPane createReportPageScrollPane(Runnable back) {
        // Top navigation bar
        HBox topNav = new HBox(20);
        topNav.setPadding(new Insets(15));
        topNav.setAlignment(Pos.CENTER_LEFT);
        topNav.setBackground(new Background(new BackgroundFill(Color.DARKSLATEBLUE, CornerRadii.EMPTY, Insets.EMPTY)));

        Label titleLabel = new Label("NAGRIK NAZAR");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 42));
        titleLabel.setStyle("-fx-effect: dropshadow(gaussian, rgba(254, 254, 255, 0.97), 5, 0, 2, 2);");
       
        addHoverAnimationForLabel(titleLabel);

        Button backHomePage = new Button("← Back Home");
        backHomePage.setStyle("""
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
        addHoverAnimationForButton(backHomePage);
        backHomePage.setOnAction(e -> back.run());

        Button loginBtn = new Button("Citizen Login");
        loginBtn.setStyle("""
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
        loginBtn.setOnAction(e -> {
            initializeCitizenLoginPage();
            primaryStage.setScene(CitizenLoginScene);
        });
        addHoverAnimationForButton(loginBtn);

        Button registerBtn = new Button("Citizen Register");
        registerBtn.setStyle("""
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
        addHoverAnimationForButton(registerBtn);
        registerBtn.setOnAction(e -> {
            initializeCitizenRegisterPage();
            primaryStage.setScene(CitizenRegisterScene);
        });

        HBox loginBox = new HBox(10, loginBtn, new Label("/"), registerBtn);
        loginBox.setAlignment(Pos.CENTER);

        Region spacer1 = new Region();
        HBox.setHgrow(spacer1, Priority.ALWAYS);
        Region spacer2 = new Region();
        HBox.setHgrow(spacer2, Priority.ALWAYS);

        topNav.getChildren().addAll(backHomePage, spacer1, titleLabel, spacer2, loginBox);

        // Heading
        Label heading = new Label("Report New Issue");
        heading.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        heading.setTextFill(Color.DARKBLUE);
        heading.setPadding(new Insets(20));
        heading.setMaxWidth(Double.MAX_VALUE);
        heading.setAlignment(Pos.CENTER);
        addHoverAnimationForLabel(heading);

        // Left Image Panel
        ImageView imageView = new ImageView(new Image("Assets/Images/logo.jpg"));
        imageView.setFitWidth(300);
        imageView.setPreserveRatio(true);

        VBox leftPane = new VBox(imageView);
        leftPane.setAlignment(Pos.CENTER);
        leftPane.setPadding(new Insets(20));

        // Form Area
        GridPane form = new GridPane();
        form.setPadding(new Insets(30));
        form.setHgap(15);
        form.setVgap(20);

        

        ComboBox<String> category = new ComboBox<>();
        category.getItems().addAll("Water", "Electricity", "Road", "Garbage");
        category.setPromptText("Select Category");

        ComboBox<String> complaintType = new ComboBox<>();
        complaintType.getItems().addAll("Leakage", "No Power", "Pothole", "Overflowing Bin");
        complaintType.setPromptText("Select Complaint");

        TextArea description = new TextArea();
        description.setPromptText("Enter Description (Max 2000 characters)");
        description.setPrefRowCount(4);

        TextField address = new TextField();
        address.setPromptText("Enter Complaint Address");

        TextField landmark = new TextField();
        landmark.setPromptText("Landmark");

        Label fileLabel = new Label("No file chosen");
        Button chooseFile = new Button("Choose File");
        chooseFile.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters()
                    .add(new FileChooser.ExtensionFilter("Images/PDF", "*.jpg", "*.png", "*.jpeg", "*.pdf"));
            selectedFile = fileChooser.showOpenDialog(stage4);
            if (selectedFile != null)
                fileLabel.setText(selectedFile.getName());
        });

        Button removeFile = new Button("Remove File");
        removeFile.setOnAction(e -> {
            selectedFile = null;
            fileLabel.setText("No file chosen");
        });

        HBox fileBox = new HBox(10, chooseFile, fileLabel, removeFile);
        fileBox.setAlignment(Pos.CENTER_LEFT);

        Button submit = new Button("Submit");
        submit.setStyle(
                "-fx-background-color: #d76908ff; -fx-background-radius: 8; -fx-cursor: hand;-fx-font-weight: bold;");
        addHoverAnimationForButton(submit);
        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!SessionManager.isLoggedIn()) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Login Required");
                    alert.setHeaderText("You must be logged in");
                    alert.setContentText("Please log in before submitting a complaint.");
                    alert.show();
                    initializeCitizenLoginPage();
                    primaryStage.setScene(CitizenLoginScene);
                    return;
                }
              
                // String cat = category.getValue();
                // String type = complaintType.getValue();
                // String desc = description.getText();
                // String addr = address.getText();
                // String land = landmark.getText();
                // String file = selectedFile != null ? selectedFile.getName() : "None";

                // if (cat == null || type == null || desc.trim().isEmpty() || addr.trim().isEmpty()) {
                //     Alert alert = new Alert(Alert.AlertType.ERROR);
                //     alert.setTitle("Validation Error");
                //     alert.setHeaderText("Missing Required Fields");
                //     alert.setContentText("Please fill all mandatory fields marked with *");
                //     alert.show();
                //     return;
                // }

                // DatabaseReference ref = FirebaseDatabase.getInstance().getReference("complaints");
                // String id = ref.push().getKey();

                // Complaint complaint = new Complaint(id ,id, cat, type, desc, addr, land, file, id);

                // ref.child(id).setValueAsync(complaint).addListener(() -> {
                //     System.out.println("Complaint submitted successfully!");
                //     Alert success = new Alert(Alert.AlertType.INFORMATION);
                //     success.setTitle("Success");
                //     success.setHeaderText("Complaint Submitted");
                //     success.setContentText("Your complaint has been submitted successfully!");
                //     success.show();

                //     // Reset form
                //     category.setValue(null);
                //     complaintType.setValue(null);
                //     description.clear();
                //     address.clear();
                //     landmark.clear();
                //     selectedFile = null;
                //     fileLabel.setText("No file chosen");

                //     initializeCitizenLoginPage();
                //     primaryStage.setScene(CitizenLoginScene);
                // }, null);
            }
        });

        Button reset = new Button("Reset");
        reset.setStyle(
                "-fx-background-color: #d76908ff; -fx-background-radius: 8; -fx-cursor: hand;-fx-font-weight: bold;");
        addHoverAnimationForButton(reset);
        reset.setOnAction(e -> {
            category.setValue(null);
            complaintType.setValue(null);
            description.clear();
            address.clear();
            landmark.clear();
            selectedFile = null;
            fileLabel.setText("No file chosen");
        });

        HBox buttonBox = new HBox(15, submit, reset);
        buttonBox.setAlignment(Pos.CENTER_RIGHT);

        form.add(new Label("Complaint Category *"), 0, 0);
        form.add(category, 1, 0);
        form.add(new Label("Complaint Type *"), 0, 1);
        form.add(complaintType, 1, 1);
        form.add(new Label("Description *"), 0, 2);
        form.add(description, 1, 2);
        form.add(new Label("Address *"), 0, 3);
        form.add(address, 1, 3);
        form.add(new Label("Landmark"), 0, 4);
        form.add(landmark, 1, 4);
        form.add(new Label("Attachments"), 0, 5);
        form.add(fileBox, 1, 5);
        form.add(buttonBox, 1, 6);

        Label formTitle = new Label("Issue Details");
        formTitle.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        formTitle.setTextFill(Color.DARKBLUE);
        formTitle.setPadding(new Insets(0, 0, 10, 0));
        formTitle.setAlignment(Pos.CENTER);

        VBox formBox = new VBox(10, formTitle, form);
        formBox.setPadding(new Insets(20));
        formBox.setSpacing(10);
        formBox.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(10), Insets.EMPTY)));
        formBox.setBorder(new Border(
                new BorderStroke(Color.LIGHTGRAY, BorderStrokeStyle.SOLID, new CornerRadii(10), new BorderWidths(2))));
        formBox.setMaxWidth(700);
        formBox.setAlignment(Pos.TOP_CENTER);

        HBox mainBox = new HBox(40, leftPane, formBox);
        mainBox.setPadding(new Insets(20));
        mainBox.setAlignment(Pos.TOP_CENTER);

        // footer

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
        contactInfo.setStyle("-fx-background-color: #1a2c44;-fx-text-fill: WHITE;");
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

        VBox fullLayout = new VBox(topNav, heading, mainBox, footer);
        fullLayout.setSpacing(10);

        ScrollPane scrollPane = new ScrollPane(fullLayout);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setPadding(new Insets(10));
     

        return scrollPane ;
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
    private void initializeCitizenLoginPage() {
        CitizenLogin cl = new CitizenLogin();
        cl.setStage2_a(primaryStage);
        CitizenLoginScene = new Scene(cl.createsScrollPane(this::handleButton), 1200, 670);
        cl.setScene2_a(CitizenLoginScene);
    }

    private void initializeCitizenRegisterPage() {
        CitizenSignUp su = new CitizenSignUp();
        su.setStage2_b(primaryStage);
        CitizenRegisterScene = new Scene(su.createScrollPane2(this::handleButton), 1200, 670);
        su.setScene2_b(CitizenRegisterScene);
    }

    private void handleButton() {
        primaryStage.setScene(ReportNewIssueScene);
    }
}
