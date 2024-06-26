package com.projects.easyHome.service;

import com.projects.easyHome.dto.EmailConfig;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;



@Service
@AllArgsConstructor
public class SendMailService implements SendMail{

    private final static Logger LOGGER = LoggerFactory.getLogger(SendMailService.class);

    private final JavaMailSender javaMailSender = null;
    private final EmailConfig emailConfig = null;

    @Override
    @Async
    public void send(String emailID, String content) {
        try{
            JavaMailSenderImpl javaMailSender1 = new JavaMailSenderImpl();
            javaMailSender1.setHost(this.emailConfig.getHost());
            javaMailSender1.setPort(this.emailConfig.getPort());
            javaMailSender1.setUsername(this.emailConfig.getSenderEmail());
            javaMailSender1.setPassword(this.emailConfig.getSenderpassword());

            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, "utf-8");
            mimeMessageHelper.setText(content, true);
            mimeMessageHelper.setTo(emailID);
            mimeMessageHelper.setSubject("Verify your Email");
            javaMailSender.send(mimeMessage);
        }catch (MessagingException e){
            LOGGER.error("Fail to send",e);
            throw new IllegalStateException("Failed to send email.");
        }
    }
}
