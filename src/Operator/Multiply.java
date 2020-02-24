package Operator;

public class Multiply implements Binary
{
    public double evaluate(double... operand)
    {
        double result=1;
        result=operand[0]*operand[1];
        return result;
    }
}
