package edu.grinnell.csc207.texteditor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class GapBufferTests {
    
    private GapBuffer buffer = new GapBuffer();

    @Test
    public void insertAndToString_test(){
        buffer.insert('H');
        buffer.insert('e');
        buffer.insert('y');
        assertEquals("Hey", buffer.toString());
    }

    @Test
    public void delete_test(){
        buffer.insert('H');
        buffer.insert('e');
        buffer.insert('y');
        buffer.delete();
        buffer.delete();
        buffer.delete();
        assertEquals("", buffer.toString());
    }

    @Test
    public void moveLeft_moveright_getcursorpos(){
        buffer.insert('H');
        buffer.insert('e');
        buffer.insert('y');
        buffer.moveLeft();
        buffer.moveLeft();
        buffer.moveRight();
        assertEquals(2, buffer.getCursorPosition());

    }

    @Test
    public void getchar_test(){
        buffer.insert('H');
        buffer.insert('e');
        buffer.insert('y');
        assertEquals('y', buffer.getChar(2));
    }

    @Test
    public void getsize_test(){
        buffer.insert('H');
        buffer.insert('e');
        buffer.insert('y');
        assertEquals(3, buffer.getSize());
    }


}
