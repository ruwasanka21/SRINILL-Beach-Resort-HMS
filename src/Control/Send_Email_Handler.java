package Control;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

public class Send_Email_Handler {

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

    public static void sendEmail(String toEmail, String subject, String body) {
        
        if (!Options.isEnableEmail) {
            System.out.println("email config disbaled!");
            return;
        }

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

                InternetAddress recipient = new InternetAddress(toEmail);
                message.setRecipient(Message.RecipientType.TO, recipient);
                message.setSubject(subject);
                message.setContent(body, "text/html; charset=utf-8");

                Transport.send(message);

                isSuccess = true;

                // UI updates must be on EDT
                javax.swing.SwingUtilities.invokeLater(()
                        -> JOptionPane.showMessageDialog(null, "✅ Email sent successfully to "
                                + "" + toEmail, "Success", JOptionPane.INFORMATION_MESSAGE)
                );

            } catch (Exception e) {
                e.printStackTrace();
                isSuccess = false;

                javax.swing.SwingUtilities.invokeLater(()
                        -> JOptionPane.showMessageDialog(null, "Email sent failed to "
                                + toEmail, "Failed", JOptionPane.ERROR_MESSAGE)
                );
            }

        }).start();
    }
}
