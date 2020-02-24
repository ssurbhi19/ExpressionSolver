package Parser;

import Creators.CreateController;
import java.util.List;

public class ParseController
{
    public void init(String str)
    {
        List<String> list;
        ArrayListGenerator arrayListGenerator = new ArrayListGenerator(str);
        list=arrayListGenerator.generate();
        CreateController createController = new CreateController(list);
        createController.init();
    }
}
