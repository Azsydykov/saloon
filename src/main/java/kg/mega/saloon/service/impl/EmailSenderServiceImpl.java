package kg.mega.saloon.service.impl;

import kg.mega.saloon.network.EmailSender;
import kg.mega.saloon.service.EmailSenderService;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

@Service
public class EmailSenderServiceImpl implements EmailSenderService {

    @Override
    public void emailSender(String email,String saloonName, Date appointmentDate, String clientName) throws IOException, MessagingException {
        final Properties properties = new Properties();
        properties.load(EmailSender.class.getClassLoader().getResourceAsStream("application.properties"));

        Session mailSession = Session.getDefaultInstance(properties);
        MimeMessage message = new MimeMessage(mailSession);
        message.setFrom(new InternetAddress("41mazkin"));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
        message.setSubject("Hello " + clientName);
        message.setText("You have successfully registered at " + " \"" + saloonName + "\"" + " salon by " + appointmentDate);

        Transport transport = mailSession.getTransport();
        transport.connect(null, "exfizyhheakjjqnh");
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }
}
