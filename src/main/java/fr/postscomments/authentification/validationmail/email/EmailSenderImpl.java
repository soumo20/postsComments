package fr.postscomments.authentification.validationmail.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderImpl implements EmailSender {

    private final JavaMailSender mailSender;
    private static final Logger logger = LoggerFactory.getLogger(EmailSenderImpl.class);

    public EmailSenderImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    @Async
    public void sendEmail(String to, String email, String subject) throws IllegalArgumentException {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText(email, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setFrom(System.getenv("MAIL_SENDER"));
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            logger.error("Failed to send email for: {}{}{}", email, e.getMessage(), System.lineSeparator());
            throw new IllegalArgumentException("Failed to send email for: " + email);
        }

    }
}
