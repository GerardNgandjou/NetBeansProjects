/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ystemmanagementstudent;

/**
 *
 * @author reli
 */
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggingExample {
    // Create a Logger instance for the class
    private static final Logger logger = Logger.getLogger(LoggingExample.class.getName());

    public static void main(String[] args) {
        // Log messages at different levels
        logger.severe("This is a SEVERE message.");
        logger.warning("This is a WARNING message.");
        logger.info("This is an INFO message.");
        logger.config("This is a CONFIG message.");
        logger.fine("This is a FINE message.");
        logger.finer("This is a FINER message.");
        logger.finest("This is a FINEST message.");

        // Log an exception
        try {
            int result = 10 / 0; // This will throw an ArithmeticException
        } catch (ArithmeticException ex) {
            logger.log(Level.SEVERE, "An error occurred: ", ex);
        }
    }
}