package creators;

import evaluator.EvaluateController;

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

        Traverse traverse1= new Traverse();
        traverse1.printPostorder(root);

        System.out.println();

        EvaluateController evaluateController = new EvaluateController();
        evaluateController.calculate(root);

    }
}
