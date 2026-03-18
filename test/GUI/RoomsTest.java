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
public class RoomsTest {
    
    public RoomsTest() {
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
     * Test of Rooms_Table_load method, of class Rooms.
     */
    @Test
    public void testRooms_Table_load() {
        System.out.println("Rooms_Table_load");
        Rooms instance = new Rooms();
        instance.Rooms_Table_load();
    }

    /**
     * Test of CheckBox_load method, of class Rooms.
     */
    @Test
    public void testCheckBox_load() {
        System.out.println("CheckBox_load");
        Rooms instance = new Rooms();
        instance.CheckBox_load();
    }
    
}
