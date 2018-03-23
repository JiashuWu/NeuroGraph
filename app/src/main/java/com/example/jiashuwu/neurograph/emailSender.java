package com.example.jiashuwu.neurograph;

import android.util.Log;

import java.util.Calendar;
import java.util.Properties;

import android.os.AsyncTask;
import android.os.Message;
import android.util.Log;

import java.util.Calendar;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * Created by Jiashu Wu on 21/03/2018.
 */

public class emailSender{

    public static void sendMessage(String smtpHost, String from, String fromUserPassword, String to, String subject, String messageContent) throws MessagingException
    {
        Log.d("email", "sending");
        Properties properties1 = new Properties();

        MyAuthenticator myAuthenticator = new MyAuthenticator(from, fromUserPassword);

        /*
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smpt.host", "smtp.gmail.com");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smpt.port", "465");
        */

        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
        // Get a Properties object
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", "smtp.gmail.com");
        properties.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        properties.setProperty("mail.smtp.socketFactory.fallback", "false");
        properties.setProperty("mail.smtp.port", "465");
        properties.setProperty("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.debug", "true");
        properties.put("mail.store.protocol", "pop3");
        properties.put("mail.transport.protocol", "smtp");
        //properties.put("mail.smpt.port", "110");

        //properties.setProperty("mail.transport.protocol", "smtp");

        Session session = Session.getDefaultInstance(properties, myAuthenticator);


        try {

            final MimeMessage message = new MimeMessage(session);

            //InternetAddress fromAddress = new InternetAddress("ldsservice1099611527776@outlook.com");
            InternetAddress fromAddress = new InternetAddress(from);
            InternetAddress toAddress = new InternetAddress(to);

            message.setFrom(fromAddress);

            message.addRecipient(RecipientType.TO, new InternetAddress(to));

            message.setSentDate(Calendar.getInstance().getTime());
            message.setSubject(subject);

            //BodyPart bodyPart = new MimeBodyPart();
            //bodyPart.setContent(messageContent, "text/html");
            //Multipart multipart = new MimeMultipart();
            //multipart.addBodyPart(bodyPart);

            //message.setContent(multipart);

            String signature = "\n\n\n\n\n\n" + "======================" + "\n" + "Neurograph Data Transfer Service" + "\n" + "======================";
            messageContent = messageContent + signature;
            message.setText(messageContent);

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try
                    {
                        Transport.send(message);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            });

            thread.start();

            if (thread != null)
            {
                thread.interrupt();
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}

/*

// REFERENCE FOR ATTACHING FILES AS ATTACHMENT

// https://stackoverflow.com/questions/16117365/sending-mail-attachment-using-java

Properties props = new java.util.Properties();
    props.put("mail.smtp.host", "yourHost");
    props.put("mail.smtp.port", "yourHostPort");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");


    // Session session = Session.getDefaultInstance(props, null);
    Session session = Session.getInstance(props,
              new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("user", "password");
                }
              });


    Message msg = new MimeMessage(session);
    try {
        msg.setFrom(new InternetAddress(mailFrom));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(mailTo));
        msg.setSubject("your subject");

        Multipart multipart = new MimeMultipart();

        MimeBodyPart textBodyPart = new MimeBodyPart();
        textBodyPart.setText("your text");

        MimeBodyPart attachmentBodyPart= new MimeBodyPart();
        DataSource source = new FileDataSource(attachementPath); // ex : "C:\\test.pdf"
        attachmentBodyPart.setDataHandler(new DataHandler(source));
        attachmentBodyPart.setFileName(fileName); // ex : "test.pdf"

        multipart.addBodyPart(textBodyPart);  // add the text part
        multipart.addBodyPart(attachmentBodyPart); // add the attachement part

        msg.setContent(multipart);


        Transport.send(msg);
    } catch (MessagingException e) {
        LOGGER.log(Level.SEVERE,"Error while sending email",e);
    }

 */