/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.socialmedia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author reli
 */
public class Users {
    // Database connection details
    String url = "jdbc:mysql://localhost/socialmediaapp";
    String user = "root";
    String password = "root";
    
    ArrayList<Users> users = new ArrayList<Users>();
    
    // Attirbut + constructors + gettors and settors
    private int userID;
    private String userName;

    public Users(int userID, String userName) {      // Construtors
        if (userID < 0) throw new IllegalArgumentException("Invalid user ID");
        this.userID = userID;
        this.userName = userName;
    }
    
    public int getUserID () {
        return this.userID;
    }
    
    public String getUserName () {
        return this.userName;
    }
    
    public void setUserID (int newUserID) {
        if (newUserID < 0) throw new IllegalArgumentException("Invalid user ID");
        this.userID = newUserID;
    }
    
    public void setUserName (String newUserName) {
        this.userName = newUserName;
    }
    
    // Methods
    public void addUser() {
        // Insertion Request
        String sql = "INSERT INTO user(userid, username) "
                   + "Values(?, ?)";
        
        try {
            // Load the MySQL JDBC Connector
//            Class.forName("com.mysql.cj.jdbc.Driver");
            Class.forName("com.mysql.cj.jdbc.Driver");
//            System.out.println("Driver loaded!");
            
            try (
            // Etablish the onnection to the database
            Connection con = DriverManager.getConnection(url, user, password);
            // create the PreparedStatement
            PreparedStatement ps = con.prepareStatement(sql);
        ) {            
            // Set parameters in the PreparedStatment
            ps.setInt(1, this.getUserID());
            ps.setString(2, this.getUserName());
            
            // Execute the query
            int rowsInserted = ps.executeUpdate();
            
            if (rowsInserted > 0) {
                System.out.println("Successfull insertion");
            }
            
        }
            
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    
    public void updateUser(int id, String newUsername) throws SQLException {
        // SQL with proper syntax and parameterized queries
        String sql = "UPDATE user SET username = ? WHERE userid = ?";

        try {
            // Load the JDBC driver (optional for modern JDBC versions)
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (
                Connection con = DriverManager.getConnection(url, user, password);
                PreparedStatement ps = con.prepareStatement(sql)
            ) {
                // Set parameters in the PreparedStatement
                ps.setString(1, newUsername);  // New username
                ps.setInt(2, id);             // User ID

                // Execute the update
                int rowsUpdated = ps.executeUpdate();

                if (rowsUpdated > 0) {
                    System.out.println("Successfully updated user");
                } else {
                    System.out.println("No user found with ID: " + id);
                }
            }
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    
    public void deleteUser(int id) {
        // Update request
        String sql = "DELETE "
                   + "FROM user "
                   + "WHERE userid = ?";
        
        try {
            // Load the MySQL JDBC Connector
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            try ( 
            // Etablish the onnection to the database
            Connection con = DriverManager.getConnection(url, user, password);
            // create the PreparedStatement
            PreparedStatement ps = con.prepareStatement(sql); 
            ) {            
            
            // Set parameters in the PreparedStatment
            ps.setInt(1, id);
            
            // Execute the query
            int rowsInserted = ps.executeUpdate();
            
            if (rowsInserted > 0) {
                System.out.println("Successfull deleted");
            }  else {
                System.out.println("No user found with ID: " + id);
            }
            
        }
            
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    
    public void displayAllUsers() throws ClassNotFoundException {
        String sql = "SELECT * FROM user";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (
                Connection con = DriverManager.getConnection(url, user, password);
                PreparedStatement stmt = con.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery(sql)
            ) {
                System.out.println("All users in the system:");
                System.out.println("-------------------------");

                boolean found = false;
                while (rs.next()) {
                    int id = rs.getInt("userid");
                    String name = rs.getString("username");
                    System.out.println("ID: " + id + " | Username: " + name);
                    found = true;
                }

                if (!found) {
                    System.out.println("No users found.");
                }
            }

        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected Error: " + e.getMessage());
        }
    }

    
}
