package Operator;

public class Subtract implements Binary
{
    public double evaluate(double... operand)
    {
        double result=0;
        double temp1,temp2;
        temp1=operand[0];
        temp2=operand[1];
        result=temp1-temp2;
        return result;
    }
}
