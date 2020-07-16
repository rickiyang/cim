package com.rim.exception;

/**
 * @author rickiyang
 * @date 2020-07-15
 * @Desc 打包异常类
 */
public class PackException extends RuntimeException {
    private static final long serialVersionUID = 1L;


    public PackException()
    {
        this( "PackError" );
    }

    public PackException(String message)
    {
        super(message);
    }

    public PackException(String message, Throwable cause) {
        super(message, cause);
    }

    public PackException(Throwable cause){
        super(cause);
    }
}
