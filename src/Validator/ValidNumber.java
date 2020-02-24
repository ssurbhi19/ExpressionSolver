package Validator;

import static java.lang.System.exit;

public class ValidNumber
{
    public boolean isValidNumber(String operand)
    {
        try
        {
            Double.parseDouble(operand);
            return true;
        }
        catch(NumberFormatException e)
        {
            return false;
        }
    }
}
