package edu.grinnell.csc207.texteditor;
import java.util.Arrays;

/**
 * A naive implementation of a text buffer using a <code>String</code>.
 */
public class SimpleStringBuffer {

    // Declaring private variables cursor and str (the buffer string)
    private String str;
    private int cursor;

    public SimpleStringBuffer(){
        this.str = "";
        this.cursor = 0;
    }


    public void insert(char ch) {
        
        this.str = this.str.substring(0, cursor) + ch + this.str.substring(cursor);
        this.cursor++;

    }

    public void delete() {
    
        this.str = this.str.substring(0, cursor - 1) + this.str.substring(cursor);
        this.cursor--;

    }

    public int getCursorPosition() {
        return this.cursor;
    }

    public void moveLeft() {
        this.cursor--;
    }

    public void moveRight() {
        this.cursor++;
    }

    public int getSize() {
        return this.str.length();
    }

    public char getChar(int i) {
        
        if (i < 0 || i > this.str.length()){
            throw new IndexOutOfBoundsException("Invalid index");
        }

        return this.str.charAt(i);
    }

    @Override
    public String toString() {

        return this.str;
    }
}
