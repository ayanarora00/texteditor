package edu.grinnell.csc207.texteditor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;

public class GapBufferTests {
    
    private GapBuffer buffer = new GapBuffer();

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

    // Extra tests aiming at making sure we cover edge cases

    // Testing if cursor moves beyond bounds 
    // With only 3 elements, we try repeatedly moving the cursor out of bounds to check if it stays in its correct position
    @Test
    public void moveBeyond(){

        buffer.insert('H');
        buffer.insert('e');
        buffer.insert('y');

        for (int i = 0; i < 500; i++) {
            buffer.moveRight();
        }
        assertEquals(3, buffer.getCursorPosition());

        for (int i = 0; i < 1000; i++) {
            buffer.moveLeft();
        }
        assertEquals(0, buffer.getCursorPosition());

    }

    // Testing to see if the size expansion works properly
    // (the initial size of the buffer is 10 - we add 500 elements)
    @Test
    public void expandSize(){

        for (int i = 0; i < 500; i++) {
            buffer.insert('H');
        }

        assertEquals(500, buffer.getSize());
        assertEquals(500, buffer.getCursorPosition());

    }

    
    // Property test:
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
