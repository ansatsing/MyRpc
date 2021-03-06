package cn.antsing.rpc.exception;

/**
 * 编解码运行时异常
 */
public class CodecException extends RuntimeException {
    public CodecException() {
        super();
    }

    public CodecException(String message) {
        super(message);
    }

    public CodecException(String message, Throwable cause) {
        super(message, cause);
    }

    public CodecException(Throwable cause) {
        super(cause);
    }
}
