/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javax.swing.JLabel;
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
public class InventoryTest {
    
    public InventoryTest() {
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
     * Test of doubleClicked method, of class Inventory.
     */
    @Test
    public void testDoubleClicked() {
        System.out.println("doubleClicked");
        Inventory instance = new Inventory();
        instance.doubleClicked();
    }

    /**
     * Test of tb_load method, of class Inventory.
     */
    @Test
    public void testTb_load() {
        System.out.println("tb_load");
        Inventory instance = new Inventory();
        instance.tb_load();
    }

    /**
     * Test of expireLabel method, of class Inventory.
     */
    @Test
    public void testExpireLabel() {
        System.out.println("expireLabel");
        Inventory instance = new Inventory();
        instance.expireLabel();
    }

    /**
     * Test of refresh method, of class Inventory.
     */
    @Test
    public void testRefresh() {
        System.out.println("refresh");
        Inventory instance = new Inventory();
        instance.refresh();
    }

    /**
     * Test of startBlinkingLabel method, of class Inventory.
     */
    @Test
    public void testStartBlinkingLabel() {
        System.out.println("startBlinkingLabel");
        JLabel lblWarning = null;
        Inventory instance = new Inventory();
        instance.startBlinkingLabel(lblWarning);
    }
    
}
