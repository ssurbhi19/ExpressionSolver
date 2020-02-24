package Operator;

public class Sine implements Unary
{
    @Override
    public double evaluate(double... operand)
    {
        double result=0;
        for(int i=0;i<operand.length;i++)
            result=Math.sin(Math.toRadians(operand[i]));
        return result;
    }
}
