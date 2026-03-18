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
public class FinanceTest {
    
    public FinanceTest() {
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
     * Test of tb_load method, of class Finance.
     */
    @Test
    public void testTb_load() {
        System.out.println("tb_load");
        Finance instance = new Finance();
        instance.tb_load();
    }

    /**
     * Test of tb_load_expense method, of class Finance.
     */
    @Test
    public void testTb_load_expense() {
        System.out.println("tb_load_expense");
        Finance instance = new Finance();
        instance.tb_load_expense();
    }

    /**
     * Test of tb_load_Dashboard method, of class Finance.
     */
    @Test
    public void testTb_load_Dashboard() {
        System.out.println("tb_load_Dashboard");
        Finance instance = new Finance();
        instance.tb_load_Dashboard();
    }

    /**
     * Test of doubleClicked_income method, of class Finance.
     */
    @Test
    public void testDoubleClicked_income() {
        System.out.println("doubleClicked_income");
        Finance instance = new Finance();
        instance.doubleClicked_income();
    }

    /**
     * Test of doubleClicked_expense method, of class Finance.
     */
    @Test
    public void testDoubleClicked_expense() {
        System.out.println("doubleClicked_expense");
        Finance instance = new Finance();
        instance.doubleClicked_expense();
    }
    
}
