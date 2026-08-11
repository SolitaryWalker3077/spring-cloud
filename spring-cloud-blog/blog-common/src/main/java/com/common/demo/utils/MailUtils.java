package com.common.demo.utils;


import jakarta.mail.internet.MimeMessage;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import java.util.Optional;

public class MailUtils {

    private JavaMailSender javaMailSender;

    private MailProperties mailProperties;

    public MailUtils(JavaMailSender javaMailSender, MailProperties mailProperties) {
        this.javaMailSender = javaMailSender;
        this.mailProperties = mailProperties;
    }

    public void send(String to, String subject, String content) throws Exception {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        //发件人
        String personal = Optional.ofNullable(mailProperties.getProperties().get("personal"))
                .orElse(mailProperties.getUsername());
        helper.setFrom(mailProperties.getUsername(), personal);
        helper.setTo(to);  //收件人
        helper.setSubject(subject);  //邮件主题
        helper.setText(content, true);

        javaMailSender.send(mimeMessage);

    }
}

