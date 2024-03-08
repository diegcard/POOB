import java.time.LocalDate;

public class Voucher {

    private LocalDate date;
    private int value;
    private boolean spend;
    
    public Voucher(int spend){
        this.date = LocalDate.now();
        this.value = spend;
        this.spend = false;
    } 
    
    public int getValue(){
        return value;
    }
    
    public boolean getSpend(){
        return spend;
    }
    
    public LocalDate getDate(){
        return date;
    }

}
