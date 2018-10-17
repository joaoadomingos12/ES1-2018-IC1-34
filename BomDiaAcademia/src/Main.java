import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class Main {
	
	private Config cfg;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	BdaAcademia bda;
	
		try {
			bda=new BdaAcademia();
			bda.toConfig();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
