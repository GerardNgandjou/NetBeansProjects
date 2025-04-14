/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package passwordgenerator;

/**
 *
 * @author reli
 */
public class PassWordGenerator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        int boardWidth = 400;
        int boardHeight = 300;
        
        Password pass = new Password();
        pass.setSize(boardWidth, boardHeight);
        pass.setVisible(true);
        pass.pack();
        pass.setResizable(false);
        pass.setLocationRelativeTo(null);
    }
    
}
