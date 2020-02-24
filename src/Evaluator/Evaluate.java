package Evaluator;

import Creators.Node;
import Exceptions.WrongTokenException;
import Operator.Binary;
import Operator.OperatorMetaData;

public class Evaluate
{
    double ans = -1.0;

    public boolean isNumeric(String strNum)
    {
        if (strNum == null)
        {
            return false;
        }
        try
        {
            double d = Double.parseDouble(strNum);
        }
        catch (NumberFormatException e)
        {
            return false;
        }
        return true;
    }

    public double BT(Node root)
    {

        String type = "";

        if (isNumeric(root.value))
            return (Double.parseDouble(root.value));
        else if (OperatorMetaData.unary.containsKey(root.value) || OperatorMetaData.binary.containsKey(root.value))
        {
            try
            {
                if (OperatorMetaData.binary.containsKey(root.value))
                    type = "binary";
                else if(OperatorMetaData.unary.containsKey(root.value))
                    type = "unary";
                else
                    throw new WrongTokenException("Invalid operand");
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
            }
        }
        if(type.equals("binary") && root.value.equals("+"))
        {
            ans=OperatorMetaData.binary.get("+").evaluate(BT(root.left), BT(root.right));
        }
        else if(type.equals("binary") && root.value.equals("*"))
        {
            ans=OperatorMetaData.binary.get("*").evaluate(BT(root.left), BT(root.right));
        }
        else if(type.equals("binary") && root.value.equals("-"))
        {
            ans=OperatorMetaData.binary.get("-").evaluate(BT(root.left), BT(root.right));
        }
        else {
            ans = OperatorMetaData.unary.get("sin").evaluate(0, BT(root.right));
        }
        return ans;
    }

    public double evaluate(Node root1)
    {
        return(BT(root1));
    }

}
