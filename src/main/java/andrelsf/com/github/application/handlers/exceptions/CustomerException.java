package andrelsf.com.github.application.handlers.exceptions;

import java.io.Serial;
import java.io.Serializable;

public class CustomerException {

  public static class DuplicateKeyException extends BaseException implements Serializable {

    @Serial
    private static final long serialVersionUID = 583336316483482160L;

    public DuplicateKeyException(String message) {
      super(message);
    }
  }

}
