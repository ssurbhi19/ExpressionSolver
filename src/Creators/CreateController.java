package Creators;

import Evaluator.Evaluate;
import Parser.ParseController;

import java.util.ArrayList;
import java.util.List;

public class CreateController
{
    List<String> list;
    public CreateController(List<String> li)
    {
        this.list=new ArrayList<>();
        this.list = li;
    }

    public void init()
    {
        PostfixCreator postfixCreator = new PostfixCreator();
        this.list=postfixCreator.getPostfix(this.list);

        TreeCreator treeCreator=new TreeCreator();
        Node root=treeCreator.constructTree(this.list);

        System.out.print("TREE TRAVERSAL ");
        Traverse traverse1= new Traverse();
        traverse1.printPostorder(root);

        System.out.println();

        Evaluate evaluate1=new Evaluate();
        System.out.println("RESULT "+evaluate1.evaluate(root));
    }
}
