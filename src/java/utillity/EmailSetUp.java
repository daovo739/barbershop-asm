/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utillity;

import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author HHPC
 */
public class EmailSetUp {
    private final String host, port, userName, password;

    public EmailSetUp(String host, String port, String userName, String password) {
        this.host = host;
        this.port = port;
        this.userName = userName;
        this.password = password;
    }

    @Override
    public String toString() {
        return "EmailSetUp{" + "host=" + host + ", port=" + port + ", userName=" + userName + ", password=" + password + '}';
    }
    
    
    public final boolean sendMail(String toAddress, String subject, String message){
        try {
            Properties properties = new Properties();
            properties.put("mail.smtp.host", this.host);
            properties.put("mail.smtp.port", this.port);
//            properties.put("mail.smtp.auth", "true");
//            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.imap.ssl.enable", "true"); // required for Gmail
            properties.put("mail.imap.auth.mechanisms", "XOAUTH2");
            
            Authenticator auth = new Authenticator() {
                @Override
                public PasswordAuthentication getPasswordAuthentication(){
                    return new PasswordAuthentication(userName, password);
                }
            };
            Session session = Session.getInstance(properties);
            Store store = session.getStore("imap");
            store.connect("imap.gmail.com", userName, "969963125210-rh5dqf5658mt25eckl2ib7hir5nc3g34.apps.googleusercontent.com");
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(userName));
            InternetAddress[] toAddresses = {new InternetAddress(toAddress)};
            msg.setRecipients(Message.RecipientType.TO, toAddresses);
            msg.setSubject(subject);
             msg.setSentDate(new Date());
            msg.setText(message);

            Transport.send(msg);
            return true;
        } catch (MessagingException ex) {
            Logger.getLogger(EmailSetUp.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
