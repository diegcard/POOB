import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ECITronicks {
    private HashMap<String, ArrayList<Store>> stores;
    
    
    
    public ECITronicks() {
        this.stores = new HashMap<>();
    }
    
    /**
     * 
     * Generate discount vouchers for all customers who have purchased products from a specific 
     * brand in a specific date range with a minimum amount.
     * 
     * @param: brand Name of the brand to whose products a discount voucher will be applied.
     * @param: initialD: Initial purchase verification date for products of the specific brand.
     * @param: finalD: Final purchase verification date for products of the specific brand.
     * @param: amount: Minimum purchase value to grant the voucher.
     * @param: total: Amount discount from Voucher.
     * 
     */
    public void generateVouchers(String brand, LocalDate initialD, LocalDate finalD, int amount, int total){
        for(ArrayList<Store> storeList : stores.values()){
            for(Store a: storeList){
                a.generateVouchers(brand, initialD, finalD, amount, total);
            }
        }
    }
}
