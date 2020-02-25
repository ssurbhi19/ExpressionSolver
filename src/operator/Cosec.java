package operator;

public class Cosec implements Unary {

    @Override
    public double evaluate(double... operand)
    {
        double result=0;
        for(int i=0;i<operand.length;i++)
            result=1/Math.sin(operand[i]);
        return result;
    }
}
