
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
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
	
	/**
	 * Construtor
	 * @throws IOException
	 * @throws JAXBException
	 */
	public BdaAcademia() throws IOException, JAXBException{
		cfg=toConfig();
		abcd = new DefaultListModel<Postt>();
		if(connIsAvailable()) {
			twHandler = new TwitterHandler (cfg);
			fbHandler= new FacebookHandler(cfg.getFacebookToken());
			emailHandler=new EmailHandler(cfg.getEmail(),cfg.getPassword());
		}else {
			readFromFile();
		}
		
		gui = new Gui(this,twHandler, fbHandler, emailHandler, cfg);
		
	}
	
	/**
	 * Inicia a aplicaï¿½ï¿½o
	 * @throws IOException
	 */
	public void start() throws IOException {
			
			gui.setVisible(true);
		
			//twHandler = new TwitterHandler (cfg);
			//addFB(fbHandler.listPosts("dia"));
			if(twHandler!=null) {
				addTT(twHandler.listPosts());
				addTT(emailHandler.listEmails());
			}
			gui.DefaultResultado = gui.transform(abcd);
			gui.list.setModel(gui.DefaultResultado);
	}
	
	
	/**
	 * Adiciona elementos ao modelo de lista
	 * @param tu
	 */
	public void addTT(ArrayList <Postt> tu){
		for (int i=0;i<tu.size();i++) {
			abcd.addElement(tu.get(i));
		}
		
	}
		public void saveOnFile(DefaultListModel<Postt> list) {
			FileOutputStream fos;
			ObjectOutputStream oos;
			try {
				fos = new FileOutputStream("posts.txt");
		
					oos = new ObjectOutputStream(fos);
					oos.writeObject(list);
					oos.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		/**
		 * Lê posts guardados anteriormente em modo offline
		 */
		public void readFromFile() {
			FileInputStream fis;
			try {
				fis = new FileInputStream("posts.txt");
			
			ObjectInputStream ois = new ObjectInputStream(fis);
			abcd = (DefaultListModel<Postt>) ois.readObject();
			ois.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
			
		/**
		 * Verifica se o existe ligação à internet
		 * @return
		 */
			public boolean connIsAvailable() {
			    try {
			        final URL url = new URL("http://www.google.com");
			        final URLConnection conn = url.openConnection();
			        conn.connect();
			        conn.getInputStream().close();
			        return true;
			    } catch (MalformedURLException e) {
			        throw new RuntimeException(e);
			    } catch (IOException e) {
			        return false;
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
