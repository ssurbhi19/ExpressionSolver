package parser;

import creators.CreateController;
import java.util.List;

public class ParseController
{
    public void init(String str)
    {
        //Used to obtain the arraylist or we can say tokens.. eg split string. for insatnce 2+3 is added into arraylist
        //like 2 at 1st index + at 2nd and 3 at 3rd.
        List<String> list;
        TokensAsArrayList tokensAsArrayList = new TokensAsArrayList(str);
        list= tokensAsArrayList.arrayListGenerator();
        //Send the obtained arrayList for making the postfix expression and expression tree.
        CreateController createController = new CreateController(list);
        createController.init();
    }
}
