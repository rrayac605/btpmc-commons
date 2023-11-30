package mx.gob.imss.cit.pmc.commons.enums;

import lombok.Getter;
import lombok.Setter;

public enum CamposIncapacidadEnum {

	TIPO_RIESGO("incapacidadDTO.cveTipoRiesgo"),
	CONSECUENCIA("incapacidadDTO.cveConsecuencia"),
	FECHA_INICIO("incapacidadDTO.fecInicio"),
	FECHA_ATENCION("incapacidadDTO.fecAtencion"),
	FECHA_ACCIDENTE("incapacidadDTO.fecAccidente"),
	FECHA_INICIO_PENSION("incapacidadDTO.fecIniPension"),
	FECHA_ALTA("incapacidadDTO.fecAltaIncapacidad"),
	FECHA_EXPEDICION("incapacidadDTO.fecExpDictamen"),
	FECHA_FIN("incapacidadDTO.fecFin"),
	DIAS_SUBSIDIADOS("incapacidadDTO.numDiasSubsidiados"),
	PORCENTAJE_INCAPACIDAD("incapacidadDTO.porPorcentajeIncapacidad"),	
	LAUDO("incapacidadDTO.cveLaudo"),
	CODIGO_DIAGNOSTICO("incapacidadDTO.numCodigoDiagnostico"),
	MEDICO_TRATANTE("incapacidadDTO.numMatMedTratante"),
	MEDICO_AUTORIZADOR("incapacidadDTO.numMatMedAutCdst"),
	CAUSA_EXTERNA("incapacidadDTO.numCausaExterna"),
	NATURALEZA("incapacidadDTO.cveNaturaleza"),
	RIESGO_FISICO("incapacidadDTO.numRiesgoFisico"),
	ACTO_INSEGURO("incapacidadDTO.numActoInseguro");

	@Setter
	@Getter
	private String nombreCampo;

	private CamposIncapacidadEnum(String nombreCampo) {
		this.nombreCampo = nombreCampo;
	}

}