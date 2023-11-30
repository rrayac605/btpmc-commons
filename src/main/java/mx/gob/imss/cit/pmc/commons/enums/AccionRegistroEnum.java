package mx.gob.imss.cit.pmc.commons.enums;

public enum AccionRegistroEnum {
	ALTA(1, "Alta"),
	MODIFICACION(2, "Modificacion"), 
	ELIMINACION(3, "Eliminacion"),
	ALTA_PENDIENTE(4, "Alta pendiente"),
	MODIFICACION_PENDIENTE(5, "Modificacion pendiente"),
	BAJA_PENDIENTE(6, "Baja pendiente"), 
	MODIFICACION_RECHAZADO(8, "Modificacion rechazado"),
	BAJA_RECHAZADO(9, "Baja rechazado"),
	ALTA_RECHAZADO(7, "Alta rechazado"),
	PENDIENTE_APROBAR(10,"Pendiente de Aprobar");
	

	public int getClave() {
		return clave;
	}

	public String getDescripcion() {
		return descripcion;
	}

	private int clave;
	private String descripcion;

	AccionRegistroEnum(Integer clave, String descripcion) {
		this.clave = clave;
		this.descripcion = descripcion;
	}

}
