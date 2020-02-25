package validator;

public class ValidNumber
{
    public boolean isValidNumber(String operand)
    {
        try
        {
            Double.parseDouble(operand);

        }
        catch(NumberFormatException e)
        {
            System.out.println(e.getMessage());
            System.exit(0);
        }
        return false;
    }
}
