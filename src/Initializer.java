import Exceptions.EmptyExpression;
import Exceptions.NoSuchOperatorFound;
import Operator.*;

public class Initializer
{
    OperatorMetaData operatorMetaData = new OperatorMetaData();

    public void init()
    {
        Add add= new Add();
        Subtract sub = new Subtract();
        Sine sin=new Sine();
        Cosec cosec = new Cosec();
        Multiply multiply = new Multiply();
        operatorMetaData.addBinary( "+",add);
        operatorMetaData.addBinary("-",sub);
        operatorMetaData.addUnary( "sin",sin);
        operatorMetaData.addUnary("cosec",cosec);
        operatorMetaData.addBinary("*",multiply);
    }
}

