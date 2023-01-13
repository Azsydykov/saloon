package kg.mega.saloon.service;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Date;

public interface EmailSenderService {
    void emailSender(String email,String sloonName, Date appointmentDate, String clientName) throws IOException, MessagingException;
}
