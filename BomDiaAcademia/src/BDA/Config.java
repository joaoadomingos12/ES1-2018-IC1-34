
import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
/**
 * 
 * Classe correspondente ao ficheiro de configura��o config.xml
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Config {
	
	//Facebook
	@XmlElement(name="fbUser")
	String facebookUser;
	@XmlElement(name="fbToken")
	String facebookToken;
	@XmlElement(name="fbAppId")
	String facebookAppId;
	@XmlElement(name="fbUser")
	String facebookSecret;
	
	//Twitter

	@XmlElement(name="twtUser")
	String twitterUser;
	public String getFacebookUser() {
		return facebookUser;
	}

	public String getFacebookToken() {
		return facebookToken;
	}

	public String getFacebookAppId() {
		return facebookAppId;
	}

	public String getFacebookSecret() {
		return facebookSecret;
	}

	public String getTwitterUser() {
		return twitterUser;
	}

	public String getTwitterToken() {
		return twitterToken;
	}

	public String getTwitterKey() {
		return twitterKey;
	}

	public String getTwitterSecret() {
		return twitterSecret;
	}

	public String getTwitterTokenSecret() {
		return twitterTokenSecret;
	}
	
	

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}



	public void setFacebookUser(String facebookUser) {
		this.facebookUser = facebookUser;
	}

	public void setFacebookToken(String facebookToken) {
		this.facebookToken = facebookToken;
	}

	public void setFacebookAppId(String facebookAppId) {
		this.facebookAppId = facebookAppId;
	}

	public void setFacebookSecret(String facebookSecret) {
		this.facebookSecret = facebookSecret;
	}

	public void setTwitterUser(String twitterUser) {
		this.twitterUser = twitterUser;
	}

	public void setTwitterToken(String twitterToken) {
		this.twitterToken = twitterToken;
	}

	public void setTwitterKey(String twitterKey) {
		this.twitterKey = twitterKey;
	}

	public void setTwitterSecret(String twitterSecret) {
		this.twitterSecret = twitterSecret;
	}

	public void setTwitterTokenSecret(String twitterTokenSecret) {
		this.twitterTokenSecret = twitterTokenSecret;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}



	@XmlElement(name="twtToken")
	String twitterToken;
	@XmlElement(name="twtKey")
	String twitterKey;
	@XmlElement(name="twtSecret")
	String twitterSecret;
	@XmlElement(name="twtTokenSecret")
	String twitterTokenSecret;
	
	@XmlElement(name="email")
	String email;
	@XmlElement(name="password")
	String password;
	
	public Config(){


	}
	
	/**
	 * Converte o objeto em ficheiro XML
	 * @throws JAXBException
	 */
	public void toXML() throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Config.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(this, new File("config.xml"));
        marshaller.marshal(this, System.out);
	}
	
	
}

