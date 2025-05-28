package com.matirmony.matirmony.service.otputil;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtil {
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendOtpEmail(String email, String otp) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(email);
            helper.setSubject("Dhanagar Matrimony - OTP");

            // OTP HTML styling
            String h2Tag = String.format("<h2 style=\"background-color: #007bff; color: white; padding: 10px 20px; border-radius: 5px; text-align: center;\">%s</h2>", otp);

            // Email HTML content with an optional image
            String htmlContent = "" +
                    "<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<body style=\"font-family: Arial, sans-serif; background-color: #f4f4f4; padding: 20px;\">\n" +
                    "  <div style=\"background-color: white; padding: 20px; border-radius: 8px; text-align: center;\">\n" +
                    "    <img src=\"https://i.pinimg.com/736x/8b/24/ba/8b24ba003c820a07369d45d4924195f7.jpg\" alt=\"Dhanagar Matrimony\" style=\"max-width: 200px; height: auto; margin-bottom: 20px;\" />\n" +  // Add your image URL here
                    "    <h1 style=\"color: #007bff;\">Welcome to Dhanagar Matrimony!</h1>\n" +
                    "    <p style=\"font-size: 1.2em; color: #555;\">Thank you for registering with us. Please use the following OTP to complete your registration process.</p>\n" +
                    "    " + h2Tag + // OTP in a box
                    "    <p style=\"font-size: 0.9em; color: #777;\">The OTP is valid for the next 2 minutes. If you did not request this, please ignore this email.</p>\n" +
                    "    <hr style=\"border-top: 1px solid #ddd; margin: 20px 0;\" />\n" +
                    "    <p style=\"font-size: 0.9em; color: #555;\">Best Regards,<br />The Dhanagar Matrimony Team</p>\n" +
                    "    <footer style=\"font-size: 0.8em; color: #aaa; text-align: center;\">\n" +
                    "      &copy; 2025 Dhanagar Matrimony. All rights reserved.\n" +
                    "    </footer>\n" +
                    "  </div>\n" +
                    "</body>\n" +
                    "</html>";

            // Set the email content
            helper.setText(htmlContent, true);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            // Handle exception
            e.printStackTrace();
        }
    }
}
