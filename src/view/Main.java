package view;

import controller.PhoneController;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PhoneController phoneController = new PhoneController();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("--CHƯƠNG TRÌNH QUẢN LÝ ĐIỆN THOẠI--");
            System.out.println("1. Thêm mới");
            System.out.println("2. Xóa");
            System.out.println("3. Xem danh sách điện thoại");
            System.out.println("4. Tìm kiếm");
            System.out.println("5. Thoát");
            System.out.print("Chọn chức năng: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    phoneController.addPhoneFromInput(scanner);
                    break;
                case 2:
                    phoneController.deletePhoneById(scanner);
                    break;
                case 3:
                    phoneController.displayAllPhones();
                    break;
                case 4:
                    phoneController.searchPhoneById(scanner);
                    break;
                case 5:
                    System.out.println("Cảm ơn bạn đã sử dụng chương trình!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Chức năng không hợp lệ!");
            }
        }
    }
}
