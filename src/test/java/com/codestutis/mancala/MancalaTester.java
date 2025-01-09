package com.codestutis.mancala;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
public class MancalaTester {
    Mancala game = new Mancala();
    @Test
    void testAIMove(){
        int expected = 0; 
        int actual = game.AIMove();

        assertEquals(expected, actual);
    }
}
