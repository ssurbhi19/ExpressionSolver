package creators;

import operator.OperatorMetaData;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class PostfixCreator
{
    private Stack<String> stack = new Stack<>();
    private List<String> postFixList = new ArrayList<>();

    private int getPreference(String c)
    {
        //Return the precedence of operator
        switch (c) {
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
                return 2;
            case "sin":
            case "cosec":
                return 3;
            default:
                return -1;
        }
    }

    private List<String> getPostFixString(List<String> list)
    {

        //Traverse the arrayList.
        for (int i = 0; i < list.size(); i++)
        {
            String word = list.get(i);
            if (word.equals(" "))
            {
                continue;
            }
            //If ( then push it directly into stack.
            if (word.equals("("))
            {
                stack.push(word);
            }
            //If the word is ) then pop till we encounter (
            else if (word.equals(")"))
            {
                while (!stack.isEmpty())
                {
                    if (stack.peek().equals("("))
                    {
                        stack.pop();
                        break;
                    }
                    else
                    {
                        postFixList.add(stack.pop() + "");
                    }
                }
            }
            //If its an operator then check the precedence and accordingly act.
            else if (OperatorMetaData.unary.containsKey(word) || OperatorMetaData.binary.containsKey(word))
            {
                if (stack.isEmpty())
                {
                    stack.push(word);
                }
                else
                {
                    while (!stack.isEmpty() && getPreference(stack.peek()) >= getPreference(word))
                    {
                        postFixList.add(stack.pop() + "");
                    }
                    stack.push(word);
                }
            }
            else
            {
                    postFixList.add(word + "");
            }
        }
        while (!stack.isEmpty())
        {
            postFixList.add(stack.pop() + "");
        }

        return postFixList;
    }

    public List<String> getPostfix(List<String> list)
    {
        return getPostFixString(list);
    }
}