package com.willshuhua;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
//        ApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.willshuhua.co.MailConfig.class");
        Engine engine = new Engine();
        engine.start();
        System.out.println( "Hello World!" );
    }
}
