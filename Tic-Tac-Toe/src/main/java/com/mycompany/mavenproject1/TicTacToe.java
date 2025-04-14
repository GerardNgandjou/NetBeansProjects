/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author reli
 */
public class TicTacToe {
    int boardHeight = 650; //50px for the text panel on top
    int boardWidht = 600;
    
    JFrame frame = new JFrame("Tic-Tac-Toe");
    JLabel label = new JLabel();
    JPanel panel = new JPanel();
    JPanel boardPanel = new JPanel();
    
    JButton[][] board = new JButton[3][3];
    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX;
    
    Boolean gameBoolean = false;
    int turns = 0;
    
    TicTacToe() {
        frame.setVisible(true);
        frame.setSize(boardWidht, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        
        label.setBackground(Color.DARK_GRAY);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 50));  
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setText("Tic-Tac-Toe");
        label.setOpaque(true);
        
        panel.setLayout(new BorderLayout());
        panel.add(label);
        frame.add(panel, BorderLayout.NORTH);
        
        boardPanel.setLayout(new GridLayout(3, 3));
        boardPanel.setBackground(Color.DARK_GRAY);
        frame.add(boardPanel);
        
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                JButton title = new JButton();
                board[r][c] = title; 
                boardPanel.add(title);
                // r = row 
                // c = column
                
                title.setBackground(Color.DARK_GRAY);
                title.setForeground(Color.white);
                title.setFont(new Font("Arial", Font.BOLD, 120));
                title.setFocusable(false);
//                title.setText(currentPlayer);

                title.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        if(gameBoolean) return;
                        
                        JButton title = (JButton) e.getSource();
                        
                        if (title.getText().isEmpty()) {
                            title.setText(currentPlayer);
                            turns++;
                            checkWinner();
                            if (!gameBoolean) {
                                currentPlayer = currentPlayer == playerX ? playerO : playerX;
                                label.setText(currentPlayer + "'s turn"); 
                            }
                                                                                
                        }
                    }
                });
            }
            
        }
    }
    
   public void checkWinner(){
       //horizontal
       for (int r = 0; r < 3; r++) {
           if (board[r][0].getText().isEmpty()) {
               continue;
           }
           
           if (board[r][0].getText() == board[r][1].getText() && 
               board[r][1].getText() == board[r][2].getText()) {
               for (int i = 0; i < 3; i++) {
                   setWinner(board[r][i]);
               }
               gameBoolean = true;
               showGameOverDialog();
               return;
           }
       }
       
       //vertical
       for (int c = 0; c < 3; c++) {
           if (board[0][c].getText().isEmpty()) {
               continue;
           }
           
           if (board[0][c].getText() == board[1][c].getText() &&
               board[1][c].getText() == board[2][c].getText()) {
               for (int i = 0; i < 3; i++) {
                   setWinner(board[i][c]);
               }
               gameBoolean = true;
               showGameOverDialog();
               return;
           }
       }
       
       //diagonally
       if (board[0][0].getText() == board[1][1].getText() &&
           board[1][1].getText() == board[2][2].getText() &&
           board[0][0].getText() != "") {
               for (int i = 0; i < 3; i++) {
                   setWinner(board[i][i]);
               }
               gameBoolean = true;
               showGameOverDialog();
               return;
       }
       
       //anti-diagonally
       if (board[0][2].getText() == board[1][1].getText() &&
           board[1][1].getText() == board[2][0].getText() &&
           board[0][2].getText() != "") {
           setWinner(board[0][2]);
           setWinner(board[1][1]);
           setWinner(board[2][0]);
           gameBoolean = true;
           showGameOverDialog();
           return;
        }
       
       if (turns == 9) {
           for (int r = 0; r < 3; r++) {
               for (int c = 0; c < 3; c++) {
                   setTie(board[r][c]);
               }
           }
           gameBoolean = true;
           showGameOverDialog();
       }
   }
   
   public void setWinner(JButton title) {
       title.setBackground(Color.GRAY);
       title.setForeground(Color.GREEN);
       label.setText(currentPlayer + " is the winner!");
   }
   
   public void setTie(JButton title) {
       title.setBackground(Color.GRAY);
       title.setForeground(Color.ORANGE);
       label.setText("Tie!");
   }
   
   private void showGameOverDialog() {
        int playAgain = JOptionPane.showConfirmDialog(frame, 
            "Do you want to play again?", 
            "Play Again", 
            JOptionPane.YES_NO_OPTION);
        if(playAgain == JOptionPane.YES_OPTION) {
            startNewGame();
        } else {
            frame.dispose();
        }
    }

    public void startNewGame(){
        // Reset board
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                board[r][c].setText("");
                board[r][c].setBackground(Color.DARK_GRAY);
                board[r][c].setForeground(Color.WHITE);
            }
        }
    
        // Reset game state
        currentPlayer = playerX;
        gameBoolean = false;
        turns = 0;
        label.setText("Tic-Tac-Toe");
    }
}
