import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Unit test of Data class
 * @author Cardenas, Cardona
 */
public class DataTest {

    /*
    @BeforeClass
    public void beforeClass(){
        
    }
    
    @Before
    public void before(){
        
    }
    */
    @Test
    public void shouldCreateData() {
        assertEquals(new Data("TRUE").string(),"TRUE");
        assertEquals(new Data("    45.8").string(),"45.8"); 
        assertEquals(new Data("    a    ").string(),"a");  
        assertEquals(new Data("      ").string(),"");  
        
        assertEquals(new Data(" DATA ").string(),"DATA");
        assertEquals(new Data(" 34.45.67 ").string(),"34.45.67");
        
        assertNotEquals(new Data("   TRUE   ").string(),"true");
        assertNotEquals(new Data("    45.8").string(),"5.8");
        assertNotEquals(new Data("    45.8").string(),"45");
        assertNotEquals(new Data("U   ").string(),"u");

    }

    @Test
    public void shouldRepresentADataAsAString() {
        assertEquals(new Data(" true ").toString(),"TRUE");
        assertEquals(new Data("    45.8  ").toString(),"45.8"); 
        assertEquals(new Data("    a    ").toString(),"a");  
        
        assertEquals(new Data(" DATA ").toString(),"FALSE");
        assertEquals(new Data(" 34.45.67 ").toString(),"FALSE");
        
        assertNotEquals(new Data("    45.8").toString(),"TRUE");
        assertNotEquals(new Data("TRUE").toString(),"a");
    }   
    

    @Test
    public void shouldKnowWhenTwoBooleansAreEquals () {
        assertTrue (new Data("TRUE").equals(new Data("TRUE" )));
        assertTrue (new Data("FALSE").equals(new Data(" False" )));

        assertFalse (new Data("   TRUE").equals(new Data(" false" )));
        assertFalse (new Data("FALSE").equals(new Data("true" )));
    }
    
    @Test
    public void shouldKnowWhenTwoNumbersAreEquals () {
        assertTrue (new Data("34.5").equals(new Data("34.5" )));
        assertTrue (new Data("34.00").equals(new Data("34" )));

        assertFalse (new Data("34.1").equals(new Data("34" )));
        assertFalse (new Data("34.1").equals(new Data("0.1" )));
    }
    
    @Test
    public void shouldKnowWhenTwoCharactersAreEquals () {
        assertTrue (new Data("x").equals(new Data("x" )));
        assertTrue (new Data(" ,").equals(new Data(" , " )));

        assertFalse (new Data(".").equals(new Data("," )));
        assertFalse (new Data("c").equals(new Data(" f  " )));
    }
}
