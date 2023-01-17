package kg.mega.saloon.service;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Date;

public interface EmailSenderService {
    void emailSender(String email,String sloonName, Date appointmentDate) throws IOException, MessagingException;
}
