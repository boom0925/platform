package com.willshuhua.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * Created by will on 2017-06-21.
 */
@Configuration
@ComponentScan(basePackageClasses = {com.willshuhua.mail.MailConfig.class, com.willshuhua.dao.DaoConfig.class}, basePackages = {"com.willshuhua.service"})
public class MailGroupConfig {

}
