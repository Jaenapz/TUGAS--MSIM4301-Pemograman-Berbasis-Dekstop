package Service;

import Entity.Menu;
import Entity.OrderInput;
import Entity.ReceiptDetail;
import Repository.MenuRepository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class OrderServiceImpl implements OrderService {

        private MenuRepository menuRepository;
        private Scanner scanner = new Scanner(System.in);

    public OrderServiceImpl(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Override
        public void displayMenu() {
            System.out.println("====Selamat Datang di Rumah Makan Kapitalis====");
            System.out.println("Menu Makanan Kami: ");
            for (Menu menu : menuRepository.getMenulist())
                if (menu.getCategory().equals("Makanan")) {
                    System.out.println("- " + menu.getName() + " = Rp." + menu.getPrice());
                }
            System.out.println("\nMenu Minuman Kami: ");
            for (Menu menu : menuRepository.getMenulist())
                if (menu.getCategory().equals("Minuman")) {
                    System.out.println("- " + menu.getName() + " = Rp." + menu.getPrice());
                }
        }

    public OrderServiceImpl() {
        super();
    }

    @Override
    public void addNewMenu() {
        System.out.print("Masukan Nama Menu Baru : ");
        String name = scanner.nextLine();
        System.out.print("Masukan Harganya : ");
        double price = Double.parseDouble(scanner.nextLine());
        System.out.print("Masukan Kategorinya : ");
        String category = scanner.nextLine();

        menuRepository.add(new Menu(name,price,category));

    }

    @Override
    public void updateMenuPrice() {

        System.out.println("Masukan nama menu yang ingin di update : ");
        String name = scanner.nextLine();
        System.out.println("Masukan Harga Barangnya : ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        for(Menu menu : menuRepository.getMenulist()){
            if(menu.getName().equals(name)){
                menu.setPrice(price);
                menuRepository.update(menu);
                System.out.println("Harga Sukses di Update.");
                return;
            }
        }
        System.out.println("Menu tidak ditemukan");
    }

    @Override
    public void removeMenu() {
        System.out.println("Masukan Menu yang ingin dihapus : ");
        String name = scanner.nextLine();

        boolean found = false;
        Iterator<Menu> iterator = menuRepository.getMenulist().iterator();
        while (iterator.hasNext()) {
            Menu menu = iterator.next();
            if (menu.getName().equals(name)) {
                iterator.remove();
                found = true;
                System.out.println("Menu berhasil dihapus.");
                break;
            }
        }

        if (!found) {
            System.out.println("Menu tidak ditemukan.");
        }
    }


    @Override
        public double calculateTotalOrder(String[] orderitem, int[] quantity) {
            double totalcost = 0;
            boolean possiblebuyonegetone = false;
            for (int i = 0; i < orderitem.length; i++) {
                if (orderitem[i] == null)
                    break;
                for (Menu menu : menuRepository.getMenulist()) {
                    if (menu.getName().equalsIgnoreCase(orderitem[i])) {
                        double peritemcost = menu.getPrice() * quantity[i];
                        if (menu.getCategory().equals("Minuman") && peritemcost >= 50000) {
                            possiblebuyonegetone = true;
                        }
                        totalcost += peritemcost;
                        break;
                    }
                }
            }
            if (possiblebuyonegetone) {
                System.out.println("\nPenawaran : Beli satu gratis satu untuk minuman berlaku!");
            }
            return totalcost;
        }

    @Override
    public ReceiptDetail totalReceipt(String[] orderItem, int[] quantity, double totalCost) {
        double subtotal = 0;
        List<String> details = new ArrayList<>();
        for (int i = 0; i < orderItem.length; i++) {
            if (orderItem[i] == null) break;
            for (Menu menu : menuRepository.getMenulist()) {
                if (menu.getName().equals(orderItem[i])) {
                    double itemcost = menu.getPrice() * quantity[i];
                    subtotal += itemcost;
                    System.out.println(menu.getName() + " x " + quantity[i] + " Rp." + itemcost);
                    break;
                }
            }
        }
        final double tax = totalCost * 0.10;
        final double serviceCharge = 20000;
        double finalCharge = totalCost + tax + serviceCharge;
        double discount = 0;

        if (totalCost > 100000) {
            discount = finalCharge * 0.1;
            finalCharge -= discount;
        }
        return new ReceiptDetail(details, tax, serviceCharge, discount, finalCharge);
    }
}
