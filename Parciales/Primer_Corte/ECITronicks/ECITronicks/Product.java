public class Product {

    private String name;
    private String brand;
    private String description;
    private int unitPrice;
    private int discount;
    
    public String getBrand(){
        return brand;
    }
    
    public int getUnitPrice(){
        return unitPrice;
    }
    
    public boolean isBrand(String brandName){
        if(brandName.equals(brand)){
            return true;
        }else{
            return false;
        }
        
    }
}
