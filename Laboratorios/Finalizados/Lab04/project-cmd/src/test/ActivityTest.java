package test;
import domain.*;


import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class ActivityTest{
   
    
    public ActivityTest(){
    }


    @Before
    public void setUp(){    
    }

    @After
    public void tearDown(){
    }
    
 
    @Test
    public void shouldCalculateTheTimeOfAComposedSecuencialActivity(){
        Composed c = new Composed("IS-BASICA", 100 , false );
        c.add(new Simple("AYED", 10, 10));
        c.add(new Simple("MBDA", 10, 20));
        c.add(new Simple("POOB", 10, 15));//Se cambia a 15
        try {
           assertEquals(45,c.time());
        } catch (ProjectException e){
            fail("Threw a exception");
        }    
    }    
    
    
     @Test
    public void shouldCalculateTheTimeOfAComposedSecuencialActivitylevel3(){
        Composed c1 = new Composed("IS-BEGINING", 100 , false );
        c1.add(new Simple("MMIN", 10, 20));
        c1.add(new Simple("AYPR", 10, 10));
        c1.add(new Simple("INSI", 10, 20));
        Composed c2 = new Composed("IS-LOGICA", 100 , false );
        c2.add(new Simple("LCAT", 10, 5));
        c2.add(new Simple("MATD", 10, 15));
        c2.add(new Simple("TPRO", 10, 15));
        c2.add(c1);
        Composed c = new Composed("IS-BASICA", 100 , false );
        c.add(new Simple("AYED", 10, 10));
        c.add(new Simple("MBDA", 10, 20));
        c.add(new Simple("POOB", 10, 15));//Se cambia a 15
        c.add(c2);
        try {
           assertEquals(130,c.time());
        } catch (ProjectException e){
            fail("Threw a exception");
        }    
    }    
    
    @Test
    public void shouldCalculateTheTimeOfAComposedParallelActivity(){
        Composed c = new Composed("IS-BASICA", 100 , true );
        c.add(new Simple("AYED", 10, 10));
        c.add(new Simple("MBDA", 10, 20));
        c.add(new Simple("POOB", 10, 15));
        try {
           assertEquals(20,c.time());
        } catch (ProjectException e){
            fail("Threw a exception");
        }    
    }  
    
    @Test
    public void shouldCalculateTheTimeOfAComposedParallelActivitylevel3(){
        Composed c1 = new Composed("IS-BEGINING", 100 , true );
        c1.add(new Simple("MMIN", 10, 20));
        c1.add(new Simple("AYPR", 10, 10));
        c1.add(new Simple("INSI", 10, 24));
        Composed c2 = new Composed("IS-LOGICA", 100 , true );
        c2.add(new Simple("LCAT", 10, 5));
        c2.add(new Simple("MATD", 10, 15));
        c2.add(new Simple("TPRO", 10, 15));
        c2.add(c1);
        Composed c = new Composed("IS-BASICA", 100 , true );
        c.add(new Simple("AYED", 10, 10));
        c.add(new Simple("MBDA", 10, 20));
        c.add(new Simple("POOB", 10, 15));//Se cambia a 15
        c.add(c2);
        try {
           assertEquals(24,c.time());
        } catch (ProjectException e){
            fail("Threw a exception");
        }    
    }   
    
    @Test
    public void shouldCalculateTheTimeOfAComposedMixActivitylevel3(){
        Composed c1 = new Composed("IS-BEGINING", 100 , true );
        c1.add(new Simple("MMIN", 10, 20));
        c1.add(new Simple("AYPR", 10, 10));
        c1.add(new Simple("INSI", 10, 24));
        Composed c2 = new Composed("IS-LOGICA", 100 , true );
        c2.add(new Simple("LCAT", 10, 5));
        c2.add(new Simple("MATD", 10, 15));
        c2.add(new Simple("TPRO", 10, 15));
        c2.add(c1);
        Composed c = new Composed("IS-BASICA", 100 , false );
        c.add(new Simple("AYED", 10, 10));
        c.add(new Simple("MBDA", 10, 20));
        c.add(new Simple("POOB", 10, 15));//Se cambia a 15
        c.add(c2);
        try {
           assertEquals(69,c.time());
        } catch (ProjectException e){
            fail("Threw a exception");
        }    
    }
    
    @Test
    public void shouldThrowExceptionIfComposedIsEmpty(){
        Composed c = new Composed("IS-BASICA", 100 , true);
        try { 
           int time=c.time();
           fail("Did not throw exception");
        } catch (ProjectException e) {
            assertEquals(ProjectException.COMPOSED_EMPTY,e.getMessage());
        }    
    }    
    
    
   @Test
    public void shouldThrowExceptionIfThereIsErrorInTime(){
        Composed c = new Composed("IS-BASICA", 100 , false );
        c.add(new Simple("AYED", 10, 10));
        c.add(new Simple("MBDA", 10, -20));
        c.add(new Simple("POOB", 10, 30));
        try { 
           int time=c.time();
           fail("Did not throw exception");
        } catch (ProjectException e) {
            assertEquals(ProjectException.TIME_ERROR,e.getMessage());
        }    
    }
    
    @Test
    public void shouldThrowExceptionIfThereIsErrorInTimeLevel3(){
        Composed c1 = new Composed("IS-BEGINING", 100 , true );
        c1.add(new Simple("MMIN", 10, -20));
        c1.add(new Simple("AYPR", 10, 10));
        c1.add(new Simple("INSI", 10, 24));
        Composed c2 = new Composed("IS-LOGICA", 100 , true );
        c2.add(new Simple("LCAT", 10, 5));
        c2.add(new Simple("MATD", 10, 15));
        c2.add(new Simple("TPRO", 10, 15));
        c2.add(c1);
        Composed c = new Composed("IS-BASICA", 100 , true );
        c.add(new Simple("AYED", 10, 10));
        c.add(new Simple("MBDA", 10, 20));
        c.add(new Simple("POOB", 10, 15));//Se cambia a 15
        c.add(c2);
        try { 
           int time=c.time();
           fail("Did not throw exception");
        } catch (ProjectException e) {
            assertEquals(ProjectException.TIME_ERROR,e.getMessage());
        }        
    }
    
    @Test
    public void shouldThrowExceptionIfThereIsErrorInTime1(){
        Composed c = new Composed("IS-BASICA", 100 , false );
        c.add(new Simple("AYED", 10, 10));
        c.add(new Simple("MBDA", 10, 20));
        c.add(new Simple("POOB", 10, 30));
        try { 
           int time=c.time();
           fail("Did not throw exception");
        } catch (ProjectException e) {
            assertEquals(ProjectException.TIME_ERROR,e.getMessage());
        }    
    }     
    
    @Test
    public void shouldThrowExceptionIfThereIsErrorInTimeLevel3_1(){
        Composed c1 = new Composed("IS-BEGINING", 100 , true );
        c1.add(new Simple("MMIN", 10, 50));
        c1.add(new Simple("AYPR", 10, 10));
        c1.add(new Simple("INSI", 10, 24));
        Composed c2 = new Composed("IS-LOGICA", 100 , true );
        c2.add(new Simple("LCAT", 10, 5));
        c2.add(new Simple("MATD", 10, 15));
        c2.add(new Simple("TPRO", 10, 15));
        c2.add(c1);
        Composed c = new Composed("IS-BASICA", 100 , true );
        c.add(new Simple("AYED", 10, 10));
        c.add(new Simple("MBDA", 10, 20));
        c.add(new Simple("POOB", 10, 15));//Se cambia a 15
        c.add(c2);
        try { 
           int time=c.time();
           fail("Did not throw exception");
        } catch (ProjectException e) {
            assertEquals(ProjectException.TIME_ERROR,e.getMessage());
        }        
    }
    
   @Test
    public void shouldThrowExceptionIfTimeIsNotKnown(){
        Composed c = new Composed("IS-BASICA", 100 , true );
        c.add(new Simple("AYED", 10, 10));
        c.add(new Simple("MBDA", 10, null));
        c.add(new Simple("POOB", 10, 30));
        try { 
           int time=c.time();
           fail("Did not throw exception");
        } catch (ProjectException e) {
            assertEquals(ProjectException.TIME_EMPTY,e.getMessage());
        }    
    }
    
    @Test
    public void shouldThrowExceptionIfTimeIsNotKnownLevel3(){
        Composed c1 = new Composed("IS-BEGINING", 100 , true );
        c1.add(new Simple("MMIN", 10, null));
        c1.add(new Simple("AYPR", 10, 10));
        c1.add(new Simple("INSI", 10, 24));
        Composed c2 = new Composed("IS-LOGICA", 100 , true );
        c2.add(new Simple("LCAT", 10, 5));
        c2.add(new Simple("MATD", 10, 15));
        c2.add(new Simple("TPRO", 10, 15));
        c2.add(c1);
        Composed c = new Composed("IS-BASICA", 100 , true );
        c.add(new Simple("AYED", 10, 10));
        c.add(new Simple("MBDA", 10, 20));
        c.add(new Simple("POOB", 10, 15));//Se cambia a 15
        c.add(c2);
        try { 
           int time=c.time();
           fail("Did not throw exception");
        } catch (ProjectException e) {
            assertEquals(ProjectException.TIME_EMPTY,e.getMessage());
        }        
    }
    
    @Test
    public void shouldAttendTimeEmptyExceptionWithDefaultValue(){
        Composed c1 = new Composed("IS-BEGINING", 100 , true );
        c1.add(new Simple("MMIN", 10, null));
        c1.add(new Simple("AYPR", 10, 10));
        c1.add(new Simple("INSI", 10, 24));
        Composed c2 = new Composed("IS-LOGICA", 100 , true );
        c2.add(new Simple("LCAT", 10, 5));
        c2.add(new Simple("MATD", 10, 15));
        c2.add(new Simple("TPRO", 10, 15));
        c2.add(c1);
        Composed c = new Composed("IS-BASICA", 100 , true );
        c.add(new Simple("AYED", 10, 10));
        c.add(new Simple("MBDA", 10, 20));
        c.add(new Simple("POOB", 10, 15));//Se cambia a 15
        c.add(c2);
        
        assertEquals(30,c.time(60,40,30));  
    }
    
    @Test
    public void shouldAttendTimeErrorExceptionWithDefaultValue(){
        Composed c1 = new Composed("IS-BEGINING", 100 , true );
        c1.add(new Simple("MMIN", 10, 21));
        c1.add(new Simple("AYPR", 10, -5));
        c1.add(new Simple("INSI", 10, 24));
        Composed c2 = new Composed("IS-LOGICA", 100 , true );
        c2.add(new Simple("LCAT", 10, 5));
        c2.add(new Simple("MATD", 10, 15));
        c2.add(new Simple("TPRO", 10, 15));
        c2.add(c1);
        Composed c = new Composed("IS-BASICA", 100 , true );
        c.add(new Simple("AYED", 10, 10));
        c.add(new Simple("MBDA", 10, 20));
        c.add(new Simple("POOB", 10, 15));//Se cambia a 15
        c.add(c2);
        
        assertEquals(40,c.time(60,40,30));
    }
    
    @Test
    public void shouldAttendComposedEmptyExceptionWithDefaultValue(){
        Composed c = new Composed("IS-BASICA", 100 , true);
        assertEquals(60,c.time(60,40,30));  
    }

    @Test
    public void shouldCalculateTheTimeOfSecuencialActivityWithModalityA(){
        Composed c = new Composed("IS-BASICA", 100 , false);
        c.add(new Simple("AYED", 10, null));
        c.add(new Simple("MBDA", 10, 20));
        c.add(new Simple("POOB", 10, 15));
        try {
           assertEquals(52,c.time('A'));
        } catch (ProjectException e){
            fail("Threw a exception");
        }
    }

    @Test
    public void shouldCalculateTheTimeOfSecuencialActivityWithModalityALevel3(){
        Composed c1 = new Composed("IS-BEGINING", 100 , false );
        c1.add(new Simple("MMIN", 10, 20));
        c1.add(new Simple("AYPR", 10, 10));
        c1.add(new Simple("INSI", 10, 80));
        Composed c2 = new Composed("IS-LOGICA", 100 , false );
        c2.add(new Simple("LCAT", 10, 5));
        c2.add(new Simple("MATD", 10, 15));
        c2.add(new Simple("TPRO", 10, 15));
        c2.add(c1);
        Composed c = new Composed("IS-BASICA", 100 , false );
        c.add(new Simple("AYED", 10, 10));
        c.add(new Simple("MBDA", 10, 20));
        c.add(new Simple("POOB", 10, 15));//Se cambia a 15
        c.add(c2);
        try {
           assertEquals(60,c.time('A'));
        } catch (ProjectException e){
            fail("Threw a exception");
        }
    }

    @Test
    public void shouldCalculateTheTimeOfSecuencialActivityWithModalityA1(){
        Composed c = new Composed("IS-BASICA", 100 , false);
        c.add(new Simple("AYED", 10, null));
        c.add(new Simple("MBDA", 10, 30));
        c.add(new Simple("POOB", 10, 15));
        try {
            assertEquals(45,c.time('A'));
        } catch (ProjectException e){
            fail("Threw a exception");
        }
    }

    @Test
    public void shouldEstimateTheTimeOfSecuencialSubActivityThatThrowsExceptionWithModalityA(){
        Composed c = new Composed("IS-BASICA", 100 , false);
        Activity c1 = new Simple("CVDS", 10, null);
        Activity c2 = new Simple("AYED", 10, 80);
        c.add(c2);
        c.add(new Simple("MBDA", 10, 20));
        c.add(new Simple("POOB", 10, 15));
        c.add(c1);
        try {
            int time = c.time('A');
            assertEquals(69,time);
            assertEquals(17,c1.getTime());
            assertEquals(17,c2.getTime());
        } catch (ProjectException e){
            fail("Threw a exception");
        }
    }

    @Test
    public void shouldEstimateTheTimeOfSecuencialSubActivityThatThrowsExceptionWithModalityM(){
        Composed c = new Composed("IS-BASICA", 100 , false);
        Activity c1 = new Simple("CVDS", 10, null);
        Activity c2 = new Simple("AYED", 10, 80);
        c.add(c2);
        c.add(new Simple("MBDA", 10, 20));
        c.add(new Simple("POOB", 10, 15));
        c.add(c1);
        try {
            int time = c.time('M');
            assertEquals(75,time);
            assertEquals(20,c1.getTime());
            assertEquals(20,c2.getTime());
        } catch (ProjectException e){
            fail("Threw a exception");
        }
    }

    @Test
    public void shouldCalculateTheTimeOfParallelActivityWithModalityA(){
        Composed c = new Composed("IS-BASICA", 100 , true);
        c.add(new Simple("AYED", 10, 10));
        c.add(new Simple("MBDA", 10, -1));
        c.add(new Simple("POOB", 10, 15));
        try {
            assertEquals(15,c.time('A'));
        } catch (ProjectException e){
            fail("Threw a exception");
        }
    }

    @Test
    public void shouldCalculateTheTimeOfParallelActivityWithModalityALevel3(){
        Composed c1 = new Composed("IS-BEGINING", 100 , true );
        c1.add(new Simple("MMIN", 10, 20));
        c1.add(new Simple("AYPR", 10, null));
        c1.add(new Simple("INSI", 10, 24));
        Composed c2 = new Composed("IS-LOGICA", 100 , true );
        c2.add(new Simple("LCAT", 10, 5));
        c2.add(new Simple("MATD", 10, 15));
        c2.add(new Simple("TPRO", 10, 15));
        c2.add(c1);
        Composed c = new Composed("IS-BASICA", 100 , true );
        c.add(new Simple("AYED", 10, 10));
        c.add(new Simple("MBDA", 10, 20));
        c.add(new Simple("POOB", 10, 15));//Se cambia a 15
        c.add(c2);
        try {
            assertEquals(20,c.time('A'));
        } catch (ProjectException e){
            fail("Threw a exception");
        }
    }

    @Test
    public void shouldCalculateTheTimeOfParallelActivityWithModalityA1(){
        Composed c = new Composed("IS-BASICA", 100 , true);
        c.add(new Simple("AYED", 10, null));
        c.add(new Simple("MBDA", 10, -1));
        c.add(new Simple("POOB", 10, 15));
        try {
            assertEquals(15,c.time('A'));
        } catch (ProjectException e){
            fail("Threw a exception");
        }
    }

    @Test
    public void shouldEstimateTheTimeOfParallelSubActivityThatThrowsExceptionWithModalityA(){
        Composed c = new Composed("IS-BASICA", 100 , true);
        Activity c1 = new Simple("CVDS", 10, null);
        Activity c2 = new Simple("AYED", 10, 80);
        c.add(c2);
        c.add(new Simple("MBDA", 10, 20));
        c.add(new Simple("POOB", 10, 15));
        c.add(c1);
        try {
            int time = c.time('A');
            assertEquals(20,time);
            assertEquals(17,c1.getTime());
            assertEquals(17,c2.getTime());
        } catch (ProjectException e){
            fail("Threw a exception");
        }
    }

    @Test
    public void shouldEstimateTheTimeOfParallelSubActivityThatThrowsExceptionWithModalityM(){
        Composed c = new Composed("IS-BASICA", 100 , true);
        Activity c1 = new Simple("CVDS", 10, null);
        Activity c2 = new Simple("AYED", 10, 80);
        c.add(c2);
        c.add(new Simple("MBDA", 10, 20));
        c.add(new Simple("POOB", 10, 15));
        c.add(c1);
        try {
            int time = c.time('M');
            assertEquals(20,time);
            assertEquals(20,c1.getTime());
            assertEquals(20,c2.getTime());
        } catch (ProjectException e){
            fail("Threw a exception");
        }
    }

    @Test
    public void shouldThrowExceptionIfAModalityTimeIsImposibleToEstimate(){
        Composed c = new Composed("IS-BASICA", 100 , true );
        c.add(new Simple("AYED", 10, 40));
        c.add(new Simple("MBDA", 10, null));
        c.add(new Simple("POOB", 10, 30));
        try {
            int time=c.time('M');
            fail("Did not throw exception");
        } catch (ProjectException e) {
            assertEquals(ProjectException.IMPOSIBLE,e.getMessage());
        }
    }

    @Test
    public void shouldThrowExceptionIfAModalityTimeIsImposibleToEstimate1(){
        Composed c = new Composed("IS-BASICA", 100 , true );
        c.add(new Simple("AYED", 10, 10));
        c.add(new Simple("MBDA", 10, null));
        c.add(new Simple("POOB", 10, 30));
        try {
            int time=c.time('C');
            fail("Did not throw exception");
        } catch (ProjectException e) {
            assertEquals(ProjectException.IMPOSIBLE,e.getMessage());
        }
    }

    @Test
    public void shouldShearchForAnActivity(){
        Composed c = new Composed("IS-BASICA", 100 , true );
        c.add(new Simple("AYED", 10, 10));
        c.add(new Simple("MBDA", 10, 20));
        c.add(new Simple("POOB", 10, 15));
        assertEquals("AYED",c.search("AYED").name());
    }

    @Test
    public void shouldShearchForAnActivityLevel3(){
        Composed c1 = new Composed("IS-BEGINING", 100 , true );
        c1.add(new Simple("MMIN", 10, 20));
        c1.add(new Simple("AYPR", 10, 10));
        c1.add(new Simple("INSI", 10, 24));
        Composed c2 = new Composed("IS-LOGICA", 100 , true );
        c2.add(new Simple("LCAT", 10, 5));
        c2.add(new Simple("MATD", 10, 15));
        c2.add(new Simple("TPRO", 10, 15));
        c2.add(c1);
        Composed c = new Composed("IS-BASICA", 100 , true );
        c.add(new Simple("AYED", 10, 10));
        c.add(new Simple("MBDA", 10, 20));
        c.add(new Simple("POOB", 10, 15));//Se cambia a 15
        c.add(c2);
        assertEquals("MMIN",c.search("MMIN").name());
    }

    @Test
    public void shouldShearchForAnActivityLevel3_1(){
        Composed c1 = new Composed("IS-BEGINING", 100 , true );
        c1.add(new Simple("MMIN", 10, 20));
        c1.add(new Simple("AYPR", 10, 10));
        c1.add(new Simple("INSI", 10, 24));
        Composed c2 = new Composed("IS-LOGICA", 100 , true );
        c2.add(new Simple("LCAT", 10, 5));
        c2.add(new Simple("MATD", 10, 15));
        c2.add(new Simple("TPRO", 10, 15));
        c2.add(c1);
        Composed c = new Composed("IS-BASICA", 100 , true );
        c.add(new Simple("AYED", 10, 10));
        c.add(new Simple("MBDA", 10, 20));
        c.add(new Simple("POOB", 10, 15));//Se cambia a 15
        c.add(c2);
        assertEquals("IS-BEGINING",c.search("IS-BEGINING").name());
    }

    @Test
    public void shouldShearchForAnActivityAndReturnNullIfDoesntExistsLevel3(){
        Composed c1 = new Composed("IS-BEGINING", 100 , true );
        c1.add(new Simple("MMIN", 10, 20));
        c1.add(new Simple("AYPR", 10, 10));
        c1.add(new Simple("INSI", 10, 24));
        Composed c2 = new Composed("IS-LOGICA", 100 , true );
        c2.add(new Simple("LCAT", 10, 5));
        c2.add(new Simple("MATD", 10, 15));
        c2.add(new Simple("TPRO", 10, 15));
        c2.add(c1);
        Composed c = new Composed("IS-BASICA", 100 , true );
        c.add(new Simple("AYED", 10, 10));
        c.add(new Simple("MBDA", 10, 20));
        c.add(new Simple("POOB", 10, 15));//Se cambia a 15
        c.add(c2);
        assertNull(c.search("PEPE"));
    }

    @Test
    public void shouldCalculateTheTimeOfASubActivity(){
        Composed c = new Composed("IS-BASICA", 100 , true );
        c.add(new Simple("AYED", 10, 10));
        c.add(new Simple("MBDA", 10, 20));
        c.add(new Simple("POOB", 10, 15));
        try {
            assertEquals(20,c.time("MBDA"));
        } catch (ProjectException e){
            fail("Threw a exception");
        }
    }

    @Test
    public void shouldCalculateTheTimeOfASubActivityLevel3(){
        Composed c1 = new Composed("IS-BEGINING", 100 , true );
        c1.add(new Simple("MMIN", 10, 20));
        c1.add(new Simple("AYPR", 10, 10));
        c1.add(new Simple("INSI", 10, 24));
        Composed c2 = new Composed("IS-LOGICA", 100 , true );
        c2.add(new Simple("LCAT", 10, 5));
        c2.add(new Simple("MATD", 10, 15));
        c2.add(new Simple("TPRO", 10, 15));
        c2.add(c1);
        Composed c = new Composed("IS-BASICA", 100 , true );
        c.add(new Simple("AYED", 10, 10));
        c.add(new Simple("MBDA", 10, 20));
        c.add(new Simple("POOB", 10, 15));//Se cambia a 15
        c.add(c2);
        try {
            assertEquals(24,c.time("IS-BEGINING"));
        } catch (ProjectException e){
            fail("Threw a exception");
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void shouldCalculateTheTimeOfASubActivityLevel3_1(){
        Composed c1 = new Composed("IS-BEGINING", 100 , false );
        c1.add(new Simple("MMIN", 10, 20));
        c1.add(new Simple("AYPR", 10, 10));
        c1.add(new Simple("INSI", 10, 24));
        Composed c2 = new Composed("IS-LOGICA", 100 , true );
        c2.add(new Simple("LCAT", 10, 5));
        c2.add(new Simple("MATD", 10, 15));
        c2.add(new Simple("TPRO", 10, 15));
        c2.add(c1);
        Composed c = new Composed("IS-BASICA", 100 , true );
        c.add(new Simple("AYED", 10, 10));
        c.add(new Simple("MBDA", 10, 20));
        c.add(new Simple("POOB", 10, 15));//Se cambia a 15
        c.add(c2);
        try {
            assertEquals(54,c.time("IS-BEGINING"));
        } catch (ProjectException e){
            fail("Threw a exception");
        }
    }

    @Test
    public void shouldThrowExceptionIfDoesntExistsASubActivityToCalculateTheTime(){
        Composed c1 = new Composed("IS-BEGINING", 100 , false );
        c1.add(new Simple("MMIN", 10, 20));
        c1.add(new Simple("AYPR", 10, 10));
        c1.add(new Simple("INSI", 10, 24));
        Composed c2 = new Composed("IS-LOGICA", 100 , true );
        c2.add(new Simple("LCAT", 10, 5));
        c2.add(new Simple("MATD", 10, 15));
        c2.add(new Simple("TPRO", 10, 15));
        c2.add(c1);
        Composed c = new Composed("IS-BASICA", 100 , true );
        c.add(new Simple("AYED", 10, 10));
        c.add(new Simple("MBDA", 10, 20));
        c.add(new Simple("POOB", 10, 15));//Se cambia a 15
        c.add(c2);
        try {
            int time=c.time("Guillermo");
            fail("Did not throw exception");
        } catch (ProjectException e) {
            assertEquals(ProjectException.UNKNOWN,e.getMessage());
        }
    }

    @Test
    public void shouldThrowExceptionIfIsImposibleCalculateTheTimeOfASubAcivity(){
        Composed c1 = new Composed("IS-BEGINING", 100 , false );
        c1.add(new Simple("MMIN", 10, 20));
        c1.add(new Simple("AYPR", 10, 40));
        c1.add(new Simple("INSI", 10, 24));
        Composed c2 = new Composed("IS-LOGICA", 100 , true );
        c2.add(new Simple("LCAT", 10, 5));
        c2.add(new Simple("MATD", 10, 15));
        c2.add(new Simple("TPRO", 10, 15));
        c2.add(c1);
        Composed c = new Composed("IS-BASICA", 100 , true );
        c.add(new Simple("AYED", 10, 10));
        c.add(new Simple("MBDA", 10, 20));
        c.add(new Simple("POOB", 10, 15));//Se cambia a 15
        c.add(c2);
        try {
            int time=c.time("AYPR");
            fail("Did not throw exception");
        } catch (ProjectException e) {
            assertEquals(ProjectException.IMPOSIBLE,e.getMessage());
        }
    }

    @Test
    public void shouldThrowExceptionIfIsImposibleCalculateTheTimeOfASubAcivity_1(){
        Composed c1 = new Composed("IS-BEGINING", 100 , false );
        c1.add(new Simple("MMIN", 10, 20));
        c1.add(new Simple("AYPR", 10, null));
        c1.add(new Simple("INSI", 10, 24));
        Composed c2 = new Composed("IS-LOGICA", 100 , true );
        c2.add(new Simple("LCAT", 10, 5));
        c2.add(new Simple("MATD", 10, 15));
        c2.add(new Simple("TPRO", 10, 15));
        c2.add(c1);
        Composed c = new Composed("IS-BASICA", 100 , true );
        c.add(new Simple("AYED", 10, 10));
        c.add(new Simple("MBDA", 10, 20));
        c.add(new Simple("POOB", 10, 15));//Se cambia a 15
        c.add(c2);
        try {
            int time=c.time("IS-BEGINING");
            fail("Did not throw exception");
        } catch (ProjectException e) {
            assertEquals(ProjectException.IMPOSIBLE,e.getMessage());
        }
    }

    @Test
    public void shouldThrowExceptionIfIsImposibleCalculateTheTimeOfASubAcivity_2(){
        Composed c1 = new Composed("IS-BEGINING", 100 , false );
        c1.add(new Simple("MMIN", 10, 20));
        c1.add(new Simple("AYPR", 10, 10));
        c1.add(new Simple("INSI", 10, 24));
        c1.add(new Composed("IS-BEGINING1", 100 , false ));
        Composed c2 = new Composed("IS-LOGICA", 100 , true );
        c2.add(new Simple("LCAT", 10, 5));
        c2.add(new Simple("MATD", 10, 15));
        c2.add(new Simple("TPRO", 10, 15));
        c2.add(c1);
        Composed c = new Composed("IS-BASICA", 100 , true );
        c.add(new Simple("AYED", 10, 10));
        c.add(new Simple("MBDA", 10, 20));
        c.add(new Simple("POOB", 10, 15));//Se cambia a 15
        c.add(c2);
        try {
            int time=c.time("IS-BEGINING1");
            fail("Did not throw exception");
        } catch (ProjectException e) {
            assertEquals(ProjectException.IMPOSIBLE,e.getMessage());
        }
    }
}