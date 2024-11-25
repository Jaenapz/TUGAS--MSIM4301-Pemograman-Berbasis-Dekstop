import Entity.Menu;

import java.util.Scanner;

public class test {

    private static Menu[] menuList = new Menu[8];

    public static void main(String[] args) {
        initializeMenu();
        displayMenu();
        processOrder();
    }

    private static void initializeMenu() {
        menuList[0] = new Menu("Nasi Padang", 25000, "Makanan");
        menuList[1] = new Menu("Ayam Penyet", 30000, "Makanan");
        menuList[2] = new Menu("Sate Ayam", 20000, "Makanan");
        menuList[3] = new Menu("Gado-Gado", 15000, "Makanan");
        menuList[4] = new Menu("Es Teh", 10000, "Minuman");
        menuList[5] = new Menu("Kopi", 15000, "Minuman");
        menuList[6] = new Menu("Jus Jeruk", 20000, "Minuman");
        menuList[7] = new Menu("Air Mineral", 5000, "Minuman");
    }

    private static void displayMenu() {
        System.out.println("Menu Restoran:");
        System.out.println("Makanan:");
        for (int i = 0; i < 4; i++) {
            System.out.println(menuList[i].getName() + " - Rp " + menuList[i].getPrice());
        }
        System.out.println("Minuman:");
        for (int i = 4; i < 8; i++) {
            System.out.println(menuList[i].getName() + " - Rp " + menuList[i].getPrice());
        }
    }

    private static void processOrder() {
        Scanner scanner = new Scanner(System.in);
        String[] pesanan = new String[4];
        int[] jumlah = new int[4];
        double totalBiaya = 0;

        for (int i = 0; i < 4; i++) {
            System.out.print("Masukkan pesanan (format: Nama Menu = Jumlah) atau ketik 'selesai' untuk mengakhiri: ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("selesai")) {
                break;
            }
            String[] parts = input.split("=");
            String namaMenu = parts[0].trim();
            int qty = Integer.parseInt(parts[1].trim());
            pesanan[i] = namaMenu;
            jumlah[i] = qty;

            for (Menu menu : menuList) {
                if (menu.getName().equalsIgnoreCase(namaMenu)) {
                    totalBiaya += menu.getPrice() * qty;
                }
            }
        }

        double pajak = totalBiaya * 0.1;
        double biayaPelayanan = 20000;
        double totalAkhir = totalBiaya + pajak + biayaPelayanan;

        // Diskon
        if (totalBiaya > 100000) {
            totalAkhir *= 0.9; // Diskon 10%
        }

        // Penawaran beli satu gratis satu untuk minuman
        boolean adaMinumanGratis = false;
        if (totalBiaya > 50000) {
            for (int i = 4; i < 8; i++) {
                if (pesanan[i - 4] != null && menuList[i].getCategory().equals("Minuman")) {
                    adaMinumanGratis = true;
                    break;
                }
            }
        }

        printReceipt(pesanan, jumlah, totalBiaya, pajak, biayaPelayanan, totalAkhir, adaMinumanGratis);
    }

    private static void printReceipt(String[] pesanan, int[] jumlah, double totalBiaya, double pajak, double biayaPelayanan, double totalAkhir, boolean adaMinumanGratis) {
        System.out.println("\n--- Struk Pesanan ---");
        for (int i = 0; i < pesanan.length; i++) {
            if (pesanan[i] != null) {
                for (Menu menu : menuList) {
                    if (menu.getName().equalsIgnoreCase(pesanan[i])) {
                        double totalHargaItem = menu.getPrice() * jumlah[i];
                        System.out.printf("%s - Jumlah: %d - Harga per item: Rp %.2f - Total: Rp %.2f\n",
                                menu.getName(), jumlah[i], menu.getPrice(), totalHargaItem);

                    }
                }
            }
        }
    }
}

