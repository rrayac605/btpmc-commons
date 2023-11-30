package mx.gob.imss.cit.pmc.commons.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * @author david.sanchez
 *
 */

public class RegistroDTO extends RegistroBDDTO {

	private static final long serialVersionUID = 1L;

	/**
	 * 1-3 Identificador 3 Alfanumérico
	 */
	@Setter
	@Getter
	private String identificador;

	/**
	 * 4-11 Folio Movimiento Original 8 Alfanumérico
	 */
	@Setter
	@Getter
	private String refFolioOriginal;

	/**
	 * 12-13 Del NSS 2 Numérico
	 */
	@Setter
	@Getter
	private String cveDelegacionNss;

	/**
	 * 14-15 Sub NSS 2 Numérico
	 */
	@Setter
	@Getter
	private String cveSubdelNss;

	/**
	 * 16-18 UMF Ads 3 Numérico
	 */
	@Setter
	@Getter
	private String cveUmfAdscripcion;

	/**
	 * 19-20 Del Atención NSS 2 Numérico
	 */
	@Setter
	@Getter
	private String cveDelegacionAtencion;

	/**
	 * 21-22 Sub Atención NSS 2 Numérico
	 */
	@Setter
	@Getter
	private String cveSubDelAtencion;

	/**
	 * 23-25 UMF Exp 3 Numérico
	 */
	@Setter
	@Getter
	private String cveUmfExp;

	/**
	 * 26-28 UMF Pagadora 3 Numérico
	 */
	@Setter
	@Getter
	private String cveUmfPagadora;

	/**
	 * 29-39 Registro Patronal 11 Alfanumérico
	 */

	/**
	 * 40-50 Número de Seguridad Social 11 Numérico
	 */
	@Getter
	@Setter
	private String numNss;

	/**
	 * 51-68 CURP 18 Alfanumérico
	 */
	@Setter
	@Getter
	private String refCurp;

	/**
	 * 69-118 Nombre 50 Alfanumérico
	 */
	@Setter
	@Getter
	private String nomAsegurado;

	/**
	 * 119 Tipo de Riesgo 1 Numérico
	 */
	@Setter
	@Getter
	private String cveTipoRiesgo;

	/**
	 * 120 Consecuencia 1 Numérico
	 */
	@Setter
	@Getter
	private String cveConsecuencia;

	/**
	 * 121-128 Fecha de Inicio 8 Numérico
	 */
	@Setter
	@Getter
	private String fecInicio;

	/**
	 * 129-136 Fecha de Atención/Recepción del paciente 8 Numérico
	 */
	@Setter
	@Getter
	private String fecAtencion;

	/**
	 * 137- 144 Fecha de accidente 8 Numérico
	 */
	@Setter
	@Getter
	private String fecAccidente;

	/**
	 * 145-152 Fecha de Inicio de Pensión 8 Numérico
	 */
	@Setter
	@Getter
	private String fecInicioPension;

	/**
	 * 153-160 Fecha de Alta 8 Numérico
	 */
	@Setter
	@Getter
	private String fecAltaRegistro;

	/**
	 * 161- 168 Fecha de expedición del Dictamen 8 Numérico
	 */
	@Setter
	@Getter
	private String fecExpedicionDictamen;

	/**
	 * 169-176 Fecha fin 8 Numérico
	 */
	@Setter
	@Getter
	private String fecFin;

	/**
	 * 177- 179 Días Subsidiados 3 Numérico
	 */
	@Setter
	@Getter
	private String numDiasSubsidiados;

	/**
	 * 180-182 Porcentaje de Incapacidad 3 Numérico
	 */
	@Setter
	@Getter
	private String porPorcentajeIncapacidad;

	/**
	 * 183-189 Salario Diario 7 Numérico
	 */
	@Setter
	@Getter
	private String numSalarioDiario;

	/**
	 * 190 Laudo 1 Numérico
	 */
	@Setter
	@Getter
	private String numLaudo;

	/**
	 * 191-194 Código de diagnostico 4 Numérico
	 */
	@Setter
	@Getter
	private String numCodigoDiagnostico;

	/**
	 * 195- 203 Matricula del médico tratante 9 Numérico
	 */
	@Setter
	@Getter
	private String numMatriculaTratante;

	/**
	 * 204- 212 Matrícula del médico que autoriza CDST 9 Numérico
	 */
	@Setter
	@Getter
	private String numMatriculaAutoriza;

	/**
	 * 204- 212 Matrícula del médico que autoriza CDST 9 Numérico
	 */
	@Setter
	@Getter
	private String numCiclo;

	/**
	 * 213-216 Causa Externa 4 Numérico
	 */
	@Setter
	@Getter
	private String cveCausaExterna;

	/**
	 * 217- 220 Naturaleza 4 Numérico
	 */
	@Setter
	@Getter
	private String cveNaturaleza;

	/**
	 * 221- 224 Riesgo Físico 4 Numérico
	 */
	@Setter
	@Getter
	private String cveRiesgoFisico;

	/**
	 * 225- 228 Acto Inseguro 4 Numérico
	 */
	@Setter
	@Getter
	private String cveActoInseguro;

	/**
	 * 229-233 Ocupación 5 Numérico
	 */
	@Setter
	@Getter
	private String cveOcupacion;

	@Getter
	@Setter
	private CifrasControlDTO cifrasControlDTO;

	@Setter
	@Getter
	private List<BitacoraErroresDTO> bitacoraErroresDTO;

	@Setter
	@Getter
	private String cveCodigoError;

	/**
	 * Campo auxiliar para el estado del registro
	 */
	@Setter
	@Getter
	private String cveEstadoRegistro;

	@Setter
	@Getter
	private String desEstadoRegistro;

	@Setter
	@Getter
	private String refCurpBDTU;

	@Setter
	@Getter
	private boolean errorCurp;

	@Setter
	@Getter
	private boolean duplicado;

	@Setter
	@Getter
	private List<RegistroDTO> susceptibles;

	@Setter
	@Getter
	private List<RegistroDTO> susceptiblesNss;

	@Setter
	@Getter
	private String desOcupacion;

	@Setter
	@Getter
	private String desConsecuencia;

	@Setter
	@Getter
	private String desLaudo;

	@Setter
	@Getter
	private String desNaturaleza;

	@Setter
	@Getter
	private String desTipoRiesgo;

	@Setter
	@Getter
	private String refNombreBDTU;

	@Setter
	@Getter
	private boolean errorNombre;

	@Setter
	@Getter
	private String cveTipoIncapacidad;

	@Setter
	@Getter
	private String desTipoIncapacidad;

	@Setter
	@Getter
	private String desCausaExterna;

	@Setter
	@Getter
	private String desRiesgoFisico;

	@Setter
	@Getter
	private String desCodigoDiagnostico;

	@Setter
	@Getter
	private String desActoInseguro;

	@Setter
	@Getter
	private boolean pasoAl;

	@Setter
	@Getter
	private AseguradoDTO aseguradoPasoAl;
}
