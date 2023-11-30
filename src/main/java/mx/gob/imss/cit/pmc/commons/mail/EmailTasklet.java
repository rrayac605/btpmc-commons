package mx.gob.imss.cit.pmc.commons.mail;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Getter;
import lombok.Setter;
import mx.gob.imss.cit.pmc.commons.to.MailTO;
import mx.gob.imss.cit.pmc.commons.to.PlantillaEmailTO;

public class EmailTasklet {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Setter
	@Getter
	protected Map<String, Object> model;

	@Setter
	@Getter
	protected MailTO mail;

	@Setter
	@Getter
	protected PlantillaEmailTO plantilla;

	public void llenaParametros() {
		try {

			mail = new MailTO();
			mail.setMailFrom(plantilla.getMailFrom());
			mail.setMailTo(plantilla.getMailTo());
			mail.setMailCc(plantilla.getMailCc());
			mail.setMailSubject(plantilla.getSubject());

			model = new HashMap<String, Object>();
			model.put("nombreCompleto", plantilla.getNombreCompleto());
			model.put("fechaEnvio", plantilla.getFechaEnvio());
			model.put("horaEnvio", plantilla.getHoraEnvio());
			model.put("sistemaOrigen", plantilla.getSistemaOrigen());
			model.put("diasEnvio", plantilla.getDiasEnvio());
			model.put("urlConsultar", plantilla.getUrlConsultar());
			model.put("errorArchivo", plantilla.getDescError());
			mail.setModel(model);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}
}
