package edu.grinnell.csc207.texteditor;

import java.util.Arrays;

/**
 * A gap buffer-based implementation of a text buffer.
 */

public class GapBuffer {

    // Declaring the private variables
    private char[] buffer;
    private int start;
    private int end;


    /** 
     * Gapbuffer constructor is used to create a new buffer
     */

    public GapBuffer() {

        // The initial length of the buffer is set to 10
        this.buffer = new char[10];
        // Start is set to 0
        this.start = 0;
        // End is set to the buffer length 
        // i.e. the entire buffer starts out as a gap
        this.end = buffer.length;

    }

    /** 
     * Insert is used to add a new character to the buffer
     * 
     * @param ch the character to insert
     */

    public void insert(char ch) {

        // If there is no gap left i.e. end - start == 0 or end == start, we first need to 
        // expand the buffer. That is done through creating a new array with double the size and then
        // copying over the elements (the gap increases). Then, the original buffer is reassigned to 
        // new array

        if (this.end == this.start){

            // Copying content of old array to new one, with the new array having double the length
            char[] newArray = Arrays.copyOf(this.buffer, this.buffer.length * 2);    

            // Copying the first (before - gap part)
            for (int i = 0; i < this.start; i++) {
                newArray[i] = this.buffer[i];
            }

            // Variable for second half's length
            int length_secondhalf = this.buffer.length - this.end;

            // Setting new end index
            int end_updated = newArray.length - length_secondhalf;

            // Copying over the second half (after gap)
            for (int i = 0; i < length_secondhalf; i++) {
                newArray[end_updated + i] = this.buffer[this.end + i];
            }

            // Reassigning buffer to the new array
            // Updating the end
            this.buffer = newArray;
            this.end = end_updated;

        }

        
        // Inserting the character at start index
        this.buffer[this.start] = ch;

        // Incrementing the start index
        this.start++;
        


    }

    /** 
     * Delete function deletes the character at the cursor position
     */

    public void delete() {

        // Checking if the start is at 0 index or not - 0 index would mean not having any characters to delete

        if (this.start > 0) {
            // Decrementing start variable
            this.start--;
        } 

    }

    /** 
     * Returns the cursor position (start value in this case)
     * @return the position/index of the cursor
     */

    public int getCursorPosition() {
        return this.start;
    }

    /** 
     * Moves the cursor (start value in this case to the left)
     */

    public void moveLeft() {

        // Checking if the cursor can be moved left 
        if (this.start > 0) {

            // Decrementing start value
            this.start--;

            // Sending the last element/character which we traverse past by moving cursor 
            // to the second half
            this.buffer[end - 1] = this.buffer[start];

            // Decreasing the end value because it has an extra character now (the one we moved)
            this.end--;

        }
        

    }

    /** 
     * Moves the cursor (start value in this case to the right)
     */

    public void moveRight() {

        // Checking if end variable's value is lesser than the buffer's actual length to confirm we're not standing
        // at the buffer's end (otherwise, we can't move right)

        if (this.end < this.buffer.length) {

            // Shifting the first element of the second half over to the end of the first half
            this.buffer[start] = buffer[end];

            // Incrementing start due to an extra element
            this.start++;

            // Incrementing end because the current end index doesn't have an element anymore
            this.end++;
            
        }
        

    }

    /**
     * Returns the size of the text (that is the size of both the halves without the gap)
     * @return the integer size of the text
     */

    public int getSize() {
        // the buffer length - the gap length
        return this.buffer.length - (this.end - this.start);
    }

    /** 
     * Returns the character at the inputted index
     * 
     * @param i the index for which we need to access the character
     * @return the character at index i 
     */

    public char getChar(int i) {
        
        // If i is less than the start value, we can simply use i as the index for the buffer
        if (i < this.start) {
            return buffer[i];
        } else {

            // If not, we need to account for the gap in our index calculations
            return buffer[i + (this.end - this.start)];
        }

    }

    /** 
     * Returns the elements of the buffer as a String
     * 
     * @param 
     * @return The String version of the buffer
     */

    public String toString() {

        // Concatenating the 2 parts (before and after the gap)
        return (String.valueOf(this.buffer, 0, this.start) +
                String.valueOf(this.buffer, this.end, buffer.length - this.end));

    }

}
