import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeMultipart;


public class EmailHandler {

	//private final String host = "pop.gmail.com";
	
	private final String host = "imap.gmail.com";
	private final String mailStoreType = "imap";
	private String user;
	private String password;
	private ArrayList<Postt> emails;

	EmailHandler(String user, String password){
		this.user = "bomdiaacademia2@gmail.com";
		this.password = "bdacademia#2018";
		emails=new ArrayList<Postt>();
	}

	public ArrayList<Postt> listEmails() {
		try {

			//create properties field
			Properties properties = new Properties();

			properties.put("mail.imap.host", host);
			properties.put("mail.imap.port", "993");
			properties.put("mail.imap.starttls.enable", "true");
			Session emailSession = Session.getDefaultInstance(properties);

			//create the POP3 store object and connect with the pop server
			//Store store = emailSession.getStore("pop3s");
			Store store = emailSession.getStore("imaps");
			store.connect(host, user, password);
			
			
			//create the folder object and open it
			Folder emailFolder = store.getFolder("INBOX");
			emailFolder.open(Folder.READ_ONLY);

			// retrieve the messages from the folder in an array and print it
			Message[] messages = emailFolder.getMessages();
			System.out.println("messages.length---" + messages.length);
			
			for(Message message : messages) {
							
				emails.add(new Postt(message.getFrom()[0].toString(),message.getSubject(),getTextFromMessage(message),
						message.getSentDate(),"Email"));
			}
			
			
			//close the store and folder objects
			emailFolder.close(false);
			store.close();		

		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return emails;
	}

	
	
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

