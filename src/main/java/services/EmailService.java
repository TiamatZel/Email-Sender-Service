package services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import entities.EmailLog;
import repositories.EmailLogRepository;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private EmailLogRepository emailLogRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void sendEmail(String to, String subject, String message) {
        // Enviar correo
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        mailSender.send(mailMessage);

        // Crear y guardar EmailLog
        EmailLog emailLog = new EmailLog();
        emailLog.setToAddress(to);
        emailLog.setSubject(subject);
        emailLog.setMessage(message);
        emailLog.setSentAt(LocalDateTime.now());
        emailLogRepository.save(emailLog);

        // Guardar en archivo JSON
        try {
            objectMapper.writeValue(
                new File("email_logs.json"),
                emailLogRepository.findAll()
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


