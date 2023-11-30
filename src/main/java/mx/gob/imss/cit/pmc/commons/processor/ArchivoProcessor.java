package mx.gob.imss.cit.pmc.commons.processor;

import java.math.BigDecimal;
import java.util.Calendar;

import mx.gob.imss.cit.pmc.commons.dto.ArchivoDTO;
import mx.gob.imss.cit.pmc.commons.dto.AseguradoDTO;
import mx.gob.imss.cit.pmc.commons.dto.CasoRegistroDTO;
import mx.gob.imss.cit.pmc.commons.dto.CifrasControlDTO;
import mx.gob.imss.cit.pmc.commons.dto.EstadoRegistroDTO;
import mx.gob.imss.cit.pmc.commons.dto.IncapacidadDTO;
import mx.gob.imss.cit.pmc.commons.dto.PatronDTO;
import mx.gob.imss.cit.pmc.commons.dto.RegistroDTO;
import mx.gob.imss.cit.pmc.commons.enums.EstadoArchivoEnum;
import mx.gob.imss.cit.pmc.commons.enums.EstadoRegistroEnum;
import mx.gob.imss.cit.pmc.commons.utils.DateUtils;
import mx.gob.imss.cit.pmc.commons.utils.Utils;

public class ArchivoProcessor {

	public AseguradoDTO procesarAseguradoDTO(RegistroDTO registro) {
		AseguradoDTO aseguradoDTO = new AseguradoDTO();
		CasoRegistroDTO caso = DateUtils.obtenerCasoRegistro(Utils.validaCadena(registro.getFecFin()) != null
				? DateUtils.parserFromStringISOMOngo(registro.getFecFin())
				: DateUtils.parserFromStringISOMOngo(registro.getFecInicioPension()));
		aseguradoDTO.setCveCasoRegistro(caso.getIdCaso() > 0 ? caso.getIdCaso() : null);
		aseguradoDTO.setDesCasoRegistro(caso.getDescripcion());
		aseguradoDTO.setCveCodigoError(registro.getCveCodigoError());
		aseguradoDTO
				.setNumCicloAnual(!registro.getFecFin().trim().equals("") ? registro.getFecFin().substring(4, 8) : "");
		aseguradoDTO.setCveDelegacionAtencion(Utils.validaEnteroMongo(registro.getCveDelegacionAtencion()));
		aseguradoDTO.setDesDelegacionAtencion(Utils.getDescripcionDelegacion(registro.getDelegacionAtencionDTO()));

		aseguradoDTO.setCveDelegacionNss(Utils.getClaveDelegacion(registro.getDelegacionAdscripcionDTO()));
		aseguradoDTO.setDesDelegacionNss(Utils.getDescripcionDelegacion(registro.getDelegacionAdscripcionDTO()));
		aseguradoDTO.setCveOcupacion(
				registro.getCveOcupacion() != null && registro.getDesOcupacion() != null ? registro.getCveOcupacion()
						: null);
		aseguradoDTO.setCveSubDelAtencion(Utils.validaEnteroMongoCero(registro.getCveSubDelAtencion()));
		aseguradoDTO.setDesSubDelAtencion(Utils.getDescripcionSubDelegacion(registro.getSubDelegacionAtencionDTO()));
		aseguradoDTO.setCveSubdelNss(Utils.getClaveSubDelegacion(registro.getSubDelegacionAdscripcionDTO()));
		aseguradoDTO.setDesSubDelNss(Utils.getDescripcionSubDelegacion(registro.getSubDelegacionAdscripcionDTO()));
		aseguradoDTO.setCveUmfAdscripcion(Utils.getClavenUmf(registro.getUmfAdscripcionDTO()));
		aseguradoDTO.setDesUmfAdscripcion(Utils.getDescripcionUmf(registro.getUmfAdscripcionDTO()));
		aseguradoDTO.setCveUmfPagadora(Utils.getClavenUmf(registro.getUmfPagadoraDTO()));
		aseguradoDTO.setDesUmfPagadora(Utils.getDescripcionUmf(registro.getUmfPagadoraDTO()));
		aseguradoDTO.setCveUmfExp(Utils.getClavenUmf(registro.getUmfExpedicionDTO()));
		aseguradoDTO.setDesUmfExp(Utils.getDescripcionUmf(registro.getUmfExpedicionDTO()));
		aseguradoDTO.setFecAlta(DateUtils.getSysDateMongoISO());
		aseguradoDTO.setFecBaja(registro.getFecBaja() != null ? DateUtils.getSysDateMongoISO() : null);
		String[] nombres = Utils.separaNombres(Utils.obtenerNombre(registro));

		aseguradoDTO.setNomAsegurado(nombres.length > 2 ? nombres[2] : "");
		aseguradoDTO.setRefPrimerApellido(nombres[0]);
		aseguradoDTO.setRefSegundoApellido(nombres.length > 1 ? nombres[1] : "");
		aseguradoDTO.setNumNss(registro.getNumNss());
		aseguradoDTO.setNumSalarioDiario(Utils.validaBigDecimal(registro.getNumSalarioDiario()));
		aseguradoDTO.setRefCurp(Utils.obtenerCurp(registro));
		aseguradoDTO.setRefFolioOriginal(registro.getRefFolioOriginal());
		aseguradoDTO.setDesOcupacion(registro.getDesOcupacion());
		calcularEstadoRegistro(aseguradoDTO, registro);
		if (registro.getDelegacionAdscripcionDTO() == null && registro.getSubDelegacionAdscripcionDTO() == null
				&& registro.getUmfAdscripcionDTO() == null) {
			aseguradoDTO.setSinUMF(true);
		}
		else {
			aseguradoDTO.setSinUMF(false);
		}
		return aseguradoDTO;
	}

	public void validarMarcaAfiliatoria(boolean existeMarca, AseguradoDTO aseguradoDTO) {

		if (existeMarca) {
			aseguradoDTO.setMarcaAfiliatoria(true);
		} else {
			aseguradoDTO.setMarcaAfiliatoria(false);
		}
	}
	
	public void asignaDelegacionPatronalAsegurado(AseguradoDTO aseguradoDTO, PatronDTO patronDTO) {
		if(patronDTO.getCveDelRegPatronal() != null) {
			aseguradoDTO.setCveDelegacionNss(patronDTO.getCveDelRegPatronal());
			aseguradoDTO.setDesDelegacionNss(patronDTO.getDesDelRegPatronal());
			aseguradoDTO.setDesSubDelAtencion(patronDTO.getDesSubDelRegPatronal());
			aseguradoDTO.setCveSubdelNss(patronDTO.getCveSubDelRegPatronal());
		}
	}
	
	protected void calcularEstadoRegistro(AseguradoDTO aseguradoDTO, RegistroDTO registro) {

		try {
			int estadoRegistroOriginal = Integer.parseInt(registro.getCveEstadoRegistro());
			EstadoRegistroDTO estadoRegistroDTO = new EstadoRegistroDTO();
			if (estadoRegistroOriginal == EstadoRegistroEnum.OTRAS.getCveEstadoRegistro()) {
				estadoRegistroDTO = estadoOtras(registro);
			} else {
				estadoRegistroDTO = estado(registro);
			}
			aseguradoDTO.setCveEstadoRegistro(estadoRegistroDTO.getCveEstadoRegistro());
			aseguradoDTO.setDesEstadoRegistro(estadoRegistroDTO.getDesEstadoRegistro());
		} catch (NumberFormatException e) {
			aseguradoDTO.setCveEstadoRegistro(0);
		}
	}

	private EstadoRegistroDTO estadoOtras(RegistroDTO registro) {
		EstadoRegistroDTO estadoRegistroDTO = new EstadoRegistroDTO();
		if (registro.getBitacoraErroresDTO() != null && registro.getBitacoraErroresDTO().size() > 0) {
			estadoRegistroDTO.setCveEstadoRegistro(EstadoRegistroEnum.ERRONEO_OTRAS.getCveEstadoRegistro());
			estadoRegistroDTO.setDesEstadoRegistro(EstadoRegistroEnum.ERRONEO_OTRAS.getDesDescripcion());
		} else if (registro.isDuplicado()) {
			estadoRegistroDTO.setCveEstadoRegistro(EstadoRegistroEnum.DUPLICADO_OTRAS.getCveEstadoRegistro());
			estadoRegistroDTO.setDesEstadoRegistro(EstadoRegistroEnum.DUPLICADO_OTRAS.getDesDescripcion());
		} else if ((registro.getSusceptibles() != null && !registro.getSusceptibles().isEmpty())
				|| (registro.getSusceptiblesNss() != null && !registro.getSusceptiblesNss().isEmpty())) {
			estadoRegistroDTO.setCveEstadoRegistro(EstadoRegistroEnum.SUSCEPTIBLE_OTRAS.getCveEstadoRegistro());
			estadoRegistroDTO.setDesEstadoRegistro(EstadoRegistroEnum.SUSCEPTIBLE_OTRAS.getDesDescripcion());
		} else {
			estadoRegistroDTO.setCveEstadoRegistro(EstadoRegistroEnum.CORRECTO_OTRAS.getCveEstadoRegistro());
			estadoRegistroDTO.setDesEstadoRegistro(EstadoRegistroEnum.CORRECTO_OTRAS.getDesDescripcion());
		}
		return estadoRegistroDTO;
	}

	private EstadoRegistroDTO estado(RegistroDTO registro) {
		EstadoRegistroDTO estadoRegistroDTO = new EstadoRegistroDTO();
		if (registro.getBitacoraErroresDTO() != null && registro.getBitacoraErroresDTO().size() > 0) {
			estadoRegistroDTO.setCveEstadoRegistro(EstadoRegistroEnum.ERRONEO.getCveEstadoRegistro());
			estadoRegistroDTO.setDesEstadoRegistro(EstadoRegistroEnum.ERRONEO.getDesDescripcion());
		} else if (registro.isDuplicado()) {
			estadoRegistroDTO.setCveEstadoRegistro(EstadoRegistroEnum.DUPLICADO.getCveEstadoRegistro());
			estadoRegistroDTO.setDesEstadoRegistro(EstadoRegistroEnum.DUPLICADO.getDesDescripcion());
		} else if ((registro.getSusceptibles() != null && !registro.getSusceptibles().isEmpty())
				|| (registro.getSusceptiblesNss() != null && !registro.getSusceptiblesNss().isEmpty())) {
			estadoRegistroDTO.setCveEstadoRegistro(EstadoRegistroEnum.SUSCEPTIBLE.getCveEstadoRegistro());
			estadoRegistroDTO.setDesEstadoRegistro(EstadoRegistroEnum.SUSCEPTIBLE.getDesDescripcion());
		} else {
			estadoRegistroDTO.setCveEstadoRegistro(EstadoRegistroEnum.CORRECTO.getCveEstadoRegistro());
			estadoRegistroDTO.setDesEstadoRegistro(EstadoRegistroEnum.CORRECTO.getDesDescripcion());
		}
		return estadoRegistroDTO;
	}

	public IncapacidadDTO procesarIncapacidadDTO(RegistroDTO registro) {
		IncapacidadDTO incapacidadDTO = new IncapacidadDTO();
		incapacidadDTO.setCveConsecuencia(
				registro.getCveConsecuencia() != null && !registro.getCveConsecuencia().trim().equals("")
						? Utils.validaEntero(registro.getCveConsecuencia())
						: null);
		incapacidadDTO.setCveLaudo(Utils.validaEnteroMongo(registro.getNumLaudo()));

		incapacidadDTO.setCveNaturaleza(
				(Utils.validaCadena(registro.getCveNaturaleza()) != null && registro.getDesNaturaleza() != null)
						? registro.getCveNaturaleza()
						: null);
		incapacidadDTO.setCveReevaluacion(null);
		incapacidadDTO.setCveTipoRiesgo(Utils.validaEnteroMongo(registro.getCveTipoRiesgo()));
		incapacidadDTO.setFecAccidente(DateUtils.parserFromStringISOMOngo(registro.getFecAccidente()));
		incapacidadDTO.setFecAlta(DateUtils.getSysDateMongoISO());
		incapacidadDTO.setFecBaja(registro.getFecBaja() != null ? DateUtils.getSysDateMongoISO() : null);
		incapacidadDTO.setFecAltaIncapacidad(DateUtils.parserFromStringISOMOngo(registro.getFecAltaRegistro()));
		incapacidadDTO.setFecAtencion(DateUtils.parserFromStringISOMOngo(registro.getFecAtencion()));
		incapacidadDTO.setFecExpDictamen(DateUtils.parserFromStringISOMOngo(registro.getFecExpedicionDictamen()));
		incapacidadDTO.setFecFin(DateUtils.parserFromStringISOMOngo(registro.getFecFin()));
		incapacidadDTO.setFecInicio(DateUtils.parserFromStringISOMOngo(registro.getFecInicio()));
		incapacidadDTO.setFecIniPension(DateUtils.parserFromStringISOMOngo(registro.getFecInicioPension()));
		incapacidadDTO.setNumActoInseguro((Utils.validaEnteroMongoCero(registro.getCveActoInseguro())));
		incapacidadDTO.setNumCausaExterna((Utils.validaCadena(registro.getCveCausaExterna())));

		incapacidadDTO.setNumCodigoDiagnostico(Utils.validaCadena(registro.getNumCodigoDiagnostico()));

		incapacidadDTO.setNumDiasSubsidiados(Utils.validaEnteroMongo(registro.getNumDiasSubsidiados()));
		incapacidadDTO.setNumMatMedAutCdst(Utils.validaCadena(registro.getNumMatriculaAutoriza()));
		incapacidadDTO.setNumMatMedTratante(Utils.validaCadena(registro.getNumMatriculaTratante()));
		incapacidadDTO.setNumRiesgoFisico((Utils.validaEnteroMongoCero(registro.getCveRiesgoFisico())));
		incapacidadDTO.setPorPorcentajeIncapacidad(Utils.validaBigDecimalNull(registro.getPorPorcentajeIncapacidad()));
		incapacidadDTO.setDesConsecuencia(Utils.validaCadena(registro.getDesConsecuencia()));
		incapacidadDTO.setDesLaudo(registro.getDesLaudo());
		incapacidadDTO.setDesNaturaleza(registro.getDesNaturaleza());
		incapacidadDTO.setDesTipoRiesgo(registro.getDesTipoRiesgo());
		incapacidadDTO.setDesCausaExterna(registro.getDesCausaExterna());
		incapacidadDTO.setCveTipoIncapacidad(Utils.validaEnteroMongo(registro.getCveTipoIncapacidad()));
		incapacidadDTO.setDesTipoIncapacidad(registro.getDesTipoIncapacidad());
		incapacidadDTO.setDesRiesgoFisico(registro.getDesRiesgoFisico());
		incapacidadDTO.setDesCodigoDiagnostico(registro.getDesCodigoDiagnostico());
		incapacidadDTO.setDesActoInseguro(registro.getDesActoInseguro());
		incapacidadDTO.setCveActoInseguro(Utils.validaEnteroMongoCero(registro.getCveActoInseguro()));
		return incapacidadDTO;
	}

	public PatronDTO procesarPatronDTO(PatronDTO patronAux, RegistroDTO registro) {
		PatronDTO patronDTO = new PatronDTO();
		if (patronAux != null && patronAux.getRefRegistroPatronal() != null) {
			patronDTO.setCveClase(patronAux.getCveClase());
			patronDTO.setDesClase(patronAux.getDesClase());
			patronDTO.setCveDelRegPatronal(patronAux.getCveDelRegPatronal());
			patronDTO.setCveFraccion(patronAux.getCveFraccion());
			patronDTO.setDesFraccion(patronAux.getDesFraccion());
			patronDTO.setNumPrima(patronAux.getNumPrima());
			patronDTO.setCveSubDelRegPatronal(patronAux.getCveSubDelRegPatronal());
			patronDTO.setDesSubDelRegPatronal(patronAux.getDesSubDelRegPatronal());
			patronDTO.setCveDelRegPatronal(patronAux.getCveDelRegPatronal());
			patronDTO.setDesDelRegPatronal(patronAux.getDesDelRegPatronal());
			patronDTO.setDesRazonSocial(patronAux.getDesRazonSocial());
			patronDTO.setDesRfc(patronAux.getDesRfc());
			patronDTO.setFecAlta(DateUtils.getSysDateMongoISO());
			patronDTO.setFecBaja(registro.getFecBaja() != null ? DateUtils.getSysDateMongoISO() : null);
			patronDTO.setRefRegistroPatronal(patronAux.getRefRegistroPatronal());
		} else {
			patronDTO.setRefRegistroPatronal(registro.getRefRegistroPatronal());
		}
		return patronDTO;
	}

	public PatronDTO validaSinUMF(PatronDTO patronAux, RegistroDTO registro) {
		PatronDTO patronDTO = new PatronDTO();
		if (patronAux != null && patronAux.getRefRegistroPatronal() != null) {
			patronDTO.setCveClase(patronAux.getCveClase());
			patronDTO.setDesClase(patronAux.getDesClase());
			patronDTO.setCveDelRegPatronal(patronAux.getCveDelRegPatronal());
			patronDTO.setCveFraccion(patronAux.getCveFraccion());
			patronDTO.setDesFraccion(patronAux.getDesFraccion());
			patronDTO.setNumPrima(patronAux.getNumPrima());
			patronDTO.setCveSubDelRegPatronal(patronAux.getCveSubDelRegPatronal());
			patronDTO.setDesSubDelRegPatronal(patronAux.getDesSubDelRegPatronal());
			patronDTO.setCveDelRegPatronal(patronAux.getCveDelRegPatronal());
			patronDTO.setDesDelRegPatronal(patronAux.getDesDelRegPatronal());
			patronDTO.setDesRazonSocial(patronAux.getDesRazonSocial());
			patronDTO.setDesRfc(patronAux.getDesRfc());
			patronDTO.setFecAlta(DateUtils.getSysDateMongoISO());
			patronDTO.setFecBaja(registro.getFecBaja() != null ? DateUtils.getSysDateMongoISO() : null);
			patronDTO.setRefRegistroPatronal(patronAux.getRefRegistroPatronal());
		} else {
			patronDTO.setRefRegistroPatronal(registro.getRefRegistroPatronal());
		}
		return patronDTO;
	}
	
	public CifrasControlDTO procesarCifrasControl(CifrasControlDTO cifrasControlDTO, long total) {

		cifrasControlDTO = new CifrasControlDTO();
		cifrasControlDTO.setNumTotalRegistros(total);
		return cifrasControlDTO;
	}

	public ArchivoDTO creaArchivo(String registros, String identificador) {
		ArchivoDTO archivoDTO = new ArchivoDTO();
		archivoDTO.setNomArchivo(Utils.obtenerNombreArchivo(identificador));
		archivoDTO.setDesIdArchivo(Utils.obtenerNombreArchivo(identificador));
		archivoDTO.setNumTotalRegistros(registros);
		archivoDTO.setCveEstadoArchivo(EstadoArchivoEnum.PROCESADO.getCveEstadoArchivo());
		archivoDTO.setCveOrigenArchivo(identificador);
		archivoDTO.setDesError(BigDecimal.ZERO.toString());
		archivoDTO.setDesEstadoArchivo(EstadoArchivoEnum.PROCESADO.getDesEstadoArchivo());
		archivoDTO.setFecAlta(DateUtils.getSysDateMongoISO());
		archivoDTO.setFecProcesoCarga(DateUtils.getSysDateMongoISO());
		return archivoDTO;
	}

	public void procesaPasoAl(RegistroDTO item, AseguradoDTO aseguradoPasoAlBDTU) {

		AseguradoDTO aseguradoPasoAl = new AseguradoDTO();
		aseguradoPasoAl.setRefFolioOriginal(item.getRefFolioOriginal());
		aseguradoPasoAl.setNumNss(item.getNumNss());
		aseguradoPasoAl.setNumNssPasoAl(aseguradoPasoAlBDTU.getNumNss());
		aseguradoPasoAl.setNomAsegurado(item.getNomAsegurado());
		aseguradoPasoAl.setRefCurp(item.getRefCurp());
		aseguradoPasoAl.setCveDelegacionNss(Integer.valueOf(item.getCveDelegacionNss()));
		aseguradoPasoAl.setCveSubdelNss(Integer.valueOf(item.getCveSubdelNss()));
		aseguradoPasoAl.setCveUmfAdscripcion(Integer.valueOf(item.getCveUmfAdscripcion()));
		aseguradoPasoAl.setFecAlta(Calendar.getInstance().getTime());
		item.setPasoAl(true);
		item.setAseguradoPasoAl(aseguradoPasoAl);
		if (aseguradoPasoAlBDTU.getCveIdPersona() != null) {
			item.setCveDelegacionNss(aseguradoPasoAlBDTU.getCveDelegacionNss() != null
					? aseguradoPasoAlBDTU.getCveDelegacionNss().toString()
					: null);
			item.setCveSubdelNss(
					aseguradoPasoAlBDTU.getCveSubdelNss() != null ? aseguradoPasoAlBDTU.getCveSubdelNss().toString()
							: null);
			item.setCveUmfAdscripcion(aseguradoPasoAlBDTU.getCveUmfAdscripcion() != null
					? aseguradoPasoAlBDTU.getCveUmfAdscripcion().toString()
					: null);
			item.setNomAsegurado(aseguradoPasoAlBDTU.getNomAsegurado());
			item.setRefCurp(aseguradoPasoAlBDTU.getRefCurp());
		}

	}

}
