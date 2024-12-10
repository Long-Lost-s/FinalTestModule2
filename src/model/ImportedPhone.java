package model;

public class ImportedPhone extends Phone{
    private String importedCountry; // Quốc gia xách tay
    private String status; // Trạng thái (đã kích hoạt, chưa kích hoạt)

    public ImportedPhone(String id, String name, double price, int quantity, String manufacturer, String importedCountry, String status) {
        super(id, name, price, quantity, manufacturer);
        this.importedCountry = importedCountry;
        this.status = status;
    }

    @Override
    public void displayInfo() {
        System.out.println("Imported Phone:");
        System.out.println("ID: " + id + ", Name: " + name + ", Price: " + price + ", Quantity: " + quantity + ", Manufacturer: " + manufacturer);
        System.out.println("Imported Country: " + importedCountry + ", Status: " + status);
    }

    public String getImportedCountry() {
        return importedCountry;
    }

    public void setImportedCountry(String importedCountry) {
        this.importedCountry = importedCountry;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
