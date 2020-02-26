package parser;

import creators.CreateController;
import java.util.List;

public class ParseController
{
    public void init(String str)
    {
        List<String> list;
        TokensAsArrayList tokensAsArrayList = new TokensAsArrayList(str);
        list= tokensAsArrayList.arrayListGenerator();
        CreateController createController = new CreateController(list);
        createController.init();
    }
}
