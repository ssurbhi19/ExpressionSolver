package Evaluator;

import Creators.Node;

public class EvaluateController
{
    private static double finalResult=0;

    public void calculate(Node root)
    {
        Evaluate evaluate1=new Evaluate();
        finalResult=evaluate1.evaluate(root);
    }

    public double result()
    {
        return finalResult;
    }
}
