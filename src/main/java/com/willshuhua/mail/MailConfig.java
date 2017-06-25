package com.willshuhua.mail;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * Created by will on 2017-06-21.
 */
@Configuration
@PropertySource("mail.properties")
public class MailConfig {

    @Value("${mail.host}")
    private String mailHost;
    @Value("${mail.port}")
    private int mailPort;
    @Value("${mail.username}")
    private String username;
    @Value("${mail.password}")
    private String password;
    @Value("${mail.auth}")
    private String auth;
    @Value("${mail.timeout}")
    private String time0ut;
    @Value("${mail.starttls.enable}")
    private String starttlsEnable;
    @Value("${mail.socketFactory.fallback}")
    private String socketFactoryFallback;
    @Value("${mail.socketFactory.class}")
    private String socketFactoryClass;

    @Bean
    public MailSender mailSender(Environment env){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(mailHost);
        mailSender.setPort(Integer.valueOf(mailPort));
        mailSender.setUsername(username);
        mailSender.setPassword(password);
        mailSender.setDefaultEncoding("utf-8");
        Properties jpro = new Properties();
        jpro.setProperty("mail.smtp.auth", auth);
        jpro.setProperty("mail.smtp.timeout", time0ut);
        jpro.setProperty("mail.smtp.starttls.enable", starttlsEnable);
        jpro.setProperty("mail.smtp.socketFactory.port", String.valueOf(mailPort));
        jpro.setProperty("mail.smtp.socketFactory.fallback", socketFactoryFallback);
        jpro.setProperty("mail.smtp.socketFactory.class", socketFactoryClass);
        mailSender.setJavaMailProperties(jpro);
        return  mailSender;
    }
}
