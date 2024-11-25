package Repository;

import Entity.Menu;

import java.util.ArrayList;

public interface MenuRepository {

   ArrayList<Menu> getMenulist();

   void add(Menu menu);

   void update(Menu menu);

   void remove(Menu menu);
}
