package Operator;

public class Add implements Binary
{
    public double evaluate(double... operand)
    {
        double result=0;
        for(int i=0;i<operand.length;i++)
            result+=operand[i];
        return result;
    }
}