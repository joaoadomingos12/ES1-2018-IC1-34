import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import javax.xml.bind.JAXBException;

import org.junit.jupiter.api.Test;


public class JUnitTests {

	Config cfg=new Config();
	BdaAcademia bda;
	EmailHandler emailHandler = new EmailHandler(cfg.getEmail(),cfg.getPassword());
	FacebookHandler fbHandler = new FacebookHandler(cfg.getFacebookToken());
	TwitterHandler twHandler = new TwitterHandler(cfg);
	Gui gui;
	
	@Test
	public void bdaTest(){
		try {
			bda = new BdaAcademia();
			gui= new Gui(bda, twHandler, fbHandler,emailHandler,cfg);
			assertNotNull(bda);
			assertEquals(true, bda.connIsAvailable());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void login() {
		login lgn;
		try {
			lgn = new login();
			assertNotNull(lgn);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
    @Test
    public void listEmailsTest() {
        // assert statements
        assertNotNull( emailHandler.listEmails().size());
       
    }
    
    @Test
    public void listTwitterPosts() {
    	
    	assertNotNull(twHandler.listPosts());
    	
    }
    
    @Test
    public void listFacebookPosts() {
    	
    	assertNotNull(fbHandler.listPosts("dia"));
    	
    }
    
    @Test
    public void guiTest() {
    	
    	assertNotNull(gui);
    	
    }
}
