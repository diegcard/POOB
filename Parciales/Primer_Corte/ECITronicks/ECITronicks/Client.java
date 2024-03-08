import java.util.ArrayList;

public class Client {

    private Integer id;
    private String name;
    private String address;
    private String cellphone;
    private String email;
    private ArrayList<Voucher> vouchers;

    /**
     * Crea un Bono para el cliente
     * @param total: cantidad total a hacer un decuento
     */
    public void generateVoucher(int total){
        Voucher vouxh = new Voucher(total);
        vouchers.add(vouxh);
    }
}
