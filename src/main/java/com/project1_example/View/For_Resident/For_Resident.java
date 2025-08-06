package com.project1_example.View.For_Resident;

import com.project1_example.View.For_Resident.Citizen_Login_Register.CitizenHomePage;
import com.project1_example.View.For_Resident.Citizen_Login_Register.CitizenLogin;
import com.project1_example.View.For_Resident.Citizen_Login_Register.CitizenSignUp;

import javafx.scene.paint.Color;
import javafx.animation.ScaleTransition;
import javafx.util.Duration;
import javafx.scene.control.Label;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
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
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class For_Resident {
        Scene scene2, ForResidentScene, CitizenLoginScene, CitizenRegisterPageScene, CitizenHomePageAfterScene;
        Stage stage2, primaryStage;

        public void setScene2(Scene scene2) {
                this.scene2 = scene2;
                ForResidentScene = scene2;

        }

        public void setStage2(Stage stage2) {
                this.stage2 = stage2;
                primaryStage = stage2;
        stage2.setResizable(false);

        }

        public StackPane createScene2(Runnable back) {

                // BACK BUTTON
                Button backbtn = new Button("← Back");

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

                // MAIN TITLE
                HBox header = new HBox(150);
                Label mainHeading = new Label("Citizen Panel");
                mainHeading.setStyle("""
                                        -fx-font-size: 30px;
                                        -fx-font-weight: bold;
                                        -fx-text-fill: linear-gradient(#ffffff, #f5f5f5);
                                        -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.75), 4, 0.2, 2, 2);
                                """);

                // LOGIN/Register
                Button adminLogin = new Button("Citizen Login");
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
                                initializeCitizenloginpage();
                                primaryStage.setScene(CitizenLoginScene);
                        }

                });
                addHoverAnimationForButton(adminLogin);
                Button adminRegister = new Button("Citizen Register");
                adminRegister.setStyle("""
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
                adminRegister.setOnAction(new EventHandler<ActionEvent>() {

                        @Override
                        public void handle(ActionEvent arg0) {
                                initializeCitizenRegisterpage();
                                primaryStage.setScene(CitizenRegisterPageScene);
                        }

                });
                addHoverAnimationForButton(adminRegister);

                HBox loginRegisterBox = new HBox(10, adminLogin, adminRegister);
                loginRegisterBox.setAlignment(Pos.CENTER_RIGHT);

                HBox leftBox = new HBox(backbtn);
                leftBox.setAlignment(Pos.CENTER_LEFT);
                leftBox.setPrefWidth(300);

                HBox centerBox = new HBox(mainHeading);
                centerBox.setAlignment(Pos.CENTER);
                centerBox.setPrefWidth(300);

                HBox rightBox = new HBox(loginRegisterBox);
                rightBox.setAlignment(Pos.CENTER_RIGHT);
                rightBox.setPrefWidth(300);

                header.getChildren().addAll(leftBox, centerBox, rightBox);
                header.setPrefHeight(120);
                header.setAlignment(Pos.CENTER);
                // header.setStyle("-fx-background-color: #1a2c44;");

                // -----------------------------------------texttitle------------------------------------------
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

                VBox vb = new VBox(20, header, hbtitle, mainLayout, footer);
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

                StackPane stackPane3 = new StackPane();
                stackPane3.getChildren().addAll(backgroundImageView, scrollPane);

             

                return stackPane3;

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



        private void initializeCitizenloginpage() {
                CitizenLogin citizenlogin = new CitizenLogin();
                citizenlogin.setStage2_a(primaryStage);
                CitizenLoginScene = new Scene(citizenlogin.createsScrollPane(this::backCitizenLoginButton), 1200, 670);
                citizenlogin.setScene2_a(CitizenLoginScene);
        }

        // LoginPage back button
        private void backCitizenLoginButton() {
                primaryStage.setScene(ForResidentScene);
        }

        private void initializeCitizenRegisterpage() {
                CitizenSignUp citizenregister = new CitizenSignUp();
                citizenregister.setStage2_b(primaryStage);
                CitizenRegisterPageScene = new Scene(
                                citizenregister.createScrollPane2(this::backCitizenRegisterPageButton), 1200, 670);
                citizenregister.setScene2_b(CitizenRegisterPageScene);
        }

        // Register-Page back button
        private void backCitizenRegisterPageButton() {
                primaryStage.setScene(ForResidentScene);
        }

        private void initializeCitizenPage() {
                CitizenHomePage citizenHomepage = new CitizenHomePage();
                citizenHomepage.setStageCH(primaryStage);
                CitizenHomePageAfterScene = new Scene(
                                citizenHomepage.createCitizenHomeScrollPane(this::handleCitizenPage), 1200, 670);
                citizenHomepage.setSceneCH(CitizenHomePageAfterScene);
        }

        // Citizen back button
        private void handleCitizenPage() {
                initializeCitizenPage();
                primaryStage.setScene(CitizenHomePageAfterScene);
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

        // Tooltip text per emoji

}
