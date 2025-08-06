package com.project1_example.View.For_Resident.CitizenPage;

import java.util.List;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.project1_example.Dao.FirebaseFileUploader;
import com.project1_example.Model.Complaint;
import com.project1_example.View.For_Resident.Citizen_Login_Register.CitizenHomePage;

import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
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
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.util.Duration;

public class CompletedReports {
    Scene scene6, CompletedPageScene, CitizenViewPageScene, CitizenReportPageScene, CitizenHomeAfterLoginScene;
    Stage stage6, primaryStage;

    public void setScene6(Scene scene6) {
        this.scene6 = scene6;
        CompletedPageScene = scene6;
    }

    public void setStage6(Stage stage6) {
        this.stage6 = stage6;
        primaryStage = stage6;
        stage6.setResizable(false);

    }

    public ScrollPane createOldSystemComplaintsPane(Runnable home2) {

        // BACK BUTTON
        HBox header1 = new HBox(350);
        Button back = new Button("Back");
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

            home2.run();

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
        // Create tab buttons
        Button ViewReportsTab = createTabButton1("View Reports", true);
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

        Button ReportsNewIssueTab = createTabButton1("Reports New Issue", true);
        ReportsNewIssueTab.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                try {
                    initializeCitizenReportpage();
                    primaryStage.setScene(CitizenReportPageScene);
                } catch (Exception e) {
                    initislizeCitizenHomePage();
                    primaryStage.setScene(CitizenHomeAfterLoginScene);
                    e.printStackTrace();
                }
            }

        });

        Button CompletedReportsTab = createTabButton1("History", false);

        navBar.getChildren().addAll(ViewReportsTab, ReportsNewIssueTab, CompletedReportsTab);

        HBox header = new HBox(40, navBar);
        header.setAlignment(Pos.CENTER);
        header.setStyle("-fx-background-color: transparent; -fx-padding: 15px;");

        // TITLE
        Label title = new Label("History");
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

        // TABLEVIEW SETUP

        TableView<Complaint> table = new TableView<>(); // ✅ The actual TableView you defined

        // Define columns

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
                            VBox textBox = new VBox(5, categoryLabel, descLabel, addressLabel, landmarkLabel, closeBtn);
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

        TableColumn<Complaint, String> statusCol = new TableColumn<>("status");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        statusCol.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(item);
                    switch (item.toLowerCase()) {
                        case "solved" -> setTextFill(Color.GREEN);
                        case "accepted" -> setTextFill(Color.ORANGE);
                        case "rejected" -> setTextFill(Color.RED);
                        default -> setTextFill(Color.BLACK);
                    }
                }
            }
        });

        TableColumn<Complaint, String> rejectionReasonCol = new TableColumn<>("Rejection Reason");
        rejectionReasonCol.setPrefWidth(300);

        // Show reason only if status == "Rejected"
        rejectionReasonCol.setCellFactory(column -> new TableCell<Complaint, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || getTableRow() == null || getTableRow().getItem() == null) {
                    setText(null);
                } else {
                    Complaint complaint = getTableRow().getItem();
                    if ("Rejected".equalsIgnoreCase(complaint.getStatus())) {
                        setText(complaint.getRejectionReason());
                    } else {
                        setText(""); // hide for approved/pending
                    }
                }
            }
        });

        table.getColumns().addAll(idCol, categoryCol, descCol, addressCol, landmarkCol, viewImageCol, statusCol,
                rejectionReasonCol);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // ✅ Observable list for all complaints
        ObservableList<Complaint> complaintList = FXCollections.observableArrayList();

        // ✅ Filtered list for search functionality
        FilteredList<Complaint> filteredList = new FilteredList<>(complaintList, p -> true);
        table.setItems(filteredList); // 🔄 Connect filtered list to table

        // ✅ SEARCH FIELD
        TextField searchField = new TextField();
        searchField.setPromptText("Search complaints...");
        searchField.textProperty().addListener((obs, oldVal, newVal) -> {
            filteredList.setPredicate(complaint -> {
                if (newVal == null || newVal.isEmpty())
                    return true;
                String filter = newVal.toLowerCase();
                return (complaint.getId() != null
                        && complaint.getId().toLowerCase().contains(filter))
                        || (complaint.getType() != null
                                && complaint.getType().toLowerCase().contains(filter))
                        || (complaint.getCategory() != null && complaint.getCategory().toLowerCase().contains(filter))
                        || (complaint.getDescription() != null
                                && complaint.getDescription().toLowerCase().contains(filter))
                        || (complaint.getAddress() != null && complaint.getAddress().toLowerCase().contains(filter))
                        || (complaint.getComplaintId() != null
                                && complaint.getComplaintId().toLowerCase().contains(filter))
                        || (complaint.getStatus() != null && complaint.getStatus().toLowerCase().contains(filter))
                        || (complaint.getLandmark() != null && complaint.getLandmark().toLowerCase().contains(filter))
                        || (complaint.getCategory() != null && complaint.getCategory().toLowerCase().contains(filter));
            });
        });

        // ✅ Fetch Firestore data in background thread
        Thread fetchDataThread = new Thread(() -> {
            try {
                Firestore db = FirebaseFileUploader.getFirestoreObject();
                ApiFuture<QuerySnapshot> future = db.collection("Complaints").get();
                List<QueryDocumentSnapshot> documents = future.get().getDocuments();

                for (QueryDocumentSnapshot doc : documents) {
                    Complaint complaint = doc.toObject(Complaint.class);
                    String status = complaint.getStatus();

                    // Now includes Accepted, Solved, and Rejected
                    if (status != null && (status.equalsIgnoreCase("Accepted") ||
                            status.equalsIgnoreCase("Solved") ||
                            status.equalsIgnoreCase("Rejected"))) {

                        Platform.runLater(() -> {
                            complaint.setId(doc.getId());
                            complaintList.add(complaint);
                        });
                    }
                }

                Platform.runLater(() -> {
                    System.out.println("Loaded " + complaintList.size() + " complaints.");
                });

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        fetchDataThread.setDaemon(true);
        fetchDataThread.start();

        VBox tableWithSearch = new VBox(10, searchField, table);
        tableWithSearch.setAlignment(Pos.CENTER);

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

        VBox content = new VBox(10, header1, header, title1, tableWithSearch, footer);
        content.setPadding(new Insets(15));

        ScrollPane scrollPane = new ScrollPane(content);
        scrollPane.setFitToWidth(true);
        scrollPane.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(10), Insets.EMPTY)));
        scrollPane.setBorder(new Border(new BorderStroke(Color.LIGHTGRAY,
                BorderStrokeStyle.SOLID, new CornerRadii(10), new BorderWidths(2))));

        return scrollPane;
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

    private void initislizeCitizenHomePage() {
        CitizenHomePage homePage = new CitizenHomePage();
        homePage.setStageCH(primaryStage);
        CitizenHomeAfterLoginScene = new Scene(homePage.createCitizenHomeScrollPane(this::handlebttonCitizenHomepage),
                1200, 670);
        homePage.setSceneCH(CitizenHomeAfterLoginScene);
    }

    private void handlebttonCitizenHomepage() {
        primaryStage.setScene(CitizenHomeAfterLoginScene);
    }

    private void initializeCitizenViewpage() {
        View_Report viewPage = new View_Report();
        viewPage.setStage5(primaryStage);
        CitizenViewPageScene = new Scene(viewPage.CreateScene5(this::handlebttonCitizenViewpage), 1200, 670);
        viewPage.setScene5(CitizenViewPageScene);
    }

    private void handlebttonCitizenViewpage() {
        initislizeCitizenHomePage();
        System.out.println("Citizen(History) View back Home");

        primaryStage.setScene(CitizenHomeAfterLoginScene);
    }

    private void initializeCitizenReportpage() {
        CitizenReportPage citizenReport = new CitizenReportPage();
        citizenReport.setStage3(primaryStage);
        CitizenReportPageScene = new Scene(citizenReport.createReportPageScrollPane(this::handlebttonCitizenReportpage),
                1200, 670);
        citizenReport.setScene3(CitizenReportPageScene);
    }

    private void handlebttonCitizenReportpage() {
        initislizeCitizenHomePage();
        System.out.println("Citizen(History) Report back Home");

        primaryStage.setScene(CitizenViewPageScene);
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

}