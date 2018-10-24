import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class Main {
	
	private static  Config cfg;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
	BdaAcademia bda;

	
		try {
			Gui gui = new Gui();
			gui.setVisible(true);
			bda=new BdaAcademia();
			cfg=bda.toConfig();
			FacebookHandler eu = new FacebookHandler(cfg.getFacebookToken(),gui);
			//System.out.println(cfg.getFacebookToken());
			eu.listPosts("ISCTE");
			
			
			
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
