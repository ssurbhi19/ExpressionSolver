package operator;

public interface Binary extends Operator
{
    //Used for binary operators.
    double evaluate(double ...operand);
}
