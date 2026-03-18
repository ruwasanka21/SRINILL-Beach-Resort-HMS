/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ASUS
 */
public class EventsTest {
    
    public EventsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of Halls_Table_load method, of class Events.
     */
    @Test
    public void testHalls_Table_load() {
        System.out.println("Halls_Table_load");
        Events instance = new Events();
        instance.Halls_Table_load();
    }

    /**
     * Test of Events_Table_load method, of class Events.
     */
    @Test
    public void testEvents_Table_load() {
        System.out.println("Events_Table_load");
        Events instance = new Events();
        instance.Events_Table_load();
    }

    /**
     * Test of doubleClicked method, of class Events.
     */
    @Test
    public void testDoubleClicked() {
        System.out.println("doubleClicked");
        Events instance = new Events();
        instance.doubleClicked();
    }
    
}
