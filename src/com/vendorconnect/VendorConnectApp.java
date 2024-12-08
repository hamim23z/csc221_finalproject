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
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class VendorConnectApp extends Application {
    private Stage primaryStage;  // The primary stage for switching scenes

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;  // Set primaryStage for scene switching

        // Fullscreen configuration for the stage
        primaryStage.setFullScreen(true);

        // Set initial scene (Home page or main content)
        Scene homeScene = createHomeScene();
        primaryStage.setScene(homeScene);
        primaryStage.setTitle("VendorConnect");
        primaryStage.show();
    }

    // Helper method to create a scene for the home page with Google Maps
    private Scene createHomeScene() {
        StackPane homeContent = new StackPane();

        // Create a WebView to display Google Maps
        WebView mapView = new WebView();
        mapView.setPrefSize(800, 600);  // Set the preferred size of the WebView

        // Enable JavaScript for the WebView
        mapView.getEngine().setJavaScriptEnabled(true);

        String htmlContent = """
        <!DOCTYPE html>
        <html>
          <head>
            <title>Vendor Connect Map</title>
            <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyANf8poiTzVJfj46o2o1Yf_4YsEg4WV8m8&callback=initMap" async defer></script>
            <style>
              html, body {
                height: 100%;
                margin: 0;
                padding: 0;
              }
              #map {
                height: 100%;
                width: 100%;
              }
            </style>
          </head>
          <body>
            <div id="map"></div>
            <script>
              function initMap() {
                // New York City coordinates
                const location = { lat: 40.7128, lng: -74.0060 };
                const map = new google.maps.Map(document.getElementById('map'), {
                  center: location,
                  zoom: 12,
                  mapTypeId: 'roadmap'
                });
                
                // Add a marker
                new google.maps.Marker({
                  position: location,
                  map: map,
                  title: 'New York City'
                });
              }
            </script>
          </body>
        </html>
        """;

        mapView.getEngine().loadContent(htmlContent);  // Load the Google Maps HTML content
        homeContent.getChildren().add(mapView);  // Add the WebView to the StackPane

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