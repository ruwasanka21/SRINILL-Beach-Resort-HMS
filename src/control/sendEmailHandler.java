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
public class sendEmailHandler {
    
    public static void sendMailForAddBooking(String cusName, String cusAddress, String checkInDate, String bookingFrom) {

        String subject = "Booking Added | " + cusName;

        String message
                = "<html>"
                + "<body style='font-family: Arial, sans-serif; background-color:#f4f6f8; padding:20px;'>"
                + "<div style='max-width:600px; margin:auto; background:#ffffff; "
                + "border-radius:6px; padding:20px; box-shadow:0 2px 6px rgba(0,0,0,0.1);'>"
                + "<h2 style='color:#2c3e50; border-bottom:1px solid #ddd; padding-bottom:10px;'>"
                + "New Booking"
                + "</h2>"
                + "<p style='font-size:14px; color:#333;'>Hello,</p>"
                + "<table style='width:100%; border-collapse:collapse; font-size:14px;'>"
                + "<tr>"
                + "<td style='padding:8px; font-weight:bold;'>Customer Name</td>"
                + "<td style='padding:8px;'>" + cusName + "</td>"
                + "</tr>"
                + "<tr style='background:#f9f9f9;'>"
                + "<td style='padding:8px; font-weight:bold;'>Address</td>"
                + "<td style='padding:8px;'>" + cusAddress + "</td>"
                + "</tr>"
                + "<tr>"
                + "<td style='padding:8px; font-weight:bold;'>Check-In Date</td>"
                + "<td style='padding:8px;'>" + checkInDate + "</td>"
                + "</tr>"
                + "<tr style='background:#f9f9f9;'>"
                + "<td style='padding:8px; font-weight:bold;'>Booking From</td>"
                + "<td style='padding:8px;'>" + bookingFrom + "</td>"
                + "</tr>"
                + "</table>"
                + "<p style='margin-top:20px; font-size:12px; color:#777;'>"
                + "This is an automated booking notification."
                + "</p>"
                + "</div>"
                + "</body>"
                + "</html>";

        EmailController.sendEmail(Options.RecieverEmailList, subject, message);
    }
       
}
