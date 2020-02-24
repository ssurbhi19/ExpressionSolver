package Creators;
import Operator.Operator;
import Operator.OperatorMetaData;
import Validator.ValidNumber;

import java.util.List;
import java.util.Stack;

class TreeCreator
{
    public Node constructTree(List<String> postfix)
    {
        ValidNumber validNumber=new ValidNumber();
        Stack<Node> stack = new Stack<>();
        Node temp, right, left;
        for (int i = 0; i < postfix.size(); i++)
        {
            if (validNumber.isValidNumber(postfix.get(i)))
            {
                    temp = new Node(postfix.get(i));
                    stack.push(temp);
            }
            else if(OperatorMetaData.binary.containsKey(postfix.get(i)) || OperatorMetaData.unary.containsKey(postfix.get(i)))
            {
                if (OperatorMetaData.binary.containsKey(postfix.get(i)))
                {
                    temp = new Node(postfix.get(i));
                    right = stack.pop();
                    left = stack.pop();
                    temp.right = right;
                    temp.left = left;
                    stack.push(temp);
                }
                else if (OperatorMetaData.unary.containsKey(postfix.get(i)))
                {
                    temp = new Node(postfix.get(i));
                    right = stack.pop();
                    temp.right = right;
                    stack.push(temp);
                }
            }
        }
        temp = stack.peek();
        stack.pop();
        return temp;
    }
}
