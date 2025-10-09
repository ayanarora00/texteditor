package edu.grinnell.csc207.texteditor;
import java.util.Arrays;

/**
 * A naive implementation of a text buffer using a <code>String</code>.
 */
public class SimpleStringBuffer {

    // Declaring private variables cursor and str (the buffer string)
    private String str;
    private int cursor;

    /** SimpleStringBuffer constructor is used to create a new buffer
     * 
     * @param nothing
     * @return doesn't return anything
     */

    public SimpleStringBuffer(){

        // Initializing the string buffer to empty and cursor to 0 (right at the start)
        this.str = "";
        this.cursor = 0;

    }


    /** Insert is used to add a new character to the buffer
     * 
     * @param char ch to be inserted into the buffer
     * @return void - doesn't return anything
     */

    public void insert(char ch) {
        
        // Uses substring function and concatenates the strings before and after the cursor
        // with the element to be inserted
        // then reassigning it to the string/buffer variable
        this.str = this.str.substring(0, cursor) + ch + this.str.substring(cursor);
        this.cursor++;

    }

    /** Delete function deletes the character at the cursor position
     * 
     * @param doesn't take parameters
     * @return void - doesn't return anything
     */

    public void delete() {
    
        // Uses substring function and concatenates the strings before and after the element to delete
        // reassigning it to the string/buffer variable
        this.str = this.str.substring(0, cursor - 1) + this.str.substring(cursor);

        // Cursor is decremented
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

        // Checking bounds  - can't move cursor left if it is right at the start
        if (this.cursor > 0){
            this.cursor--;
        }

    }

    /** Moves the cursor to the right
     * 
     * @param doesn't take parameters
     * @return void - doesn't return anything
     */

    public void moveRight() {

        // Checking bounds  - can't move cursor right if it is already at the last element
        if (this.cursor < str.length()){
            this.cursor++;
        }
        
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
     * @throws IndexOutOfBoundsException
     */

    public char getChar(int i) throws IndexOutOfBoundsException {
        
        // Checking if i is valid
        if (i < 0 || i > this.str.length()){
            throw new IndexOutOfBoundsException("Invalid index");
        }

        // If valid, element at i'th index is return
        return this.str.charAt(i);
    }

    /** Returns the String/text buffer
     * 
     * @param doesn't take anything as parameter
     * @return String - the string buffer which has all the text
     */

    @Override
    public String toString() {
        return this.str;
    }
}
