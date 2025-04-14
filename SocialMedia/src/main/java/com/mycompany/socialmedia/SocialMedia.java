/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.socialmedia;

import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author reli
 */

public class SocialMedia {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        
        Scanner scan = new Scanner(System.in);
        
        int choose;
        int chose;
        
        int likeID;
        String contentLike;
        int commentID;
        String contentComm;
        int postID;
        String contentPost;
        int userID;
        String userName = "";
        
        Users users;
        Like like;
        Comment comment;
        Posts posts;
        
        do {            
            
            System.out.println("_____Welcome in my social media app_____");
            System.out.println("| 1. Users                             |");
            System.out.println("| 2. Likes                             |");
            System.out.println("| 3. Posts                             |");
            System.out.println("| 4. Comments                          |");
            System.out.println("| 5. Exit                              |");
            System.out.println("________________________________________");
            choose = scan.nextInt();
            
            switch (choose) {
                case 1:
                    
                    do {                        
                        System.out.println("__________Handling Users__________");
                        System.out.println("| 1. Add Users                   |");
                        System.out.println("| 2. Update Users                |");
                        System.out.println("| 3. Delete Users                |");
                        System.out.println("| 4. Display users               |");
                        System.out.println("| 5. Exit                        |");
                        System.out.println("__________________________________");
                        chose = scan.nextInt();
                        
                        switch (chose) {
                            case 1:
                                System.out.print("Enter the user ID (Integer only): ");
                                userID = scan.nextInt();
                                scan.nextLine(); // ← flush the leftover newline
                                System.out.print("Enter the user Name: ");
                                userName = scan.nextLine();

                                users = new Users(userID, userName);
                                users.addUser();
                                break;

                            case 2:
                                System.out.print("Enter the user ID (Integer only): ");
                                userID = scan.nextInt();
                                scan.nextLine(); // ← flush the leftover newline
                                System.out.print("Enter the user Name: ");
                                userName = scan.nextLine();

                                users = new Users(userID, userName);
                                users.updateUser(userID, userName);
                                break;
                                
                            case 3:
                                System.out.print("Enter the user ID (Integer only): ");
                                userID = scan.nextInt();
                                scan.nextLine(); // ← flush the leftover newline
                                
                                users = new Users(userID, userName);
                                users.deleteUser(userID);
                                break;
                                
                            case 4:
                                userID = 0;
                                userName = "";
                                
                                users = new Users(userID, userName);
                                users.displayAllUsers();
                                break;
                                
                            case 5:
                                System.out.println("See you next time");
                                break;

                            default:
                                System.out.println("Invalid choice. Please try again.");
                                break;
//                              throw new AssertionError();
                        }
                        
                    } while (chose != 5);
                    
                    break;
                    
                case 2:
                    
                    do {                        
                        System.out.println("__________Handling Likes__________");
                        System.out.println("| 1. Add Likes                   |");
                        System.out.println("| 2. Update Likes                |");
                        System.out.println("| 3. Delete Likes                |");
                        System.out.println("| 4. Display Likes               |");
                        System.out.println("| 5. Exit                        |");
                        System.out.println("__________________________________");
                        chose = scan.nextInt();
                        
                        switch (chose) {
                            case 1:
                                System.out.print("Enter the like ID (Integer only): ");
                                likeID = scan.nextInt();
                                scan.nextLine(); // ← flush the leftover newline
                                System.out.print("Enter the user ID (Integer only): ");
                                userID = scan.nextInt();
                                scan.nextLine(); // ← flush the leftover newline
                                System.out.print("Enter the content like: ");
                                contentLike = scan.nextLine();

                                like = new Like(likeID, contentLike, userID);
                                like.addLike();
                                break;

                            case 2:
                                System.out.print("Enter the like ID (Integer only): ");
                                likeID = scan.nextInt();
                                scan.nextLine(); // ← flush the leftover newline
                                System.out.print("Enter the user ID (Integer only): ");
                                userID = scan.nextInt();
                                scan.nextLine(); // ← flush the leftover newline
                                System.out.print("Enter the content like: ");
                                contentLike = scan.nextLine();

                                like = new Like(likeID, contentLike, userID);
                                like.updateLike(likeID, contentLike, userID);
                                break;
                                
                            case 3:
                                System.out.print("Enter the like ID (Integer only): ");
                                likeID = scan.nextInt();
                                scan.nextLine(); // ← flush the leftover newline
                                userID = 0;
                                contentLike = "";
                                
                                like = new Like(likeID, contentLike, userID);
                                like.deleteLike(likeID);
                                like.hashCode();
                                break;
                                
                            case 4:
                                likeID = 0;
                                userID = 0;
                                contentLike = "";
                                
                                like = new Like(likeID, contentLike, userID);
                                like.displayAllLikes();
                                break;
                                
                            case 5:
                                System.out.println("See you next time");
                                break;

                            default:
                                System.out.println("Invalid choice. Please try again.");
                                break;
//                              throw new AssertionError();
                        }
                        
                    } while (chose != 5);
                    
                    break;
                    
                case 3:
                    
                    do {                        
                        System.out.println("__________Handling Posts__________");
                        System.out.println("| 1. Add Posts                   |");
                        System.out.println("| 2. Update Posts                |");
                        System.out.println("| 3. Delete Posts                |");
                        System.out.println("| 4. Display Posts               |");
                        System.out.println("| 5. Exit                        |");
                        System.out.println("__________________________________");
                        chose = scan.nextInt();
                        
                        switch (chose) {
                            case 1:
                                System.out.print("Enter the post ID (Integer only): ");
                                postID = scan.nextInt();
                                scan.nextLine(); // ← flush the leftover newline
                                System.out.print("Enter the user ID (Integer only): ");
                                userID = scan.nextInt();
                                scan.nextLine(); // ← flush the leftover newline
                                System.out.print("Enter the content post: ");
                                contentPost = scan.nextLine();

                                posts = new Posts(postID, contentPost, userID);
                                posts.addPost();
                                break;

                            case 2:
                                System.out.print("Enter the post ID (Integer only): ");
                                postID = scan.nextInt();
                                scan.nextLine(); // ← flush the leftover newline
                                System.out.print("Enter the user ID (Integer only): ");
                                userID = scan.nextInt();
                                scan.nextLine(); // ← flush the leftover newline
                                System.out.print("Enter the content post: ");
                                contentPost = scan.nextLine();

                                posts = new Posts(postID, contentPost, userID);
                                posts.updatePost(postID, userID, contentPost);
                                break;
                                
                            case 3:
                                System.out.print("Enter the post ID (Integer only): ");
                                postID = scan.nextInt();
                                scan.nextLine(); // ← flush the leftover newline
                                userID = 0;
                                contentPost = "";
                                
                                posts = new Posts(postID, contentPost, userID);
                                posts.deletePost(postID);
                                posts.hashCode();
                                break;
                                
                            case 4:
                                postID = 0;
                                userID = 0;
                                contentPost = "";
                                
                                posts = new Posts(postID, contentPost, userID);
                                posts.displayAllPosts();
                                break;
                                
                            case 5:
                                System.out.println("See you next time");
                                break;

                            default:
                                System.out.println("Invalid choice. Please try again.");
                                break;
//                              throw new AssertionError();
                        }
                        
                    } while (chose != 5);
                    
                    break;
                    
                case 4:
                    
                    do {                        
                        System.out.println("__________Handling Comments__________");
                        System.out.println("| 1. Add Comments                   |");
                        System.out.println("| 2. Update Comments                |");
                        System.out.println("| 3. Delete Comments                |");
                        System.out.println("| 4. Display Comments               |");
                        System.out.println("| 5. Exit                           |");
                        System.out.println("_____________________________________");
                        chose = scan.nextInt();
                        
                        switch (chose) {
                            case 1:
                                System.out.print("Enter the comment ID (Integer only): ");
                                commentID = scan.nextInt();
                                scan.nextLine(); // ← flush the leftover newline
                                System.out.print("Enter the user ID (Integer only): ");
                                userID = scan.nextInt();
                                System.out.print("Enter the post ID (Integer only): ");
                                postID = scan.nextInt();
                                scan.nextLine(); // ← flush the leftover newline
//                                scan.nextLine(); // ← flush the leftover newline
                                System.out.print("Enter the content comment: ");
                                contentComm = scan.nextLine();

                                comment = new Comment(commentID, contentComm, postID, userID);
                                comment.addComment();
                                break;

                            case 2:
                                System.out.print("Enter the comment ID (Integer only): ");
                                commentID = scan.nextInt();
                                scan.nextLine(); // ← flush the leftover newline
                                System.out.print("Enter the user ID (Integer only): ");
                                userID = scan.nextInt();
                                scan.nextLine(); // ← flush the leftover newline
                                System.out.print("Enter the post ID (Integer only): ");
                                postID = scan.nextInt();
                                scan.nextLine(); // ← flush the leftover newline
                                System.out.print("Enter the content comment: ");
                                contentComm = scan.nextLine();

                                comment = new Comment(commentID, contentComm, postID, userID);
                                comment.updateComment(commentID, contentComm, postID, userID);
                                break;
                                
                            case 3:
                                System.out.print("Enter the comment ID (Integer only): ");
                                commentID = scan.nextInt();
                                scan.nextLine(); // ← flush the leftover newline
                                userID = 0;
                                contentComm = "";
                                postID = 0;
                                
                                comment = new Comment(commentID, contentComm, postID, userID);
                                comment.deleteComment(commentID);
                                comment.hashCode();
                                break;
                                
                            case 4:
                                commentID = 0;
                                userID = 0;
                                contentComm = "";
                                postID = 0;
                                
                                comment = new Comment(commentID, contentComm, postID, userID);
                                comment.displayAllComments();
                                break;
                                
                            case 5:
                                System.out.println("See you next time");
                                break;

                            default:
                                System.out.println("Invalid choice. Please try again.");
                                break;
//                              throw new AssertionError();
                        }
                        
                    } while (chose != 5);
                    
                    break;                    
                    
                case 5:
                    System.out.println("Thanks you for your visit");
                    break;    
                    
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
//                  throw new AssertionError();
            }
            
        } while (choose != 5);

    }
}
