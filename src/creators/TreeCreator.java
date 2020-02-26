package creators;
import operator.OperatorMetaData;
import validator.ValidNumber;

import java.util.List;
import java.util.Stack;

class TreeCreator
{
    public Node constructTree(List<String> postfix)
    {
        ValidNumber validNumber=new ValidNumber();
        Stack<Node> stack = new Stack<>();

        //Temp is used for storing a new node created.
        //Left : stores the left child.
        //Right : stores the right child.

        Node temp, right, left;
        for (int i = 0; i < postfix.size(); i++)
        {
            //If the value is not a operator then simply create a new node and push it into a stack.

            if (!(OperatorMetaData.binary.containsKey(postfix.get(i)) || OperatorMetaData.unary.containsKey(postfix.get(i))))
            {
                    temp = new Node(postfix.get(i));
                    stack.push(temp);
            }
            //If the value is an operator:
            else if(OperatorMetaData.binary.containsKey(postfix.get(i)) || OperatorMetaData.unary.containsKey(postfix.get(i)))
            {
                //If its binary operator then pop 2 values from the stack.
                if (OperatorMetaData.binary.containsKey(postfix.get(i)))
                {
                    temp = new Node(postfix.get(i));
                    right = stack.pop();
                    left = stack.pop();
                    temp.right = right;
                    temp.left = left;
                    stack.push(temp);
                }
                //If its unary then pop 1
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
