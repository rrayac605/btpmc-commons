package mx.gob.imss.cit.pmc.commons.enums;

public enum SituacionRegistroEnum {

	APROBADO(1, "Aprobado"), RECHAZADO(3, "Rechazado"), PENDIENTE(2, "Pendiente de aprobar")

	;

	public int getClave() {
		return clave;
	}

	public String getDescripcion() {
		return descripcion;
	}

	private int clave;
	private String descripcion;

	SituacionRegistroEnum(Integer clave, String descripcion) {
		this.clave = clave;
		this.descripcion = descripcion;

	}

}
