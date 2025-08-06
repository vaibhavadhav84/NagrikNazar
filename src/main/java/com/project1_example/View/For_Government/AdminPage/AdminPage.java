package com.project1_example.View.For_Government.AdminPage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

import com.google.api.core.ApiFuture;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.project1_example.Controller.FirebaseAuthService;
import com.project1_example.Dao.FirebaseFileUploader;
import com.project1_example.Dao.FirebaseInitializer;
import com.project1_example.Model.Complaint;
import com.project1_example.View.For_Government.Admin_Login_Register.AdminLogin;
import com.project1_example.View.For_Resident.CitizenPage.View_Report;

import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.print.PrinterJob;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.util.Duration;

public class AdminPage extends View_Report {
    Firestore db = FirebaseAuthService.getFirestoreObject();
    FirebaseAuthService authlogin = new FirebaseAuthService();
    FirebaseFileUploader db1 = new FirebaseFileUploader();
    View_Report viewReportInstance = new View_Report();

    // private View_Report viewReportInstance;
    public AdminPage(View_Report viewReportInstance) {
        this.viewReportInstance = viewReportInstance;
    }

    private TableView<Complaint> table = new TableView<>();
    private ObservableList<Complaint> complaintsList = FXCollections.observableArrayList();

    // private File selectedFile;
    Scene scene2, adminPageviewScene, adminCOmpleteReportScene, adminLoginPage, GovermentPageScene;
    Stage stage2, primaryStage;

    public void setScene2(Scene scene2) {
        this.scene2 = scene2;
        adminPageviewScene = scene2;
    }

    public void setStage2(Stage stage2) {
        this.stage2 = stage2;
        primaryStage = stage2;
    }

    public AdminPage() {
        FirebaseInitializer.initialize();
        FirebaseAuthService.getFirestoreObject();
    }

    public ScrollPane CreateScene7(Runnable Back) {

        // BACK BUTTON
        HBox header1 = new HBox(350);
        Button back = new Button("LogOut");
        back.setStyle("""
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
        back.setOnAction(e -> {
            try {
                Back.run();
            } catch (Exception ex) {
                initializeadminloginpage();
                primaryStage.setScene(adminLoginPage);
                ex.printStackTrace();
            }
        });

        addHoverAnimationForButton(back);

        Label titleLabel = new Label("NAGRIK NAZAR");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 35));
        titleLabel.setStyle("-fx-effect: dropshadow(gaussian, rgba(241, 240, 245, 0.97), 5, 0, 2, 2);");
        titleLabel.setAlignment(Pos.CENTER);

        addHoverAnimationForLabel(titleLabel);

        header1.getChildren().addAll(back, titleLabel);
        header1.setBackground(new Background(new BackgroundFill(Color.DARKSLATEBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        header1.setMaxWidth(1800);
        header1.setMinHeight(100);
        header1.setAlignment(Pos.CENTER_LEFT);

        // NAVIGATION BAR
        HBox navBar = new HBox(10);
        navBar.setAlignment(Pos.CENTER);
        Button adminHome = createTabButton1("Admin Home ", false);
        adminHome.setFont(new Font(16));

        Button history = createTabButton1("History", true);
        history.setFont(new Font(16));
        history.setOnAction(e -> {
            initializeAdminCompletedReportPage();
            ;
            primaryStage.setScene(adminCOmpleteReportScene);
        });

        navBar.getChildren().addAll(adminHome, history);

        HBox header = new HBox(40, navBar);
        header.setAlignment(Pos.CENTER);
        header.setStyle("-fx-background-color: transparent; -fx-padding: 15px;");

        // TITLE
        Label title = new Label("View Reports");
        title.setFont(Font.font(50));
        title.setStyle("""
                -fx-font-size: 30px;
                -fx-font-weight: bold;
                -fx-text-fill: linear-gradient(#ffffff, #f5f5f5);
                -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.75), 4, 0.2, 2, 2);
                """);
        title.setTextFill(Color.BLACK);
        title.setPadding(new Insets(10, 0, 10, 0));
        HBox title1 = new HBox(20, title);
        title1.setAlignment(Pos.CENTER);

        // ---------------------- TABLE VIEW SETUP ----------------------
        TableView<Complaint> table = new TableView<>();

        TableColumn<Complaint, String> idCol = new TableColumn<>("Token ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id")); // Field name from Complaint.java
        idCol.setPrefWidth(150);

        TableColumn<Complaint, String> categoryCol = new TableColumn<>("Category");
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));

        TableColumn<Complaint, String> typeCol = new TableColumn<>("ComplaintType");
        typeCol.setCellValueFactory(new PropertyValueFactory<>("complaintType"));

        TableColumn<Complaint, String> descCol = new TableColumn<>("Description");
        descCol.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableColumn<Complaint, String> addressCol = new TableColumn<>("Address");
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));

        TableColumn<Complaint, String> landmarkCol = new TableColumn<>("Landmark");
        landmarkCol.setCellValueFactory(new PropertyValueFactory<>("landmark"));

        TableColumn<Complaint, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        statusCol.setCellValueFactory(cellData -> new SimpleStringProperty("Pending"));

        TableColumn<Complaint, String> dateCol = new TableColumn<>("Date");
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        dateCol.setSortType(TableColumn.SortType.DESCENDING);

        TableColumn<Complaint, Void> viewImageCol = new TableColumn<>("View Image");

        viewImageCol.setCellFactory(col -> new TableCell<Complaint, Void>() {
            private final Hyperlink link = new Hyperlink("Open");
            {
                link.setOnAction(e -> {
                    Complaint complaint = getTableView().getItems().get(getIndex());
                    String url = complaint.getAttachmentUrl();

                    if (url != null && !url.isEmpty()) {
                        try {
                            // Create a popup stage
                            Stage popupStage = new Stage();
                            popupStage.setTitle("Complaint Image Preview");

                            // Load image

                            ImageView imageView = new ImageView(new Image(url.replace(" ", "%20")));
                            imageView.setFitWidth(635);
                            imageView.setPreserveRatio(true);
                            imageView.setStyle("-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.6), 10, 0.5, 0, 2);"
                                    + "-fx-border-color: #dcdde1; -fx-border-width: 4px; -fx-border-radius: 10px;");

                            // Text Info
                            Text categoryTitle = new Text("Category: ");
                            categoryTitle.setStyle("-fx-font-weight: bold; -fx-font-size: 22px; -fx-fill: #2d3436;");

                            Text categoryValue = new Text(complaint.getCategory());
                            categoryValue.setStyle("-fx-font-size: 20px; -fx-fill: #2d3436;");

                            TextFlow categoryLabel = new TextFlow(categoryTitle, categoryValue);
                            categoryLabel.setMaxWidth(600);

                            Text descTitle = new Text("Description: ");
                            descTitle.setStyle("-fx-font-weight: bold; -fx-font-size: 22px; -fx-fill: #2d3436;");

                            Text descValue = new Text(complaint.getDescription());
                            descValue.setStyle("-fx-font-size: 20px; -fx-fill: #2d3436;");

                            TextFlow descLabel = new TextFlow(descTitle, descValue);
                            descLabel.setMaxWidth(600);

                            Text addressTitle = new Text("Address: ");
                            addressTitle.setStyle("-fx-font-weight: bold; -fx-font-size: 22px; -fx-fill: #2d3436;");

                            Text addressValue = new Text(complaint.getAddress());
                            addressValue.setStyle("-fx-font-size: 20px; -fx-fill: #2d3436;");

                            TextFlow addressLabel = new TextFlow(addressTitle, addressValue);
                            addressLabel.setMaxWidth(600);

                            Text landmarkTitle = new Text("Landmark: ");
                            landmarkTitle.setStyle("-fx-font-weight: bold; -fx-font-size: 22px; -fx-fill: #2d3436;");

                            Text landmarkValue = new Text(complaint.getLandmark());
                            landmarkValue.setStyle("-fx-font-size: 20px; -fx-fill: #2d3436;");

                            TextFlow landmarkLabel = new TextFlow(landmarkTitle, landmarkValue);
                            landmarkLabel.setMaxWidth(600);

                            VBox deptBox = new VBox();
                            if ("Accepted".equalsIgnoreCase(complaint.getStatus())) {
                                Text officeTitle = new Text("Office: ");
                                officeTitle.setStyle("-fx-font-weight: bold; -fx-font-size: 22px; -fx-fill: #2d3436;");

                                Text officeValue = new Text(getOfficeNameByCategory(complaint.getCategory()));
                                officeValue.setStyle("-fx-font-size: 20px; -fx-fill: #2d3436;");

                                TextFlow officeLabel = new TextFlow(officeTitle, officeValue);
                                officeLabel.setMaxWidth(600);

                                Text numberTitle = new Text("Contact: ");
                                numberTitle.setStyle("-fx-font-weight: bold; -fx-font-size: 22px; -fx-fill: #2d3436;");

                                Text numberValue = new Text(getOfficeNumberByCategory(complaint.getCategory()));
                                numberValue.setStyle("-fx-font-size: 20px; -fx-fill: #2d3436;");

                                TextFlow numberLabel = new TextFlow(numberTitle, numberValue);
                                numberLabel.setMaxWidth(600);

                                deptBox.getChildren().addAll(officeLabel, numberLabel);

                            }

                            Text rejectionTitle = new Text("Rejection Reason: ");
                            rejectionTitle
                                    .setStyle("-fx-font-weight: bold; -fx-font-size: 22px; -fx-fill: #d63031;");

                            Text rejectionValue = new Text(
                                    "Rejected".equalsIgnoreCase(complaint.getStatus())
                                            && complaint.getRejectionReason() != null
                                                    ? complaint.getRejectionReason()
                                                    : "N/A");
                            rejectionValue.setStyle("-fx-font-size: 20px; -fx-fill: #d63031;");

                            TextFlow rejectionLabel = new TextFlow(rejectionTitle, rejectionValue);
                            rejectionLabel.setMaxWidth(600);

                            Button closeBtn = new Button("Close");
                            closeBtn.setStyle("""
                                        -fx-background-color: #d63031;
                                        -fx-text-fill: white;
                                        -fx-font-weight: bold;
                                        -fx-font-size: 14px;
                                        -fx-background-radius: 8;
                                        -fx-cursor: hand;
                                        -fx-padding: 8 20 8 20;
                                    """);
                            closeBtn.setOnAction(event -> popupStage.close());

                            // Styling
                            VBox textBox = new VBox(5, categoryLabel, descLabel, addressLabel, landmarkLabel, deptBox,rejectionLabel,
                                    closeBtn);
                            textBox.setPadding(new Insets(10));

                            ScrollPane scrollPane = new ScrollPane(imageView);
                            scrollPane.setFitToWidth(true);
                            scrollPane.setStyle("-fx-background: transparent;");

                            VBox contentBox = new VBox(15, scrollPane, textBox);
                            contentBox.setPadding(new Insets(20));
                            contentBox.setAlignment(Pos.CENTER);

                            Scene popupScene = new Scene(contentBox, 700, 600);
                            popupStage.setScene(popupScene);
                            popupStage.show();

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(link);
                }
            }
        });

        TableColumn<Complaint, Void> actionCol = new TableColumn<>("Action");

        actionCol.setCellFactory(col -> new TableCell<>() {

            private final Button acceptBtn = new Button("Accept");
            private final Button rejectBtn = new Button("Reject");
            private final HBox buttonBox = new HBox(10, acceptBtn, rejectBtn);
            private final Label statusLabel = new Label();

            {
                acceptBtn.setStyle("-fx-background-color: green; -fx-text-fill: white;");
                rejectBtn.setStyle("-fx-background-color: red; -fx-text-fill: white;");

                acceptBtn.setOnAction(event -> {
                    Complaint complaint = getTableView().getItems().get(getIndex());
                    if (complaint.getId() != null && !complaint.getId().isEmpty()) {
                        updateStatusInFirestore(complaint.getId(), "Accepted");
                        complaint.setStatus("Resolved");
                        table.refresh();
                    } else {
                        System.out.println("❌ Accept Failed: Complaint ID is null or empty");
                    }
                });

                rejectBtn.setOnAction(evt -> {
                    Complaint complaint = getTableView().getItems().get(getIndex());

                    if (complaint.getId() == null || complaint.getId().isEmpty()) {
                        System.out.println("❌ Reject Failed: Complaint ID missing");
                        return;
                    }

                    TextInputDialog dialog = new TextInputDialog();
                    dialog.setTitle("Reject Complaint");
                    dialog.setHeaderText("Enter a description / reason for rejecting:");
                    dialog.setContentText("Description:");

                    dialog.showAndWait().ifPresent(reason -> {
                        // save to Firestore
                        updateRejectionInFirestore(complaint.getId(), "Rejected", reason);

                        // update in-memory object so the table refreshes immediately
                        complaint.setStatus("Rejected");
                        complaint.setRejectionReason(reason);

                        table.refresh();
                    });
                });

            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || getIndex() >= getTableView().getItems().size()) {
                    setGraphic(null);
                } else {
                    Complaint complaint = getTableView().getItems().get(getIndex());
                    String status = complaint.getStatus();

                    if (status == null || status.equalsIgnoreCase("Pending")) {
                        setGraphic(buttonBox);
                    } else {
                        statusLabel.setText(status);
                        statusLabel.setStyle(status.equalsIgnoreCase("Resolved")
                                ? "-fx-text-fill: green; -fx-font-weight: bold;"
                                : "-fx-text-fill: red; -fx-font-weight: bold;");
                        setGraphic(statusLabel);

                    }
                }
            }
        });

        TableColumn<Complaint, String> deptContactCol = new TableColumn<>("Department Contact");
        deptContactCol.setCellValueFactory(cellData -> {
            Complaint complaint = cellData.getValue();
            if ("Accepted".equalsIgnoreCase(complaint.getStatus())) {
                String name = getOfficeNameByCategory(complaint.getCategory());
                String number = getOfficeNumberByCategory(complaint.getCategory());
                return new SimpleStringProperty(name + " - " + number);
            } else {
                return new SimpleStringProperty("-");
            }
        });

        TableColumn<Complaint, String> rejectionReasonCol = new TableColumn<>("Rejection Reason");
        rejectionReasonCol.setCellValueFactory(new PropertyValueFactory<>("rejectionReason"));
        rejectionReasonCol.setPrefWidth(180);

        table.getColumns().addAll(idCol, categoryCol, descCol, addressCol, landmarkCol, dateCol,
                viewImageCol, actionCol, rejectionReasonCol, deptContactCol);

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        ObservableList<Complaint> complaintList = FXCollections.observableArrayList();

        FilteredList<Complaint> filteredList = new FilteredList<>(complaintList, p -> true);
        table.setItems(filteredList);

        TextField searchField = new TextField();
        searchField.setPromptText("Search complaints...");
        searchField.textProperty().addListener((obs, oldVal, newVal) -> {
            filteredList.setPredicate(complaint -> {
                if (newVal == null || newVal.isEmpty())
                    return true;
                String filter = newVal.toLowerCase();
                return (complaint.getType() != null
                        && complaint.getType().toLowerCase().contains(filter)) ||
                        (complaint.getDescription() != null
                                && complaint.getDescription().toLowerCase().contains(filter))
                        ||
                        (complaint.getAddress() != null && complaint.getAddress().toLowerCase().contains(filter)) ||
                        (complaint.getLandmark() != null && complaint.getLandmark().toLowerCase().contains(filter)) ||
                        (complaint.getCategory() != null && complaint.getCategory().toLowerCase().contains(filter));
            });
        });

        Thread fetchDataThread = new Thread(() -> {
            try {
                Firestore db = FirebaseFileUploader.getFirestoreObject();
                List<QueryDocumentSnapshot> documents = db.collection("Complaints").get().get().getDocuments();

                for (QueryDocumentSnapshot doc : documents) {
                    Complaint complaint = doc.toObject(Complaint.class);
                    Platform.runLater(() -> complaintList.add(complaint));
                    complaint.setId(doc.getId());

                }

                viewReportInstance.fetchComplaintsFromFirestore();
                if (viewReportInstance != null) {
                    Platform.runLater(() -> viewReportInstance.fetchComplaintsFromFirestore());
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        fetchDataThread.setDaemon(true);
        fetchDataThread.start();

        Button downloadBtn = new Button("Download");
        downloadBtn.setOnAction(e -> {
            try (PrintWriter writer = new PrintWriter("complaints_export.txt")) {
                for (Complaint c : filteredList) {
                    writer.println(c);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        Button printBtn = new Button("Print");
        printBtn.setOnAction(e -> {
            PrinterJob job = PrinterJob.createPrinterJob();
            if (job != null && job.showPrintDialog(stage2)) {
                boolean success = job.printPage(table);
                if (success)
                    job.endJob();
            }
        });

        HBox exportBar = new HBox(10, downloadBtn, printBtn);
        exportBar.setAlignment(Pos.CENTER_RIGHT);

        VBox tableWithControls = new VBox(10, searchField, exportBar, table);
        tableWithControls.setPadding(new Insets(10));
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

        VBox content = new VBox(10, header1, header, title1, tableWithControls, footer);
        content.setPadding(new Insets(15));

        ScrollPane scrollPane = new ScrollPane(content);
        scrollPane.setFitToWidth(true);
        scrollPane.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(10), Insets.EMPTY)));
        scrollPane.setBorder(new Border(new BorderStroke(Color.LIGHTGRAY,
                BorderStrokeStyle.SOLID, new CornerRadii(10), new BorderWidths(2))));

        loadComplaintsFromFirestore();

        return scrollPane;
    }

    private String getOfficeNameByCategory(String category) {
        if (category == null)
            return "Unknown Department";

        switch (category.toLowerCase()) {
            case "water":
                return "Water Supply Department";
            case "electricity":
                return "Power Distribution Office";
            case "road":
                return "Public Works Department";
            case "garbage":
                return "Municipal Waste Department";
            case "drainage":
                return "Drainage Maintenance Department";
            case "street light":
                return "Urban Lighting Department";
            case "sanitation":
                return "Sanitation and Hygiene Department";
            case "public transport":
                return "City Transport Authority";
            case "parks":
                return "Parks and Recreation Department";
            case "sewage":
                return "Sewage Treatment Board";
            case "internet":
                return "IT and Infrastructure Department";
            case "building safety":
                return "Municipal Building Safety Division";
            default:
                return "General Civic Department";
        }
    }

    private String getOfficeNumberByCategory(String category) {
        if (category == null)
            return "N/A";

        switch (category.toLowerCase()) {
            case "water":
                return "022-123456";
            case "electricity":
                return "022-654321";
            case "road":
                return "022-987654";
            case "garbage":
                return "022-111222";
            case "drainage":
                return "022-333444";
            case "street light":
                return "022-555666";
            case "sanitation":
                return "022-777888";
            case "public transport":
                return "022-909090";
            case "parks":
                return "022-888777";
            case "sewage":
                return "022-666555";
            case "internet":
                return "022-444333";
            case "building safety":
                return "022-222111";
            default:
                return "022-000000";
        }
    }

    private void updateRejectionInFirestore(String documentId,
            String newStatus,
            String reason) {
        Firestore db = FirestoreClient.getFirestore();

        db.collection("Complaints")
                .document(documentId)
                .update(
                        "status", newStatus,
                        "statusUpdatedAt", Timestamp.now(),
                        "rejectionReason", reason,
                        "approval", "Denied")
                .addListener(() -> System.out.println("✅ Stored rejection reason: " + reason),
                        Executors.newSingleThreadExecutor());
    }

    private Button createTabButton1(String text, boolean isActive) {
        Button tab = new Button(text);
        tab.setFont(Font.font("Arial", 14));
        tab.setPrefHeight(40);
        tab.setPrefWidth(200);
        if (isActive) {
            tab.setStyle("-fx-background-color: #6c5ce7; -fx-text-fill: white; -fx-border-color: #6c5ce7;");
        } else {
            tab.setStyle("-fx-background-color: #f8f9fa; -fx-text-fill: #2c3e50; -fx-border-color: #dee2e6;");
            tab.setOnMouseEntered(e -> tab.setStyle("-fx-background-color: #e9ecef; -fx-text-fill: #2c3e50;"));
            tab.setOnMouseExited(e -> tab.setStyle("-fx-background-color: #f8f9fa; -fx-text-fill: #2c3e50;"));
        }
        return tab;
    }

    private void loadComplaintsFromFirestore() {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = db.collection("Complaints").get();

        new Thread(() -> {
            try {
                List<QueryDocumentSnapshot> documents = future.get().getDocuments();
                List<Complaint> complaints = new ArrayList<>();

                for (QueryDocumentSnapshot doc : documents) {
                    Complaint complaint = doc.toObject(Complaint.class);
                    complaint.setDocumentId(doc.getId()); // important!
                    complaints.add(complaint);
                }

                Platform.runLater(() -> complaintsList.setAll(complaints));
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void updateStatusInFirestore(String documentId, String newStatus) {
        if (documentId == null || documentId.trim().isEmpty()) {
            System.err.println("❌ Firestore Update Failed: documentId is null or empty");
            return;
        }

        Firestore db = FirestoreClient.getFirestore();
        db.collection("Complaints").document(documentId)
                .update("status", newStatus,
                        "statusUpdatedAt", Timestamp.now(),
                        "approval", "Verified")
                .addListener(() -> {
                    System.out.println("✅ Status updated to: " + newStatus);
                    Platform.runLater(() -> fetchComplaintsFromFirestore());
                }, Executors.newSingleThreadExecutor());

    }

    public void updateComplaintStatusInFirestore(Complaint complaint, String newStatus) {
        Firestore db = FirestoreClient.getFirestore();

        ApiFuture<WriteResult> future = db.collection("Complaints")
                .document(complaint.getDocumentId())
                .update("status", newStatus);

        new Thread(() -> {
            try {
                future.get(); // Waits for the update to complete

                Platform.runLater(() -> {
                    complaint.setStatus(newStatus);
                    table.refresh();
                });

                System.out.println("Status updated to: " + newStatus);
            } catch (Exception e) {
                e.printStackTrace();
                Platform.runLater(() -> showAlert("Error", "Failed to update complaint status."));
            }
        }).start();

    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Login Page -Navigation
    private void initializeadminloginpage() {
        AdminLogin ul = new AdminLogin();
        ul.setStage1_a(primaryStage);
        adminLoginPage = new Scene(ul.createsAdminLoginScrollPane(this::handlebuttnadminlogin), 1200, 670);
        ul.setScene1_a(adminLoginPage);

    }

    private void handlebuttnadminlogin() {
        initializeadminloginpage();
        primaryStage.setScene(adminLoginPage);
    }

    private void initializeAdminCompletedReportPage() {
        AdminCompletedReports completedReports = new AdminCompletedReports();
        completedReports.setStage6(primaryStage);
        adminCOmpleteReportScene = new Scene(
                completedReports.createAdminCompletedReportsPane(this::handleButtonAdminPage, null), 1200, 670);
        completedReports.setScene6(adminCOmpleteReportScene);

    }

    private void handleButtonAdminPage() {

        primaryStage.setScene(adminPageviewScene);
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

} // End of AdminPage
