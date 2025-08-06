package com.project1_example.View.For_Resident.Citizen_Login_Register;

import com.project1_example.View.For_Resident.CitizenPage.CitizenReportPage;
import com.project1_example.View.For_Resident.CitizenPage.CompletedReports;
import com.project1_example.View.For_Resident.CitizenPage.View_Report;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
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
import javafx.stage.Stage;
import javafx.util.Duration;

public class CitizenHomePage {

        Scene sceneCH, CitizenAfterHomePageScene, ForResidentScene1, CitizenViewPageScene, CitizenReportScene,
                        CitizenCompletedReportScene,
                        CitizenLoginPage;
        Stage stageCH, primaryStage;

        public void setSceneCH(Scene sceneCH) {
                this.sceneCH = sceneCH;
                CitizenAfterHomePageScene = sceneCH;
        }

        public void setStageCH(Stage stageCH) {
                this.stageCH = stageCH;
                primaryStage = stageCH;
                stageCH.setResizable(false);

        }

        public StackPane createCitizenHomeScrollPane(Runnable login) {

                Button backbtn = new Button("← LogOut");

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

                                login.run();
                                System.out.println("--------" + login);
                                System.out.println("logout button");

                        }

                });
                addHoverAnimationForButton(backbtn);

                Label mainHeading = new Label("Citizen Panel");
                mainHeading.setStyle("""
                                        -fx-font-size: 30px;
                                        -fx-font-weight: bold;
                                        -fx-text-fill: linear-gradient(#ffffff, #f5f5f5);
                                        -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.75), 4, 0.2, 2, 2);
                                """);
                addHoverAnimationForLabel(mainHeading);

                HBox leftBox = new HBox(backbtn);
                leftBox.setAlignment(Pos.CENTER_LEFT);
                // leftBox.setPrefWidth(350);

                HBox centerBox = new HBox(mainHeading);
                centerBox.setAlignment(Pos.CENTER);
                // centerBox.setPrefWidth(300);
                HBox header = new HBox(400);
                header.getChildren().addAll(leftBox, centerBox);
                header.setPrefHeight(120);
                header.setBackground(new Background(
                                new BackgroundFill(Color.DARKSLATEBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
                header.setMaxWidth(1800);
                header.setMinHeight(100);

                // NavBar
                HBox navBar = new HBox(10);
                navBar.setAlignment(Pos.CENTER);

                // Create tab buttons
                Button ViewReportsTab = createTabButton5("View Reports", true);
                ViewReportsTab.setOnAction(new EventHandler<ActionEvent>() {

                        @Override
                        public void handle(ActionEvent arg0) {
                                System.out.println("View Report");
                                initializeCitizenViewpage();
                                primaryStage.setScene(CitizenViewPageScene);
                        }

                });
                addHoverAnimationForButton(ViewReportsTab);
                Button ReportsNewIssueTab = createTabButton5("Reports New Issue", true);
                ReportsNewIssueTab.setOnAction(new EventHandler<ActionEvent>() {

                        @Override
                        public void handle(ActionEvent arg0) {
                                System.out.println("Report Report");
                                initializeCitizenReportspage();
                                primaryStage.setScene(CitizenReportScene);
                        }

                });
                addHoverAnimationForButton(ReportsNewIssueTab);

                Button CompletedReportsTab = createTabButton5("History", true);
                CompletedReportsTab.setOnAction(new EventHandler<ActionEvent>() {

                        @Override
                        public void handle(ActionEvent arg0) {
                                System.out.println("Completed Report");
                                initializeCitizenCompletedReportpage();
                                primaryStage.setScene(CitizenCompletedReportScene);
                        }
                });
                addHoverAnimationForButton(CompletedReportsTab);

                navBar.getChildren().addAll(ViewReportsTab, ReportsNewIssueTab, CompletedReportsTab);

                Text tx5 = new Text("Report Together. Resolve Together.");
                tx5.setStyle("""
                                -fx-font-size: 20px;
                                -fx-font-weight: bold;
                                -fx-fill: linear-gradient(#ffffff, #dcdcdc);
                                -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.7), 3, 0.3, 2, 2);
                                """);
                HBox hbtitle = new HBox(tx5);
                hbtitle.setPrefWidth(100);
                hbtitle.setPrefHeight(30);
                hbtitle.setAlignment(Pos.CENTER);
                hbtitle.setPadding(new Insets(5));

                VBox mainLayout = new VBox(60); // increased space between sections
                mainLayout.setAlignment(Pos.CENTER);
                mainLayout.setTranslateY(600); // adjust if needed (height of window)

                // Animation to move it upward into position
                TranslateTransition slideIn = new TranslateTransition(Duration.seconds(1.5), mainLayout);
                slideIn.setToY(0); // Final Y position (normal)
                slideIn.setInterpolator(javafx.animation.Interpolator.EASE_OUT); // Smooth effect
                slideIn.play();

                // Add all sections
                mainLayout.getChildren().addAll(

                                createSection(
                                                "\"Your Voice, Your City\" - Transform your community one report at a time\r\n"
                                                                + //
                                                                "\r\n" + //
                                                                "\"Every Issue Matters\" - From potholes to park maintenance, make your voice heard\r\n"
                                                                + //
                                                                "\r\n" + //
                                                                "\"Civic Change Starts Here\" - Connect directly with local government in seconds ",
                                                "Transparent",
                                                "INCREASED ACCESS",
                                                "Connect with residents using your official mobile app, along with web platforms, push notifications, and dynamic translations.",
                                                "BRANDED APPS",
                                                "Assets/Images/ico_img1.jpg"),
                                createSection(
                                                "\"Skip the phone calls - report issues instantly with photos and location\"\r\n"
                                                                + //
                                                                "\r\n" + //
                                                                "\"Real-time updates on your submissions and city responses\"\r\n"
                                                                + //
                                                                "\r\n" + //
                                                                "\"Average response time: 48 hours for priority issues\"",
                                                "Transparent",
                                                "CENTRALIZED COMMUNICATION",
                                                "Communicate directly with other staﬀ users and collaborate across departments in one central location.",
                                                "PROCESS AUTOMATION",
                                                "Assets/Images/ico_img2.jpg"),
                                createSection(
                                                "\"Report. Track. Transform.\" - Follow your community issues from submission to resolution\r\n"
                                                                + //
                                                                "\r\n" + //
                                                                "\"See Something? Say Something. Fix Something.\" - Your neighborhood needs your eyes and voice\r\n"
                                                                + //
                                                                "\r\n" + //
                                                                "\"Democracy in Your Pocket\" - Instant access to responsive local government",
                                                "Transparent",
                                                "ACTIONABLE INSIGHTS",
                                                "Leverage data to define priorities and make decisions that improve resident service.",
                                                "CUSTOM REPORTING",
                                                "Assets/Images/ico_img3.jpg"));

                // --------------------------------------------------(10,Box1,Box2,Box3,Box4);End---------------------------------------------------------

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
                // ---------- BACKGROUND LAYOUT ----------

                /// ================================MainViewScene==============================================================

                VBox vb = new VBox(20, header, navBar, hbtitle, mainLayout, footer);
                vb.setPadding(new Insets(10, 10, 10, 10));

                // Load and configure background ImageView
                ImageView backgroundImageView = new ImageView(new Image("Assets/Images/for_resident1.jpeg"));
                backgroundImageView.setFitWidth(1200);
                backgroundImageView.setFitHeight(670);
                backgroundImageView.setPreserveRatio(false);

                backgroundImageView.setEffect(new javafx.scene.effect.GaussianBlur(10)); // Adjust radius as needed

                ScrollPane scrollPane = new ScrollPane(vb);
                scrollPane.setFitToWidth(true);
                scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
                scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
                scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

                ScrollPane scrollPane1 = new ScrollPane(vb);
                scrollPane1.setFitToWidth(true);
                scrollPane1.setStyle("-fx-background: transparent; -fx-background-color: transparent;");

                StackPane stackPane4 = new StackPane();
                stackPane4.getChildren().addAll(backgroundImageView, scrollPane);

                return stackPane4;

        }

        // Utility method to create each section
        private VBox createSection(String titleText, String bgColor, String cardTitle, String cardText,
                        String cardTitle2, String imagePath) {
                VBox section = new VBox(30);
                section.setPadding(new Insets(40, 30, 40, 30));
                section.setStyle("-fx-background-color: " + bgColor + "; -fx-background-radius: 10;");
                section.setAlignment(Pos.TOP_CENTER);
                section.setMaxWidth(820);
                section.setBorder(new Border(new BorderStroke(Color.LIGHTGRAY,
                                BorderStrokeStyle.SOLID, new CornerRadii(10), BorderWidths.DEFAULT)));

                ImageView imageView = new ImageView(new Image(imagePath)); // Example: "file:Assets/Images/icon1.png"
                imageView.setFitWidth(500);
                imageView.setFitHeight(130);
                imageView.setPreserveRatio(true);
                VBox container = new VBox(imageView);
                container.setAlignment(Pos.CENTER);

                // Section title
                Label title = new Label(titleText);
                title.setFont(Font.font("Segoe UI", 15));
                title.setTextFill(Color.web("#8b0f0fff"));
                title.setStyle("-fx-font-weight: bold;");

                // Icons row with emoji & tooltip
                HBox iconRow = new HBox(25);
                iconRow.setAlignment(Pos.CENTER);

                // Info Card
                VBox card = new VBox(12);
                card.setPadding(new Insets(20));
                card.setAlignment(Pos.TOP_LEFT);
                card.setMaxWidth(500);
                card.setStyle("""
                                -fx-background-color: rgba(255, 255, 255, 0.25);
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

                addHoverAnimation(card);

                card.getChildren().addAll(cardTitleLbl, cardBody, cardTitleLb2, container);
                section.getChildren().addAll(title, iconRow, card);
                return section;
        }

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

        private Button createTabButton5(String text, boolean isActive) {
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

        // // Initializelogin
        // private void initializeCitizenLoginpage() {
        // CitizenLogin citizenPage = new CitizenLogin();
        // citizenPage.setStage2_a(primaryStage);
        // CitizenLoginPage = new
        // Scene(citizenPage.createsScrollPane(this::backHomeHandleButton), 1200, 670);
        // }

        // private void backHomeHandleButton() {
        // primaryStage.setScene(CitizenAfterHomePageScene);
        // }

        // // ForCitizen
        // private void initializeResidentPage() {
        // For_Resident residentPage = new For_Resident();
        // residentPage.setStage2(primaryStage);
        // ForResidentScene1 = new
        // Scene(residentPage.createScene2(this::handleButtonResidentPage), 1200, 670);
        // residentPage.setScene2(ForResidentScene1);
        // }

        // private void handleButtonResidentPage() {
        // primaryStage.setScene(ForResidentScene1);
        // }

        // InitislizeCitizen
        private void initializeCitizenViewpage() {
                View_Report viewReport = new View_Report();
                viewReport.setStage5(primaryStage);
                CitizenViewPageScene = new Scene(viewReport.CreateScene5(this::backbtnhandle1), 1200, 670);
                System.out.println("view");
                viewReport.setScene5(CitizenViewPageScene);
        }

        private void backbtnhandle1() {
                System.out.println("View");
                primaryStage.setScene(CitizenAfterHomePageScene);
        }

        // CitizenReport
        private void initializeCitizenReportspage() {
                CitizenReportPage citizenReport = new CitizenReportPage();
                citizenReport.setStage3(primaryStage);
                CitizenReportScene = new Scene(citizenReport.createReportPageScrollPane(this::backbtnhandle2), 1200,
                                670);
                citizenReport.setScene3(CitizenReportScene);
        }

        private void backbtnhandle2() {

                System.out.println("Report");
                primaryStage.setScene(CitizenAfterHomePageScene);
        }

        // CompletedReport
        private void initializeCitizenCompletedReportpage() {
                CompletedReports completedReport = new CompletedReports();
                completedReport.setStage6(primaryStage);
                CitizenCompletedReportScene = new Scene(
                                completedReport.createOldSystemComplaintsPane(this::backbtnhandle3), 1200, 670);
                completedReport.setScene6(CitizenCompletedReportScene);
        }

        private void backbtnhandle3() {
                System.out.println("History");
                primaryStage.setScene(CitizenAfterHomePageScene);
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
