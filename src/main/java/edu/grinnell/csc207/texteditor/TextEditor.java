// Write tests for both buffers
// Implement expand
// Implement input output and file input output

package edu.grinnell.csc207.texteditor;
import java.io.IOException;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

/**
 * The driver for the TextEditor Application.
 */
public class TextEditor {


    public void drawBuffer(GapBuffer buf, Screen screen) throws IOException {

        int cols = screen.getTerminalSize().getColumns();

        for (int i = 0; i <  buf.getSize(); i++){

            if (i < cols){
                char ch = buf.getChar(i);
                TextCharacter c = new TextCharacter(ch);
                screen.setCharacter(i, 0, c);
            }
            
        }

        int cursor = buf.getCursorPosition();
        screen.setCursorPosition(new TerminalPosition(cursor, 0));


        screen.refresh();

    } 
     
     /**
     * The main entry point for the TextEditor application.
     * @param args command-line arguments.
     * @throws IOException 
     */

    public static void main(String[] args) throws IOException {

        if (args.length != 1) {
            System.err.println("Usage: java TextEditor <filename>");
            System.exit(1);
        }

        Screen screen = new DefaultTerminalFactory().createScreen();
        screen.startScreen();

        GapBuffer buf = new GapBuffer();
        new TextEditor().drawBuffer(buf, screen);

        String path = args[0];
        System.out.format("Loading %s...\n", path);

        screen.stopScreen();
    }


}
