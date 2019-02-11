package com.blackboard.backEnd.service;

import com.blackboard.backEnd.model.Student;
import com.blackboard.backEnd.model.LogInfo;
import com.blackboard.backEnd.model.MailReturn;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * EmailService class, used for sending the email from our frontend input
 */
@Service
public class EmailService {

    /**
     * This method calls two helper functions to initialize and send an email to a
     * designated user. The email contains an attachment which is used to store the customer
     * info, since there could potentially be thousands of entries.
     * @param mailObj the recipient of the message received from front end
     * @throws MessagingException exception if mail fails to send
     */
    public static void sendMail(MailReturn mailObj)
            throws MessagingException {

        createOutput(mailObj.getFinalList());
        //SMTP server properties
        Properties properties = new Properties();
        putProperties(properties);

        //creates a new e-mail message
        Message msg = constructMessage(mailObj, properties);

        //finally sends the e-mail
        Transport.send(msg);
    }

    /**
     * This method sets up the details of the message and the connection used to send the mail. Here,
     * we authenticate the sender and add the attachment to the email. Finally, the method returns a
     * message object which we use in the main method of this class.
     * @param mailObj object with email data from frontend
     * @param properties properties object for credential info
     * @return returns a message object which contains all of the data
     * @throws MessagingException if the mail fails to initialize
     */
    public static Message constructMessage(MailReturn mailObj, Properties properties) throws MessagingException{

        //creates a new session with an authenticator
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(LogInfo.prop.getProperty("mailFrom"),
                        LogInfo.prop.getProperty("emailPassword"));
            }
        };
        Session session = Session.getInstance(properties, auth);

        Message msg = new MimeMessage(session);

        msg.setFrom(new InternetAddress(LogInfo.prop.getProperty("mailFrom")));
        InternetAddress[] toAddresses = {new InternetAddress(mailObj.getAddress())};
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject(mailObj.getSubjectM());
        msg.setSentDate(new Date());

        //creates message part
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(mailObj.getBodyM(), "text/html");

        //creates multi-part
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);

        //adds attachment
        if (LogInfo.prop.getProperty("attachFiles") != null) {
            MimeBodyPart attachPart = new MimeBodyPart();

            try {
                attachPart.attachFile(LogInfo.prop.getProperty("attachFiles"));
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            multipart.addBodyPart(attachPart);
        }

        //sets the multi-part as e-mail's content
        msg.setContent(multipart);

        return msg;
    }

    /**
     * This method simply uses the properties object and the properties file to initialize the mail credentials
     * @param properties the properties object which is passed from the main method of this class
     */
    public static void putProperties(Properties properties) {

        properties.put("mail.smtp.host", LogInfo.prop.getProperty("hostSMTP"));
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.user", LogInfo.prop.getProperty("mailFrom"));
        properties.put("mail.password", LogInfo.prop.getProperty("emailPassword"));
    }



    /**
     * This method sends the customer data to our output log, which will be used as the file to be attached
     * in the email.
     * @param studentList the result set is passed so we can access the database info for each customer
     */
    private static void createOutput(List<Student> studentList) {
        int count = 0;
        Student prev = studentList.get(0);

        LogInfo.sendOutput("=== Texas Customers ===\n\n");
        for (Student student : studentList){
            if (count != 0 && !student.getState().equals(prev.getState()) && prev.getState().equals("TX")){
                LogInfo.sendOutput("=== Out of State Customers ===\n\n");
            }
            LogInfo.sendOutput("Student #" + count++);
            LogInfo.sendOutput(student.getFirstName() + " " + student.getLast());
            LogInfo.sendOutput(student.getEmail());
            LogInfo.sendOutput(student.getAddress());
            LogInfo.sendOutput(student.getCity() + " " +
                    student.getState() + " " +
                    student.getZip());
            LogInfo.sendOutput("\n");
            prev = student;
        }

    }
}
