import java.util.Comparator;
import java.util.Date;


public class Postt  {

	private String message;
	private Date data;
	private String ID;
	private long IDtwitter;
	private String tipo;
	
	
	public Postt() {}
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



	public void setID(String iD) {
		ID = iD;
	}
	
	@Override
	public String toString() {
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
	
	


	public static final Comparator<Postt> comparador = new Comparator<Postt>() {

		@Override 
		public int compare(Postt d, Postt d1) {
			return d.getData().compareTo(d.getData());
		};
	};


}



