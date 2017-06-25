package com.willshuhua;

import com.willshuhua.entity.UserInfo;
import com.willshuhua.mail.MailConfig;
import com.willshuhua.service.MailService;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.ResourceUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by will on 2017-06-22.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {com.willshuhua.config.MailGroupConfig.class})
public class Engine {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private MailService mailService;

    @Value("${mail.username}")
    private String mailUserName;

    private void sendSingleEmail(String name, String email, String text) throws MessagingException {
        String content = text.replace("PERSON_NAME", name);
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
        helper.setFrom(mailUserName);
        helper.setTo(email);
        helper.setSubject("服务器信息通知");
        helper.setText(content, true);      //true可以显示html格式的文本
        mailSender.send(message);
        System.out.println(name + "===================发送成功");
    }

    private void sendEmail() throws MessagingException, IOException {
        String text = "";
        File file = ResourceUtils.getFile("classpath:mail.html");
        text = FileUtils.readFileToString(file, "utf-8");
        List<UserInfo> userInfoList = mailService.queryAllUserInfo();
        for (UserInfo userInfo : userInfoList){
            sendSingleEmail(userInfo.getName(), userInfo.getEmail(), text);
        }
    }

    @Test
    public void start(){
        try {
            sendEmail();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
