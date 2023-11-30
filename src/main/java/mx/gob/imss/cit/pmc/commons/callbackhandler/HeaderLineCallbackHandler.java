package mx.gob.imss.cit.pmc.commons.callbackhandler;

import java.util.Calendar;

import mx.gob.imss.cit.pmc.commons.dto.ArchivoDTO;
import mx.gob.imss.cit.pmc.commons.dto.CabeceraDTO;
import mx.gob.imss.cit.pmc.commons.enums.EstadoArchivoEnum;
import mx.gob.imss.cit.pmc.commons.utils.DateUtils;
import mx.gob.imss.cit.pmc.commons.utils.Utils;

public class HeaderLineCallbackHandler {

	public CabeceraDTO crearCabecera(String line) {

		CabeceraDTO cabeceraDTO = new CabeceraDTO();
		cabeceraDTO.setNumRegistros(Long.parseLong(line.substring(0, 7)));
		cabeceraDTO.setFecProceso(Calendar.getInstance().getTime());
		cabeceraDTO.setCveArchivo(line.substring(15, 18));
		return cabeceraDTO;
	}

	public ArchivoDTO crearArchivo(String id, int registros) {

		ArchivoDTO archivoDTO = new ArchivoDTO();
		archivoDTO.setCveEstadoArchivo(EstadoArchivoEnum.NO_PROCESADO.getCveEstadoArchivo());
		archivoDTO.setDesEstadoArchivo(EstadoArchivoEnum.NO_PROCESADO.getDesEstadoArchivo());
		archivoDTO.setDesError("El total de registros no coincide con el total de registro leídos");
		archivoDTO.setNomArchivo(Utils.obtenerNombreArchivo(id));
		archivoDTO.setDesIdArchivo(Utils.obtenerNombreArchivo(id));
		archivoDTO.setFecProcesoCarga(DateUtils.getSysDateMongoISO());
		archivoDTO.setNumTotalRegistros(String.valueOf(registros));
		archivoDTO.setFecAlta(DateUtils.getSysDateMongoISO());
		archivoDTO.setCveOrigenArchivo(id);
		return archivoDTO;
	}
	
	public ArchivoDTO crearArchivoSISAT(String id, int registros) {

		ArchivoDTO archivoDTO = new ArchivoDTO();
		archivoDTO.setCveEstadoArchivo(EstadoArchivoEnum.NO_PROCESADO.getCveEstadoArchivo());
		archivoDTO.setDesEstadoArchivo(EstadoArchivoEnum.NO_PROCESADO.getDesEstadoArchivo());
		archivoDTO.setDesError("El total de registros no coincide con el total de registro leídos");
		archivoDTO.setNomArchivo(Utils.obtenerNombreArchivoSISAT(id));
		archivoDTO.setDesIdArchivo(Utils.obtenerNombreArchivoSISAT(id));
		archivoDTO.setFecProcesoCarga(DateUtils.getSysDateMongoISO());
		archivoDTO.setNumTotalRegistros(String.valueOf(registros));
		archivoDTO.setFecAlta(DateUtils.getSysDateMongoISO());
		archivoDTO.setCveOrigenArchivo(id);
		return archivoDTO;
	}
	
	public ArchivoDTO crearArchivoNSSA(String id, int registros) {

		ArchivoDTO archivoDTO = new ArchivoDTO();
		archivoDTO.setCveEstadoArchivo(EstadoArchivoEnum.NO_PROCESADO.getCveEstadoArchivo());
		archivoDTO.setDesEstadoArchivo(EstadoArchivoEnum.NO_PROCESADO.getDesEstadoArchivo());
		archivoDTO.setDesError("El total de registros no coincide con el total de registro leídos");
		archivoDTO.setNomArchivo(Utils.obtenerNombreArchivoNSSA(id));
		archivoDTO.setDesIdArchivo(Utils.obtenerNombreArchivoNSSA(id));
		archivoDTO.setFecProcesoCarga(DateUtils.getSysDateMongoISO());
		archivoDTO.setNumTotalRegistros(String.valueOf(registros));
		archivoDTO.setFecAlta(DateUtils.getSysDateMongoISO());
		archivoDTO.setCveOrigenArchivo(id);
		return archivoDTO;
	}
	
	public ArchivoDTO crearArchivoCorrecto(String id, int registros) {

		ArchivoDTO archivoDTO = new ArchivoDTO();
		archivoDTO.setCveEstadoArchivo(EstadoArchivoEnum.PROCESADO.getCveEstadoArchivo());
		archivoDTO.setDesEstadoArchivo(EstadoArchivoEnum.PROCESADO.getDesEstadoArchivo());
		archivoDTO.setNomArchivo(Utils.obtenerNombreArchivo(id));
		archivoDTO.setDesIdArchivo(Utils.obtenerNombreArchivo(id));
		archivoDTO.setFecProcesoCarga(DateUtils.getSysDateMongoISO());
		archivoDTO.setNumTotalRegistros(String.valueOf(registros));
		archivoDTO.setFecAlta(DateUtils.getSysDateMongoISO());
		archivoDTO.setCveOrigenArchivo(id);
		return archivoDTO;
	}
	
	public ArchivoDTO crearArchivoCorrectoNSSA(String id, int registros) {

		ArchivoDTO archivoDTO = new ArchivoDTO();
		archivoDTO.setCveEstadoArchivo(EstadoArchivoEnum.PROCESADO.getCveEstadoArchivo());
		archivoDTO.setDesEstadoArchivo(EstadoArchivoEnum.PROCESADO.getDesEstadoArchivo());
		archivoDTO.setNomArchivo(Utils.obtenerNombreArchivoNSSA(id));
		archivoDTO.setDesIdArchivo(Utils.obtenerNombreArchivoNSSA(id));
		archivoDTO.setFecProcesoCarga(DateUtils.getSysDateMongoISO());
		archivoDTO.setNumTotalRegistros(String.valueOf(registros));
		archivoDTO.setFecAlta(DateUtils.getSysDateMongoISO());
		archivoDTO.setCveOrigenArchivo(id);
		return archivoDTO;
	}
	
	public ArchivoDTO crearArchivoCorrectoSISAT(String id, int registros) {

		ArchivoDTO archivoDTO = new ArchivoDTO();
		archivoDTO.setCveEstadoArchivo(EstadoArchivoEnum.PROCESADO.getCveEstadoArchivo());
		archivoDTO.setDesEstadoArchivo(EstadoArchivoEnum.PROCESADO.getDesEstadoArchivo());
		archivoDTO.setNomArchivo(Utils.obtenerNombreArchivoSISAT(id));
		archivoDTO.setDesIdArchivo(Utils.obtenerNombreArchivoSISAT(id));
		archivoDTO.setFecProcesoCarga(DateUtils.getSysDateMongoISO());
		archivoDTO.setNumTotalRegistros(String.valueOf(registros));
		archivoDTO.setFecAlta(DateUtils.getSysDateMongoISO());
		archivoDTO.setCveOrigenArchivo(id);
		return archivoDTO;
	}

}
