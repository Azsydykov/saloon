package kg.mega.saloon;

import kg.mega.saloon.models.dto.ClientDto;
import kg.mega.saloon.models.entities.Client;
import kg.mega.saloon.network.EmailSender;
import kg.mega.saloon.service.ClientService;
import kg.mega.saloon.service.impl.ClientServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.xml.sax.SAXException;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.Properties;

@SpringBootApplication
public class SaloonApplication {

	public static void main(String[] args) {
		SpringApplication.	run(SaloonApplication.class, args);

	}
}
