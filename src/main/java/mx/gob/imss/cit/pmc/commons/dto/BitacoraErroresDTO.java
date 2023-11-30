package mx.gob.imss.cit.pmc.commons.dto;

import java.io.Serializable;

import com.mongodb.lang.Nullable;

import lombok.Getter;
import lombok.Setter;

public class BitacoraErroresDTO extends FechasAuditoriaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Setter
	@Getter
	@Nullable
	private String cveError;

	@Setter
	@Getter
	@Nullable
	private String desError;

	@Setter
	@Getter
	@Nullable
	private String cveTipoError;

	@Setter
	@Getter
	@Nullable
	private String desTipoError;

	@Setter
	@Getter
	@Nullable
	private String cveIdCodigoError;

	@Setter
	@Getter
	@Nullable
	private String desCodigoError;
	
	@Setter
	@Getter
	@Nullable
	private String desCampo;
	
	@Setter
	@Getter
	@Nullable
	private String desValorOriginal;

	@Override
	public String toString() {
		return "BitacoraErroresDTO [cveError=" + cveError + ", desError=" + desError + ", cveTipoError=" + cveTipoError
				+ ", desTipoError=" + desTipoError + ", cveIdCodigoError=" + cveIdCodigoError + ", desCodigoError="
				+ desCodigoError + ", desCampo=" + desCampo + ", desValorOriginal=" + desValorOriginal + "]";
	}

}
