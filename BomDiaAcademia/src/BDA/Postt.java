
import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;


public class Postt  implements Serializable{

	/**
	 * Classe de conversão de emails, tweets e posts
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	private Date data;
	private String ID;
	private long IDtwitter;
	private String tipo;
	private String from;
	private String subject;
	private int position;
	
	
	public Postt(String id,String msg,Date data, String tipo) {
		this.ID=id;
		this.message=msg;
		this.data=data;	
		this.tipo=tipo;
	}
	
	public Postt(long id,String msg,Date data, String tipo) {
		this.IDtwitter=id;
		this.message=msg;
		this.data=data;	
		this.tipo=tipo;
	}
	
	public Postt(String from,String subject, String msg,Date data, String tipo, int position) {
		this.subject=subject;
		this.message=msg;
		this.data=data;	
		this.tipo=tipo;
		this.from=from;
		this.position=position;
	}
	

	

	public long getIDtwitter() {
		return IDtwitter;
	}
	public void setIDtwitter(long iDtwitter) {
		IDtwitter = iDtwitter;
	}
	public String getTipo() {
		return tipo;
	}



	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getPosition() {
		return position;
	}

	public void setID(String ID) {
		this.ID = ID;
	}
	
	@Override
	public String toString() {
		if(tipo.equals("Email")) {
			return data.toString().substring(0,20)+tipo+":"+subject;
		}
		return data.toString().substring(0,20)+ " "+ (data.getYear()+1900) + tipo+ " : " + message;
	}

	public String getID() {
		return ID;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		message = message;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	
	

/**
 * Comparador de datas
 */
	public static final Comparator<Postt> comparador = new Comparator<Postt>() {

		@Override 
		public int compare(Postt d, Postt d1) {
			return d1.getData().compareTo(d.getData());
		};
	};


}


