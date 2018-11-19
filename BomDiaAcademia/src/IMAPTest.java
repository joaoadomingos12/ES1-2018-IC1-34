import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;



public class IMAPTest {

    @Test
    public void listEmailsTest() {
    	
    	Config cfg = new Config();
    	EmailHandler emailHandler = new EmailHandler(cfg.getEmail(),cfg.getPassword());
    	
        // assert statements
        assertEquals(3, emailHandler.listEmails().size(), "email number should be 3");
       
    }
}
