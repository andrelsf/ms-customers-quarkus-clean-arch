package andrelsf.com.github.application.handlers;

import static andrelsf.com.github.application.handlers.exceptions.ErrorMessage.CONTACT_SYSADMIN;
import static andrelsf.com.github.application.handlers.exceptions.ErrorMessage.UNPROCESSABLE_CONTENT;
import static andrelsf.com.github.application.utils.Mapper.buildApiError;

import andrelsf.com.github.application.handlers.exceptions.CustomerException.DuplicateKeyException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.jboss.logging.Logger;
import org.jboss.resteasy.reactive.RestResponse.StatusCode;

@Provider
@Produces(MediaType.APPLICATION_JSON)
public class ApplicationExceptionHandler implements ExceptionMapper<Throwable> {

  private static final Logger logger = Logger.getLogger(
      ApplicationExceptionHandler.class.getName());

  @Override
  public Response toResponse(Throwable ex) {
    logger.error(ex.getMessage(), ex);
    if (ex instanceof DuplicateKeyException) {
      return this.buildErrorResponse(UNPROCESSABLE_CONTENT.getCode(), ex.getMessage());
    }
    if (ex instanceof EntityNotFoundException) {
      return this.buildErrorResponse(StatusCode.NOT_FOUND, ex.getMessage());
    }
    return this.buildErrorResponse(CONTACT_SYSADMIN.getCode(), CONTACT_SYSADMIN.getMessage());
  }

  private Response buildErrorResponse(final int statusCode, final String message) {
    return Response.status(statusCode)
        .entity(buildApiError(statusCode, message))
        .build();
  }
}
