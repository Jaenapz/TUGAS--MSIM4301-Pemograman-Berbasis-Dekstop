package Repository;

import Entity.Menu;

import java.util.ArrayList;

public class MenuRepositoryImpl implements MenuRepository {

    private ArrayList<Menu> menuList;

    public MenuRepositoryImpl(ArrayList<Menu> manuList) {
        this.menuList = manuList;
    }

    public MenuRepositoryImpl() {
        menuList = new ArrayList<>();
        menuList.add(new Menu("Ayam Panggang", 15000, "Makanan"));
        menuList.add(new Menu("Soto Ayam", 20000, "Makanan"));
        menuList.add(new Menu("Ayam Bakar", 17000, "Makanan"));
        menuList.add(new Menu("Soto Sapi", 35000, "Makanan"));
        menuList.add(new Menu("Es Jeruk", 10000, "Minuman"));
        menuList.add(new Menu("Es Teh Manis", 8000, "Minuman"));
        menuList.add(new Menu("Es Kelapa", 15000, "Minuman"));
        menuList.add(new Menu("Es Lemon Tea", 12000, "Minuman"));
    }

    @Override
    public ArrayList<Menu> getMenulist() {
        return menuList;
    }


    @Override
    public void add(Menu menu) {
        menuList.add(menu);
    }

    @Override
    public void update(Menu menu) {

        for (Menu menus : menuList){
            if (menus.getName().equals(menu.getName())){
                menus.setPrice(menu.getPrice());
            }
        }
    }

    @Override
    public void remove(Menu menu) {
        menuList.remove(menu);
    }
}


