package com.project1_example.View.For_Government;

import javafx.util.Duration;

import com.project1_example.View.For_Government.Admin_Login_Register.AdminLogin;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class For_Government {
    Scene scene1, forGovernementPage, adminLoginPage, adminRegisterPage;
    Stage stage1, primaryStage;

    public Scene getScene1() {
        return scene1;
    }

    public void setScene1(Scene scene1) {
        this.scene1 = scene1;
        forGovernementPage = scene1;
    }

    public Stage getStage1() {
        return stage1;
    }

    public void setStage1(Stage stage1) {
        this.stage1 = stage1;
        primaryStage = stage1;
        stage1.setResizable(false);
    }

    public StackPane createScene2(Runnable back) {

        // image
        ImageView gov_img = new ImageView(new Image("Assets\\Images\\gov_image3.png"));
        gov_img.setFitWidth(100);
        gov_img.setFitHeight(120);

        // MAIN TITLE
        HBox header = new HBox(130);
        Label mainHeading = new Label("Admin Panel");
        mainHeading.setStyle("""
                        -fx-font-size: 30px;
                        -fx-font-weight: bold;
                        -fx-text-fill: linear-gradient(#ffffff, #c5c5c5ff);
                        -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.75), 4, 0.2, 2, 2);
                """);
        mainHeading.setTextAlignment(TextAlignment.CENTER);

        addHoverAnimationForLabel(mainHeading);

        HBox middleheader = new HBox(10, gov_img, mainHeading);
        middleheader.setAlignment(Pos.CENTER);

        // BACK BUTTON
        Button backbtn = new Button("Back");
        backbtn.setStyle("""
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

        backbtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                back.run();
            }

        });

        addHoverAnimationForButton(backbtn);

        // LOGIN/Register
        Button adminLogin = new Button("Admin Login");
        adminLogin.setStyle("""
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

        adminLogin.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                initializeadminloginpage();
                primaryStage.setScene(adminLoginPage);
            }

        });

        addHoverAnimationForButton(adminLogin);

        // Button adminRegister = new Button("Admin Register");
        // adminRegister.setStyle("""
        // -fx-background-color: rgba(255, 255, 255, 0.15);
        // -fx-text-fill: white;
        // -fx-font-weight: bold;
        // -fx-font-size: 14px;
        // -fx-background-radius: 12;
        // -fx-border-radius: 12;
        // -fx-border-color: rgba(255, 255, 255, 0.5);
        // -fx-border-width: 1;
        // -fx-padding: 8 20 8 20;
        // -fx-cursor: hand;
        // """);

        // adminRegister.setOnAction(new EventHandler<ActionEvent>() {

        // @Override
        // public void handle(ActionEvent arg0) {
        // initializeadminRegisterpage();
        // primaryStage.setScene(adminRegisterPage);
        // }

        // });

        // addHoverAnimationForButton(adminRegister);

        HBox loginRegisterBox = new HBox(10, adminLogin);
        loginRegisterBox.setAlignment(Pos.CENTER_RIGHT);

        HBox leftBox = new HBox(backbtn);
        leftBox.setAlignment(Pos.CENTER_LEFT);
        leftBox.setPrefWidth(300);

        HBox centerBox = new HBox(gov_img, mainHeading);
        centerBox.setAlignment(Pos.CENTER);
        centerBox.setPrefWidth(300);

        HBox rightBox = new HBox(loginRegisterBox);
        rightBox.setAlignment(Pos.CENTER_RIGHT);
        rightBox.setPrefWidth(300);

        header.getChildren().addAll(leftBox, centerBox, rightBox);
        header.setPrefHeight(120);
        header.setAlignment(Pos.CENTER);

        // ============================================================================================================

        Text middleHeading = new Text("Smarter Governance Starts Here.");
        middleHeading.setStyle("""
                        -fx-font-size: 30px;
                        -fx-font-weight: bold;
                        -fx-text-fill: linear-gradient(#ffffff, #f5f5f5);
                        -fx-effect: dropshadow(gaussian, rgba(246, 239, 239, 0.75), 4, 0.2, 2, 2);
                """);

        addHoverAnimationForLabel(mainHeading);

        // Sectoon 1,2,3
        VBox mainLayout = new VBox(60); // increased space between sections
        mainLayout.setAlignment(Pos.CENTER);

        // Add all sections
        mainLayout.getChildren().addAll(
                createSection(
                        "RESIDENT ENGAGEMENT",
                        new String[] { "👥", "📱", "📩" },
                        "transperent;",
                        "INCREASED ACCESS",
                        "Connect with residents using your official mobile app, along with web platforms, push notifications, and dynamic translations.",
                        "BRANDED APPS",
                        "Your very own city app will provide residents a single location to submit requests and accessother city information.",
                        "PUSH NOTIFICATIONS",
                        "Reach mobile users directly with important events and city alerts."),
                createSection(
                        "SMARTER WORKFLOW",
                        new String[] { "📋", "⚙️", "❓" },
                        "transperent;",
                        "CENTRALIZED COMMUNICATION",
                        "Communicate directly with other staﬀ users and collaborate across departments in one central location.",
                        "PROCESS AUTOMATION",
                        "Streamline request assignments with automated routing and notiﬁcations.",
                        "KNOWLEDGE BASE",
                        "Provide residents a wiki-based library of answers to their most common questions and reduce staﬀ inquiries."),
                createSection(
                        "DATA-DRIVEN DECISIONS",
                        new String[] { "📊", "📈", "👤" },
                        "transperent;",
                        "ACTIONABLE INSIGHTS",
                        "Leverage data to define priorities and make decisions that improve resident service.",
                        "CUSTOM REPORTING",
                        "Analyze request trends and departmental performance through fully customizable reports and ﬂexible data visualizations.",
                        "VISUAL MAPPING",
                        "Identify problem areas with a heat map. Integrate your GIS data to ﬁlter, view, and interact with requests by neighborhood and zone."));

        // ---------- FOOTER ----------
        HBox footer = new HBox();
        footer.setPadding(new Insets(10));
         footer.setStyle("-fx-background-color: #1a2c44;");
        footer.setAlignment(Pos.CENTER_LEFT);
        footer.setPrefHeight(80); // ensure enough height
        footer.setSpacing(50);

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

        VBox vb = new VBox(20, header, middleHeading, mainLayout, footer);
        vb.setPadding(new Insets(10, 10, 10, 10));
        vb.setAlignment(Pos.CENTER);

        // Load and configure background ImageView
        ImageView backgroundImageView = new ImageView(new Image("Assets/Images/for_resident1.jpeg"));
        backgroundImageView.setFitWidth(1200);
        backgroundImageView.setFitHeight(670);
        backgroundImageView.setPreserveRatio(false);

        backgroundImageView.setEffect(new javafx.scene.effect.GaussianBlur(12)); // Adjust radius as needed

        // scrollable content VBox
        ScrollPane scrollPane = new ScrollPane(vb);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        StackPane stackPane5 = new StackPane();
        stackPane5.getChildren().addAll(backgroundImageView, scrollPane);

        return stackPane5;
    }

    // Utility method to create each section
    private VBox createSection(String titleText, String[] emojis, String bgColor, String cardTitle, String cardText,
            String cardTitle2, String cardText2, String cardTitle3, String cardText3) {
        VBox section = new VBox(30);
        section.setPadding(new Insets(40, 30, 40, 30));
        section.setStyle("-fx-background-color: " + bgColor + "; -fx-background-radius: 10;");
        section.setAlignment(Pos.TOP_CENTER);
        section.setMaxWidth(820);
        section.setBorder(new Border(new BorderStroke(Color.LIGHTGRAY,
                BorderStrokeStyle.SOLID, new CornerRadii(10), BorderWidths.DEFAULT)));

        // Section title
        Label title = new Label(titleText);
        title.setFont(Font.font("Segoe UI", 26));
        title.setTextFill(Color.web("#8b0f0fff"));
        title.setStyle("-fx-font-weight: bold;");
        title.setStyle("""
                        -fx-font-size: 30px;
                        -fx-font-weight: bold;
                        -fx-text-fill: linear-gradient(#ffffff, #ecebebff);
                        -fx-effect: dropshadow(gaussian, rgba(246, 239, 239, 0.75), 4, 0.2, 2, 2);
                """);

        // Icons row with emoji & tooltip
        HBox iconRow = new HBox(25);
        iconRow.setAlignment(Pos.CENTER);

        for (String emoji : emojis) {
            Label icon = new Label(emoji);
            icon.setFont(Font.font(30));
            icon.setStyle("""
                    -fx-text-fill: white;
                    -fx-font-weight: bold;
                    """);

            Tooltip tooltip = new Tooltip(getTooltipText(emoji));
            Tooltip.install(icon, tooltip);

            addHoverAnimation(icon);
            iconRow.getChildren().add(icon);
        }

        // Info Card
        VBox card = new VBox(12);
        card.setPadding(new Insets(20));
        card.setAlignment(Pos.TOP_LEFT);
        card.setMaxWidth(500);
        card.setStyle("""
                -fx-background-color: rgba(228, 223, 223, 0.25);
                -fx-border-color: #dddddd;
                -fx-border-radius: 12;
                -fx-background-radius: 12;
                -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.25), 8, 0.3, 2, 2);
                -fx-padding: 20;
                """);

        card.setEffect(new javafx.scene.effect.GaussianBlur(12));

        Label cardTitleLbl = new Label(cardTitle);
        cardTitleLbl.setFont(Font.font("Segoe UI", 17));
        cardTitleLbl.setTextFill(Color.web("#1a1a1a"));
        cardTitleLbl.setStyle("-fx-font-weight: bold;");

        Label cardBody = new Label(cardText);
        cardBody.setWrapText(true);
        cardBody.setFont(Font.font("Segoe UI", 14));
        cardBody.setTextFill(Color.web("black"));

        Label cardTitleLb2 = new Label(cardTitle2);
        cardTitleLb2.setFont(Font.font("Segoe UI", 17));
        cardTitleLb2.setTextFill(Color.web("#1a1a1a"));
        cardTitleLb2.setStyle("-fx-font-weight: bold;");

        Label cardBody2 = new Label(cardText2);
        cardBody2.setWrapText(true);
        cardBody2.setFont(Font.font("Segoe UI", 14));
        cardBody2.setTextFill(Color.web("#444"));

        Label cardTitleLb3 = new Label(cardTitle3);
        cardTitleLb3.setFont(Font.font("Segoe UI", 17));
        cardTitleLb3.setTextFill(Color.web("#1a1a1a"));
        cardTitleLb3.setStyle("-fx-font-weight: bold;");

        Label cardBody3 = new Label(cardText3);
        cardBody3.setWrapText(true);
        cardBody3.setFont(Font.font("Segoe UI", 14));
        cardBody3.setTextFill(Color.web("BLACK"));

        addHoverAnimation(card);

        card.getChildren().addAll(cardTitleLbl, cardBody, cardTitleLb2, cardBody2, cardTitleLb3, cardBody3);
        section.getChildren().addAll(title, iconRow, card);

        return section;
    }

    // Hover effect
    private void addHoverAnimation(Region node) {
        ScaleTransition enlarge = new ScaleTransition(Duration.millis(150), node);
        enlarge.setToX(1.05);
        enlarge.setToY(1.05);

        ScaleTransition reset = new ScaleTransition(Duration.millis(150), node);
        reset.setToX(1);
        reset.setToY(1);

        node.setOnMouseEntered(e -> enlarge.playFromStart());
        node.setOnMouseExited(e -> reset.playFromStart());
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

    // Tooltip text per emoji
    private String getTooltipText(String emoji) {
        return switch (emoji) {
            case "👥" -> "Residents";
            case "📱" -> "Mobile App";
            case "🖥️" -> "Web Portal";
            case "📩" -> "Push Notifications";
            case "🌐" -> "Translation Support";

            case "📋" -> "Service Requests";
            case "⚙️" -> "Work Automation";
            case "❓" -> "Help & Support";
            case "💬" -> "Resident Messaging";

            case "📊" -> "Data Charts";
            case "📈" -> "Growth Analytics";
            case "👤" -> "User Reports";
            default -> "Feature";
        };
    }

    // Login Page -Navigation
    private void initializeadminloginpage() {
        AdminLogin adminlogin = new AdminLogin();
        adminlogin.setStage1_a(primaryStage);
        adminLoginPage = new Scene(adminlogin.createsAdminLoginScrollPane(this::backGovernmentLoginButton), 1200, 670);
        adminlogin.setScene1_a(adminLoginPage);

    }

    // LoginPage back button
    private void backGovernmentLoginButton() {
        primaryStage.setScene(forGovernementPage);
    }

    // // Register-Page -Navigation
    // private void initializeadminRegisterpage() {
    // AdminSignUp adminSignUp = new AdminSignUp();
    // adminSignUp.setStage1_b(primaryStage);
    // adminRegisterPage = new
    // Scene(adminSignUp.createsAdminSignUpScrollPane(this::backGovermentRegisterButton));
    // adminSignUp.setScene1_b(adminRegisterPage);

    // }

    // // Register-Page back button
    // private void backGovermentRegisterButton() {
    // primaryStage.setScene(forGovernementPage);
    // }
}