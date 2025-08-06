package com.project1_example.View.For_Resident.CitizenPage;

import java.io.File;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.project1_example.Dao.FirebaseFileUploader;
import com.project1_example.Dao.FirebaseInitializer;
import com.project1_example.Dao.FirebaseUtil;
import com.project1_example.Model.Complaint;

import com.project1_example.View.For_Resident.Citizen_Login_Register.CitizenHomePage;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.stage.Modality;

public class CitizenReportPage {
    Scene scene3, CitizenReportPageScene, CitizenViewPageScene, CompletedReportScene, CitizenHomeAfterLoginScene;
    Stage stage3, primaryStage;

    public void setScene3(Scene scene3) {
        this.scene3 = scene3;
        CitizenReportPageScene = scene3;
    }

    public void setStage3(Stage stage3) {
        this.stage3 = stage3;
        primaryStage = stage3;
        stage3.setResizable(false);
    }

    static {
        FirebaseInitializer.initialize();
    }

    private ComboBox<String> category;
    private ComboBox<String> complaintType;
    private TextField description;
    private TextField address;
    private TextField landmark;
    private Label fileLabel;

    private Label fileUploadStatusLabel;

    public CitizenReportPage() {
        FirebaseInitializer.initialize(); // Initialize Firebase only once

    }

    Firestore db = FirebaseUtil.getFirestoreObject();
    private File selectedFile;

    public ScrollPane createReportPageScrollPane(Runnable home3) {

        // ---------- TOP NAV BAR ----------
        HBox topNav = new HBox(350);
        topNav.setPadding(new Insets(15));
        topNav.setBackground(new Background(new BackgroundFill(Color.DARKSLATEBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        topNav.setMaxWidth(1800);
        topNav.setMinHeight(100);
        topNav.setAlignment(Pos.CENTER_LEFT);

        Label titleLabel = new Label("NAGRIK NAZAR");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 35));
        titleLabel.setStyle("-fx-effect: dropshadow(gaussian, rgba(241, 240, 245, 0.97), 5, 0, 2, 2);");
        titleLabel.setAlignment(Pos.CENTER);
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
        backHomePage.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {

                home3.run();

            }

        });

        topNav.getChildren().addAll(backHomePage, titleLabel);

        // NavBar
        HBox navBar = new HBox(10);
        navBar.setAlignment(Pos.CENTER);
        // Create tab buttons
        Button ViewReportsTab = createTabButton2("View Reports", true);
        ViewReportsTab.setFont(new Font(16));

        ViewReportsTab.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                try {
                    initializeCitizenViewpage();
                    primaryStage.setScene(CitizenViewPageScene);
                } catch (Exception e) {
                    initislizeCitizenHomePage();
                    primaryStage.setScene(CitizenHomeAfterLoginScene);
                    e.printStackTrace();
                }
            }

        });
        Button ReportsNewIssueTab = createTabButton2("Reports New Issue", false);
        ReportsNewIssueTab.setFont(new Font(16));

        Button CompletedReportsTab = createTabButton2("History", true);
        CompletedReportsTab.setFont(new Font(16));
        CompletedReportsTab.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                try {
                    initializeCitizenCompletedReportpage();
                    primaryStage.setScene(CompletedReportScene);
                } catch (Exception e) {
                    initislizeCitizenHomePage();
                    primaryStage.setScene(CitizenHomeAfterLoginScene);
                    e.printStackTrace();
                }
            }

        });

        navBar.getChildren().addAll(ViewReportsTab, ReportsNewIssueTab, CompletedReportsTab);

        HBox header = new HBox(40, navBar);
        header.setStyle("-fx-background-color: transperent; -fx-padding: 15px;");
        header.setAlignment(Pos.CENTER);

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

        category = new ComboBox<>();
        complaintType = new ComboBox<>();
        description = new TextField();
        address = new TextField();
        landmark = new TextField();
        fileLabel = new Label("No file chosen");

        TableColumn<Complaint, String> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));

        ComboBox<String> category = new ComboBox<>();
        category.getItems().addAll(
                "Water",
                "Electricity",
                "Road",
                "Garbage",
                "Drainage",
                "Street Light",
                "Sanitation",
                "Public Transport",
                "Parks",
                "Sewage",
                "Internet",
                "Building Safety");
        category.setPromptText("Select Category");
        category.setEditable(true);

        ComboBox<String> complaintType = new ComboBox<>();
        complaintType.getItems().addAll(
                "Leakage",
                "No Power",
                "Pothole",
                "Overflowing Bin",
                "No Supply",
                "Voltage Fluctuation",
                "Blocked Drain",
                "Light Not Working",
                "Unclean Area",
                "Bus Not On Time",
                "Damaged Park Equipment",
                "Backflow",
                "Slow Internet",
                "Illegal Construction");
        complaintType.setPromptText("Select Complaint");
        complaintType.setEditable(true);

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
            selectedFile = fileChooser.showOpenDialog(stage3);
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
        submit.setOnAction(event -> {

            String cat = category.getValue();
            String type = complaintType.getValue();
            String desc = description.getText();
            String addr = address.getText();
            String land = landmark.getText();

            if (cat == null || type == null || desc.trim().isEmpty() || addr.trim().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Validation Error", "Please fill all mandatory fields.");
                return;
            }

            // Show progress spinner
            Stage loader = showLoadingStage("Submitting...");
            loader.show();

            new Thread(() -> {
                try {
                    String fileUrl = null;
                    if (selectedFile != null) {
                        fileUrl = FirebaseFileUploader.uploadFileToFirebase(selectedFile, cat, type, desc, addr, land,
                                selectedFile);
                    }

                    DatabaseReference complaintsRef = FirebaseDatabase.getInstance().getReference("complaints");
                    String id = complaintsRef.push().getKey();
                    String status = "Pending";
                    String approval = "Pending";

                    Complaint complaint = new Complaint(
                            id, // id
                            cat, // category
                            type, // complaintType
                            desc, // description
                            addr, // address
                            land, // landmark
                            fileUrl != null ? fileUrl : "", // filename
                            status, // status
                            approval // approval
                    );
                    complaintsRef.child(id).setValueAsync(complaint);


                    // On success, switch back to UI thread
                    javafx.application.Platform.runLater(() -> {
                        loader.close();
                        showAlert(Alert.AlertType.INFORMATION, "Success", "Complaint submitted successfully.");
                        resetForm(); // clear the form
                    });


                } catch (Exception e) {
                    e.printStackTrace();
                    javafx.application.Platform.runLater(() -> {
                        loader.close();
                        showAlert(Alert.AlertType.ERROR, "Error", "Upload failed: " + e.getMessage());
                    });
                }
            }).start();
        });

        Button reset = new Button("Reset");
        reset.setStyle(
                "-fx-background-color: #d76908ff; -fx-background-radius: 8; -fx-cursor: hand;-fx-font-weight: bold;");
        addHoverAnimationForButton(reset);
        reset.setOnAction(e -> resetForm());

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
        formBox.setBorder(new Border(new BorderStroke(Color.LIGHTGRAY,
                BorderStrokeStyle.SOLID, new CornerRadii(10), new BorderWidths(2))));
        formBox.setMaxWidth(700);
        formBox.setAlignment(Pos.TOP_CENTER);

        HBox mainBox = new HBox(40, leftPane, formBox);
        mainBox.setPadding(new Insets(20));
        mainBox.setAlignment(Pos.TOP_CENTER);

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

        VBox fullLayout = new VBox(topNav, header, heading, mainBox, footer);
        fullLayout.setSpacing(10);

        ScrollPane scrollPane = new ScrollPane(fullLayout);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setPadding(new Insets(10));
        // StackPane root = new StackPane(scrollPane1);
        // root.setBackground(background);

        return scrollPane;
    }

    private Button createTabButton2(String text, boolean isActive) {
        Button tab = new Button(text);
        tab.setFont(Font.font("Arial", 14));
        tab.setPrefHeight(40);
        tab.setPrefWidth(200);

        if (isActive) {
            tab.setStyle("-fx-background-color: #6c5ce7; " +
                    "-fx-text-fill: white; " +
                    "-fx-border-color: #6c5ce7; " +
                    "-fx-border-width: 2; " +
                    "-fx-background-radius: 0; " +
                    "-fx-border-radius: 0;");
        } else {
            tab.setStyle("-fx-background-color: #f8f9fa; " +
                    "-fx-text-fill: #2c3e50; " +
                    "-fx-border-color: #dee2e6; " +
                    "-fx-border-width: 1; " +
                    "-fx-background-radius: 0; " +
                    "-fx-border-radius: 0;");

            // Hover effect for inactive tabs
            tab.setOnMouseEntered(e -> {
                tab.setStyle("-fx-background-color: #e9ecef; " +
                        "-fx-text-fill: #2c3e50; " +
                        "-fx-border-color: #dee2e6; " +
                        "-fx-border-width: 1; " +
                        "-fx-background-radius: 0; " +
                        "-fx-border-radius: 0;");
            });

            tab.setOnMouseExited(e -> {
                tab.setStyle("-fx-background-color: #f8f9fa; " +
                        "-fx-text-fill: #2c3e50; " +
                        "-fx-border-color: #dee2e6; " +
                        "-fx-border-width: 1; " +
                        "-fx-background-radius: 0; " +
                        "-fx-border-radius: 0;");
            });
        }

        return tab;
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void resetForm() {
        if (category != null)
            category.setValue(null);
        if (complaintType != null)
            complaintType.setValue(null);
        if (description != null)
            description.clear();
        if (address != null)
            address.clear();
        if (landmark != null)
            landmark.clear();
        selectedFile = null;

        // If you show the file name or image somewhere, clear it too
        fileLabel.setText("No file selected"); // if you're using a label

    }

    private Stage showLoadingStage(String message) {
        Stage stage = new Stage();
        VBox box = new VBox(10);
        box.setAlignment(Pos.CENTER);

        ProgressIndicator progress = new ProgressIndicator();
        Label label = new Label(message);

        box.getChildren().addAll(progress, label);
        Scene scene = new Scene(box, 200, 120);

        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Loading...");
        return stage;
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

    private void initislizeCitizenHomePage() {
        CitizenHomePage homePage2 = new CitizenHomePage();
        homePage2.setStageCH(primaryStage);
        CitizenHomeAfterLoginScene = new Scene(homePage2.createCitizenHomeScrollPane(this::handlebttonCitizenHomepage),
                1200, 670);
        homePage2.setSceneCH(CitizenHomeAfterLoginScene);
    }

    private void handlebttonCitizenHomepage() {
        primaryStage.setScene(CitizenHomeAfterLoginScene);
    }

    private void initializeCitizenViewpage() {
        View_Report viewPageReport = new View_Report();
        viewPageReport.setStage5(primaryStage);
        CitizenViewPageScene = new Scene(viewPageReport.CreateScene5(this::handlebttonCitizenViewpage), 1200,
                670);
        viewPageReport.setScene5(CitizenViewPageScene);
    }

    private void handlebttonCitizenViewpage() {
        initislizeCitizenHomePage();
        System.out.println("Citizen(Report) View back Home");
        primaryStage.setScene(CitizenHomeAfterLoginScene);
    }

    private void initializeCitizenCompletedReportpage() {
        CompletedReports completedReport = new CompletedReports();
        completedReport.setStage6(primaryStage);
        CompletedReportScene = new Scene(
                completedReport.createOldSystemComplaintsPane(this::handlebttonCitizenCompletedpage), 1200, 670);
        completedReport.setScene6(CompletedReportScene);
    }

    private void handlebttonCitizenCompletedpage() {
        initislizeCitizenHomePage();
        System.out.println("Citizen(View) History back Home");
        primaryStage.setScene(CitizenHomeAfterLoginScene);
    }

}
