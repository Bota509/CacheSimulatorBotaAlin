import Controller.CacheController;
import body.Cache;
import views.CacheView;
import views.StartPage;

public class Main {
    public static void main(String[] args) {

        StartPage startPage = new StartPage();
        CacheView cacheView = new CacheView();
        CacheController cacheController = new CacheController(startPage,cacheView);

    }
}
