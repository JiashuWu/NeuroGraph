package com.example.jiashuwu.neurograph;

import android.content.Context;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Properties;

import android.os.AsyncTask;
import android.os.Message;
import android.util.Log;

import java.util.Calendar;
import java.util.Properties;
import java.util.logging.SocketHandler;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
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
import javax.sql.DataSource;

import static android.content.Context.MODE_PRIVATE;
import static android.content.Context.SHORTCUT_SERVICE;

/**
 * Created by Jiashu Wu on 21/03/2018.
 */

public class emailSender{

    public static Calendar calendar;
    public static int year;
    public static int month;
    public static int day;
    public static int hour;
    public static int minute;
    public static int second;
    public static int millisecond;
    public static String month_s;
    public static String day_s;
    public static String hour_s;
    public static String minute_s;
    public static String second_s;
    public static String millisecond_s;

    public static String generate_time_bottom_text = "";

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
        properties.setProperty("mail.smtp.socketFactory.fallback", "false");
        properties.setProperty("mail.smtp.port", "465");
        properties.setProperty("mail.smtp.socketFactory.port", "465");
        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

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

            String signature = "\n\n\n\n\n\n" + "================================" + "\n" + "Neurograph Data Transfer Service" + "\n" + "================================";
            messageContent = messageContent + signature;
            message.setText(messageContent);

            calendar = Calendar.getInstance();
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH) + 1;
            day = calendar.get(Calendar.DAY_OF_MONTH);
            hour = calendar.get(Calendar.HOUR_OF_DAY);
            minute = calendar.get(Calendar.MINUTE);
            second = calendar.get(Calendar.SECOND);
            millisecond = calendar.get(Calendar.MILLISECOND);

            if (String.valueOf(month).length() == 1)
            {
                month_s = "0" + String.valueOf(month);
            }
            else
            {
                month_s = String.valueOf(month);
            }
            if (String.valueOf(day).length() == 1)
            {
                day_s = "0" + String.valueOf(day);
            }
            else
            {
                day_s = String.valueOf(day);
            }
            if (String.valueOf(hour).length() == 1)
            {
                hour_s = "0" + String.valueOf(hour);
            }
            else
            {
                hour_s = String.valueOf(hour);
            }
            if (String.valueOf(minute).length() == 1)
            {
                minute_s = "0" + String.valueOf(minute);
            }
            else
            {
                minute_s = String.valueOf(minute);
            }
            if (String.valueOf(second).length() == 1)
            {
                second_s = "0" + String.valueOf(second);
            }
            else
            {
                second_s = String.valueOf(second);
            }
            if (String.valueOf(millisecond).length() == 1)
            {
                millisecond_s = "00" + String.valueOf(millisecond);
            }
            else if (String.valueOf(millisecond).length() == 2)
            {
                millisecond_s = "0" + String.valueOf(millisecond);
            }
            else if (String.valueOf(millisecond).length() == 3)
            {
                millisecond_s = String.valueOf(millisecond);
            }

            String file_time = String.valueOf(year) +  month_s + day_s + hour_s + minute_s + second_s + millisecond_s;

            generate_time_bottom_text = "NeurographDataEmail\n" + "Generated at " + String.valueOf(year) + "-" + month_s + "-" + day_s + " " + hour_s + ":" + minute_s + ":" + second_s + "." + millisecond_s + "\n";


            // TRYING TO CREATE A TXT FILE AND A CSV FILE;
            String output_file_name = "NeurographOutputDataFile" + file_time + ".txt";
            String output_csv_file_name = "NeurographOutputDataFile" + file_time + ".csv";
            // Sharing.file_version = Sharing.file_version + 1;

            File file = new File(Environment.getExternalStorageDirectory(), "Neurograph");
            if (!file.exists())
            {
                file.mkdirs();
            }


            if (Sharing.email_txt)
            {
                File output_file = new File(Environment.getExternalStorageDirectory(), "/Neurograph/" + output_file_name);
                FileWriter fileWriter = new FileWriter(output_file);
                try {
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    bufferedWriter.write(messageContent);
                    bufferedWriter.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (Sharing.email_readme)
            {
                File output_readme = new File(Environment.getExternalStorageDirectory(), "/Neurograph/" + "NeurographDataFileReadme.txt");
                FileWriter fileWriter2 = new FileWriter(output_readme);
                try {
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter2);
                    String content = SharingReadMe.readme;
                    bufferedWriter.write(content);
                    bufferedWriter.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


            if (Sharing.email_csv)
            {
                File output_csv_file = new File(Environment.getExternalStorageDirectory(), "/Neurograph/" + output_csv_file_name);
                FileWriter fileWriter1 = new FileWriter(output_csv_file);
                try {
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter1);
                    int i = 0;
                    for (i = 0; i < Sharing.csv_string_arraylist.size(); i++) {
                        bufferedWriter.write(Sharing.csv_string_arraylist.get(i));
                    }
                    bufferedWriter.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


            if (Sharing.email_db)
            {
                File output_db_file_src = new File(Sharing.db_file_path);
                //String db_file_from = "/data/data" + getApplicationContext().getPackageName() + "/databases/" + DatabaseInformation.databaseName;
                //String db_file_to = Environment.getExternalStorageDirectory() + "/Neurograph/";
                Log.d("NULLTESTING", String.valueOf(output_db_file_src == null));
                File output_db_file_dest = new File(Environment.getExternalStorageDirectory(), "/Neurograph/" + "NeurographOutputDatabase" + file_time + ".db");
                Log.d("NULLTESTING", String.valueOf(output_db_file_dest == null));
                if (!output_db_file_dest.exists()) {
                    output_db_file_dest.createNewFile();
                }

                FileChannel source_db = null;
                FileChannel destination = null;
                source_db = new FileInputStream(output_db_file_src).getChannel();
                destination = new FileOutputStream(output_db_file_dest).getChannel();
                if (destination != null && source_db != null) {
                    source_db.transferTo(0, source_db.size(), destination);
                }
                if (destination != null) {
                    destination.close();
                }
                if (source_db != null) {
                    source_db.close();
                }
            }

            String file_path = "";
            file_path = Environment.getExternalStorageDirectory() + "/Neurograph/" + output_file_name + "\n";
            file_path = file_path + Environment.getExternalStorageDirectory() + "/Neurograph/" + output_csv_file_name + "\n";
            file_path = file_path + Environment.getExternalStorageDirectory() + "/Neurograph/" + "NeurographOutputDataFileReadme.txt" + "\n";
            file_path = file_path + Environment.getExternalStorageDirectory() + "/Neurograph/" + "NeurographOutputDatabase" + file_time + ".db" + "\n";


            Log.d("file_path1", file_path);
            Sharing.file_path = file_path;
            String txt_file_path = Environment.getExternalStorageDirectory() + "/Neurograph/" + output_file_name;
            String csv_file_path = Environment.getExternalStorageDirectory() + "/Neurograph/" + output_csv_file_name;
            String readme_file_path = Environment.getExternalStorageDirectory() + "/Neurograph/" + "NeurographDataFileReadme.txt";
            String database_file_path = Environment.getExternalStorageDirectory() + "/Neurograph/" + "NeurographOutputDatabase" + file_time + ".db";


            Multipart multipart = new MimeMultipart();


            // TODO THIS IS OPTIONAL


            MimeBodyPart attachmentBodyPart = new MimeBodyPart();
            if (Sharing.email_txt)
            {
                javax.activation.DataSource source = new FileDataSource(txt_file_path);
                attachmentBodyPart.setDataHandler(new DataHandler(source));
                attachmentBodyPart.setFileName(output_file_name);
            }

            MimeBodyPart attachmentBodyPart1 = new MimeBodyPart();
            if (Sharing.email_csv)
            {
                javax.activation.DataSource source1 = new FileDataSource(csv_file_path);
                attachmentBodyPart1.setDataHandler(new DataHandler(source1));
                attachmentBodyPart1.setFileName(output_csv_file_name);
            }

            MimeBodyPart attachmentBodyPart2 = new MimeBodyPart();
            if (Sharing.email_readme)
            {
                javax.activation.DataSource source2 = new FileDataSource(readme_file_path);
                attachmentBodyPart2.setDataHandler(new DataHandler(source2));
                attachmentBodyPart2.setFileName("NeurographOutputDataFileReadme.txt");
            }

            MimeBodyPart attachmentBodyPart3 = new MimeBodyPart();
            if (Sharing.email_db)
            {
                javax.activation.DataSource source3 = new FileDataSource(database_file_path);
                attachmentBodyPart3.setDataHandler(new DataHandler(source3));
                attachmentBodyPart3.setFileName("NeurographOutputDatabase" + file_time + ".db");
            }

            MimeBodyPart textBodyPart = new MimeBodyPart();

            // TODO THIS IS OPTIONAL
            if (!Sharing.show_as_content)
            {
                textBodyPart.setText(SharingReadMe.email_content_title + signature + "\n\n\n" + generate_time_bottom_text);
            }
            else
            {
                textBodyPart.setText(SharingReadMe.email_content_title + "\n\n\n" + messageContent  + "\n\n\n" + generate_time_bottom_text);
            }



            multipart.addBodyPart(textBodyPart);
            if (Sharing.email_txt)
            {
                multipart.addBodyPart(attachmentBodyPart);
            }
            if (Sharing.email_csv)
            {
                multipart.addBodyPart(attachmentBodyPart1);
            }
            if (Sharing.email_readme)
            {
                multipart.addBodyPart(attachmentBodyPart2);
            }
            if (Sharing.email_db)
            {
                multipart.addBodyPart(attachmentBodyPart3);
            }


            message.setContent(multipart);

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