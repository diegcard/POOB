import java.util.ArrayList;
import java.time.LocalDate;

public class Store {

    private String name;
    private String address;
    private String atentionSchedule;
    private ArrayList<Employee> employees;
    private ArrayList<Supplier> suppliers;
    private ArrayList<Bill> bills;
    private ArrayList<Product> products;
    private Location location;
    
    public Location getLocation(){
        return location;
    }

    public void generateVouchers(String brand, LocalDate initialD, LocalDate finalID, int amount, int total){
        for(Bill b: bills){
            b.generateVouchers(brand, initialD, finalID, amount, total);
        }
    }
}
