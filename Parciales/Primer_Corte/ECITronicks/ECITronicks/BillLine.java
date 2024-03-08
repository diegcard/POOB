public class BillLine {
    
    private int amount;
    private int price;
    private Product product;
    
    
    
    public boolean validatePurchase(String brandName, int amount){
        if(product.isBrand(brandName) && product.getUnitPrice() > amount){
            return true;
        }else{
            return false;
        }
    }
    
}
