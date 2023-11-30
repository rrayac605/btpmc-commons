package mx.gob.imss.cit.pmc.commons.dto;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Getter;
import lombok.Setter;
import mx.gob.imss.cit.pmc.commons.support.CustomDateDeserializer;
import mx.gob.imss.cit.pmc.commons.support.CustomDateSerializer;

@Document(collection = "BITACORA_CONSULTA_ST3")
public class BitacoraConsultaST3 {

	@Setter
	@Getter
	private String refFolioOriginal;
	
	@Setter
	@Getter
	private String numNss;
	
	@Getter
	@Setter
	private String nomUsuario;
	
	@Getter
	@Setter
	private int codigoRespuesta;
	
	@Getter
	@Setter
	private ObjectId objectIdOrigen;
	
	@Setter
	@Getter
	@JsonDeserialize(using = CustomDateDeserializer.class)
	@JsonSerialize(using = CustomDateSerializer.class)
	private Date fecConsulta;
}