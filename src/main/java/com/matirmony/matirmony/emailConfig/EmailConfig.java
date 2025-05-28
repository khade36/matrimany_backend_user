package com.matirmony.matirmony.emailConfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import java.util.Properties;

@Configuration
public class EmailConfig {
    @Value("${spring.mail.host}")
    private String mailHost;
    @Value("${spring.mail.port}")
    private String mailPort;
    @Value("${spring.mail.username}")
    private String  mailUsername;
    @Value("${spring.mail.password}")
    private String password;

@Bean
    public JavaMailSender getJavaMailSender(){
    JavaMailSenderImpl javaMailSender=new JavaMailSenderImpl();
    javaMailSender.setHost(mailHost);
    javaMailSender.setPort(Integer.parseInt(mailPort));
    javaMailSender.setUsername(mailUsername);
    javaMailSender.setPassword(password);
    Properties properties=javaMailSender.getJavaMailProperties();
    properties.put("mail.smtp.starttls.enable",true);
    return javaMailSender;
   }
}
