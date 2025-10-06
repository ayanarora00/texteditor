package edu.grinnell.csc207.texteditor;

import java.util.Arrays;

/**
 * A gap buffer-based implementation of a text buffer.
 */
public class GapBuffer {

    private char[] buffer;
    private int start;
    private int end;

    public GapBuffer(){
        this.buffer = new char[10];
        this.start = 0;
        this.end = buffer.length;
    }

    public void insert(char ch) {
        if (this.end - this.start == 0){
            char[] newArray = Arrays.copyOf(buffer, buffer.length * 2);    
            buffer = newArray;
        }
        this.buffer[start] = ch;
        this.start++;
    }

    public void delete() {
        this.start--;
    }

    public int getCursorPosition() {
        return this.start;
    }

    public void moveLeft() {

        this.start--;
        this.buffer[end - 1] = this.buffer[start];
        this.end--;

    }

    public void moveRight() {

        this.buffer[start] = buffer[end];
        this.start++;
        this.end++;

    }

    public int getSize() {
        return this.buffer.length - (this.end - this.start);
    }

    public char getChar(int i) {
        
        if (i < this.start){
            return buffer[i];
        } else {
            return buffer[i + (this.end - this.start)];
        }

    }

    public String toString() {
        return (String.valueOf(this.buffer, 0, this.start) + String.valueOf(this.buffer, this.end, buffer.length));
    }
}
