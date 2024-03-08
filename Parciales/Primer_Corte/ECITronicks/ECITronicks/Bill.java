import java.time.LocalDate;
import java.util.ArrayList;

public class Bill {

    private LocalDate date;
    private int total;
    private ArrayList<Payment> payments;
    private ArrayList<BillLine> lines;
    private Store store;
    private Client client;
    private Employee employee;
    private Voucher voucher;
    
    public Voucher getVoucher(){
        return voucher;
    }

    public void generateVouchers(String brand, LocalDate initialD, LocalDate finalID, int amount, int total){
        ArrayList<BillLine> linesValidate = new ArrayList<BillLine>();
        if((date.isEqual(initialD) || date.isAfter(initialD)) && (date.isEqual(finalID) || date.isBefore(finalID))){
            for(BillLine bl : lines){
                if (bl.validatePurchase(brand, amount)){
                    linesValidate.add(bl);
                }
            }
        }
        if(linesValidate.size() > 0){
            client.generateVoucher(total);
        }
    }
}
