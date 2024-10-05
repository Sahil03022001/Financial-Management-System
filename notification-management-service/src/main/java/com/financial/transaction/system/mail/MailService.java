package com.financial.transaction.system.mail;

import com.financial.transaction.system.dto.EmailRequest;
import com.financial.transaction.system.kafka.consumer.KafkaConsumerService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    private static final Logger LOG = LoggerFactory.getLogger(MailService.class);

    @Autowired
    private JavaMailSender emailSender;

    public void sendMimeEmail(EmailRequest emailRequest) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            String[] toArray = emailRequest.getTo().toArray(new String[0]);
            helper.setTo(toArray);

            if (emailRequest.getCc() != null && !emailRequest.getCc().isEmpty()) {
                String[] ccArray = emailRequest.getCc().toArray(new String[0]);
                helper.setCc(ccArray);
            }

            helper.setSubject(emailRequest.getSubject());
            helper.setText(emailRequest.getText(), true);

            emailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
