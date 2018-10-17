import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class BdaAcademia {
	
	Config cfg;
	
	public BdaAcademia(){
		
	}
	
	public void toConfig() throws JAXBException {
        File file = new File("config.xml");
        JAXBContext jaxbContext = JAXBContext.newInstance(Config.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        cfg = (Config) unmarshaller.unmarshal(file);
        System.out.println(cfg);
	}
	
}
