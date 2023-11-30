package mx.gob.imss.cit.pmc.commons.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

public class CifrasControlDTO extends FechasAuditoriaDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	@Setter
	@Getter
	private long numTotalRegistros;
	@Setter
	@Getter
	private long numRegistrosCorrectos;
	@Setter
	@Getter
	private long numRegistrosCorrectosOtras;
	@Setter
	@Getter
	private long numRegistrosErrorOtras;
	@Setter
	@Getter
	private long numRegistrosError;
	@Setter
	@Getter
	private long numRegistrosSusOtras;
	@Setter
	@Getter
	private long numRegistrosDupOtras;
	@Setter
	@Getter
	private long numRegistrosBaja;
	@Setter
	@Getter
	private long numRegistrosBajaOtras;
	@Setter
	@Getter
	private long numRegistrosDup;
	@Setter
	@Getter
	private long numRegistrosSus;
	@Setter
	@Getter
	private long numPasoAl;

	public long sumatoria() {
		return this.numRegistrosCorrectos + this.numRegistrosCorrectosOtras + this.numRegistrosErrorOtras
				+ this.numRegistrosError + this.numRegistrosSusOtras + this.numRegistrosDupOtras + this.numRegistrosBaja
				+ this.numRegistrosBajaOtras + this.numRegistrosDup + this.numRegistrosSus;
	}

	@Override
	public String toString() {
		return "CifrasControlDTO [numTotalRegistros=" + numTotalRegistros + ", numRegistrosCorrectos="
				+ numRegistrosCorrectos + ", numRegistrosCorrectosOtras=" + numRegistrosCorrectosOtras
				+ ", numRegistrosErrorOtras=" + numRegistrosErrorOtras + ", numRegistrosError=" + numRegistrosError
				+ ", numRegistrosSusOtras=" + numRegistrosSusOtras + ", numRegistrosDupOtras=" + numRegistrosDupOtras
				+ ", numRegistrosBaja=" + numRegistrosBaja + ", numRegistrosBajaOtras=" + numRegistrosBajaOtras
				+ ", numRegistrosDup=" + numRegistrosDup + ", numRegistrosSus=" + numRegistrosSus + "]";
	}

}
