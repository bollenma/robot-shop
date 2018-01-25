package arhs.cube.robotshop.common.exception;

import org.apache.commons.lang3.exception.ContextedRuntimeException;
import org.apache.commons.lang3.exception.ExceptionContext;

/**
 * @author bollenma
 */
public class SystemException extends ContextedRuntimeException {
    public SystemException() {
    }

    public SystemException(final Throwable cause) {
        super(cause);
    }

    public SystemException(final String message) {
        super(message);
    }

    public SystemException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public SystemException(final String message, final Throwable cause, final ExceptionContext context) {
        super(message, cause, context);
    }
}
