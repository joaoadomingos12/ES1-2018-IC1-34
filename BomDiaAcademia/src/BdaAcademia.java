import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class BdaAcademia {
	
	private Config cfg;
	private FacebookHandler fbHandler;
	private Gui gui;
	
	public BdaAcademia() throws IOException{
		gui = new Gui();
	}
	
	/**
	 * Inicia a aplicação
	 * @throws IOException
	 */
	public void start() throws IOException {
		try {
			
			gui.setVisible(true);
			cfg=toConfig();
			 fbHandler= new FacebookHandler(cfg.getFacebookToken(),gui);
			//System.out.println(cfg.getFacebookToken());
			fbHandler.listPosts("ISCTE");
			
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Transforma o objeto config num ficheiro XML
	 * @return config
	 * @throws JAXBException
	 */
	public Config toConfig() throws JAXBException {
        File file = new File("config.xml");
        JAXBContext jaxbContext = JAXBContext.newInstance(Config.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        cfg = (Config) unmarshaller.unmarshal(file);
        System.out.println(cfg);
        return cfg;
	}
	
}
