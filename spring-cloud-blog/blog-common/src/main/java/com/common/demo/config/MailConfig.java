package com.common.demo.config;

import com.common.demo.utils.MailUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.mail.javamail.JavaMailSender;

public class MailConfig {

    @Bean
    @ConditionalOnProperty(prefix = "spring.mail",name = "username")
    public MailUtils mail(JavaMailSender javaMailSender, MailProperties mailProperties) {
        return new MailUtils(javaMailSender,mailProperties);
    }
}
