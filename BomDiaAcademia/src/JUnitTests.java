

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;





public class JUnitTests {

	Config cfg=new Config();
	
    @Test
    public void listEmailsTest() {
    	
    
    	EmailHandler emailHandler = new EmailHandler(cfg.getEmail(),cfg.getPassword());
    	
        // assert statements
        assertEquals(3, emailHandler.listEmails().size(), "email number should be 3");
       
    }
    
    @Test
    public void listTwitterPosts() {
    	TwitterHandler twHandler = new TwitterHandler(cfg);
    	assertNotNull(twHandler.listPosts());
    	
    }
    
    @Test
    public void listFacebookPosts() {
    	FacebookHandler fbHandler = new FacebookHandler(cfg.getFacebookToken());
    	assertNotNull(fbHandler.listPosts("dia"));
    	
    }
}
