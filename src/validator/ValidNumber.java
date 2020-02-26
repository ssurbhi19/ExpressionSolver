package validator;

public class ValidNumber
{
    public boolean isValidNumber(String operand)
    {
        //Used to check the validity of the number. If valid return true else throw an exception.
        try
        {
            Double.parseDouble(operand);

        }
        catch(NumberFormatException e)
        {
            System.out.println(e.getMessage());
            System.exit(0);
        }
        return true;
    }
}
