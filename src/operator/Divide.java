package operator;

import static java.lang.System.exit;

public class Divide implements Binary
{

    @Override
    public double evaluate(double... operand)
    {
        double result=0;
        try
        {
            result=operand[0]/operand[1];
        }
        catch (ArithmeticException e)
        {
            System.out.println("Divide by zero");
            exit(0);
        }
        return result;
    }
}
