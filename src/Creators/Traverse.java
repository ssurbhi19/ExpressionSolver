package Creators;

public class Traverse
{
    public void printPostorder(Node node1)
    {
        if (node1 == null)
            return;
        printPostorder(node1.left);
        printPostorder(node1.right);
        System.out.print(node1.value + " ");
    }
}
