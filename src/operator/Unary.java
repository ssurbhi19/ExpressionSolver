package operator;

public interface Unary extends Operator
{
    //Used for unary operators.
    double evaluate(double ...operand);
}
