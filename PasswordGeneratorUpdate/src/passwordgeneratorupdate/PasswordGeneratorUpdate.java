/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package passwordgeneratorupdate;

/**
 *
 * @author reli
 */
public class PasswordGeneratorUpdate {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        int boardWidth = 400;
        int boardHeight = 300;
        
        passwordGeneratorUpdate passwordgeneratorupdate = new passwordGeneratorUpdate();
        passwordgeneratorupdate.setVisible(true);
        passwordgeneratorupdate.setLocationRelativeTo(null);
        passwordgeneratorupdate.setResizable(false);
        passwordgeneratorupdate.pack();
        passwordgeneratorupdate.setSize(boardWidth, boardHeight);
        
    }
    
}
