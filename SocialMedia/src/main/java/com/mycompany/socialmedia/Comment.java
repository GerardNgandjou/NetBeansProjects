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

/**
 *
 * @author reli
 */
public class Comment {
    
    //Database information
    String url = "jdbc:mysql://localhost/socialmediaapp";
    String user = "root";
    String password = "root";
    
    // Attributs
    private int commentID;
    private String contentComm;
    private int postID;
    private int userID;

    // The constructor
    public Comment(int commentID, String contentComm, int postID, int userID) {
        if (commentID < 0) throw new IllegalArgumentException("Invalid user ID");
        if (postID < 0) throw new IllegalArgumentException("Invalid user ID");
        if (userID < 0) throw new IllegalArgumentException("Invalid user ID");
        this.commentID = commentID;
        this.contentComm = contentComm;
        this.postID = postID;
        this.userID = userID;
    }
    
    // the Gettors
    public int getCommentID() {
        return this.commentID;
    }
    
    public String getContentComm() {
        return this.contentComm;
    }
    
    public int getPostID() {
        return this.postID;
    }
    
    public int getUserID() {
        return this.userID;
    }
    
    // The Settors
    public void setCommentID(int newCommentID) {
        if (newCommentID < 0) throw new IllegalArgumentException("Invalid user ID");
        this.commentID = newCommentID;
    }
    
    public void setUserID(int newUserID) {
        if (newUserID < 0) throw new IllegalArgumentException("Invalid user ID");
        this.userID = newUserID;
    }
    
    public void setPostID(int newPostID) {
        if (newPostID < 0) throw new IllegalArgumentException("Invalid user ID");
        this.postID = newPostID;
    }
    
    public void setContentComID(String newContentComID) {
        this.contentComm = newContentComID;
    }
    
    public void addComment() {
        // Insertion Request
        String sql = "INSERT INTO comments(idcomment, contentcom, userid, postid)"
                   + "Values(?, ?, ?, ?)";
        
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
            ps.setInt(1, getCommentID());
            ps.setString(2, getContentComm());
            ps.setInt(3, getUserID());
            ps.setInt(4, getPostID());
            
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
    
    public void updateComment(int comID, String contenCo, int posID, int useID) throws SQLException {
        // SQL with proper syntax and parameterized queries
        String sql = "UPDATE comments "
               + "SET contentcom = ?, userid = ?, postid = ? "
               + "WHERE idcomment = ?";
    
        try {
            // Load the JDBC driver (optional for modern JDBC versions)
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (
                Connection con = DriverManager.getConnection(url, user, password);
                PreparedStatement ps = con.prepareStatement(sql)
            ) {
                // Set parameters in the PreparedStatement
                ps.setString(1, contenCo);  // New username
                ps.setInt(2, posID);
                ps.setInt(3, useID);
                ps.setInt(4, comID);             // User ID

                // Execute the update
                int rowsUpdated = ps.executeUpdate();

                if (rowsUpdated > 0) {
                    System.out.println("Successfully updated user");
                } else {
                    System.out.println("No user found with ID: " + comID);
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
    
    public void deleteComment(int id) {
         // Update request
        String sql = "DELETE "
                   + "FROM comments "
                   + "WHERE idcomment = ?";
        
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
    
    public void displayAllComments() {
        // SQL query to retrieve all rows from the 'comments' table
        String sql = "SELECT * FROM comments";

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
                System.out.println("All Comments in the system:");
                System.out.println("-------------------------");

                boolean found = false; // To check if any comment was found

                // Loop through each row in the result set
                while (rs.next()) {
                    // Extract data from the current row
                    int commentId = rs.getInt("idcomment");
                    String content = rs.getString("contentcom");
                    int userId = rs.getInt("userid");
                    int postId = rs.getInt("postid");
                    

                    // Print the comment info to the console
                    System.out.println("Comment ID: " + commentId + 
                                       " | Content: " + content + 
                                       " | User ID: " + userId + 
                                       " | post ID: " + postId);
                    found = true; // At least one comment found
                }

                // If no comments were found, print a message
                if (!found) {
                    System.out.println("No comments found.");
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
