/**
 * DataMatrix class defines operations between matrices such as addition, subtraction, or transpose.
 * However, the data within the matrices are not typical; they are instances of the Data class with special behavior.
 * 
 * @author Cardenas y Cardona
 */
import javax.swing.JOptionPane;
public class DataMatrix{

    private Data [][] data;
    private int row;
    private int colum;
    
    /**
     * Creates a matrix with the specified size and values.
     * 
     * @param datas The data array representing the matrix.
     */
    public DataMatrix(String [][] datas) {
        this.row = datas.length;
        this.colum = datas[0].length;
        this.data = new Data[row][colum];
        for(int i=0; i < row; i++){
            for(int j=0; j< colum; j++){
                data[i][j] = new Data(datas[i][j]);
            }
        }
        
    }

     /**
     * Gets the data matrix.
     * 
     * @return The data matrix.
     */
    public Data[][] getDataMatrix(){
        return data;
    }
    
    /**
     * Bono:
     * Creamos una nueva funcion que sea transponer la matrix dada
     * 
     * @return DataMatrix Transpuesta
     */
    public DataMatrix transpose() {
        DataMatrix transposedMatrix = new DataMatrix(new String[colum][row]);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < colum; j++) {
                transposedMatrix.getDataMatrix()[j][i] = data[i][j];
            }
        }
        return transposedMatrix;
    }

    /**
     * Bono:
     * Multiplica la matriz por un escalar.
     * 
     * @param scalar el escalar por el cual se multiplicará la matriz
     * @return una nueva matriz resultante de la multiplicación por el escalar
     */
    public DataMatrix multiplyByScalar(String scalar){
        String[][] newMatrix = new String[row][colum];
        for(int i=0;i<row;i++){
            for(int j=0;j<colum;j++){
                Data variable = data[i][j];
                if(variable.type() == 'n'){
                    newMatrix[i][j] = (Double.parseDouble(variable.toString()) * Double.parseDouble(scalar))+"";
                }
            }
        }
        return new DataMatrix(newMatrix);
    }

    /**
     * Gets the number of rows in the matrix.
     * 
     * @return The number of rows.
     */
    public int getRow() {
        return row;
    }

    /**
     * Gets the number of columns in the matrix.
     * 
     * @return The number of columns.
     */
    public int getColumn() {
        return colum;
    }
    
    /**
     * Returns a matrix with the dimensions of the current matrix.
     * 
     * @return A DataMatrix representing the shape of the current matrix.
     */
    public DataMatrix shape(){
        String[][] tamaño = {{String.valueOf(row),String.valueOf(colum)}};
        DataMatrix longi = new DataMatrix(tamaño);
        return longi;
    }
    
    /**
     * Returns a string representation of the matrix.
     * 
     * @param row The number of rows.
     * @param column The number of columns.
     * @return A string representation of the matrix.
     */
    public String toString(int row, int column){
        int[] anchosColumna = obtenerAnchosColumna(data);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                sb.append(String.format("%-" + anchosColumna[j] + "s", data[i][j].toString()));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Returns the width of each column in the matrix.
     *
     * @param matriz The matrix.
     * @return An array with the width of each column.
     */
    private static int[] obtenerAnchosColumna(Data[][] matriz) {
        int[] anchosColumna = new int[matriz[0].length];
        for (Data[] fila : matriz) {
            for (int i = 0; i < fila.length; i++) {
                if (fila[i].toString().length() > anchosColumna[i]) {
                    anchosColumna[i] = fila[i].toString().length()+1;
                }
            }
        }
        return anchosColumna;
    }
    
    
    /**
     * Reshapes the matrix to the specified dimensions.
     * 
     * @param row The new number of rows.
     * @param column The new number of columns.
     * @return A reshaped DataMatrix.
     */
    public DataMatrix reshape(int row, int column){
        String[][] newMatrix = new String[column][row];
        for(int i=0;i<row;i++){
            for(int j=0;j<column;j++){
                newMatrix[j][i] = data[i][j].toString();
            }
        }
        return new DataMatrix(newMatrix);
    }
    
    /**
     * Adds another matrix to the current matrix.
     * 
     * @param t The matrix to add.
     * @return The result of the addition.
     */
    public DataMatrix add(DataMatrix t){
        Data[][] nuevo = t.getDataMatrix();
        String[][] newMatrix = new String[row][colum];
        for(int i=0;i<row;i++){
            for(int j=0;j<colum;j++){
                newMatrix[i][j] = (data[i][j].add(nuevo[i][j])).toString();
            }
        }
        DataMatrix result = new DataMatrix(newMatrix);
        return result;
    }
        
    /**
     * Subs another matrix to the current matrix.
     * 
     * @param t The matrix to substract.
     * @return The result of the substraction.
     */
    public DataMatrix sub(DataMatrix t){
        Data[][] nuevo = t.getDataMatrix();
        String[][] newMatrix = new String[row][colum];
        for(int i=0;i<row;i++){
            for(int j=0;j<colum;j++){
                newMatrix[i][j] = (data[i][j].sub(nuevo[i][j])).toString();
            }
        }
        DataMatrix result = new DataMatrix(newMatrix);
        return result;
    }
    
    /**
     * Checks if another matrix is equal to the current matrix.
     * 
     * @param other The matrix to compare.
     * @return True if the matrices are equal, false otherwise.
     */
    public boolean equals(DataMatrix other) {
        Data[][] nuevo = other.getDataMatrix();
        boolean result = true;
        if(row != other.getRow() || colum != other.getColumn()){
            JOptionPane.showMessageDialog(null, "Tamaños incorrectos");
            result = false;
        }else{
            for(int i=0;i<row;i++){
                for(int j=0;j<colum;j++){
                    if(!data[i][j].equals(nuevo[i][j])){
                        result = false;
                        break;
                    }
                }
            } 
        }
        return result;
    }
    
    
    /**
     * Overrides the equals method to compare with another object.
     * 
     * @param other The object to compare.
     * @return True if the object is a DataMatrix and is equal to the current matrix, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
            return equals((DataMatrix)other);
    }
    
    
    /**
     * Overrides the toString method to return a string representation of the matrix.
     * 
     * @return A string representation of the matrix.
     */
    @Override
    public String toString () {
          return toString(row,colum);
    }   
 
}
