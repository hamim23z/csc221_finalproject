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
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
        mapView.setPrefSize(800, 600);

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

    private Scene createHowToUseScene() {
        // Use ScrollPane to handle overflow for the content, not the navbar
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        // Create a VBox for the content of the How-to-Use page
        VBox contentLayout = new VBox(10);
        contentLayout.setStyle("-fx-background-color: #f0f4f8; -fx-padding: 20px;");

        // Adding informative text
        Text howToUseText = new Text(
        		"WELCOME TO VENDORCONNECT\n\n" +

    "VIEW THE MAP OF VENDORS:\n" +
    "- AS SOON AS YOU LOG IN, YOU WILL BE ABLE TO VIEW A MAP SHOWING THE LOCATIONS OF VARIOUS VENDORS. " +
    "THE MAP WILL BE INTERACTIVE, ALLOWING YOU TO ZOOM IN AND OUT TO EXPLORE DIFFERENT AREAS. " +
    "IT IS A GREAT WAY TO GET TO KNOW THE VENDORS AROUND YOU AND MAKE INFORMED DECISIONS WHEN LOOKING FOR PRODUCTS OR SERVICES. " +
    "THE MAP IS ACCURATE AND UPDATED REGULARLY, PROVIDING A RELIABLE VIEW OF WHERE TO FIND YOUR FAVORITE VENDORS.\n\n" +
    
    "ADD YOUR OWN VENDORS TO THE MAP:\n" +
    "- ONCE YOU'RE LOGGED IN, YOU CAN EASILY ADD YOUR OWN VENDORS TO THE MAP. SIMPLY FILL OUT A FORM WITH " +
    "THEIR DETAILS, INCLUDING THE NAME, LOCATION, CATEGORY, AND OTHER RELEVANT INFORMATION. THIS WILL ALLOW OTHER USERS TO DISCOVER YOUR VENDORS " +
    "AND MAKE IT EASY FOR YOUR BUSINESS TO GAIN EXPOSURE. " +
    "THIS IS PARTICULARLY USEFUL FOR LOCAL BUSINESSES WANTING TO GET MORE VISIBILITY AND CONNECT WITH POTENTIAL CUSTOMERS.\n\n" +

    "EASY AND USER-FRIENDLY INTERFACE:\n" +
    "- VENDORCONNECT IS DESIGNED WITH SIMPLICITY IN MIND, MAKING IT EXTREMELY EASY TO NAVIGATE AND ADD CONTENT. " +
    "THE INTERFACE IS CLEAN AND INTUITIVE, PERFECT FOR USERS OF ALL TECHNICAL LEVELS. " +
    "THE DESIGN FOCUSES ON USER EXPERIENCE, WITH QUICK LOADING TIMES AND RESPONSIVE INTERACTIONS THAT ENSURE A SMOOTH EXPERIENCE ACROSS ALL DEVICES. " +
    "YOU CAN LOG IN, BROWSE THE MAP, AND ADD VENDORS WITH JUST A FEW SIMPLE CLICKS. NO COMPLEX STEPS OR FRUSTRATING NAVIGATION REQUIRED.\n\n" +

    "ENJOY SEAMLESS NAVIGATION:\n" +
    "- THE INTERFACE IS INTUITIVE, ALLOWING YOU TO BROWSE, INTERACT, AND CONTRIBUTE TO THE VENDOR MAP EFFORTLESSLY. " +
    "SIMPLY CLICK ON THE MAP, FILL OUT THE DETAILS, AND WATCH YOUR VENDOR LOCATIONS POPULATE ON THE MAP. " +
    "IT'S A FAST AND EFFICIENT WAY TO GET YOUR BUSINESS ON THE MAP, WITHOUT ANY COMPLICATED PROCESSES. " +
    "YOUR CUSTOMERS WILL BE ABLE TO FIND YOU IN NO TIME.\n\n" +

    "SUPPORT AND COMMUNITY:\n" +
    "- WE UNDERSTAND THAT QUESTIONS AND CHALLENGES MAY ARISE. THAT'S WHY VENDORCONNECT OFFERS 24/7 SUPPORT. " +
    "YOU CAN CONTACT US FOR ASSISTANCE OR JOIN OUR COMMUNITY FOR HELP FROM OTHER USERS. " +
    "WE HAVE A DEDICATED TEAM READY TO HELP YOU TROUBLESHOOT ISSUES AND ENSURE YOUR EXPERIENCE IS AS SMOOTH AS POSSIBLE. " +
    "JOINING OUR COMMUNITY FORUM CAN ALSO HELP YOU CONNECT WITH OTHER BUSINESSES AND SHARE TIPS AND INSIGHTS. " +
    "TOGETHER, WE CAN MAKE VENDORCONNECT EVEN BETTER.\n\n" +

    "CONCLUSION:\n" +
    "- WHETHER YOU'RE A VENDOR OR A USER LOOKING FOR SERVICES, VENDORCONNECT MAKES IT SIMPLE TO FIND AND ADD " +
    "VENDORS TO THE MAP. WE STRIVE TO CONNECT LOCAL BUSINESSES AND CUSTOMERS IN THE MOST EFFICIENT AND USER-FRIENDLY " +
    "WAY POSSIBLE. WE HOPE YOU ENJOY USING OUR PLATFORM AND FIND IT HELPFUL! OUR GOAL IS TO MAKE THE PROCESS AS SIMPLE, " +
    "FAST, AND EFFICIENT AS POSSIBLE, SO YOU CAN SPEND MORE TIME DOING WHAT YOU LOVE."
        );

        // Set a comprehensive style in a single call
        howToUseText.setStyle(
            "-fx-font-size: 18px;" +
            "-fx-fill: #333333;" +
            "-fx-font-family: Arial, sans-serif;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #2980b9;" +
            "-fx-padding: 30px;"
        );

        // Set wrapping width dynamically
        howToUseText.wrappingWidthProperty().bind(scrollPane.widthProperty().subtract(60));

        // Add the text to the content layout
        contentLayout.getChildren().add(howToUseText);

        // Now, create a VBox for the whole page (including the navbar and content)
        VBox layout = new VBox();
        layout.getChildren().addAll(createNavbar(), contentLayout);

        // Set the layout as the content of the ScrollPane
        scrollPane.setContent(layout);

        // Return the scene
        return new Scene(scrollPane, 800, 600);
    }

    // Helper method to create a scene for the "About Us" page
    private Scene createAboutUsScene() {
        // Use ScrollPane to handle overflow
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        // Create a VBox to hold the content
        VBox layout = new VBox(10);
        layout.setStyle("-fx-background-color: #f0f4f8; -fx-padding: 20px;");

        // About Us content (4 paragraphs)
        Text aboutUsText = new Text(
            "At VendorConnect, we believe in making connections between businesses and their customers in the easiest way possible. " +
            "Our platform brings local vendors and their customers closer, helping businesses grow by making it simple for potential customers " +
            "to find services and products near them. Whether you're a small business owner or a large enterprise, VendorConnect provides the tools " +
            "necessary to gain visibility and increase your customer base.\n\n" +
            
            "The idea for VendorConnect stemmed from the desire to simplify the process of finding vendors in your area. Often, people spend a " +
            "significant amount of time searching for local vendors and services, but with VendorConnect, you can quickly locate a variety of " +
            "options right at your fingertips. The platform is designed to be simple to use, making it easy for anyone to navigate, whether you're " +
            "a business owner or a customer.\n\n" +
            
            "We strive to create a positive experience for both vendors and customers. Vendors can easily add their businesses to the map, " +
            "ensuring that customers know where to find them. For customers, the platform provides the convenience of exploring different " +
            "vendors, reading reviews, and making informed decisions based on their needs. The map interface allows for seamless interaction, " +
            "allowing you to zoom in on specific areas and discover new vendors with ease.\n\n" +
            
            "As we continue to expand, our goal is to build a strong community of vendors and customers who can interact and grow together. " +
            "We're committed to improving the platform, enhancing the user experience, and helping businesses thrive. VendorConnect is here to " +
            "support businesses of all sizes, giving them the tools they need to succeed in a highly competitive market."
        );

        // Set a comprehensive style in a single call
        aboutUsText.setStyle(
            "-fx-font-size: 18px;" +
            "-fx-fill: #333333;" +
            "-fx-font-family: Arial, sans-serif;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #2980b9;" +
            "-fx-padding: 30px;"
        );

        // Set wrapping width dynamically
        aboutUsText.wrappingWidthProperty().bind(scrollPane.widthProperty().subtract(60));

        // Add the About Us text to the layout
        layout.getChildren().addAll(createNavbar(), aboutUsText);

        // Add an image to the bottom of the screen
        Image image = new Image("https://via.placeholder.com/800x200.png?text=Your+Image+Here");  // Example image URL
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(800);  // Set the width to fill the screen
        imageView.setPreserveRatio(true);  // Maintain aspect ratio of the image
        layout.getChildren().add(imageView);  // Add the image to the layout

        // Set content of ScrollPane
        scrollPane.setContent(layout);

        // Create scene
        Scene scene = new Scene(scrollPane, 800, 600);

        return scene;
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
        navbar.setStyle("-fx-background-color: #333; -fx-padding: 10px; -fx-pref-width: 100%;");

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

        // Create labels for "How to Use", "About Us", and "Login"
        Label howToUseLabel = createNavLink("How to Use");
        Label aboutUsLabel = createNavLink("About Us");
        Label loginLabel = createNavLink("Login");

        // Add the labels to the right-side HBox
        HBox rightSide = new HBox(20);
        rightSide.setAlignment(Pos.CENTER_RIGHT);
        rightSide.getChildren().addAll(howToUseLabel, aboutUsLabel, loginLabel);

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
                case "How to Use":
                    primaryStage.setScene(createHowToUseScene());
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