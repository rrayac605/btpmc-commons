package mx.gob.imss.cit.pmc.commons.utils;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mx.gob.imss.cit.pmc.commons.dto.AseguradoDTO;
import mx.gob.imss.cit.pmc.commons.dto.DelegacionDTO;
import mx.gob.imss.cit.pmc.commons.dto.IncapacidadDTO;
import mx.gob.imss.cit.pmc.commons.dto.PatronDTO;
import mx.gob.imss.cit.pmc.commons.dto.RegistroDTO;
import mx.gob.imss.cit.pmc.commons.dto.SubDelegacionDTO;
import mx.gob.imss.cit.pmc.commons.dto.UMFDTO;

public class Utils {

	private final static Logger logger = LoggerFactory.getLogger(Utils.class);

	public static String obtenerNombreArchivo(String id) {
		DateFormat df = new SimpleDateFormat("MMyyyy");
		Calendar calendar = Calendar.getInstance();
		int mes = calendar.get(Calendar.MONTH);
		int year = calendar.get(Calendar.YEAR);
		if (mes == 0) {
			mes = 11;
			year = year - 1;
		} else {
			mes = mes - 1;
		}
		calendar.set(Calendar.MONTH, mes);
		calendar.set(Calendar.YEAR, year);
		StringBuilder sb = new StringBuilder();
		sb.append(id);
		sb.append("_");
		sb.append(df.format(calendar.getTime()));
		sb.append(".txt");
		logger.debug("Nombre de archivo: {}", sb.toString());
		return sb.toString();
	}

	public static String obtenerNombreArchivoNSSA(String id) {
		DateFormat df = new SimpleDateFormat("MMyyyy");
		Calendar calendar = Calendar.getInstance();
		StringBuilder sb = new StringBuilder();
		sb.append(id);
		sb.append("_");
		sb.append("04");
		sb.append(df.format(calendar.getTime()));
		sb.append(".txt");
		logger.debug("Nombre de archivo: {}", sb.toString());
		return sb.toString();
	}

	public static String obtenerNombreArchivoSISAT(String id) {
		DateFormat df = new SimpleDateFormat("MMyyyy");
		Calendar calendar = Calendar.getInstance();
		StringBuilder sb = new StringBuilder();
		sb.append(id);
		sb.append("_");
		sb.append(df.format(calendar.getTime()));
		sb.append(".txt");
		logger.debug("Nombre de archivo: {}", sb.toString());
		return sb.toString();
	}

	public static int validaEntero(String cadena) {
		int valor = 0;
		try {
			valor = Integer.valueOf(cadena);
		} catch (NumberFormatException e) {
			logger.debug("validaEntero Valor: {}", cadena, e.getMessage());
		}
		return valor;
	}

	public static int validaEntero(Integer cadena) {
		int valor = 0;
		try {
			valor = cadena != null ? cadena : 0;
		} catch (NumberFormatException e) {
			logger.debug("validaEntero Valor: {}", cadena, e.getMessage());
		}
		return valor;
	}

	public static Integer validaEnteroMongo(String cadena) {
		Integer valor = null;
		try {
			valor = Integer.valueOf(cadena);
		} catch (NumberFormatException e) {
			logger.debug("validaEntero Valor: {}", cadena, e.getMessage());
		}
		return valor;
	}

	public static Integer validaEnteroMongoCero(String cadena) {
		Integer valor = null;
		try {
			valor = Integer.valueOf(cadena);
			valor = valor > 0 ? valor : null;
		} catch (NumberFormatException e) {
			logger.debug("validaEntero Valor: {}", cadena, e.getMessage());
		}
		return valor;
	}

	public static BigDecimal validaBigDecimal(String cadena) {
		BigDecimal valor = BigDecimal.ZERO;
		try {
			valor = new BigDecimal(cadena);
		} catch (NumberFormatException e) {
			logger.debug("validaBigDecimal Valor: {}", cadena, e.getMessage());
		}
		return valor;
	}

	public static BigDecimal validaBigDecimalNull(String cadena) {
		BigDecimal valor = null;
		try {
			valor = new BigDecimal(cadena);
		} catch (NumberFormatException e) {
			logger.debug("validaBigDecimal Valor: {}", cadena, e.getMessage());
		}
		return valor;
	}

	public static String validaCadena(String cadena) {
		String cadenaSalida = null;
		try {
			cadenaSalida = cadena != null && !cadena.trim().equals("") ? cadena : null;
		} catch (NumberFormatException e) {
			logger.debug("validaCadena Valor: {}", cadena, e.getMessage());
		}
		return cadenaSalida;
	}

	public static String[] separaNombres(String nombre) {
		String[] nombres = nombre.split("\\$");
		return nombres;
	}

	public static String obtenerCurp(RegistroDTO registroDTO) {
		return registroDTO.isErrorCurp()
				&& (registroDTO.getRefCurpBDTU() != null && !registroDTO.getRefCurpBDTU().trim().equals(""))
						? registroDTO.getRefCurpBDTU()
						: registroDTO.getRefCurp();
	}

	/**
	 * • Si los datos del identificador, folio, fecha de recepción y consecuencia
	 * son diferentes, asignarle al estado del registro: “Susceptibles de ajuste”.
	 * 
	 * @param susceptibles
	 * @param susceptible
	 * @param fechas
	 * @return
	 */
	public static boolean susceptibleUno(List<RegistroDTO> susceptibles, RegistroDTO susceptible) {
		boolean esSusceptible = false;
		for (RegistroDTO susceptibleLista : susceptibles) {
			if (!susceptibleLista.getRefFolioOriginal().equals(susceptible.getRefFolioOriginal())
					&& !susceptibleLista.getFecAtencion().equals(susceptible.getFecAtencion())
					&& !susceptibleLista.getCveConsecuencia().equals(susceptible.getCveConsecuencia())) {
				esSusceptible = true;
				break;
			}
		}
		return esSusceptible;
	}

	public static boolean susceptibleDos(RegistroDTO susceptible, Date fechaHabilInicio, Date fechaHabilFin) {
		boolean esSusceptible = false;

		if (DateUtils.parserFromString(susceptible.getFecInicio()).compareTo(fechaHabilInicio) < 0
				&& DateUtils.parserFromString(susceptible.getFecFin()).compareTo(fechaHabilFin) > 0) {
			esSusceptible = true;
		}

		return esSusceptible;
	}

	/**
	 * • Cuando además coincidan los campos: Registro Patronal, Tipo de riesgo y
	 * fecha de inicio con diferente consecuencia se le asignará al estado del
	 * registro: “Susceptibles de ajuste”. Cuando se encuentren más de dos registros
	 * con porcentaje de incapacidad se tomará el del número de folio mayor (más
	 * reciente). para efecto de evitar ambigüedades y se le asignará el mismo
	 * estado del registro (susceptible de ajuste).
	 * 
	 * @param susceptibles
	 * @param susceptible
	 * @return
	 */
	public static boolean susceptibleTres(List<RegistroDTO> susceptibles, RegistroDTO susceptible) {
		boolean esSusceptible = false;
		for (RegistroDTO susceptibleLista : susceptibles) {
			if (susceptibleLista.getRefRegistroPatronal().equals(susceptible.getRefRegistroPatronal())
					&& susceptibleLista.getCveTipoRiesgo().equals(susceptible.getCveTipoRiesgo())
					&& susceptibleLista.getFecInicio().equals(susceptible.getFecInicio())
					&& !susceptibleLista.getCveConsecuencia().equals(susceptible.getCveConsecuencia())) {
				esSusceptible = true;
				break;
			}
		}
		return esSusceptible;
	}

	/**
	 * 
	 * @param susceptibles
	 * @param susceptible
	 * @return
	 */
	public static boolean susceptibleCuatro(List<RegistroDTO> susceptibles, RegistroDTO susceptible) {
		boolean esSusceptible = false;
		for (RegistroDTO susceptibleLista : susceptibles) {
			if (susceptibleLista.getRefRegistroPatronal().equals(susceptible.getRefRegistroPatronal())
					&& susceptibleLista.getCveTipoRiesgo().equals(susceptible.getCveTipoRiesgo())
					&& susceptibleLista.getFecInicio().equals(susceptible.getFecInicio())
					&& !susceptibleLista.getCveConsecuencia().equals(susceptible.getCveConsecuencia())) {
				esSusceptible = true;
				break;
			}
		}
		return esSusceptible;
	}

	/**
	 * • Cuando además coincidan los campos: Registro Patronal, Fecha de inicio,
	 * fecha fin y Tipo de riesgo siendo diferentes las consecuencias, días
	 * subsidiados y/o porcentaje de incapacidad, asignarle al estado del registro:
	 * “Susceptibles de ajuste”.
	 * 
	 * @param susceptibles
	 * @param susceptible
	 * @return
	 */
	public static boolean susceptibleCinco(List<RegistroDTO> susceptibles, RegistroDTO susceptible) {
		boolean esSusceptible = false;
		for (RegistroDTO susceptibleLista : susceptibles) {
			if (susceptibleLista.getRefRegistroPatronal().equals(susceptible.getRefRegistroPatronal())
					&& susceptibleLista.getFecInicio().equals(susceptible.getFecInicio())
					&& susceptibleLista.getFecFin().equals(susceptible.getFecFin())
					&& susceptibleLista.getCveTipoRiesgo().equals(susceptible.getCveTipoRiesgo())
					&& (!susceptibleLista.getCveConsecuencia().equals(susceptible.getCveConsecuencia())
							|| !susceptibleLista.getNumDiasSubsidiados().equals(susceptible.getNumDiasSubsidiados()))
					|| !susceptibleLista.getPorPorcentajeIncapacidad()
							.equals(susceptible.getPorPorcentajeIncapacidad())) {
				esSusceptible = true;
				break;
			}
		}
		return esSusceptible;
	}

	/**
	 * • Cuando además coincidan los campos: Registro Patronal, Tipo de riesgo y
	 * consecuencia, y el archivo de origen sea “RTT”, pero las fechas de inicio y
	 * termino sean diferentes, asignarle al estado del registro: “Susceptibles de
	 * ajuste”. * @param susceptibles
	 * 
	 * @param susceptible
	 * @return
	 */
	public static boolean susceptibleRTT(List<RegistroDTO> susceptibles, RegistroDTO susceptible) {
		boolean esSusceptible = false;
		for (RegistroDTO susceptibleLista : susceptibles) {
			if (susceptibleLista.getRefRegistroPatronal().equals(susceptible.getRefRegistroPatronal())
					&& susceptibleLista.getCveTipoRiesgo().equals(susceptible.getCveTipoRiesgo())
					&& susceptibleLista.getCveConsecuencia().equals(susceptible.getCveConsecuencia())
					&& !susceptibleLista.getFecInicio().equals(susceptible.getFecInicio())
					&& !susceptibleLista.getFecFin().equals(susceptible.getFecFin())) {
				esSusceptible = true;
				break;
			}
		}
		return esSusceptible;
	}

	/**
	 * • Cuando además coincidan los campos: Registro Patronal y Tipo de riesgo, y
	 * el archivo de origen sea “ST3 o ST5”, pero las fechas de inicio y termino
	 * sean diferentes, asignarle al estado del registro: “Susceptibles de ajuste”.
	 * 
	 * @param susceptibles
	 * @param susceptible
	 * @return
	 */
	public static boolean susceptibleST3ST5(List<RegistroDTO> susceptibles, RegistroDTO susceptible) {
		boolean esSusceptible = false;
		for (RegistroDTO susceptibleLista : susceptibles) {
			if (susceptibleLista.getRefRegistroPatronal().equals(susceptible.getRefRegistroPatronal())
					&& susceptibleLista.getCveTipoRiesgo().equals(susceptible.getCveTipoRiesgo())
					&& !susceptibleLista.getFecInicio().equals(susceptible.getFecInicio())
					&& !susceptibleLista.getFecFin().equals(susceptible.getFecFin())) {
				esSusceptible = true;
				break;
			}
		}
		return esSusceptible;
	}

	public static boolean susceptibleSeis(List<RegistroDTO> susceptibles, RegistroDTO susceptible) {
		boolean esSusceptible = false;
		for (RegistroDTO susceptibleLista : susceptibles) {
			if (susceptibleLista.getRefRegistroPatronal().equals(susceptible.getRefRegistroPatronal())
					&& susceptibleLista.getCveTipoRiesgo().equals(susceptible.getCveTipoRiesgo())
					&& !susceptibleLista.getCveConsecuencia().equals(susceptible.getCveConsecuencia())
					&& !susceptibleLista.getFecInicio().equals(susceptible.getFecInicio())
					&& !susceptibleLista.getFecFin().equals(susceptible.getFecFin())
					&& (!susceptible.getNumDiasSubsidiados().trim().equals("")
							|| !susceptible.getPorPorcentajeIncapacidad().trim().equals(""))) {
				esSusceptible = true;
				break;
			}
		}
		return esSusceptible;
	}

	public static String obtenerNombre(RegistroDTO registroDTO) {
		return registroDTO.isErrorNombre()
				&& (registroDTO.getRefNombreBDTU() != null && !registroDTO.getRefNombreBDTU().trim().equals(""))
						? registroDTO.getRefNombreBDTU()
						: registroDTO.getNomAsegurado();
	}

	public static String obtenerDelegacion(RegistroDTO item, AseguradoDTO aseguradoDTO) {
		String cveDelegacion = null;
		if (Utils.validaEntero(item.getCveDelegacionNss()) == 0) {
			cveDelegacion = (aseguradoDTO.getCveDelegacionNss() != null ? aseguradoDTO.getCveDelegacionNss().toString()
					: "0");
			item.setCveDelegacionNss(cveDelegacion);
		} else {
			cveDelegacion = item.getCveDelegacionNss();
		}
		return cveDelegacion;
	}

	public static String obtenerSubDelegacion(RegistroDTO item, AseguradoDTO aseguradoDTO) {
		return Utils.validaEntero(item.getCveSubdelNss()) == 0
				? (aseguradoDTO.getCveSubdelNss() != null ? aseguradoDTO.getCveSubdelNss().toString() : "0")
				: item.getCveSubdelNss();
	}

	public static String obtenerUmf(RegistroDTO item, AseguradoDTO aseguradoDTO) {
		return Utils.validaEntero(item.getCveUmfAdscripcion()) == 0
				? (aseguradoDTO.getCveUmfAdscripcion() != null ? aseguradoDTO.getCveUmfAdscripcion().toString() : "0")
				: item.getCveUmfAdscripcion();
	}

	public static String getDescripcionDelegacion(DelegacionDTO delegacionDTO) {
		return delegacionDTO != null ? delegacionDTO.getDescripcion() : null;
	}

	public static Integer getClaveDelegacion(DelegacionDTO delegacionDTO) {
		return delegacionDTO != null ? delegacionDTO.getClave() : null;
	}

	public static String getDescripcionSubDelegacion(SubDelegacionDTO subDelegacionDTO) {
		return subDelegacionDTO != null ? subDelegacionDTO.getDescripcion() : null;
	}

	public static Integer getClaveSubDelegacion(SubDelegacionDTO subDelegacionDTO) {
		return subDelegacionDTO != null ? subDelegacionDTO.getClave() : null;
	}

	public static String getDescripcionUmf(UMFDTO uMFDTO) {
		return uMFDTO != null && uMFDTO.getIdUMF() > 0 ? uMFDTO.getDescripcion() : null;
	}

	public static Integer getClavenUmf(UMFDTO uMFDTO) {
		return uMFDTO != null && uMFDTO.getIdUMF() > 0 ? uMFDTO.getIdUMF() : null;
	}

	public static RegistroDTO fillChange(AseguradoDTO asegurado, PatronDTO patron, IncapacidadDTO incapacidad) {
		RegistroDTO cambio = new RegistroDTO();
		cambio.setNumNss(StringUtils.safetyValidateString(asegurado.getNumNss()));
		cambio.setRefCurp(StringUtils.safetyValidateString(asegurado.getRefCurp()));
		cambio.setNomAsegurado(StringUtils.safetyValidateString(asegurado.getNomAsegurado()));
		cambio.setNumSalarioDiario(NumberUtils.safetyValidateBigDecimalString(asegurado.getNumSalarioDiario()));
		cambio.setNumCiclo(StringUtils.safetyValidateString(asegurado.getNumCicloAnual()));
		cambio.setRefFolioOriginal(StringUtils.safetyValidateString(asegurado.getRefFolioOriginal()));
		// cambio.set(NumberUtils.safetyValidateInteger(asegurado.getCveCasoRegistro()));
		cambio.setCveCodigoError(StringUtils.safetyValidateString(asegurado.getCveCodigoError()));
		cambio.setCveDelegacionAtencion(NumberUtils.safetyValidateIntegerString(asegurado.getCveDelegacionAtencion()));
		cambio.setCveDelegacionNss(NumberUtils.safetyValidateIntegerString(asegurado.getCveDelegacionNss()));
		cambio.setCveEstadoRegistro(NumberUtils.safetyValidateIntegerString(asegurado.getCveEstadoRegistro()));
		cambio.setCveSubDelAtencion(NumberUtils.safetyValidateIntegerString(asegurado.getCveSubDelAtencion()));
		cambio.setCveSubdelNss(NumberUtils.safetyValidateIntegerString(asegurado.getCveSubdelNss()));
		cambio.setCveUmfAdscripcion(NumberUtils.safetyValidateIntegerString(asegurado.getCveUmfAdscripcion()));
		cambio.setCveUmfExp(NumberUtils.safetyValidateIntegerString(asegurado.getCveUmfExp()));
		cambio.setCveUmfPagadora(NumberUtils.safetyValidateIntegerString(asegurado.getCveUmfPagadora()));
		cambio.setCveOcupacion(StringUtils.safetyValidateString(asegurado.getCveOcupacion()));
		cambio.setObjectIdPatron(StringUtils.safetyValidateString(asegurado.getObjectIdArchivo()));
		cambio.setDesEstadoRegistro(StringUtils.safetyValidateString(asegurado.getDesEstadoRegistro()));
		cambio.setDesOcupacion(StringUtils.safetyValidateString(asegurado.getDesOcupacion()));

		cambio.setFecActualizacion(asegurado.getFecActualizacion());

		cambio.setRefRegistroPatronal(StringUtils.safetyValidateString(patron.getRefRegistroPatronal()));
		cambio.setDesRazonSocial(StringUtils.safetyValidateString(patron.getDesRazonSocial()));
		cambio.setCveClase(NumberUtils.safetyValidateInteger(patron.getCveClase()));
		cambio.setCveDelRegPatronal(NumberUtils.safetyValidateInteger(patron.getCveDelRegPatronal()));
		cambio.setCveFraccion(NumberUtils.safetyValidateInteger(patron.getCveFraccion()));
		cambio.setNumPrima(NumberUtils.safetyValidateBigDecimal(patron.getNumPrima()));
		cambio.setCveSubDelRegPatronal(NumberUtils.safetyValidateInteger(patron.getCveSubDelRegPatronal()));
		cambio.setDesClase(StringUtils.safetyValidateString(patron.getDesClase()));
		cambio.setDesDelRegPatronal(StringUtils.safetyValidateString(patron.getDesDelRegPatronal()));
		cambio.setDesFraccion(StringUtils.safetyValidateString(patron.getDesFraccion()));
		cambio.setDesPrima(StringUtils.safetyValidateString(patron.getDesPrima()));
		cambio.setDesSubDelRegPatronal(StringUtils.safetyValidateString(patron.getDesSubDelRegPatronal()));
		cambio.setFecInicio(DateUtils.parserDatetoString(incapacidad.getFecInicio()));
		cambio.setFecAtencion(DateUtils.parserDatetoString(incapacidad.getFecAtencion()));
		cambio.setFecAccidente(DateUtils.parserDatetoString(incapacidad.getFecAccidente()));
		cambio.setFecAltaRegistro(DateUtils.parserDatetoString(incapacidad.getFecAltaIncapacidad()));
		cambio.setFecExpedicionDictamen(DateUtils.parserDatetoString(incapacidad.getFecExpDictamen()));
		cambio.setFecFin(DateUtils.parserDatetoString(incapacidad.getFecFin()));
		cambio.setNumDiasSubsidiados(NumberUtils.safetyValidateIntegerString(incapacidad.getNumDiasSubsidiados()));
		cambio.setPorPorcentajeIncapacidad(
				NumberUtils.safetyValidateBigDecimalString(incapacidad.getPorPorcentajeIncapacidad()));
		cambio.setCveCausaExterna(StringUtils.safetyValidateString(incapacidad.getNumCausaExterna()));
		cambio.setCveRiesgoFisico(StringUtils.safetyValidateIntegerString(incapacidad.getNumRiesgoFisico()));
		cambio.setCveActoInseguro(StringUtils.safetyValidateIntegerString(incapacidad.getNumActoInseguro()));
		cambio.setNumMatriculaAutoriza(StringUtils.safetyValidateString(incapacidad.getNumMatMedAutCdst()));
		cambio.setNumMatriculaTratante(StringUtils.safetyValidateString(incapacidad.getNumMatMedTratante()));
		cambio.setNumCodigoDiagnostico(StringUtils.safetyValidateString(incapacidad.getNumCodigoDiagnostico()));
		cambio.setFecInicioPension(DateUtils.parserDatetoString(incapacidad.getFecIniPension()));
		cambio.setCveConsecuencia(StringUtils.safetyValidateIntegerString(incapacidad.getCveConsecuencia()));
		cambio.setNumLaudo(StringUtils.safetyValidateIntegerString(incapacidad.getCveLaudo()));
		cambio.setCveTipoRiesgo(StringUtils.safetyValidateIntegerString(incapacidad.getCveTipoRiesgo()));
		cambio.setCveNaturaleza(StringUtils.safetyValidateString(incapacidad.getCveNaturaleza()));
		cambio.setDesConsecuencia(StringUtils.safetyValidateString(incapacidad.getDesConsecuencia()));
		cambio.setDesLaudo(StringUtils.safetyValidateString(incapacidad.getDesLaudo()));
		cambio.setDesNaturaleza(StringUtils.safetyValidateString(incapacidad.getDesNaturaleza()));
		cambio.setDesTipoRiesgo(StringUtils.safetyValidateString(incapacidad.getDesTipoRiesgo()));

		cambio.setDesCausaExterna(StringUtils.safetyValidateString(incapacidad.getDesCausaExterna()));
		cambio.setDesActoInseguro(StringUtils.safetyValidateString(incapacidad.getDesActoInseguro()));
		cambio.setDesOcupacion(StringUtils.safetyValidateString(asegurado.getDesOcupacion()));
		cambio.setDesRiesgoFisico(StringUtils.safetyValidateString(incapacidad.getDesRiesgoFisico()));
		cambio.setDesTipoIncapacidad(StringUtils.safetyValidateString(incapacidad.getDesTipoIncapacidad()));
		cambio.setDesRiesgoFisico(StringUtils.safetyValidateString(incapacidad.getDesRiesgoFisico()));
		cambio.setDesCodigoDiagnostico(StringUtils.safetyValidateString(incapacidad.getDesCodigoDiagnostico()));
		cambio.setDesRfc(StringUtils.safetyValidateString(patron.getDesRfc()));
		cambio.setCveTipoIncapacidad(StringUtils.safetyValidateIntegerString(incapacidad.getCveTipoIncapacidad()));
		cambio.setNumCodigoDiagnostico(StringUtils.safetyValidateString(incapacidad.getNumCodigoDiagnostico()));
		cambio.setNumDiasSubsidiados(NumberUtils.safetyValidateIntegerString(incapacidad.getNumDiasSubsidiados()));
		
		return cambio;
	}

}
