package com.vendorconnect;

import java.sql.*;

public class VendorConnectDb {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:shop.db"; // SQLite database file
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                System.out.println("Connected to the database.");
                createTables(conn);
                // You can perform CRUD operations here
                insertSampleData(conn);
                fetchAndDisplayData(conn);
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e.getMessage());
        }
    }

    private static void createTables(Connection conn) throws SQLException {
        String createMerchandiseTable = """
            CREATE TABLE IF NOT EXISTS Merchandise (
                merch_id INTEGER PRIMARY KEY AUTOINCREMENT,
                merch_name TEXT NOT NULL
            );
        """;

        String createCustomerTable = """
            CREATE TABLE IF NOT EXISTS Customer (
                cust_id INTEGER PRIMARY KEY AUTOINCREMENT,
                cust_username TEXT NOT NULL,
                cust_passwd TEXT NOT NULL
            );
        """;

        String createVendorTable = """
            CREATE TABLE IF NOT EXISTS Vendor (
                vendor_id INTEGER PRIMARY KEY AUTOINCREMENT,
                vendor_username TEXT NOT NULL,
                vendor_passwd TEXT NOT NULL,
                vendor_address TEXT NOT NULL,
                vendor_city TEXT NOT NULL,
                vendor_zip TEXT NOT NULL,
                merch_id INTEGER NOT NULL,
                FOREIGN KEY (merch_id) REFERENCES Merchandise(merch_id)
            );
        """;

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(createMerchandiseTable);
            stmt.execute(createCustomerTable);
            stmt.execute(createVendorTable);
            System.out.println("Tables created successfully.");
        }
    }

    private static void insertSampleData(Connection conn) throws SQLException {
        String insertMerchandise = "INSERT INTO Merchandise (merch_name) VALUES (?)";
        String insertCustomer = "INSERT INTO Customer (cust_username, cust_passwd) VALUES (?, ?)";
        String insertVendor = """
            INSERT INTO Vendor (vendor_username, vendor_passwd, vendor_address, vendor_city, vendor_zip, merch_id)
            VALUES (?, ?, ?, ?, ?, ?)
        """;

        try (PreparedStatement merchStmt = conn.prepareStatement(insertMerchandise);
             PreparedStatement custStmt = conn.prepareStatement(insertCustomer);
             PreparedStatement vendorStmt = conn.prepareStatement(insertVendor)) {

            // Insert Merchandise
            merchStmt.setString(1, "Laptop");
            merchStmt.executeUpdate();

            // Insert Customer
            custStmt.setString(1, "john_doe");
            custStmt.setString(2, "password123");
            custStmt.executeUpdate();

            // Insert Vendor
            vendorStmt.setString(1, "vendor1");
            vendorStmt.setString(2, "securepass");
            vendorStmt.setString(3, "123 Market St");
            vendorStmt.setString(4, "Brooklyn");
            vendorStmt.setString(5, "12201");
            vendorStmt.setInt(6, 1); // Assuming the merch_id of the Laptop is 1
            vendorStmt.executeUpdate();
        }

        System.out.println("Sample data inserted successfully.");
    }

    private static void fetchAndDisplayData(Connection conn) throws SQLException {
        String query = """
            SELECT v.vendor_id, v.vendor_username, m.merch_name
            FROM Vendor v
            JOIN Merchandise m ON v.merch_id = m.merch_id
        """;

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                System.out.printf("Vendor ID: %d, Username: %s, Merchandise: %s%n",
                        rs.getInt("vendor_id"),
                        rs.getString("vendor_username"),
                        rs.getString("merch_name"));
            }
        }
    }
}
