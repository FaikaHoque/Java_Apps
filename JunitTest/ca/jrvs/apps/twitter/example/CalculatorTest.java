package ca.jrvs.apps.twitter.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CalculatorTest {

    @Test
    public void add() throws Exception{
        final int result = new Calculator().add(2,3);
        assertEquals(5, result);
        System.out.println("result is perfecto");
    }

    @Before
    public void setUp() throws Exception {
        System.out.println("before");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("after");
    }
}