package exceptions;

import static java.lang.System.exit;

public class WrongTokenException extends Exception
{
    public WrongTokenException(String exception1)
    {
        super(exception1);
    }
}
