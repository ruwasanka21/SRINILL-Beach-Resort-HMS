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
public class Item_Add_PopupTest {
    
    public Item_Add_PopupTest() {
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
     * Test of supLoad method, of class Item_Add_Popup.
     */
    @Test
    public void testSupLoad() {
        System.out.println("supLoad");
        Item_Add_Popup instance = null;
        instance.supLoad();
    }

    /**
     * Test of enterKey method, of class Item_Add_Popup.
     */
    @Test
    public void testEnterKey() {
        System.out.println("enterKey");
        Item_Add_Popup instance = null;
        instance.enterKey();
    }

    /**
     * Test of main method, of class Item_Add_Popup.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        Item_Add_Popup.main(args);
    }
    
}
