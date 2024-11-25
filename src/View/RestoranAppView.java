package View;

import Entity.OrderInput;
import Entity.ReceiptDetail;
import Service.OrderService;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class RestoranAppView {

    private Scanner scanner = new Scanner(System.in);

    private OrderService orderService;

    public RestoranAppView(OrderService orderService) {
        this.orderService = orderService;
    }

    public void chooseService(){
        while (true){
            System.out.println("=====Pilih Layanan Di Bawah ini :");
            System.out.println("1. Sebagai Pelanggan - Pilih Menu");
            System.out.println("2. Sebagai Manajemen Restoran");
            System.out.println("0. Selesai");
            System.out.print("Pilih Menu : ");
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        orderService.displayMenu();
                        OrderInput orderInput = getOrderInput();
                        double totalCost = orderService.calculateTotalOrder(orderInput.getOrderitem(), orderInput.getQuantity());
                        ReceiptDetail receiptDetail = orderService.totalReceipt(orderInput.getOrderitem(), orderInput.getQuantity(), totalCost);
                        printTotalReceipt(receiptDetail);
                        break;
                    case 2:
                        manageMenu();
                        break;
                    case 0:
                        System.out.println("Terima Kasih,Sampai Jumpa!");
                        return;
                    default:
                        System.out.println("Pilihan tidak valid,silahkan masukan coba lagi");
                }
            }catch (InputMismatchException e){
                System.out.println("Input tidak valid,Harap masukan angka!");
                scanner.nextLine();
            }
        }
    }

    public void manageMenu() {
        while (true) {
            System.out.println("=====Menu Pengelolaan Restoran======");
            System.out.println("1. Tambah Menu");
            System.out.println("2. Updata Harga Menu");
            System.out.println("3. Hapus Menu");
            System.out.println("4. Kembali ke menu utama");
            System.out.print("Pilih opsi : ");
            int input = scanner.nextInt();
            scanner.nextLine();
                switch (input) {
                    case 1:
                        orderService.addNewMenu();
                        break;
                    case 2:
                        orderService.updateMenuPrice();
                        break;
                    case 3:
                        orderService.removeMenu();
                        break;
                    case 4:
                        return;
                    default:
                        System.out.println("Pilihan tidak valid coba masukan lagi!");
                }

            }
        }

    public OrderInput getOrderInput() {
        System.out.println("Masukkan pesanan Anda (contoh: Nasi Goreng = 2) dan tekan 'Enter' untuk mengakhiri :");

        ArrayList<String> orderItemList = new ArrayList<>();
        ArrayList<Integer> quantityList = new ArrayList<>();

        int i = 1;
        while (true){
            System.out.print("Pesanan " + i + " :" );
            String input = scanner.nextLine();

            if (input.isEmpty()) break;

            String[] parts = input.split("=");
            if (parts.length == 2){
                try {
                    String orderItem = parts[0].trim();
                    int quantity = Integer.parseInt(parts[1].trim());
                    orderItemList.add(orderItem);
                    quantityList.add(quantity);
                    i++;
                }catch (NumberFormatException e){
                    System.out.println("Format Kuantitas salah.Harap Masukan Angka!");
                }

            }else {
                System.out.println("Format Tidak Valid,Masukan Format yang benar");
            }
        }
        String[] orderItem = orderItemList.toArray(new String[0]);
        int[] quantity = quantityList.stream().mapToInt(quantities -> quantities).toArray();
        return new OrderInput(orderItem,quantity);

    }
        public void printTotalReceipt(ReceiptDetail receiptDetail) {
        System.out.println("=============STRUK PESANAN==============");
        for (String detail : receiptDetail.getOrderItem()) {
            System.out.println(detail);
        }
        System.out.println("Pajak (10%): Rp " + receiptDetail.getTax());
        System.out.println("Biaya Layanan: Rp " + receiptDetail.getServiceCharge());
        if (receiptDetail.getDiscount() > 0) {
            System.out.println("Diskon (10%): Rp " + receiptDetail.getDiscount());
        }
        System.out.println("Total Pembayaran: Rp " + receiptDetail.getFinalCharge());
        System.out.println("=====================");
    }

}
