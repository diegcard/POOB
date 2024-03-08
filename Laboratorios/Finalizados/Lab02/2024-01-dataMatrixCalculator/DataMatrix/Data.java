
/**
 * Represents a simple data: boolean ('TRUE', 'FALSE'), numerical (integer or real), character (any character, if it is a digit it is considered numeric)
 *
 * @author Cardenas y Cardona
 */
public class Data{
    private String value;
    private char dataType;
    private String origin;

    /**
     * Constructs a new Data given its value, If it is not possible, it is assumed FALSE
     */
    public Data(String s){
        this.origin = s.trim();
        this.value = origin;
        setType();
    }

    
    /**
     * calculate data type 
     */
    private void setType(){
        try{
            Double.parseDouble(value);
            this.dataType = 'n';
        }catch(NumberFormatException e){
            if(value.equalsIgnoreCase("true") || value.equalsIgnoreCase("false")){
                value = value.toUpperCase();
                this.dataType = 'b';
            }else if(value.length()==1){
                this.dataType = 'c';
            }else{
                value = "FALSE";
                this.dataType = 'b';
            }
        }
    }
    
    //Add the specified data whit this data if this data is a boolean
    private Data addBool(Data b){
        String result;
        switch(b.type()){
            case 'b':
                result = String.valueOf(Boolean.parseBoolean(this.value) || Boolean.parseBoolean(b.toString()));
                break;
            case 'c':
                if(this.value.equalsIgnoreCase("TRUE")){
                    result = b.toString().charAt(0) > 'T' ? b.toString() : "T";
                }else{
                    result = b.toString().charAt(0) > 'F' ? b.toString() : "F";
                }
                break;
            default:
                if(Double.parseDouble(b.toString()) == 0){
                    result = String.valueOf(Boolean.parseBoolean(this.value) || false);
                }else{
                    result = "TRUE";
                }
        }
        Data newData = new Data(result);
        return newData;
    }
    
    //Add the specified data whit this data if this data is a char
    private Data addChar(Data b){
        String result;
        switch(b.type()){
            case 'b':
                if(b.toString().equalsIgnoreCase("TRUE")){
                    result = this.value.charAt(0) > 'T' ? this.value : "T";
                }else{
                    result = this.value.charAt(0) > 'F' ? this.value : "F";
                }
                break;
            case 'c':
                result = this.value.charAt(0) > b.toString().charAt(0) ? this.value : b.toString();
                break;
            default:
                result = String.valueOf((double)this.value.charAt(0) + Double.parseDouble(b.toString()));
        }
        Data newData = new Data(result);
        return newData;
    }
    
    //Add the specified data whit this data if this data is a number
    private Data addNum(Data b){
        String result;
        switch(b.type()){
            case 'b':
                if(Double.parseDouble(this.value) == 0){
                    result = String.valueOf(Boolean.parseBoolean(b.toString()) || false);
                }else{
                    result = "TRUE";
                }
                break;
            case 'c':
                result = String.valueOf((double)b.toString().charAt(0) + Double.parseDouble(this.value));
                break;
            default:
                result = String.valueOf(Double.parseDouble(this.value) + Double.parseDouble(b.toString()));
        }
        Data newData = new Data(result);
        return newData;
    }
    
     /**
     * Add the specified data with this data
     */   
    // boolean + boolean. + is OR
    // numerical + numerical. + is +
    // character + character. + is the largest character
    // boolean + numerical. Transform the number to boolean. If 0, true. False otherwise.
    // boolean + character. Transform the boolean to character. TRUE is T and FALSE is F.
    // + is commutative
    public Data add(Data b){
        if(this.dataType == 'b' ){
            return addBool(b);
        }else if(this.dataType == 'c'){
            return addChar(b);
        }else{
            return addNum(b);
        }
    }
    
    //Substract the specified data whit this data if this data is a boolean
    private Data subBool(Data b){
        String result;
        switch(b.type()){
            case 'b':
                result = String.valueOf(Boolean.parseBoolean(this.value) && Boolean.parseBoolean(b.toString()));
                break;
            case 'c':
                if(this.value.equalsIgnoreCase("TRUE")){
                    result = b.toString().charAt(0) < 'T' ? b.toString() : "T";
                }else{
                    result = b.toString().charAt(0) < 'F' ? b.toString() : "F";
                }
                break;
            default:
                if(Double.parseDouble(b.toString()) == 0){
                    result = "FALSE";
                }else{
                    result = String.valueOf(Boolean.parseBoolean(this.value) && true);
                }
        }
        Data newData = new Data(result);
        return newData;
    }
    
    //substract the specified data whit this data if this data is a char
    private Data subChar(Data b){
        String result;
        switch(b.type()){
            case 'b':
                if(b.toString().equalsIgnoreCase("TRUE")){
                    result = this.value.charAt(0) < 'T' ? this.value : "T";
                }else{
                    result = this.value.charAt(0) < 'F' ? this.value : "F";
                }
                break;
            case 'c':
                result = this.value.charAt(0) < b.toString().charAt(0) ? this.value : b.toString();
                break;
            default:
                result = String.valueOf((double)this.value.charAt(0) - Double.parseDouble(b.toString()));
        }
        Data newData = new Data(result);
        return newData;
    }

    /**
     * Resta los datos especificados de estos datos si estos datos son un número.
     *
     * @param b Los datos a restar de estos datos.
     * @return Un nuevo objeto Data que representa el resultado de la resta.
     *
     * El método funciona de la siguiente manera:
     * - Si el tipo de los datos especificados es booleano ('b'), verifica si el valor de estos datos es 0.
     *   Si es así, establece el resultado en "FALSE". De lo contrario, convierte los datos especificados a booleano y realiza una operación lógica AND con true.
     * - Si el tipo de los datos especificados es carácter ('c'), resta el valor ASCII del carácter del valor de estos datos.
     * - Si el tipo de los datos especificados no es ni booleano ni carácter, resta el valor de los datos especificados del valor de estos datos.
     */
    private Data subNum(Data b){
        String result;
        switch(b.type()){
            case 'b':
                if(Double.parseDouble(this.value) == 0){
                    result = "FALSE";
                }else{
                    result = String.valueOf(Boolean.parseBoolean(b.toString()) && true);
                }
                break;
            case 'c':
                result = String.valueOf((double)b.toString().charAt(0) - Double.parseDouble(this.value));
                break;
            default:
                result = String.valueOf(Double.parseDouble(this.value) - Double.parseDouble(b.toString()));
        }
        Data newData = new Data(result);
        return newData;
    }
    
     /**
     * Substract the specified data with this data
     */   
    // boolean - boolean. - is AND
    // numerical - numerical. + is -
    // character - character. - is the smaller character
    // boolean - numerical. Transform the number to boolean. If 0, true. False otherwise.
    // boolean - character. Transform the boolean to character. TRUE is T and FALSE is F.
    // + is commutative
    public Data sub(Data b){
        if(this.dataType == 'b' ){
            return subBool(b);
        }else if(this.dataType == 'c'){
            return subChar(b);
        }else{
            return subNum(b);
        }
    }
    
    /**
     * Returns the data type
     * @returns 'b', 'n', or 'c'
     */
    public char type(){
        return dataType;
    }
    
    @Override
    /**
     * Return the string representation of the data, not the original string
     */
    public String toString () {
          return value;
    }   
    
     /**
     * Return the string used to create the Data without leading or trailing blanks
     */   
    public String string() {
          return origin;
    }  
    
    /**
     * return if two Data objects are equals
     */
    public boolean equals(Data b){
        if(this.dataType == 'n' && b.type() == 'n'){
            return Double.parseDouble(this.value) == Double.parseDouble(b.toString());
        }else{
            return this.value.equalsIgnoreCase(b.toString());
        }
    }
}
