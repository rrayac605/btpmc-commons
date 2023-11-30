package mx.gob.imss.cit.pmc.commons.to;

import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

public class MailTO {

	@Getter
	@Setter
	private String mailFrom;

	@Getter
	@Setter
	private String []mailTo;

	@Getter
	@Setter
	private String []mailCc;

	@Getter
	@Setter
	private String mailBcc;

	@Getter
	@Setter
	private String mailSubject;

	@Getter
	@Setter
	private String mailContent;

	@Getter
	@Setter
	private String contentType;

	@Getter
	@Setter
	private List<Object> attachments;

	@Getter
	@Setter
	private Map<String, Object> model;

	public MailTO() {
		contentType = "text/plain";
	}

}
