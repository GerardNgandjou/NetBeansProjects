/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.socialmedia;

import static java.lang.System.err;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author reli
 */
public class Like {
    
    // Database connection details
    String url = "jdbc:mysql://localhost/socialmediaapp";
    String user = "root";
    String password = "root";
    
    private int likeID;
    private String contentLike;
    private int userID;

    public Like(int likeID, String contentLike, int userID) {
        if (userID < 0) throw new IllegalArgumentException("Invalid user ID");
        if (likeID < 0) throw new IllegalArgumentException("Invalid user ID");
        this.likeID = likeID;
        this.contentLike = contentLike;
        this.userID = userID;
    }
    
     public int getLikeID() {
        return this.likeID;
    }
    
    public int getUserID() {
        return this.userID;
    }
    
    public String getContentLike() { 
        return this.contentLike;
    }
    
    public void setLikeID(int newLikeID) {
        if (newLikeID < 0) throw new IllegalArgumentException("Invalid user ID");
        this.likeID = newLikeID;
    }
    
    public void setUserID(int newUserID) {
        if (newUserID < 0) throw new IllegalArgumentException("Invalid user ID");
        this.userID = newUserID;
    }
    
    public void setContentLike(String newContentLike) {
        this.contentLike = newContentLike;
    }
    
    public void addLike() {
        // Insertion Request
        String sql = "INSERT INTO likes(idlike, contentlike, userid) "
                   + "Values(?, ?, ?)";
        
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
            ps.setInt(1, this.likeID);
            ps.setString(2, this.contentLike);
            ps.setInt(3, this.userID);
            
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
    
    public void updateLike(int likeID, String contenLi, int useID) throws SQLException {
        // SQL with proper syntax and parameterized queries
        String sql = "UPDATE likes SET contentlike = ?, userid = ? WHERE idlike = ?";
    
        try {
            // Load the JDBC driver (optional for modern JDBC versions)
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (
                Connection con = DriverManager.getConnection(url, user, password);
                PreparedStatement ps = con.prepareStatement(sql)
            ) {
                // Set parameters in the PreparedStatement
                ps.setString(1, contenLi);  // New username
                ps.setInt(2, useID);           // User ID
                ps.setInt(3, likeID);

                // Execute the update
                int rowsUpdated = ps.executeUpdate();

                if (rowsUpdated > 0) {
                    out.println("Successfully updated user");
                } else {
                    out.println("No user found with ID: " + likeID);
                }
            }
        } catch (ClassNotFoundException e) {
            err.println("MySQL JDBC Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            err.println("Database error: " + e.getMessage());
        } catch (Exception e) {
            err.println(e.getMessage());
        }
    }
    
    public void deleteLike(int id) {
         // Update request
        String sql = "DELETE "
                   + "FROM likes "
                   + "WHERE idlike = ?";
        
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
                out.println("Successfull deleted");
            }  else {
                out.println("No user found with ID: " + id);
            }
            
        }
            
        } catch (ClassNotFoundException e) {
            err.println("MySQL JDBC Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            err.println("Database error: " + e.getMessage());
        } catch (Exception e) {
            err.println(e.getMessage());
        }
    }
    
        public void displayAllLikes() {
        // SQL query to retrieve all rows from the 'likes' table
        String sql = "SELECT * FROM likes";

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Use try-with-resources to automatically close the connection, statement, and result set
            try (
                // Establish the connection to the database
                Connection con = DriverManager.getConnection(url, user, password);

                // Create a prepared statement for the SQL query
                PreparedStatement stmt = con.prepareStatement(sql);

                // Execute the query and store the result set
                ResultSet rs = stmt.executeQuery()
            ) {
                // Print header for display
                System.out.println("All Likes in the system:");
                System.out.println("-------------------------");

                boolean found = false; // To check if any like was found

                // Loop through each row in the result set
                while (rs.next()) {
                    // Extract data from the current row
                    int likeId = rs.getInt("idlike");
                    String content = rs.getString("contentlike");
                    int userId = rs.getInt("userid");
                    

                    // Print the like info to the console
                    System.out.println("Like ID: " + likeId + 
                                       " | Content: " + content + 
                                       " | User ID: " + userId);
                    found = true; // At least one like found
                }

                // If no likes were found, print a message
                if (!found) {
                    System.out.println("No like found.");
                }

            }
        } catch (ClassNotFoundException e) {
            // Error if JDBC driver is not found
            System.err.println("JDBC Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            // Error if there's a problem with the SQL or database connection
            System.err.println("SQL Error: " + e.getMessage());
        } catch (Exception e) {
            // Catch any other unexpected error
            System.err.println("Unexpected Error: " + e.getMessage());
        }
    }
    
}
