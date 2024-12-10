package controller;

import model.ImportedPhone;
import model.OfficialPhone;
import model.Phone;
import utils.CVSUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PhoneController {
    private List<Phone> phoneList;

    public PhoneController() {
        this.phoneList = CVSUtils.readPhonesFromCSV(); // Load phones from CSV file on start
    }

    // Add phone from input
    public void addPhoneFromInput(Scanner scanner) {
        System.out.println("Chọn loại điện thoại:");
        System.out.println("1. Điện thoại chính hãng");
        System.out.println("2. Điện thoại xách tay");
        int type = scanner.nextInt();
        scanner.nextLine();

        String id = phoneList.isEmpty()
                ? "1"
                : String.valueOf(Integer.parseInt(phoneList.get(phoneList.size() - 1).getId()) + 1);

        System.out.println("ID tự động gán cho điện thoại mới: " + id);

        System.out.print("Nhập tên điện thoại: ");
        String name = scanner.nextLine();
        if (name.trim().isEmpty()) {
            System.out.println("Tên điện thoại không được để trống!");
            return;
        }
        if (!name.matches("[A-Za-z0-9 ]+")) {
            System.out.println("Tên điện thoại không hợp lệ! (Không chứa ký tự đặc biệt)");
            return;
        }

        System.out.print("Nhập giá bán: ");
        double price = 0;
        try {
            price = scanner.nextDouble();
            if (price <= 0) {
                System.out.println("Giá bán phải là một số dương.");
                return;
            }
        } catch (Exception e) {
            System.out.println("Giá bán không hợp lệ.");
            scanner.nextLine();
            return;
        }

        System.out.print("Nhập số lượng: ");
        int quantity = 0;
        try {
            quantity = scanner.nextInt();
            if (quantity <= 0) {
                System.out.println("Số lượng phải là một số nguyên dương.");
                return;
            }
        } catch (Exception e) {
            System.out.println("Số lượng không hợp lệ.");
            scanner.nextLine(); // Clear invalid input
            return;
        }
        scanner.nextLine(); // Clear buffer

        System.out.print("Nhập nhà sản xuất: ");
        String manufacturer = scanner.nextLine();
        if (manufacturer.trim().isEmpty()) {
            System.out.println("Nhà sản xuất không được để trống!");
            return;
        }

        // Kiểm tra loại điện thoại
        String status = null;
        if (type == 1) {
            System.out.print("Nhập thời gian bảo hành (tháng): ");
            int warrantyPeriod = 0;
            try {
                warrantyPeriod = scanner.nextInt();
                if (warrantyPeriod <= 0 && warrantyPeriod > 730) {
                    System.out.println("Thời gian bảo hành tối đa là 2 năm.");
                    return;
                }
            } catch (Exception e) {
                System.out.println("Thời gian bảo hành không hợp lệ.");
                scanner.nextLine(); // Clear invalid input
                return;
            }
            scanner.nextLine(); // Clear buffer

            System.out.print("Nhập phạm vi bảo hành (trong nước/quốc tế): ");
            String warrantyScope = scanner.nextLine();
            if (!status.equals("quốc tế") && !status.equals("trong nước")) {
                System.out.println("Vui long nhập lại phạm vi bảo hành");
                return;
            }

            // Thêm điện thoại chính hãng vào danh sách
            phoneList.add(new OfficialPhone(id, name, price, quantity, manufacturer, warrantyPeriod, warrantyScope));
        } else if (type == 2) {
            System.out.print("Nhập quốc gia xách tay: ");
            String importedCountry = scanner.nextLine();
            if (importedCountry.trim().isEmpty()) {
                System.out.println("Quốc gia xách tay không được để trống!");
                return;
            }

            System.out.print("Nhập trạng thái (đã sửa chữa/chưa sửa chữa): ");
            status = scanner.nextLine();
            if (!status.equals("đã sửa chữa") && !status.equals("chưa sửa chữa")) {
                System.out.println("Trạng thái không hợp lệ. Vui lòng nhập 'đã sửa chữa' hoặc 'chưa sửa chữa'.");
                return;
            }

            // Thêm điện thoại xách tay vào danh sách
            phoneList.add(new ImportedPhone(id, name, price, quantity, manufacturer, importedCountry, status));
        } else {
            System.out.println("Loại điện thoại không hợp lệ!");
            return;
        }

        // Save updated phone list to CSV
        CVSUtils.writePhonesToCSV(phoneList);
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
            // Save updated phone list to CSV
            CVSUtils.writePhonesToCSV(phoneList);
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
