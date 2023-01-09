package kg.mega.saloon.service;

import javax.mail.MessagingException;
import java.io.IOException;

public interface EmailSenderService {
    void emailSender(String email) throws IOException, MessagingException;
}
