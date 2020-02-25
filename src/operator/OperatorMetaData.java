package operator;

import java.util.HashMap;

public class OperatorMetaData
{
    public static HashMap<String,Operator> unary = new HashMap<>();
    public static HashMap<String,Operator> binary= new HashMap<>();

    public void addUnary(String opt,Unary unaryObj)
    {
        unary.put(opt,unaryObj);
    }

    public void addBinary(String opt,Binary binaryObj)
    {
        binary.put(opt,binaryObj);
    }

}
