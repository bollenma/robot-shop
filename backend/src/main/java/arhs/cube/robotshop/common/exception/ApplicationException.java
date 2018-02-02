package arhs.cube.robotshop.common.exception;

import org.apache.commons.lang3.exception.ContextedRuntimeException;
import org.apache.commons.lang3.exception.ExceptionContext;

/**
 * @author bollenma
 */
public class ApplicationException extends ContextedRuntimeException {

    public ApplicationException() {
    }

    public ApplicationException(final Throwable cause) {
        super(cause);
    }

    public ApplicationException(final String message) {
        super(message);
    }

    public ApplicationException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ApplicationException(final String message, final Throwable cause, final ExceptionContext context) {
        super(message, cause, context);
    }


}
