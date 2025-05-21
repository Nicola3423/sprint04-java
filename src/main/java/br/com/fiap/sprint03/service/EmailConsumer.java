package br.com.fiap.sprint03.service;


import br.com.fiap.sprint03.model.EmailMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailConsumer {

    private final JavaMailSender mailSender;

    public EmailConsumer(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @RabbitListener(queues = "${rabbitmq.queue.name}")
    public void handleEmailNotification(EmailMessage message) {
        try {
            log.info("Preparando envio de e-mail para: {}", message.getTo());

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(message.getTo());
            mailMessage.setSubject(message.getSubject());
            mailMessage.setText(message.getBody());

            mailSender.send(mailMessage);

            log.info("E-mail enviado com sucesso para: {}", message.getTo());
        } catch (Exception e) {
            log.error("Falha ao enviar e-mail para {}: {}", message.getTo(), e.getMessage());
        }
    }
}
