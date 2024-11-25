import Repository.MenuRepository;
import Repository.MenuRepositoryImpl;
import Service.OrderService;
import Service.OrderServiceImpl;
import View.RestoranAppView;

public class Main {

    public static void main(String[] args) {

        MenuRepository menuRepository = new MenuRepositoryImpl();
        OrderService orderService = new OrderServiceImpl(menuRepository);
        RestoranAppView restoranAppView = new RestoranAppView(orderService);

        restoranAppView.chooseService();
    }
}
