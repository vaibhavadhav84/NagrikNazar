package com.project1_example.View.About_US;

import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

public class about_us {
    Scene A_Scene, aboutusPageScene, forGovernment, forResidentPage;
    Stage A_Stage, primaryStage;

    public void setA_Scene(Scene a_Scene) {
        A_Scene = a_Scene;
        aboutusPageScene = A_Scene;
    }

    public void setA_Stage(Stage a_Stage) {
        A_Stage = a_Stage;
        primaryStage = A_Stage;
        A_Stage.setResizable(false);

    }

    public StackPane createAboutScrollPane(Runnable back) {

        A_Stage.setTitle("Nagrik Nazar");

        // ---------- TOP NAV BAR ----------
        HBox topNav = new HBox(20);
        topNav.setPadding(new Insets(10, 3, 0, 0));

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

        Button BackBtn = new Button("Back");
        BackBtn.setStyle("""
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
        BackBtn.setOnAction(e -> back.run());
        addHoverAnimationForButton(BackBtn);

        HBox topBtn1 = new HBox(9, title2);
        topBtn1.setAlignment(Pos.CENTER);
        HBox topBtn = new HBox(20, BackBtn);
        topBtn.setAlignment(Pos.TOP_RIGHT);
        HBox topBtn2 = new HBox(850, topBtn1, topBtn);

        topNav.getChildren().addAll(topBtn2);
        topNav.setBackground(new Background(new BackgroundFill(Color.DARKSLATEBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        topNav.setPrefHeight(50);

        // ---------- BACKGROUND IMAGE ----------
        ImageView bgImage = new ImageView(new Image("Assets\\Images\\homeBackground.jpeg"));
        bgImage.setFitWidth(1200);
        bgImage.setFitHeight(670);
        bgImage.setPreserveRatio(false);
        bgImage.setSmooth(true);
        bgImage.setCache(true);

        // ---------- CARD CONTENT ----------
        VBox card = new VBox(20);
        card.setAlignment(Pos.TOP_CENTER);
        card.setPadding(new Insets(30, 50, 30, 50));
        card.setMaxWidth(1500);
        card.setStyle(
                "-fx-background-color: rgba(255, 255, 255, 0.95);" +
                        "-fx-background-radius: 25;" +
                        "-fx-border-radius: 25;" +
                        "-fx-border-color: #ffffff;" +
                        "-fx-border-width: 2;" +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.15), 40, 0.1, 0, 10);");

        Label title = new Label("About Our Project");
        title.setFont(Font.font("Segoe UI", FontWeight.BOLD, 42));
        title.setTextFill(Color.web("#2c3e50"));

        Label projectDesc = new Label(
                "We, the students of Core2Web, have developed a 'Public Issue Reporting Platform' using JavaFX and Firebase. This platform allows people to easily submit their complaints or reports.");
        projectDesc.setFont(Font.font("Segoe UI", 18));
        projectDesc.setTextFill(Color.web("#34495e"));
        projectDesc.setWrapText(true);
        projectDesc.setTextAlignment(TextAlignment.CENTER);
        projectDesc.setMaxWidth(750);

        Label classLabel = new Label("Our Institute - Core2Web");
        classLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 28));
        classLabel.setTextFill(Color.web("#2980b9"));

        HBox clasBox = new HBox(20);
        clasBox.setAlignment(Pos.CENTER);

        ImageView logo = new ImageView(new Image(
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTsfhnqJshiRhlC0Bx_o5A28Y1LBf-f-IM6UA&s"));
        logo.setFitWidth(190);
        logo.setFitHeight(190);
        logo.setPreserveRatio(true);

        Label classDesc = new Label(
                "At Core2Web, students learn with real coding tasks, get guidance from expert mentors, and work on projects that make them ready for the industry.");
        classDesc.setFont(Font.font("Segoe UI", 17));
        classDesc.setWrapText(true);
        classDesc.setTextFill(Color.web("#34495e"));
        classDesc.setMaxWidth(500);
        classDesc.setTextAlignment(TextAlignment.LEFT);

        clasBox.getChildren().addAll(logo, classDesc);

        Label sirLabel = new Label("Special Thanks to Shashi Sir");
        sirLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 28));
        sirLabel.setTextFill(Color.web("#2980b9"));

        HBox sirBox = new HBox(20);
        sirBox.setAlignment(Pos.CENTER);

        ImageView sirImage = new ImageView(
                new Image("https://www.core2web.in/assets/images/landing/ShashiSirMobile.png"));
        sirImage.setFitWidth(250);
        sirImage.setFitHeight(250);
        sirImage.setPreserveRatio(true);

        Label sirDesc = new Label(
                "I sincerely thank Shashi Sir for his amazing way of teaching,\nwhich made learning simple and fun. The practical sessions and projects from the Core2Web team really helped me gain confidence.");
        sirDesc.setFont(Font.font("Segoe UI", 17));
        sirDesc.setWrapText(true);
        sirDesc.setTextFill(Color.web("#34495e"));
        sirDesc.setMaxWidth(500);
        sirDesc.setTextAlignment(TextAlignment.LEFT);

        sirBox.getChildren().addAll(sirImage, sirDesc);

        Label mentorLabel = new Label("Project Mentors");
        mentorLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 28));
        mentorLabel.setTextFill(Color.web("#2980b9"));

        Label mentors = new Label("Sachin Sir, Pramod Sir, Shiv Sir, Subodh Sir, Akshay Sir");
        mentors.setFont(Font.font("Segoe UI", FontWeight.BOLD, 20));
        mentors.setTextFill(Color.web("#2c3e50"));

        Label mentorDesc = new Label("Guided us throughout the project with strong technical support and mentorship.");
        mentorDesc.setFont(Font.font("Segoe UI", 17));
        mentorDesc.setWrapText(true);
        mentorDesc.setTextFill(Color.web("#34495e"));
        mentorDesc.setTextAlignment(TextAlignment.CENTER);
        mentorDesc.setMaxWidth(750);

        Label teamLead = new Label("Team Leader");
        teamLead.setFont(Font.font("Segoe UI", FontWeight.BOLD, 20));
        teamLead.setTextFill(Color.web("#2980b9"));

        Label teamLeaderDesc = new Label(
                "Sumit Katkar led us through the development of the project with clear vision and dedication.");
        teamLeaderDesc.setFont(Font.font("Segoe UI", 16));
        teamLeaderDesc.setWrapText(true);
        teamLeaderDesc.setTextFill(Color.web("#2d3436"));
        teamLeaderDesc.setTextAlignment(TextAlignment.CENTER);

        Label teamLabel = new Label("Our Dedicated Team");
        teamLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 20));
        teamLabel.setTextFill(Color.web("#2980b9"));

        HBox memberName = new HBox(70);
        memberName.setAlignment(Pos.CENTER);
        Text Vaibhav = new Text("Vaibhav Adhav");
        Text Rohan = new Text("Rohan Panchal");

        Vaibhav.setFont(Font.font("Segoe UI", FontWeight.BOLD, 21));
        Rohan.setFont(Font.font("Segoe UI", FontWeight.BOLD, 21));

        memberName.getChildren().addAll(Vaibhav, Rohan);

        card.getChildren().addAll(
                title, projectDesc,
                classLabel, clasBox,
                sirLabel, sirBox,
                mentorLabel, mentors, mentorDesc,
                teamLead, teamLeaderDesc,
                teamLabel, memberName);

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

        // ---------- OVERLAY CONTENT ----------
        VBox overlayContent = new VBox();
        overlayContent.getChildren().addAll(topNav, card, footer);
        overlayContent.setAlignment(Pos.TOP_CENTER);
        overlayContent.setPadding(new Insets(10));
        overlayContent.setStyle("-fx-background-color: transperent;");


        // ---------- SCROLLPANE ----------
        ScrollPane scrollPane = new ScrollPane(overlayContent);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: Transperent;");
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        // ---------- STACKED LAYOUT ----------
        StackPane fullContent = new StackPane();
        fullContent.getChildren().addAll(bgImage, scrollPane);

        return fullContent;
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
