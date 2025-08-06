package com.project1_example.View.Home;

import com.project1_example.View.About_US.about_us;
import com.project1_example.View.For_Government.For_Government;
import com.project1_example.View.For_Resident.For_Resident;
import com.project1_example.View.Report_New_Issue.ReportNewIssue;

import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Home extends Application {

    Scene mainHomePage, forGovernment, forResidentPage, reportNewIssuePage, loginafterCitizenPage, AboutUsPageScene;
    Stage primaryStage;

    @Override
    public void start(Stage stage) {

        stage.setTitle("Nagrik Nazar");

        // ---------- TOP NAV BAR ----------
        HBox topNav = new HBox(20);
        topNav.setPadding(new Insets(10, 3, 0, 0));
        // topNav.setStyle("-fx-background-color: rgba(26, 44, 68, 0.6);");

        Label title2 = new Label("NAGRIK NAZAR");

        title2.setStyle("""
                    -fx-font-size: 23px;
                    -fx-font-weight: bold;
                    -fx-text-fill: linear-gradient(#ffffff, #f5f5f5);
                    -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.75), 4, 0.2, 2, 2);
                """);

        title2.setAlignment(Pos.TOP_LEFT);
        title2.setPadding(new Insets(10, 0, 10, 10));

        addHoverAnimationForLabel(title2);

        Button govBtn = new Button("For Governments");

        govBtn.setStyle("""
                    -fx-background-color: rgba(255, 255, 255, 0.15);
                    -fx-text-fill: white;
                    -fx-font-weight: bold;
                    -fx-font-size: 14px;
                    -fx-background-radius: 12;
                    -fx-border-radius: 12;
                    -fx-border-color: rgba(3, 144, 253, 0.5);
                    -fx-border-width: 1;
                    -fx-padding: 8 20 8 20;
                    -fx-cursor: hand;
                """);

        govBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                initializeforgovernment();
                primaryStage.setScene(forGovernment);
            }

        });

        Button resBtn = new Button("For Residents");

        resBtn.setStyle("""
                    -fx-background-color: rgba(255, 255, 255, 0.15);
                    -fx-text-fill: white;
                    -fx-font-weight: bold;
                    -fx-font-size: 14px;
                    -fx-background-radius: 12;
                    -fx-border-radius: 12;
                    -fx-border-color: rgba(4, 106, 248, 0.5);
                    -fx-border-width: 1;
                    -fx-padding: 8 20 8 20;
                    -fx-cursor: hand;
                """);

        resBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                initializeforresident();
                primaryStage.setScene(forResidentPage);
            }

        });

        Button reportBtn = new Button("Report an Issue");

        reportBtn.setStyle("""
                    -fx-background-color: rgba(255, 255, 255, 0.15);
                    -fx-text-fill: white;
                    -fx-font-weight: bold;
                    -fx-font-size: 14px;
                    -fx-background-radius: 12;
                    -fx-border-radius: 12;
                    -fx-border-color: rgba(7, 143, 248, 0.5);
                    -fx-border-width: 1;
                    -fx-padding: 8 20 8 20;
                    -fx-cursor: hand;
                """);

        reportBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                initializeReportNewIssue();
                primaryStage.setScene(reportNewIssuePage);
            }

        });

        Button aboutUsBtn = new Button("About Us");

        aboutUsBtn.setStyle("""
                    -fx-background-color: rgba(255, 255, 255, 0.15);
                    -fx-text-fill: white;
                    -fx-font-weight: bold;
                    -fx-font-size: 14px;
                    -fx-background-radius: 12;
                    -fx-border-radius: 12;
                    -fx-border-color: rgba(2, 115, 243, 0.5);
                    -fx-border-width: 1;
                    -fx-padding: 8 20 8 20;
                    -fx-cursor: hand;
                """);

        aboutUsBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                initializeAboutUS();
                primaryStage.setScene(AboutUsPageScene);
            }

        });

        // animation
        addHoverAnimationForButton(govBtn);
        addHoverAnimationForButton(resBtn);
        addHoverAnimationForButton(reportBtn);
        addHoverAnimationForButton(aboutUsBtn);

        HBox topBtn1 = new HBox(9, title2);
        topBtn1.setAlignment(Pos.TOP_LEFT);
        HBox topBtn = new HBox(7, govBtn, resBtn, reportBtn, aboutUsBtn);
        topBtn.setAlignment(Pos.TOP_RIGHT);

        HBox topBtn2 = new HBox(410, topBtn1, topBtn);
        topNav.getChildren().addAll(topBtn2);
        topNav.setPrefHeight(50);

        // ---------- CENTER CONTENT ----------

        // Background image with blur
        ImageView bgImage = new ImageView(new Image("Assets\\Images\\homeBackground.jpeg"));
        bgImage.setFitWidth(1200);
        bgImage.setFitHeight(670);
        bgImage.setPreserveRatio(false);
        bgImage.setSmooth(true);
        bgImage.setCache(true);
        bgImage.setEffect(new GaussianBlur(10)); // Apply blur only to background

        // Content VBox
        VBox centerBox = new VBox(20);
        centerBox.setAlignment(Pos.CENTER);
        centerBox.setPrefSize(800, 600);
        centerBox.setPadding(new Insets(20));

        // Title
        Label title = new Label("Click it. Report it. Fix it");
        title.setTextFill(Color.WHITE);
        title.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        title.setTextAlignment(TextAlignment.CENTER);
        title.setWrapText(true);

        Label title_a = new Label("Nagrik Nazar is a citizen-first initiative that allows residents to report\n\r " +
                "civic issues—from potholes to waterlogging—directly to the authorities.");
        title_a.setTextFill(Color.WHITE);
        title_a.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        // Buttons
        HBox actionBtns = new HBox(30);
        Button govAction = new Button("← GOVERNMENT");
        govAction.setStyle("""
                    -fx-background-color: rgba(255, 255, 255, 0.15);
                    -fx-text-fill: white;
                    -fx-font-weight: bold;
                    -fx-font-size: 14px;
                    -fx-background-radius: 12;
                    -fx-border-radius: 12;
                    -fx-border-color: rgba(4, 137, 246, 0.5);
                    -fx-border-width: 1;
                    -fx-padding: 8 20 8 20;
                    -fx-cursor: hand;
                """);

        govAction.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                initializeforgovernment();
                primaryStage.setScene(forGovernment);
            }

        });

        Button resAction = new Button("RESIDENTS →");

        resAction.setStyle("""
                    -fx-background-color: rgba(255, 255, 255, 0.15);
                    -fx-text-fill: white;
                    -fx-font-weight: bold;
                    -fx-font-size: 14px;
                    -fx-background-radius: 12;
                    -fx-border-radius: 12;
                    -fx-border-color: rgba(7, 130, 246, 0.5);
                    -fx-border-width: 1;
                    -fx-padding: 8 20 8 20;
                    -fx-cursor: hand;
                """);
        resAction.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                initializeforresident();
                primaryStage.setScene(forResidentPage);
            }

        });

        actionBtns.setAlignment(Pos.CENTER);
        actionBtns.getChildren().addAll(govAction, resAction);

        // animation
        addHoverAnimationForButton(govAction);
        addHoverAnimationForButton(resAction);

        centerBox.getChildren().addAll(title, actionBtns);

        // ---------- FOOTER ----------
        HBox footer = new HBox();
        footer.setPadding(new Insets(10));
        footer.setAlignment(Pos.CENTER_LEFT);
        footer.setPrefHeight(80); // ensure enough height
        footer.setSpacing(0);

        // Contact Info (Footer)
        Label titleLabel = new Label("Contact Us:");
        Label emailLabel = new Label("✉ nagriknazar@gmail.com");
        Label phoneLabel = new Label("📞 9999988885");
        Label locationLabel = new Label("📍 Pune, Maharashtra, India");

        // Set fully opaque white text for each label
        titleLabel.setTextFill(Color.WHITE);
        emailLabel.setTextFill(Color.WHITE);
        phoneLabel.setTextFill(Color.WHITE);
        locationLabel.setTextFill(Color.WHITE);

        VBox contactInfo = new VBox(titleLabel, emailLabel, phoneLabel, locationLabel);
        contactInfo.setAlignment(Pos.CENTER_LEFT);
        contactInfo.setMaxWidth(400);

        contactInfo.setPadding(new Insets(10));
        contactInfo.setStyle("-fx-text-fill: WHITE;");

        contactInfo.setMaxWidth(5000);
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

        // ---------- BACKGROUND LAYOUT ----------
        BorderPane root = new BorderPane();
        root.setTop(topNav);
        root.setBottom(footer);

        StackPane fullContent = new StackPane();

        // Create container for all content
        VBox overlayContent = new VBox();
        overlayContent.getChildren().addAll(topNav, centerBox, footer);
        overlayContent.setAlignment(Pos.TOP_CENTER);
        overlayContent.setPadding(new Insets(20));

        // Put image + content in stack
        fullContent.getChildren().addAll(bgImage, overlayContent);

        Scene scene = new Scene(fullContent, 1200, 670);
        stage.setResizable(false);
        stage.setScene(scene);
        mainHomePage = scene;
        primaryStage = stage;
        stage.getIcons().add(new Image("Assets/Images/logo.jpg"));
        stage.show();
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

    // ForGoverments Page
    private void initializeforgovernment() {
        For_Government fg = new For_Government();
        fg.setStage1(primaryStage);
        forGovernment = new Scene(fg.createScene2(this::backButton), 1200, 670);
        fg.setScene1(forGovernment);
    }

    private void backButton() {
        primaryStage.setScene(mainHomePage);
    }

    private void initializeforresident() {
        For_Resident residentPageSc = new For_Resident();
        residentPageSc.setStage2(primaryStage);
        forResidentPage = new Scene(residentPageSc.createScene2(this::residentbackButton), 1200, 670);
        residentPageSc.setScene2(forResidentPage);
    }

    private void residentbackButton() {
        initializeforresident();
        primaryStage.setScene(mainHomePage);
    }

    private void initializeReportNewIssue() {
        ReportNewIssue reportNewIssueHome = new ReportNewIssue();
        reportNewIssueHome.setStage4(primaryStage);
        reportNewIssuePage = new Scene(reportNewIssueHome.createReportPageScrollPane(this::reportbackButton), 1200,
                670);
        reportNewIssueHome.setScene4(reportNewIssuePage);

    }

    private void reportbackButton() {
        primaryStage.setScene(mainHomePage);
    }

    // About_Us
    private void initializeAboutUS() {
        about_us AboutUS = new about_us();
        AboutUS.setA_Stage(primaryStage);
        AboutUsPageScene = new Scene(AboutUS.createAboutScrollPane(this::handleButtonHome), 1200, 670);
        AboutUS.setA_Scene(AboutUsPageScene);
        ;
    }

    private void handleButtonHome() {
        primaryStage.setScene(mainHomePage);
    }
}
