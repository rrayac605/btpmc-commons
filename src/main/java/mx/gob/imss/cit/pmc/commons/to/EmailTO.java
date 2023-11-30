package mx.gob.imss.cit.pmc.commons.to;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

public class EmailTO implements Serializable{
	private static final long serialVersionUID = 8002319468646301899L;
	
	@Getter
	@Setter
	public String nombre;
	@Getter
	@Setter
	public String email;

}
