/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author ASUS
 */
import java.util.Properties;
import javax.mail.*;

public class EmailController {

    private static volatile boolean isSuccess = false;

    public boolean getIsSuccess() {
        return isSuccess;
    }

    public static boolean isInternetAvailable() {
        try {
            return java.net.InetAddress.getByName("8.8.8.8").isReachable(3000);
        } catch (Exception e) {
            return false;
        }
    }

    public static void sendEmail(String[] emailList, String subject, String body) {

        new Thread(() -> {

            if (!isInternetAvailable()) {
                isSuccess = false;
                javax.swing.SwingUtilities.invokeLater(()
                        -> javax.swing.JOptionPane.showMessageDialog(
                                null,
                                "No internet connection.\nPlease check your network and try again.",
                                "Network Error",
                                javax.swing.JOptionPane.ERROR_MESSAGE
                        )
                );
                return;
            }

            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");

            Session session = Session.getInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(Options.senderEmail, Options.senderEmailPassword);
                }
            });

            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(Options.senderEmail, "Srinill Beach Resort"));

                // Convert String[] to InternetAddress[]
                InternetAddress[] recipients = new InternetAddress[emailList.length];
                for (int i = 0; i < emailList.length; i++) {
                    recipients[i] = new InternetAddress(emailList[i]);
                }

                message.setRecipients(Message.RecipientType.TO, recipients);
                message.setSubject(subject);
                message.setContent(body, "text/html; charset=utf-8");

                Transport.send(message);

                isSuccess = true;

                // UI updates must be on EDT
                javax.swing.SwingUtilities.invokeLater(()
                        -> System.out.println("Email sent successfully!")
                );

            } catch (Exception e) {
                e.printStackTrace();
                isSuccess = false;

                javax.swing.SwingUtilities.invokeLater(()
                        -> System.out.println("Email sent failed!")
                );
            }

        }).start();
    }
}
