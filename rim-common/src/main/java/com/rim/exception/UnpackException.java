package com.rim.exception;

/**
 * @author rickiyang
 * @date 2020-07-15
 * @Desc 解包异常类
 */
public class UnpackException extends RuntimeException
{
    public static final long serialVersionUID = 12L;

    public UnpackException()
    {
        this( "Unpack error" );
    }

    public UnpackException( String message )
    {
        super( message );
    }

    public UnpackException( Throwable cause )
    {
        super( cause );
    }

    public UnpackException( String message, Throwable cause )
    {
        super( message, cause );
    }

}

