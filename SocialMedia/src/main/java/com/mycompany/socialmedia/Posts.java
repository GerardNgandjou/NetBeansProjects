package com.mycompany.socialmedia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Posts {

    // Database connection details
    String url = "jdbc:mysql://localhost/socialmediaapp";
    String user = "root";
    String password = "root";

    private int postID;
    private String content;
    private int userID;

    public Posts(int postID, String content, int userID) {
        if (postID < 0) throw new IllegalArgumentException("Invalid post ID");
        if (userID < 0) throw new IllegalArgumentException("Invalid user ID");
        this.postID = postID;
        this.content = content;
        this.userID = userID;
    }

    public int getPostID() {
        return this.postID;
    }

    public int getUserID() {
        return this.userID;
    }

    public String getContent() {
        return this.content;
    }

    public void setPostID(int newPostID) {
        if (newPostID < 0) throw new IllegalArgumentException("Invalid post ID");
        this.postID = newPostID;
    }

    public void setUserID(int newUserID) {
        if (newUserID < 0) throw new IllegalArgumentException("Invalid user ID");
        this.userID = newUserID;
    }

    public void setContent(String newContent) {
        this.content = newContent;
    }

    private boolean userExists(int userId) {
        String sql = "SELECT userid FROM user WHERE userid = ?";
        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            System.err.println("Database error checking user: " + e.getMessage());
            return false;
        }
    }

    public void addPost() {
        if (!userExists(this.userID)) {
            System.err.println("User with ID " + this.userID + " does not exist.");
            return;
        }

        String sql = "INSERT INTO posts(postid, userid, contentpost) VALUES (?, ?, ?)";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection(url, user, password);
                 PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, this.postID);
                ps.setInt(2, this.userID);
                ps.setString(3, this.content);

                int rowsInserted = ps.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("Post added successfully.");
                }
            }
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL Driver error: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }

    public void updatePost(int postID, int userID, String contentPost) {
        if (!userExists(userID)) {
            System.err.println("User with ID " + userID + " does not exist.");
            return;
        }

        String sql = "UPDATE posts SET userid = ?, contentpost = ? WHERE postid = ?";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection(url, user, password);
                 PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, userID);
                ps.setString(2, contentPost);
                ps.setInt(3, postID);

                int rowsUpdated = ps.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Post updated successfully.");
                } else {
                    System.out.println("No post found with ID: " + postID);
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public void deletePost(int id) {
        String sql = "DELETE FROM posts WHERE postid = ?";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection(url, user, password);
                 PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, id);

                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Post deleted successfully.");
                } else {
                    System.out.println("No post found with ID: " + id);
                }
            }
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }

    public void displayAllPosts() {
        String sql = "SELECT postid, userid, contentpost FROM posts";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection(url, user, password);
                 PreparedStatement stmt = con.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {

                System.out.println("All Posts:");
                System.out.println("-----------");
                boolean found = false;

                while (rs.next()) {
                    found = true;
                    System.out.println(
                        "Post ID: " + rs.getInt("postid") +
                        " | User ID: " + rs.getInt("userid") +
                        " | Content: " + rs.getString("contentpost")
                    );
                }

                if (!found) {
                    System.out.println("No posts found.");
                }
            }
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
        }
    }
}