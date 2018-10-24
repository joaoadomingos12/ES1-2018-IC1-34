import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;

@XmlRootElement
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

	@XmlElement(name="twtToken")
	String twitterToken;
	@XmlElement(name="twtKey")
	String twitterKey;
	@XmlElement(name="twtSecret")
	String twitterSecret;
	@XmlElement(name="twtTokenSecret")
	String twitterTokenSecret;
	
	public Config(){
		/*facebookUser="Joao Domingos";
		facebookToken="EAAGVafw8IuIBAAnjw6VXhxLpAJ2Y1v1ZAEOHNbfegzMwYjJsip2WbLjrYKFPcllvJwEHdrToujttl5DMJwWpIeL06xTn4DM96ROvDgJW9uzAf8jKS8NUUlDoejxNnYaumAvTAHKuheE3xMFEVQBavrZAZBivrYZD";		
		twitterUser="AcademiaBda";
		twitterKey="axr8W68lwG2fYA2rvzt1GJmWF";
		twitterSecret="KhJwPrDKlOaecH2OoHNdvAjh7CVuvmIW49xpSXeXcigf3omgAO";
		twitterToken="1052285380026806272-NOPAb6y9KaVHi8mWvUqVboZCDRzj83";
		twitterTokenSecret="icvTMMUofe6Qi6souL29VEOktCjN5W7XAW5ZtsxYZ2c4A";*/
	}
	
	public void toXML() throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Config.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(this, new File("config.xml"));
        marshaller.marshal(this, System.out);
	}
	
	
}
