package operator;

public class Add implements Binary
{
    //Evaluate the addition expression.
    public double evaluate(double... operand)
    {
        double result=0;
        for(int i=0;i<operand.length;i++)
            result+=operand[i];
        return result;
    }
}
