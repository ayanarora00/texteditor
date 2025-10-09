package edu.grinnell.csc207.texteditor;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.gui2.Direction;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.swing.SwingTerminalFrame;

/**
 * The driver for the TextEditor Application.
 */
public class TextEditor {


    public static void drawBuffer(GapBuffer buf, Screen screen) throws IOException {

        // Total number of columns that the screen has (the length)
        int cols = screen.getTerminalSize().getColumns();

        // We clear the screen every time for it to update later
        screen.clear();

        //  Rendering the content to the screen. We traverse the buffer and adds the characters to 
        // the screen
        for (int i = 0; i <  buf.getSize(); i++){

            // We make sure i is in the bounds of the screen's length
            if (i < cols){

                // Assigning the buffer character to a TextCharacter
                char ch = buf.getChar(i);
                TextCharacter c = new TextCharacter(ch);

                // Printing that TextCharacter on screen
                screen.setCharacter(i, 0, c);
            }
            
        }

        // Getting cursor position
        int cursor = buf.getCursorPosition();

        // Setting/rendering the cursor to the screen based off of the cursor position
        screen.setCursorPosition(new TerminalPosition(cursor, 0));

        // Refreshing the screen
        screen.refresh();

    } 
     
     /**
     * The main entry point for the TextEditor application.
     * @param args command-line arguments.
     * @throws IOException 
     */

    public static void main(String[] args) throws IOException {

        // Message explaining how to run if people don't use a filename or give too many arguments
        if (args.length != 1) {
            System.err.println("Usage: java TextEditor <filename>");
            System.exit(1);
        }

        // The filename passed as argument stored as string
        String input = args[0];

        // Argument input string converted to a path
        Path path = Paths.get(input);

        // Assigning a new gapbuffer
        GapBuffer buf = new GapBuffer();

        // Checking validity of path and path location
        if (Files.exists(path) && Files.isRegularFile(path)){
            
            System.out.format("Loading %s...\n", input);

            // Reading contents from the textfile path 
            String file = Files.readString(path);

            // Writing the string extracted from text file to a character array
            char[] arr = file.toCharArray();

            // Inserting elements from character array into the buffer
            for (int i = 0; i < arr.length; i++){
                buf.insert(arr[i]);
            }

        }


        // Screen screen = new DefaultTerminalFactory().createScreen(); - original code
        // This piece of code came from Prof. Osera to tackle problems Lanterna creates with Windoes
        // It initializes the frame and the screen
        SwingTerminalFrame frame = new DefaultTerminalFactory().createSwingTerminal();
        frame.setTitle("Text Editor");
        frame.setVisible(true);
        Screen screen = new TerminalScreen(frame);
        screen.startScreen();

        // The running variable is set to true (only the escape key later on will switch it to false and
        // we'll exit the loop running the editor)

        boolean isRunning = true;
        
        // The loop which makes the editor run
        while (isRunning) {

            // Checking for keystrokes and executing the appropriate functions according to the 
            // inputted keyboard click
            KeyStroke stroke = screen.readInput();
            KeyType key = stroke.getKeyType();
            
            if (key.equals(KeyType.ArrowLeft)) {
                buf.moveLeft();
            } else if (key.equals(KeyType.ArrowRight)) {
                buf.moveRight();
            } else if (key.equals(KeyType.Backspace)) {
                buf.delete();
            } else if (key.equals(KeyType.Character)) {
                char ch = stroke.getCharacter();
                buf.insert(ch);
            } else if (key.equals(KeyType.Escape)) {
                isRunning = false;
            }

            // Drawing the buffer according to the keystrokes
            drawBuffer(buf, screen);

        }


        // Stopping the screen
        screen.stopScreen();
        Files.writeString(path, buf.toString());

    }


}
