
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class BdaAcademia {
	
	private Config cfg;
	private FacebookHandler fbHandler;
	private Gui gui;
	private TwitterHandler twHandler;
	public DefaultListModel <Postt> abcd;
	private EmailHandler emailHandler;
	
	public BdaAcademia() throws IOException, JAXBException{
		cfg=toConfig();
		twHandler = new TwitterHandler (cfg);
		fbHandler= new FacebookHandler(cfg.getFacebookToken());
		gui = new Gui(twHandler, fbHandler);
		emailHandler=new EmailHandler(cfg.getEmail(),cfg.getPassword());
		abcd = new DefaultListModel<Postt>();
	}
	
	/**
	 * Inicia a aplica��o
	 * @throws IOException
	 */
	public void start() throws IOException {
			
			gui.setVisible(true);
		
			twHandler = new TwitterHandler (cfg);
			//addFB(fbHandler.listPosts("dia"));
			addTT(twHandler.listPosts());
			addTT(emailHandler.listEmails());
			gui.DefaultResultado = gui.transform(abcd);
			gui.list.setModel(gui.DefaultResultado);
			
	}
	
	
	public void addTT(ArrayList <Postt> tu){
		for (int i=0;i<tu.size();i++) {
			abcd.addElement(tu.get(i));
		}
	}/**
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
