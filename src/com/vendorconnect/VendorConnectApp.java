package com.vendorconnect;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class VendorConnectApp extends Application {
    private Stage primaryStage;  // The primary stage for switching scenes

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;  // Set primaryStage for scene switching

        // Create the navbar (HBox) and set it on the top
        HBox navbar = new HBox(20); // 20px spacing between items
        navbar.setAlignment(Pos.CENTER_LEFT);
        navbar.setStyle("-fx-background-color: #333; -fx-padding: 10px;");

        // Logo (VendorConnect) on the left with click event to go to homepage
        Text logoText = new Text("VendorConnect");
        logoText.setFont(new Font(24));  // Set font size
        logoText.setStyle("-fx-fill: white; -fx-font-weight: bold;");  // Make text bold and white
        logoText.setOnMouseClicked((MouseEvent e) -> {
            primaryStage.setScene(createHomeScene());  // Redirect to the home scene
        });
        navbar.getChildren().add(logoText);

        // Spacer to push the labels all the way to the right
        Region spacer = new Region();
        HBox.setHgrow(spacer, javafx.scene.layout.Priority.ALWAYS);

        // Create labels for "Read More", "About Us", and "Login"
        Label readMoreLabel = createNavLink("Read More");
        Label aboutUsLabel = createNavLink("About Us");
        Label loginLabel = createNavLink("Login");

        // Add the labels to the right-side HBox
        HBox rightSide = new HBox(20);  // Horizontal box for right-side links
        rightSide.setAlignment(Pos.CENTER_RIGHT);
        rightSide.getChildren().addAll(readMoreLabel, aboutUsLabel, loginLabel);

        // Add the spacer and right-side labels to the navbar
        navbar.getChildren().addAll(spacer, rightSide);

        // Fullscreen configuration for the stage
        primaryStage.setFullScreen(true);

        // Set initial scene (Home page or main content)
        Scene homeScene = createHomeScene();
        primaryStage.setScene(homeScene);
        primaryStage.setTitle("VendorConnect");
        primaryStage.show();
    }

    // Helper method to create a scene for the home page
    private Scene createHomeScene() {
        StackPane homeContent = new StackPane();
        homeContent.getChildren().add(new Text("Welcome to the Home Page"));

        // Layout (VBox to stack navbar on top and main content below)
        VBox layout = new VBox();
        layout.getChildren().addAll(createNavbar(), homeContent);

        return new Scene(layout, 800, 600);  // Initial scene size
    }

    // Helper method to create a scene for the "Read More" page
    private Scene createReadMoreScene() {
        StackPane readMoreContent = new StackPane();
        readMoreContent.getChildren().add(new Text("Welcome to the Read More Page"));

        // Layout (VBox to stack navbar on top and main content below)
        VBox layout = new VBox();
        layout.getChildren().addAll(createNavbar(), readMoreContent);

        return new Scene(layout, 800, 600);  // Initial scene size
    }

    // Helper method to create a scene for the "About Us" page
    private Scene createAboutUsScene() {
        StackPane aboutUsContent = new StackPane();
        aboutUsContent.getChildren().add(new Text("Welcome to the About Us Page"));

        // Layout (VBox to stack navbar on top and main content below)
        VBox layout = new VBox();
        layout.getChildren().addAll(createNavbar(), aboutUsContent);

        return new Scene(layout, 800, 600);  // Initial scene size
    }

    // Helper method to create a scene for the "Login" page
    private Scene createLoginScene() {
        StackPane loginContent = new StackPane();
        loginContent.getChildren().add(new Text("Welcome to the Login Page"));

        // Layout (VBox to stack navbar on top and main content below)
        VBox layout = new VBox();
        layout.getChildren().addAll(createNavbar(), loginContent);

        return new Scene(layout, 800, 600);  // Initial scene size
    }

    // Helper method to create the navbar
    private HBox createNavbar() {
        HBox navbar = new HBox(20);
        navbar.setAlignment(Pos.CENTER_LEFT);
        navbar.setStyle("-fx-background-color: #333; -fx-padding: 10px;");

        // Logo (VendorConnect) on the left with click event to go to homepage
        Text logoText = new Text("VendorConnect");
        logoText.setFont(new Font(24));
        logoText.setStyle("-fx-fill: white; -fx-font-weight: bold;");
        logoText.setOnMouseClicked((MouseEvent e) -> {
            primaryStage.setScene(createHomeScene());  // Redirect to the home scene
        });
        navbar.getChildren().add(logoText);

        // Spacer to push the labels all the way to the right
        Region spacer = new Region();
        HBox.setHgrow(spacer, javafx.scene.layout.Priority.ALWAYS);

        // Create labels for "Read More", "About Us", and "Login"
        Label readMoreLabel = createNavLink("Read More");
        Label aboutUsLabel = createNavLink("About Us");
        Label loginLabel = createNavLink("Login");

        // Add the labels to the right-side HBox
        HBox rightSide = new HBox(20);
        rightSide.setAlignment(Pos.CENTER_RIGHT);
        rightSide.getChildren().addAll(readMoreLabel, aboutUsLabel, loginLabel);

        // Add the spacer and right-side labels to the navbar
        navbar.getChildren().addAll(spacer, rightSide);

        return navbar;
    }

    // Helper method to create a clickable Label with hover effect
    private Label createNavLink(String text) {
        Label label = new Label(text);
        label.setStyle("-fx-text-fill: white; -fx-font-size: 16px; -fx-cursor: hand;");

        // On mouse hover, underline the text
        label.setOnMouseEntered((MouseEvent e) -> {
            label.setStyle("-fx-text-fill: white; -fx-font-size: 16px; -fx-text-decoration: underline; -fx-cursor: hand;");
        });

        // Remove underline when mouse exits
        label.setOnMouseExited((MouseEvent e) -> {
            label.setStyle("-fx-text-fill: white; -fx-font-size: 16px; -fx-cursor: hand;");
        });

        // Add a click event handler (switch scenes)
        label.setOnMouseClicked((MouseEvent e) -> {
            switch (text) {
                case "Read More":
                    primaryStage.setScene(createReadMoreScene());
                    break;
                case "About Us":
                    primaryStage.setScene(createAboutUsScene());
                    break;
                case "Login":
                    primaryStage.setScene(createLoginScene());
                    break;
            }
        });

        return label;
    }
}