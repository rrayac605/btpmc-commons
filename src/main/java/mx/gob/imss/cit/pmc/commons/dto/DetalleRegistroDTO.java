package mx.gob.imss.cit.pmc.commons.dto;

import java.io.Serializable;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document("MCT_MOVIMIENTO")
public class DetalleRegistroDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public DetalleRegistroDTO() {
		this.aseguradoDTO = new AseguradoDTO();
		this.incapacidadDTO = new IncapacidadDTO();
		this.patronDTO = new PatronDTO();
	}

	@Getter
	@Setter
	@Id
	private ObjectId objectId;

	@Getter
	@Setter
	private ObjectId identificadorArchivo;

	@Getter
	@Setter
	private String cveOrigenArchivo;

	@Getter
	@Setter
	private AseguradoDTO aseguradoDTO;

	@Getter
	@Setter
	private PatronDTO patronDTO;

	@Getter
	@Setter
	private IncapacidadDTO incapacidadDTO;

	@Setter
	@Getter
	private List<BitacoraErroresDTO> bitacoraErroresDTO;

	@Setter
	@Getter
	private List<AuditoriaDTO> auditorias;
	
	@Getter
	@Setter
	private AseguradoDTO aseguradoPasoAl;

	/** Solo se utiliza para la consulta */
	@Getter
	private Integer numberOfIds;

	/** Solo se utiliza en la consulta */
	@Getter
	private ArchivoDTO archivoDTO;

	@Override
	public String toString() {
		return "DetalleRegistroDTO [objectId=" + objectId + ", identificadorArchivo=" + identificadorArchivo
				+ ", cveOrigenArchivo=" + cveOrigenArchivo + ", aseguradoDTO=" + aseguradoDTO + ", patronDTO="
				+ patronDTO + ", incapacidadDTO=" + incapacidadDTO + ", bitacoraErroresDTO=" + bitacoraErroresDTO + "]";
	}

}
