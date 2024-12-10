package controller;

import model.ImportedPhone;
import model.OfficialPhone;
import model.Phone;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PhoneController {
    private List<Phone> phoneList = new ArrayList<>();

    // Thêm điện thoại mới
    public void addPhoneFromInput(Scanner scanner) {
        System.out.println("Chọn loại điện thoại:");
        System.out.println("1. Điện thoại chính hãng");
        System.out.println("2. Điện thoại xách tay");
        int type = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nhập ID: ");
        String id = scanner.nextLine();
        System.out.print("Nhập tên điện thoại: ");
        String name = scanner.nextLine();
        System.out.print("Nhập giá bán: ");
        double price = scanner.nextDouble();
        System.out.print("Nhập số lượng: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nhập nhà sản xuất: ");
        String manufacturer = scanner.nextLine();

        if (type == 1) {
            System.out.print("Nhập thời gian bảo hành (tháng): ");
            int warrantyPeriod = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Nhập phạm vi bảo hành (trong nước/quốc tế): ");
            String warrantyScope = scanner.nextLine();
            phoneList.add(new OfficialPhone(id, name, price, quantity, manufacturer, warrantyPeriod, warrantyScope));
        } else if (type == 2) {
            System.out.print("Nhập quốc gia xách tay: ");
            String importedCountry = scanner.nextLine();
            System.out.print("Nhập trạng thái (đã kích hoạt/chưa kích hoạt): ");
            String status = scanner.nextLine();
            phoneList.add(new ImportedPhone(id, name, price, quantity, manufacturer, importedCountry, status));
        } else {
            System.out.println("Loại điện thoại không hợp lệ!");
        }
        System.out.println("Đã thêm điện thoại thành công!");
    }

    // Xóa điện thoại
    public void deletePhoneById(Scanner scanner) {
        System.out.print("Nhập ID điện thoại cần xóa: ");
        String id = scanner.nextLine();
        Phone phone = phoneList.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (phone != null) {
            phoneList.remove(phone);
            System.out.println("Đã xóa điện thoại thành công!");
        } else {
            System.out.println("Không tìm thấy điện thoại với ID: " + id);
        }
    }

    // Hiển thị danh sách điện thoại
    public void displayAllPhones() {
        if (phoneList.isEmpty()) {
            System.out.println("Danh sách điện thoại rỗng.");
        } else {
            for (Phone phone : phoneList) {
                phone.displayInfo();
                System.out.println();
            }
        }
    }

    // Tìm kiếm điện thoại theo ID
    public void searchPhoneById(Scanner scanner) {
        System.out.print("Nhập ID điện thoại cần tìm: ");
        String id = scanner.nextLine();
        Phone phone = phoneList.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (phone != null) {
            phone.displayInfo();
        } else {
            System.out.println("Không tìm thấy điện thoại với ID: " + id);
        }
    }
}
