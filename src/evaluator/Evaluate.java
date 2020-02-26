package evaluator;

import creators.Node;
import exceptions.NoSuchOperatorFound;
import exceptions.WrongTokenException;
import operator.OperatorMetaData;

public class Evaluate
{
    private double ans = -1.0;

    private boolean isNumeric(String strNum)
    {
        //Checks if the string is a valis number or not.
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

    private double BT(Node root)
    {

        String type = "";

        //If we have encountered a leaf node, return its value.
        if (isNumeric(root.value))
            return (Double.parseDouble(root.value));
        //If the root is an operator
        else if (OperatorMetaData.unary.containsKey(root.value) || OperatorMetaData.binary.containsKey(root.value))
        {
            try
            {
                //Assign the type of Operator
                if (OperatorMetaData.binary.containsKey(root.value))
                    type = "binary";
                else if(OperatorMetaData.unary.containsKey(root.value))
                    type = "unary";
                else
                    throw new NoSuchOperatorFound("Invalid operand");
            }
            catch (NoSuchOperatorFound e)
            {
                System.out.println(e.getMessage());
            }
        }
        //Check the type and value of the root.
        if(type.equals("binary") && root.value.equals("+"))
        {
            //get the value(here it is object) associated with the key "+" and call the evaluate  function with 2 operands.
            ans=OperatorMetaData.binary.get("+").evaluate(BT(root.left), BT(root.right));
        }
        else if(type.equals("binary") && root.value.equals("*"))
        {
            ans=OperatorMetaData.binary.get("*").evaluate(BT(root.left), BT(root.right));
        }
        else if(type.equals("binary") && root.value.equals("/"))
        {
            ans=OperatorMetaData.binary.get("/").evaluate(BT(root.left), BT(root.right));
        }
        else if(type.equals("binary") && root.value.equals("-"))
        {
            ans=OperatorMetaData.binary.get("-").evaluate(BT(root.left), BT(root.right));
        }
        else {
            //If the type is unary give 1 operand i.e., the right child.
            ans = OperatorMetaData.unary.get("sin").evaluate( BT(root.right));
        }
        return ans;
    }

    public double evaluate(Node root1)
    {
        return(BT(root1));
    }

}
