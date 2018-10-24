import java.util.Comparator;
import java.util.Date;


public class Postt  {

	private String message;
	private Date data;
	private String ID;
	private String tipo;
	
	

	public Postt(String id,String msg,Date data, String tipo) {
		this.ID=id;
		this.message=msg;
		this.data=data;	
		this.tipo=tipo;
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
		return tipo+ ": " + message  +" data: " + data + "ID: " + ID;
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



