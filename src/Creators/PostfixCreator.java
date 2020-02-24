package Creators;

import Operator.OperatorMetaData;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class PostfixCreator
{
    private int getPreference(String c)
    {

        if(c.equals("+") || c.equals("-")) return 1;
        else if (c.equals("*") || c.equals("/")) return 2;
        else if (c.equals("sin") || (c.equals("cosec"))) return 3;
        else return -1;
    }

    private List<String> getPostFixString(List<String> list)
    {
        Stack<String> stack = new Stack<>();
        List<String> postFixList = new ArrayList<>();

        boolean flag = false;
        for (int i = 0; i < list.size(); i++)
        {
            String word = list.get(i);
            if (word.equals(" "))
            {
                continue;
            }
            if (word.equals("("))
            {
                stack.push(word);
                flag = false;
            }
            else if (word.equals(")"))
            {
                flag = false;
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
            else if (OperatorMetaData.unary.containsKey(word) || OperatorMetaData.binary.containsKey(word))
            {
                flag = false;
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
                if (flag)
                {
                    String lastNumber = postFixList.get(postFixList.size() - 1);
                    lastNumber += word;
                    postFixList.set(postFixList.size() - 1, lastNumber);
                }
                else
                    postFixList.add(word + "");

                flag = true;
            }
        }
        while (!stack.isEmpty())
        {
            postFixList.add(stack.pop() + "");
        }

        System.out.println("POSTFIX"+postFixList);
        return postFixList;
    }

    public List<String> getPostfix(List<String> list)
    {
        return getPostFixString(list);
    }
}