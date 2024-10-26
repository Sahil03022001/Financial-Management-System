package com.financial.transaction.notificationsystem.mail;

import com.financial.transaction.notificationsystem.dto.EmailRequest;
import com.financial.transaction.system.response.UserResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailUpdateMailService {

    private static final Logger LOG = LoggerFactory.getLogger(UserDetailUpdateMailService.class);

    @Autowired
    MailService mailService;

    private final String subject = "User Details Updation Status";

    public void sendUserDetailUpdateMail(UserResponseDto data) {
        LOG.info("Received request for user detail updation notification sender for user_id: {}", data.getUserId());

        EmailRequest request = new EmailRequest();

        List<String> toList = new ArrayList<>();
        toList.add(data.getEmail());

        List<String> ccList = new ArrayList<>();
        ccList.add("dhiman.sahil03022001@gmail.com");

        String body = createEmailBody(data);

        request.setTo(toList);
        request.setCc(ccList);
        request.setSubject(subject);
        request.setText(body);

        mailService.sendMimeEmail(request);
    }

    private String createEmailBody(UserResponseDto userDetails) {
        return "<html>" +
                "<body>" +
                "<h2>Your Details Have Been Updated</h2>" +
                "<p>Dear " + userDetails.getFirstName() + " " + userDetails.getLastName() + ",</p>" +
                "<p>We are pleased to inform you that your details have been successfully updated. Below are your current details:</p>" +
                "<table style='border: 1px solid #ddd; border-collapse: collapse; width: 100%;'>" +
                "<tr style='background-color: #f2f2f2;'>" +
                "<th style='border: 1px solid #ddd; padding: 8px;'>Field</th>" +
                "<th style='border: 1px solid #ddd; padding: 8px;'>Value</th>" +
                "</tr>" +
                "<tr>" +
                "<td style='border: 1px solid #ddd; padding: 8px;'>User ID</td>" +
                "<td style='border: 1px solid #ddd; padding: 8px;'>" + userDetails.getUserId() + "</td>" +
                "</tr>" +
                "<tr>" +
                "<td style='border: 1px solid #ddd; padding: 8px;'>First Name</td>" +
                "<td style='border: 1px solid #ddd; padding: 8px;'>" + userDetails.getFirstName() + "</td>" +
                "</tr>" +
                "<tr>" +
                "<td style='border: 1px solid #ddd; padding: 8px;'>Last Name</td>" +
                "<td style='border: 1px solid #ddd; padding: 8px;'>" + userDetails.getLastName() + "</td>" +
                "</tr>" +
                "<tr>" +
                "<td style='border: 1px solid #ddd; padding: 8px;'>Email</td>" +
                "<td style='border: 1px solid #ddd; padding: 8px;'>" + userDetails.getEmail() + "</td>" +
                "</tr>" +
                "<tr>" +
                "<td style='border: 1px solid #ddd; padding: 8px;'>Mobile Number</td>" +
                "<td style='border: 1px solid #ddd; padding: 8px;'>" + userDetails.getMobNo() + "</td>" +
                "</tr>" +
                "<tr>" +
                "<td style='border: 1px solid #ddd; padding: 8px;'>Date of Birth</td>" +
                "<td style='border: 1px solid #ddd; padding: 8px;'>" + userDetails.getDateOfBirth() + "</td>" +
                "</tr>" +
                "<tr>" +
                "<td style='border: 1px solid #ddd; padding: 8px;'>Address</td>" +
                "<td style='border: 1px solid #ddd; padding: 8px;'>" + userDetails.getAddress() + "</td>" +
                "</tr>" +
                "</table>" +
                "<p>If you did not request this change, please contact our support team.</p>" +
                "<p>Thank you!</p>" +
                "<p>Regards,<br>FMS System</p>" +
                "</body>" +
                "</html>";
    }

}
