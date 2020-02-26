package operator;
//Operator parent class
public interface Operator
{
    //For taking in variable length of arguments like 1 operand is used by unary and 2 for binary.
    double evaluate(double ...operand);
}
