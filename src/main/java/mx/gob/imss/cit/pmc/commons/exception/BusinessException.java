package mx.gob.imss.cit.pmc.commons.exception;

import mx.gob.imss.cit.pmc.commons.dto.ErrorResponse;
import mx.gob.imss.cit.pmc.commons.enums.EnumHttpStatus;

public class BusinessException extends Exception {

	  /**
	   *
	   */
	  private static final long serialVersionUID = -872483897865904849L;

	  private ErrorResponse errorResponse;
	  
	  public BusinessException(Exception e) {
		  super(e);
	  }

	  public BusinessException(EnumHttpStatus status, String businessMessage, String reasonPhrase) {

	    super(reasonPhrase);

	    errorResponse = new ErrorResponse(status, businessMessage, reasonPhrase);
	  }

	  public BusinessException(ErrorResponse errorResponse) {
	    this.errorResponse = errorResponse;
	  }

	  public ErrorResponse getErrorResponse() {
	    return errorResponse;
	  }

	  public void setErrorResponse(ErrorResponse errorResponse) {
	    this.errorResponse = errorResponse;
	  }

	}