package edu.grinnell.csc207.texteditor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import net.jqwik.api.*;
import net.jqwik.api.constraints.*;

public class SimpleStringBufferTests {
    
    private SimpleStringBuffer buffer = new SimpleStringBuffer();

    @Test
    public void insertAndToString_test() {
        buffer.insert('H');
        buffer.insert('e');
        buffer.insert('y');
        assertEquals("Hey", buffer.toString());
    }

    @Test
    public void delete_test() {
        buffer.insert('H');
        buffer.insert('e');
        buffer.insert('y');
        buffer.delete();
        buffer.delete();
        buffer.delete();
        buffer.delete();
        assertEquals("", buffer.toString());
    }

    @Test
    public void moveLeft_moveright_getcursorpos() {
        buffer.insert('H');
        buffer.insert('e');
        buffer.insert('y');
        buffer.moveLeft();
        buffer.moveLeft();
        buffer.moveRight();
        assertEquals(2, buffer.getCursorPosition());

    }

    @Test
    public void getchar_test() {
        buffer.insert('H');
        buffer.insert('e');
        buffer.insert('y');
        assertEquals('y', buffer.getChar(2));
    }

    @Test
    public void getsize_test() {
        buffer.insert('H');
        buffer.insert('e');
        buffer.insert('y');
        assertEquals(3, buffer.getSize());
    }

    // Despite cursor movements, the output from converting the buffer to a string should'nt change
    @Property
    public boolean Cursor_toString(
            @ForAll @IntRange(min = 1, max = 1000) int move,
            @ForAll String s) {
                

                // Inserting everything from the String into the buffer
                for (int i = 0; i < s.length(); i++) {
                    buffer.insert(s.charAt(i));
                }

                // Creating a string from the buffer using toString()
                String string = buffer.toString();

                // Moving the cursor left and right 
                for (int i = 0; i < move; i++) {

                    if (i % 4 == 0){
                        buffer.moveLeft();
                    } else {
                        buffer.moveRight();
                    }

                }

                // Returning a boolean value corresponding to whether the string object from toString()
                // still equals to converting the buffer after playing around with cursor positions.
                return string.equals(buffer.toString());
                
    }

}



