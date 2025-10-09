package edu.grinnell.csc207.texteditor;
import java.util.Arrays;

/**
 * A naive implementation of a text buffer using a <code>String</code>.
 */
public class SimpleStringBuffer {

    // Declaring private variables cursor and str (the buffer string)
    private String str;
    private int cursor;

    /** Gapbuffer constructor is used to create a new buffer
     * 
     * @param nothing
     * @return doesn't return anythinG
     */

    public SimpleStringBuffer(){
        this.str = "";
        this.cursor = 0;
    }


    /** Insert is used to add a new character to the buffer
     * 
     * @param char ch to be inserted into the buffer
     * @return void - doesn't return anything
     */
    public void insert(char ch) {
        
        this.str = this.str.substring(0, cursor) + ch + this.str.substring(cursor);
        this.cursor++;

    }

    /** Delete function deletes the character at the cursor position
     * 
     * @param doesn't take parameters
     * @return void - doesn't return anything
     */

    public void delete() {
    
        this.str = this.str.substring(0, cursor - 1) + this.str.substring(cursor);
        this.cursor--;

    }

    /** Returns the cursor position
     * 
     * @param doesn't take parameters
     * @return int - the position/index of the cursor
     */

    public int getCursorPosition() {
        return this.cursor;
    }

    /** Moves the cursor to the left
     * 
     * @param doesn't take parameters
     * @return void - doesn't return anything
     */

    public void moveLeft() {
        this.cursor--;
    }

    /** Moves the cursor to the right
     * 
     * @param doesn't take parameters
     * @return void - doesn't return anything
     */

    public void moveRight() {
        this.cursor++;
    }

    /** Returns the size of the string
     * 
     * @param doesn't take parameters
     * @return int - the size of the text string
     */

    public int getSize() {
        return this.str.length();
    }

    /** Returns the character at the inputted index
     * 
     * @param int i - the index for which we need to access the character
     * @return char - the character at index i 
     */

    public char getChar(int i) {
        
        if (i < 0 || i > this.str.length()){
            throw new IndexOutOfBoundsException("Invalid index");
        }

        return this.str.charAt(i);
    }

    /** Returns the String/text buffer
     * 
     * @param doesn't take anything as parameter
     * @return String - the string which has all the text
     */

    @Override
    public String toString() {

        return this.str;
    }
}
