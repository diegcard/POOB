import java.util.HashMap;
import javax.swing.JOptionPane;

/**
 * Data Matrix Calculator
 * @author Cardenas y Cardona
 */
    
public class DataMatrixCalculator{
    
    private HashMap<String, DataMatrix> variables;
    private boolean isOk;
    
    
    /**
     * Constructor of a DataMatrixCalculator object
     */
    public DataMatrixCalculator(){
        this.variables = new HashMap<String,DataMatrix>();
        this.isOk = true;
    }


    /**
     * Assign a matrix to a variable
     * @param name is a String Variable's name
     * @param values is a String matrix is the Variable
     */
    public void assign(String name, String values[][] ){
        DataMatrix value = new DataMatrix(values);
        variables.put(name,value);
        this.isOk = true;
    }

    public boolean areEqual(String var1, String var2) {
        if (!variables.containsKey(var1) || !variables.containsKey(var2)) {
            this.isOk = false;
            return false;
        }
        DataMatrix matrix1 = variables.get(var1);
        DataMatrix matrix2 = variables.get(var2);
        boolean result = matrix1.equals(matrix2);
        this.isOk = result;
        return result;
    }

    public void multiplyByScalar(String resultVar, String var, String scalar) {
        if (!variables.containsKey(var)) {
            this.isOk = false;
            return;
        }
        DataMatrix matrix = variables.get(var);
        DataMatrix result = matrix.multiplyByScalar(scalar);
        variables.put(resultVar, result);
        this.isOk = true;
    }

    /**
     * Consult the variables of a calculator
     * @return variables is a String Arrays with all variable's name
     */
    public String[] variables(){
        String[] allVariables = new String[variables.size()];
        int i = 0;
        for(String variable: variables.keySet()){
            allVariables[i] = variable;
            i++;
        }
        return allVariables;
    }

    //Creame el metodo assignBinary
       
    /**
     * Assigns the value of an operation to a variable (unary operations)
     *  a := unary b
     *  The unary operators are: s (hape), t (ranspose)
     *  
     *  @param a is a name variable of the result 
     *  @param unary is a char whith the operation that the method are going to do
     *  @param b is the variable that will chance with the operation
     */
    // shape returns a (1x2) matrix with the shape (rows, columns)
    // In transpose b is a transpose matrix
    //If this is not possible, it returns the (1x1) matrix with a false value
    public void assign(String a, char unary, String b){
        try{
            DataMatrix value = variables.get(b);
            DataMatrix result;
            switch (unary){
                case 's':
                    result = value.shape();
                    variables.put(a,result);
                    break;
                case 't':
                    result = value.reshape(value.getRow(),value.getColumn());
                    variables.put(a,result);
                    break;
                default:
                    throw new Exception("Operador desconocido");
            }
            this.isOk = true;
        } catch(Exception e){
            String[][] defecto= {{"FALSE"}};
            DataMatrix result1 = new DataMatrix(defecto);
            variables.put(a,result1);
            this.isOk = false;
        }
    }    
    
    /**
     * Assigns the value of an operation to a variable (simple binary operations)
     *  a := b simple c
     *  The simple operators are: +, -
     *  
     *  @param a is a name variable of the result 
     *  @param unary is a char whith the operation that the method are going to do
     *  @param b is the first operation variable
     *  @param c is the second operation variable
     */
     //If this is not possible, it returns the (1x1) matrix with a false value
    public void assign(String a, String b, char sBinary, String c){
        try{
            DataMatrix value1 = variables.get(b); DataMatrix value2 = variables.get(c); DataMatrix result;
            if(value1.getRow() != value2.getRow() || value1.getColumn() != value2.getColumn()){throw new Exception("Tamaños distintos");
                }else{
                    switch (sBinary){
                    case '+':
                        result = value1.add(value2);
                        variables.put(a,result);
                        break;
                    case '-':
                        result = value1.sub(value2);
                        variables.put(a,result);
                        break;
                    default: throw new Exception("Operador desconocido");
                    }
                }
            this.isOk = true;
        }catch(Exception e){
            String[][] defecto= {{"FALSE"}};
            DataMatrix result1 = new DataMatrix(defecto);
            variables.put(a,result1);
            this.isOk = false;
        }
    }   
    

    /**
     * Returns the string represention of a matrix. Columns must be aligned.
     * @param variable is the variable's number of the matrix to pass to string
     */
    public String toString(String variable){
        if(!variables.containsKey(variable)){
            JOptionPane.showMessageDialog(null, "La variable no existe");
            this.isOk = false;
            return null;
        }else{
            DataMatrix result = variables.get(variable);
            this.isOk = true;
            return result.toString();
        }
    }
    
    /**
     * If the last operation was successful
     */
    public boolean ok(){
        return isOk;
    }
}
    



