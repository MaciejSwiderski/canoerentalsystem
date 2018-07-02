package com.canoerent;

import com.canoerent.model.Mail;
import com.canoerent.service.EmailService;
import javafx.application.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;

@SpringBootApplication
public class CanoeRentApplication {


    //private static Logger log = LoggerFactory.getLogger(CanoeRentApplication.class);

    //@Autowired
    //private EmailService emailService;


    public static void main(String[] args) {
        SpringApplication.run(CanoeRentApplication.class, args);
    }

//        @Override
//        public void run(ApplicationArguments applicationArguments) throws Exception {
//            log.info("Spring Mail - Sending Simple Email with JavaMailSender Example");
//
//            Mail mail = new Mail();
//            mail.setFrom("maciejswiderski261@gmail.com");
//            mail.setTo("maciejswiderski@wp.pl");
//            mail.setSubject("New rent of canoes - please make a payment");
//            mail.setContent("You have rented canoes please make a payment. https://www.ipko.pl/");
//
//            emailService.sendSimpleMessage(mail);

    }


