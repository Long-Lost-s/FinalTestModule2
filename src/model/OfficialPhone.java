package model;

public class OfficialPhone extends Phone{
    private int warrantyPeriod; // Thời gian bảo hành (tháng)
    private String warrantyScope; // Trong nước hoặc quốc tế

    public OfficialPhone(String id, String name, double price, int quantity, String manufacturer, int warrantyPeriod, String warrantyScope) {
        super(id, name, price, quantity, manufacturer);
        this.warrantyPeriod = warrantyPeriod;
        this.warrantyScope = warrantyScope;
    }

    @Override
    public void displayInfo() {
        System.out.println("Official Phone:");
        System.out.println("ID: " + id + ", Name: " + name + ", Price: " + price + ", Quantity: " + quantity + ", Manufacturer: " + manufacturer);
        System.out.println("Warranty Period: " + warrantyPeriod + " months, Warranty Scope: " + warrantyScope);
    }

    public int getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setWarrantyPeriod(int warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }

    public String getWarrantyScope() {
        return warrantyScope;
    }

    public void setWarrantyScope(String warrantyScope) {
        this.warrantyScope = warrantyScope;
    }
}
