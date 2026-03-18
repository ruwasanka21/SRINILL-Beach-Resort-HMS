/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Control.Send_Email_Handler;
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
public class Send_Email_HandlerTest {
    
    public Send_Email_HandlerTest() {
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
     * Test of sendEmail method, of class Send_Email_Handler.
     */
    @Test
    public void testSendEmail() {
        System.out.println("sendEmail");
        String toEmail = "";
        String subject = "";
        String body = "";
        Send_Email_Handler.sendEmail(toEmail, subject, body);
    }
    
}
