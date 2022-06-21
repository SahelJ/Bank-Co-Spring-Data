package nfs.bankco.Utils.email;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailUtility {
    static final String HOST = "node18-eu.n0c.com";
    static final String TLS_PORT = "587";

    static final String SSL_PORT = "465";
    static final String USERNAME = "bankco.noreply@legilmalas.fr";
    static final String PASSWORD = "bv/K&eK1?be:v&5://";

    public static boolean createNewCustomerRequestPendingConfirmationEmail(String toAddress) {
        String subject = "Confirmation de reception de votre demande";
        String message = "BLABLA";
        try {
            sendEmail(toAddress, subject, message);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println(e);
            return false;
        }
    }


    public static void sendEmail(String toAddress, String subject, String messageBody) throws AddressException, MessagingException {
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", HOST);
        properties.put("mail.smtp.port", SSL_PORT);
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.starttls.required", "true");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }

        });

        session.setDebug(true);

        try {
            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(USERNAME));

            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toAddress));

            message.setSubject(subject);
            InputStream in = EmailUtility.class.getClassLoader().getResourceAsStream("email_templates/template1.html");
            InputStreamReader isr = new InputStreamReader(in, StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder someHtmlMessage = new StringBuilder();

            br.lines().forEach(line -> someHtmlMessage.append(line));

            message.setContent(someHtmlMessage.toString(), "text/html; charset=utf-8");
            Transport.send(message);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}