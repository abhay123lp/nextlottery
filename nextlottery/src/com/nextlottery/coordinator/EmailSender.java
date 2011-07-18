package com.nextlottery.coordinator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {

	private final String emaillist = "." + File.separator +"resources" + File.separator + "emaillist.txt";
	public String getEmaillist() {
		return emaillist;
	}
	public List<String> getEmails(){
		File list = new File(this.emaillist);
		List<String> emails = null;
		BufferedReader br = null;
		try{
			br = new BufferedReader(new FileReader(list));
			emails = new ArrayList<String>();
			String line;
			while((line = br.readLine()) != null){
				emails.add(line);
			}
		}
		catch(Exception e){
			e.printStackTrace();			
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} 
		return emails;
	}


	public void send(String emailContent){
		List<String> emails = this.getEmails();
		
		String host = "smtp.gmail.com";
		int port = 587;
		String username = "";
		String password = "";

		
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");

		Session session = Session.getInstance(props);
		Message msg = new MimeMessage(session);
		try{
			InternetAddress addressFrom = new InternetAddress("");
			msg.setFrom(addressFrom);
			InternetAddress[] addressTo = new InternetAddress[emails.size()];
			for(int i = 0; i < addressTo.length; i++){
				addressTo[i] = new InternetAddress(emails.get(i));
			}

			msg.setRecipients(Message.RecipientType.TO, addressTo);
			msg.setSubject("From the fortune teller. ");
			msg.setContent(emailContent, "text/plain");
			Transport transport = session.getTransport("smtp");
			transport.connect(host, port, username, password);

			Transport.send(msg);
		} catch(Exception e) {
			e.printStackTrace();
		}

	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EmailSender es = new EmailSender();
		System.out.println(new File(es.getEmaillist()).exists());
		es.send("Hello world.");
	}

}
