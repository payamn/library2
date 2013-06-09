package domain.mail;

import domain.model.Person;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSender {

	public static void SendMail(String to,String text,String subject) {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("sad.library","palmartifact");
			}
		});
		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("sad.library@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to));
			message.setSubject(subject);
			message.setText(text);
			Transport.send(message);
			System.out.println("Done");

		} catch (MessagingException e) {
			System.out.println("Send Mail Error in class MailSender.");
			throw new RuntimeException(e);
		}
	}

	public static void sendFinishedAuctionMail(Person person, String name) {
		// after finishing auction , for people who are not auction's winner
		SendMail(person.getMail(), "Hi "+name+"\n"+"the auction that you joined is finished and you are not the winner.", "finished auction");
	}
	
	public static void sendAuctionSuccessMailToWinner(Person person,String Mail,String name) {
		// after finishing auction , for people who is auction's winner
		SendMail(person.getMail(), "Hi "+name+"\n"+"the auction that you joined is finished and you are the winner.\n"+"You can communicate with : "+Mail, "finished auction");
	}
	public static void sendAuctionWarningMailToOwner(Person person,String name) {
		// after finishing auction , for people who is auction's owner
		SendMail(person.getMail(), "the auction that you had made will be finished on the day after tomorrow ,please come and choose the winner. \nThank you.", "finished auction");
	}
	public static void sendAuctionSuccessMailToOwner(Person person,String name) {
		// after finishing auction , for people who is auction's owner
		SendMail(person.getMail(), "the auction that you had made is finished and the winner is awared .", "finished auction");
	}
	public static void sendAuctionExpiredMailToOwner(Person person, String name) {
		// after finishing auction , when owner didn't decide and auction has been expired
		SendMail(person.getMail(),"Hi "+name+"\n"+"the auction that you had made is finished without your reaction unsuccessfully .", "finished auction");
	}
}
