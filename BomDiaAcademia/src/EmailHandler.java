
import java.io.IOException;
import java.net.PasswordAuthentication;
import java.util.ArrayList;
import java.util.Properties;

import javax.mail.*;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class EmailHandler {

	//private final String host = "pop.gmail.com";
	
	private final String host = "smtp.gmail.com";
	private final String mailStoreType = "smtp";
	private String user;
	private String password;
	private ArrayList<Postt> emails;
	private Message[] messages;
	
	/**
	 * Construtor
	 * @param user
	 * @param password
	 */
	public EmailHandler(String user, String password){
		this.user=user;
		this.password=password;
		emails=new ArrayList<Postt>();
	}

	/**
	 * Lista os emails e adiciona-os à GUI
	 * @return
	 */
	public ArrayList<Postt> listEmails() {
		try {

			//create properties field
			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");

			Session emailSession = Session.getInstance(props,
					  new Authenticator() {
						protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
							return new javax.mail.PasswordAuthentication(user, password);
						}
					  });
			
			//create the POP3 store object and connect with the pop server
			//Store store = emailSession.getStore("pop3s");
			Store store = emailSession.getStore("imaps");
			store.connect(host, user, password);
			
			
			//create the folder object and open it
			Folder emailFolder = store.getFolder("INBOX");
			emailFolder.open(Folder.READ_ONLY);

			// retrieve the messages from the folder in an array and print it
			messages = emailFolder.getMessages();
		
			int cont=0;
			for(Message message : messages) {
							
				emails.add(new Postt(message.getFrom()[0].toString(),message.getSubject(),getTextFromMessage(message),
						message.getSentDate(),"Email",cont));
				cont++;
				
			}
			
			
			//close the store and folder objects
//			emailFolder.close(false);
//			store.close();		

		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return emails;
	}

	/**
	 * Responder ao email selecionado
	 * @param position
	 * @param text
	 */
	public void reply(int position, String text) {
		MimeMessage reply;
		try {
			reply = (MimeMessage) messages[position].reply(false);
		
		reply.setFrom(messages[position].getFrom()[0]);
		reply.setText(text);
		Transport.send(reply);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Obtem a mensagem do email a que se pretende responder
	 * @param message
	 * @return
	 * @throws MessagingException
	 * @throws IOException
	 */
	private String getTextFromMessage(Message message) throws MessagingException, IOException {
	    String result = "";
	    if (message.isMimeType("text/plain")) {
	        result = message.getContent().toString();
	    } else if (message.isMimeType("multipart/*")) {
	        MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
	        result = getTextFromMimeMultipart(mimeMultipart);
	    }
	    return result;
	}

	/**
	 * Função auxiliar
	 * @param mimeMultipart
	 * @return
	 * @throws MessagingException
	 * @throws IOException
	 */
	private String getTextFromMimeMultipart(
	        MimeMultipart mimeMultipart)  throws MessagingException, IOException{
	    String result = "";
	    int count = mimeMultipart.getCount();
	    for (int i = 0; i < count; i++) {
	        BodyPart bodyPart = mimeMultipart.getBodyPart(i);
	        if (bodyPart.isMimeType("text/plain")) {
	            result = result + "\n" + bodyPart.getContent();
	            break; // without break same text appears twice in my tests
	        } else if (bodyPart.isMimeType("text/html")) {
	            String html = (String) bodyPart.getContent();
	            result = result + "\n" + org.jsoup.Jsoup.parse(html).text();
	        } else if (bodyPart.getContent() instanceof MimeMultipart){
	            result = result + getTextFromMimeMultipart((MimeMultipart)bodyPart.getContent());
	        }
	    }
	    return result;
	}

}

