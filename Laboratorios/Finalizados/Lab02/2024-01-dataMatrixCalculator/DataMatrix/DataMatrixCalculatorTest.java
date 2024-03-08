

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 * The test class DataMatrixCalculatorTest.
 *
 * @author  Cardenas - Cardona
 */
public class DataMatrixCalculatorTest
{
    private DataMatrixCalculator calculadora = new DataMatrixCalculator();

    @BeforeEach
    public void before(){
        String[][] variable1 = {{"True","False"},{"3","c"}};
        calculadora.assign("variable1",variable1);
        String[][] variable2 = {{"3.21","sanche"},{"14.21","m"}};
        calculadora.assign("variable2",variable2);
        String[][] variable3 = {{"False","T","True"},{"k","l","7.14"}};
        calculadora.assign("variable3",variable3);
        String[][] variable4 = {{"W","true    "},{"N","False"}};
        calculadora.assign("variable4",variable4);
    }
    
    @Test
    public void shouldCreateVariable() {
        String[][] variable3 = {{"t","T","false "},{"14.20.1","g","704"}};
        calculadora.assign("variable3",variable3);
    
        // verificar el ultimo dato que se ingreso bien
        assertTrue(calculadora.ok());
        //devolver todas las variables
        String[] valorDevolver= {"variable1","variable3","variable2","variable4"};
        assertArrayEquals(calculadora.variables(),valorDevolver);
        //pasar a toString una variable
        assertEquals(calculadora.toString("variable1"),"TRUE FALSE \n3    c     \n");
    }

    @Test
    public void shouldNotReturnFalseToString() {

        assertNotEquals(calculadora.toString("variable1"),"TRUE FALSE \n3    c    \n");
        assertNotEquals(calculadora.toString("variable3"),"");
        assertNotEquals(calculadora.toString("variable3"),"falefals");
    }
    
    @Test
    public void shouldReturnShapeOfAVariable() {
        calculadora.assign("result",'s',"variable1");
        assertEquals(calculadora.toString("result"),"2 2 \n");
        calculadora.assign("result1",'s',"variable3");
        assertEquals(calculadora.toString("result1"),"2 3 \n");
    }
    
    @Test
    public void shouldNotReturnFalseShapeOfAVariable() {
        calculadora.assign("result",'s',"variable1");
        assertNotEquals(calculadora.toString("result"),"2 3 \n");
        calculadora.assign("result1",'s',"variable3");
        assertNotEquals(calculadora.toString("result1"),"3 3 \n");
    }
    
    

    @Test
    public void shouldReturnTransposeOfAVariable() {
        calculadora.assign("result",'t',"variable1");
        assertEquals(calculadora.toString("result"),"TRUE 3 \nFALSEc \n");
        calculadora.assign("result1",'t',"variable3");
        assertEquals(calculadora.toString("result1"),"FALSE k    \nT     l    \nTRUE  7.14 \n");
    }
    
    @Test
    public void shouldNotAcceptOthersOperators() {
        calculadora.assign("result",'j',"variable7");
        assertEquals(calculadora.toString("result"),"FALSE \n");
        calculadora.assign("result1",'j',"result");
        assertEquals(calculadora.toString("result1"),"FALSE \n");
        calculadora.assign("result1",'t',"jj");
        assertEquals(calculadora.toString("result1"),"FALSE \n");
        calculadora.assign("result","variable1",'k',"variable2");
        assertEquals(calculadora.toString("result"),"FALSE \n");
    }
    
    @Test
    public void shouldNotAcceptVariablesNonExistents() {
        calculadora.assign("result1",'t',"jj");
        assertEquals(calculadora.toString("result1"),"FALSE \n");
        calculadora.assign("result","variable1",'+',"pepito");
        assertEquals(calculadora.toString("result"),"FALSE \n");
    }
 
    @Test
    public void shouldReturnAdditionOfTwoVariable() {
        calculadora.assign("result","variable1",'+',"variable2");
        assertEquals(calculadora.toString("result"),"TRUE FALSE \n17.21m     \n");
        calculadora.assign("result1","variable1",'+',"variable4");
        assertEquals(calculadora.toString("result1"),"W    TRUE \n81.0 c    \n");
    }
    
    @Test
    public void shouldNotAcceptTwoVariablesWithDiferentSize() {
        calculadora.assign("result","variable1",'+',"variable3");
        assertEquals(calculadora.toString("result"),"FALSE \n");
        calculadora.assign("result1","variable1",'-',"variable3");
        assertEquals(calculadora.toString("result1"),"FALSE \n");
    }
    
    @Test
    public void shouldReturnSubstraccionOfTwoVariable() {
        calculadora.assign("result","variable1",'-',"variable2");
        assertEquals(calculadora.toString("result"),"TRUE   FALSE \n-11.21 c     \n");
        calculadora.assign("result1","variable1",'-',"variable4");
        assertEquals(calculadora.toString("result1"),"T    FALSE \n75.0 F     \n");
    }
    
    @Test
    public void testMultiplyByScalar() {
        String[][] matrix = {{"1", "2"}, {"3", "4"}};
        calculadora.assign("matrix", matrix);
        calculadora.multiplyByScalar("result", "matrix", "2");
        assertEquals("2 4 \n6 8 \n", calculadora.toString("result"));
        calculadora.multiplyByScalar("resultNonExistent", "nonExistent", "2");
        assertEquals("FALSE \n", calculadora.toString("resultNonExistent"));
    }
    
    @Test
    public void testAreEqual() {
        String[][] matrix1 = {{"1", "2"}, {"3", "4"}};
        String[][] matrix2 = {{"1", "2"}, {"3", "4"}};
        String[][] matrix3 = {{"5", "6"}, {"7", "8"}};
        calculadora.assign("matrix1", matrix1);
        calculadora.assign("matrix2", matrix2);
        calculadora.assign("matrix3", matrix3);
        assertTrue(calculadora.areEqual("matrix1", "matrix2"));
        assertFalse(calculadora.areEqual("matrix1", "matrix3"));
        assertFalse(calculadora.areEqual("matrix1", "nonExistent"));
    }
}
