/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

/**
 *
 * @author ASUS
 */
public class Options {
    public static int selectedWindowXPosition = 0;
    
    public static int USER_PRIVILEGE = 0;
    public static int ADMIN_PRIVILEGE = 1;
    
    public static int BOOKING_CONFIRM = 1;
    public static int CHECK_IN_STATUS = 2;
    public static int CHECK_OUT_STATUS = 3;
    public static int BOOKING_CANCELED = 0;
    
    public static String CASH_PAYMENT = "CASH";
    public static String CARD_PAYMENT = "CARD";
    public static String BOTH_PAYMENT = "CASH & CARD";
    
    public static boolean isEnableEmail = true;
    public static String[] RecieverEmailList = {"prashansamm200327@gmail.com"};
    public static String senderEmail = "balancergame@gmail.com";
    public static String senderEmailPassword = "qlms mghn hlin dhiq";
}
