package andrelsf.com.github.application.handlers.exceptions;

import static org.jboss.resteasy.reactive.RestResponse.StatusCode.INTERNAL_SERVER_ERROR;

public enum ErrorMessage {
  UNPROCESSABLE_CONTENT(422, "Unprocessable content. "),
  CONTACT_SYSADMIN(INTERNAL_SERVER_ERROR, "Contact SysAdmin");

  private final int code;
  private final String message;

  ErrorMessage(final int code, final String message) {
    this.code = code;
    this.message = message;
  }

  public int getCode() {
    return this.code;
  }

  public String getMessage() {
    return this.message;
  }

}
